package rob.netflix2app.Registering;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import rob.netflix2app.R;
import rob.netflix2app.RoomDatabase.BioObj;
import rob.netflix2app.RoomDatabase.DatabaseViewModel;
import rob.netflix2app.RoomDatabase.MySingleton_Bio_DB;


public class RegisterStep1Fragment extends Fragment {

    private static final String TAG = RegisterStep1Fragment.class.getSimpleName();
    private Button registerStep1Button;
    private TextView loginPageTextView;
    AutoCompleteTextView emailRegisterEditText;
    AutoCompleteTextView numberRegisterEditText;
    EditText passwordEditText;
    DatabaseViewModel databaseViewModel;
    NavController navController;



    public RegisterStep1Fragment() {
        // Required empty public constructor
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        databaseViewModel = ViewModelProviders.of(this).get(DatabaseViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register_step1, container, false);
        initialization(view);
        emailCheckAutoCompleteTextView(this);
        return view;
    }


    private void initialization(View view) {
        registerStep1Button = view.findViewById(R.id.registerStep1Button);
        loginPageTextView = view.findViewById(R.id.loginPageTextView);
        emailRegisterEditText = view.findViewById(R.id.emailRegisterEditText);
        numberRegisterEditText = view.findViewById(R.id.numberRegisterEditText);
        passwordEditText = view.findViewById(R.id.passwordEditText);

    }

    private void emailCheckAutoCompleteTextView(RegisterStep1Fragment registerStep1Fragment) {
        emailRegisterEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                boolean boolEmailValidation = emailValidation( charSequence);
                if (boolEmailValidation){
                    registerStep1Button.setEnabled(true);
                    Log.i(TAG, "onTextChanged: ");
                }else {
                    registerStep1Button.setEnabled(false);
                }

                if (charSequence.length() == 0){
                    registerStep1Button.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.disable_button_twitter));
                }
                if (charSequence.length() >= 1){
                    registerStep1Button.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.enable_button_twitter));
                }



                registerStep1Button.setOnClickListener(new View.OnClickListener() {
                    
                    @Override
                    public void onClick(View view) {
                        navController.navigate(R.id.action_registerFragment_to_customizeYourAccountRegistering);
                        LiveData<BioObj> userFind = MySingleton_Bio_DB.getInstance(getContext())
                                .databaseBio_dao()
                                .findUserByNamePass(emailRegisterEditText.getText().toString().trim(), passwordEditText.getText().toString().trim());

                        userFind.observe(getViewLifecycleOwner(), new Observer<BioObj>() {
                            @Override
                            public void onChanged(BioObj bioObj) {
                                if (bioObj != null){
                                    //check, if Account exists would invoke SnackBar and Action Have Button jump to Registering Fragment
                                    if (!bioObj.getUserName().isEmpty() && !bioObj.getPassword().isEmpty()){
                                        Snackbar snackbar = Snackbar.make(view, "", BaseTransientBottomBar.LENGTH_LONG);

                                        View custonSnackBar = getLayoutInflater().inflate(R.layout.customsnackbar, null);
                                        snackbar.getView().setBackgroundColor(Color.TRANSPARENT);
                                        Snackbar.SnackbarLayout snackbarLayout = (Snackbar.SnackbarLayout) snackbar.getView();
                                        Button btnSnackBar = custonSnackBar.findViewById(R.id.snackButton);
                                        snackbarLayout.setPadding(0,0,0,0);

                                        snackbarLayout.addView(custonSnackBar, 0);
                                        snackbar.show();

                                        btnSnackBar.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                navController.navigate(R.id.action_registerFragment_to_loginFragment);
                                            }
                                        });


                                    }else {
                                        if (!emailRegisterEditText.getText().toString().isEmpty() && !passwordEditText.getText().toString().isEmpty()){
                                            BioObj login_username_password = new BioObj(emailRegisterEditText.getText().toString().trim().toUpperCase(),
                                                    passwordEditText.getText().toString().trim());

                                            RegisterStep1Fragment.InsertAsyncTask insertAsyncTask = new RegisterStep1Fragment.InsertAsyncTask();
                                            insertAsyncTask.execute(login_username_password);

                                            Snackbar.make(view, "You Haven't a Account ", BaseTransientBottomBar.LENGTH_SHORT).show();
                                        }

                                    }
                                }
                            }
                        });
                    }
                });
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    public boolean emailValidation(CharSequence email){
        if (email.length() != 0){
            String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(email);
            return matcher.matches();
        }
        return false;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);

        navController.navigate(R.id.action_registerFragment_to_customizeYourAccountRegistering);

        loginPageTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_registerFragment_to_loginFragment);
            }
        });
    }

    class InsertAsyncTask extends AsyncTask<BioObj, Void, Void> {

        @Override
        protected Void doInBackground(BioObj... bioObjs) {
            MySingleton_Bio_DB.getInstance(getContext())
                    .databaseBio_dao()
                    .insertBio(bioObjs[0]);
            Log.i(TAG, "doInBackground: Created");

            return null;
        }
    }
}