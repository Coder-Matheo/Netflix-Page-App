package rob.netflix2app;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import rob.netflix2app.RoomDatabase.BioObj;
import rob.netflix2app.RoomDatabase.DatabaseViewModel;
import rob.netflix2app.RoomDatabase.MySingletonRoom_Bio_DB;
import rob.netflix2app.Screen.NavigationDrawerMainActivity;


public class LoginStep2Fragment extends Fragment {

    private EditText emailStep2EditText;
    private EditText passwordStep2EditText;
    private Button loginStep2Button;
    private TextView registerPageStep2TextView;
    DatabaseViewModel databaseViewModel;
    NavController navController;



    private static final String TAG = LoginStep2Fragment.class.getSimpleName();
    TextView  newAccountTextView;
    LinearLayout linearLayout3, linearLayout4;


    public LoginStep2Fragment() {
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
        View view = inflater.inflate(R.layout.fragment_login_step2, container, false);
        // Inflate the layout for this fragment

        Intent intentToScreenActivity = new Intent(getActivity(), NavigationDrawerMainActivity.class);
        //startActivity(intentToScreenActivity);

        initialization(view);
        emailCheckAutoCompleteTextView(this);
        return view;
    }




    private void initialization(View view) {
        emailStep2EditText = view.findViewById(R.id.emailStep2EditText);
        passwordStep2EditText = view.findViewById(R.id.passwordStep2EditText);
        loginStep2Button = view.findViewById(R.id.loginStep2Button);

        String usernameOrEmail = getArguments().getString("usernameOrEmail");
        emailStep2EditText.setText(usernameOrEmail);
        passwordStep2EditText.setTextColor(ContextCompat.getColor(getContext(), R.color.colorPrimaryDark));

    }

    private void emailCheckAutoCompleteTextView(LoginStep2Fragment loginFragment) {


        passwordStep2EditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                boolean boolEmailValidation = emailValidation( charSequence);

                if (boolEmailValidation){
                    loginStep2Button.setEnabled(true);
                    checkExistsUserInDatabase(emailStep2EditText.getText().toString().trim(),
                            passwordStep2EditText.getText().toString());
                }else {
                    loginStep2Button.setEnabled(false);
                }

                if (charSequence.length() == 0){
                    loginStep2Button.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.disable_button_twitter));
                }
                if (charSequence.length() >= 1){
                    loginStep2Button.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.enable_button_twitter));
                }

                loginStep2Button.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        checkExistsUserInDatabase();
                        Toast.makeText(getContext(), "Test of clicked", Toast.LENGTH_SHORT).show();
                        //check database, if username and password exists, then return the value.
                        //if return Value Password and Username, leap to ScreenActivity
                        LiveData<BioObj> userFind = MySingletonRoom_Bio_DB.getInstance(getContext())
                                .databaseBio_dao()
                                .findUserByNameOrPhoneNumberOrEmail(emailStep2EditText.getText().toString().trim());

                        userFind.observe(getViewLifecycleOwner(), new Observer<BioObj>() {
                            @Override
                            public void onChanged(BioObj bioObj) {
                                //verification, if user have a Account(Save in Database Bio), invoke the SnackBar
                                try {
                                    if (bioObj == null){
                                        customizeSnackBarFunction(view, "You have a account ...", "Register");
                                        checkExistsUserInDatabase();
                                    }else {
                                        customizeSnackBarFunction(view, "You have a Account", "Register");
                                    }
                                }catch (Exception e){
                                        e.printStackTrace();
                                }

                            }
                        });
                    }
                });
            }

            @Override
            public void afterTextChanged(Editable editable) { }
        });

    }


    public void checkExistsUserInDatabase(){
        List<BioObj> existsUserList = new ArrayList<>();

        LiveData<List<BioObj>> userList = MySingletonRoom_Bio_DB.getInstance(getContext())
                .databaseBio_dao()
                .getAllUsers();

        userList.observe(LoginStep2Fragment.this, new Observer<List<BioObj>>() {
            @Override
            public void onChanged(List<BioObj> bioObjs) {
                if (bioObjs != null){
                    for (int i = 0; i < bioObjs.size(); i++){
                        Log.i(TAG, "Account User: "+ bioObjs.get(i).getUserName());
                        Log.i(TAG, "onChanged: "+ bioObjs.get(i).getPassword());


                    }
                }
            }
        });



        LiveData<BioObj> userFind = MySingletonRoom_Bio_DB.getInstance(getContext())
                .databaseBio_dao()
                .findUserByNamePass(emailStep2EditText.getText().toString().trim(),
                        passwordStep2EditText.getText().toString().trim());

        userFind.observe(LoginStep2Fragment.this, new Observer<BioObj>() {
            @Override
            public void onChanged(BioObj bioObj) {
                if (bioObj != null){
                    Log.i(TAG, "onChanged11: "+ bioObj.getUserName());


                }
            }
        });

    }



    public void checkExistsUserInDatabase(String emailAddress,  String password){

        LiveData<List<BioObj>> userList = MySingletonRoom_Bio_DB.getInstance(getContext())
                .databaseBio_dao()
                .getAllUsers();

        userList.observe(LoginStep2Fragment.this, new Observer<List<BioObj>>() {
            @Override
            public void onChanged(List<BioObj> bioObjs) {
                if (bioObjs != null){
                    for (int i = 0; i < bioObjs.size(); i++){
                        Log.i(TAG, "onChanged: "+ bioObjs.get(i).getUserName());
                        Log.i(TAG, "onChanged: "+ bioObjs.get(i).getPassword());

                    }
                }
            }
        });



        LiveData<BioObj> userFind = MySingletonRoom_Bio_DB.getInstance(getContext())
                .databaseBio_dao()
                .findUserByNamePass(emailStep2EditText.getText().toString().trim(), passwordStep2EditText.getText().toString().trim());

        userFind.observe(LoginStep2Fragment.this, new Observer<BioObj>() {
            @Override
            public void onChanged(BioObj bioObj) {
                if (bioObj != null){
                    Log.i(TAG, "onChanged11: "+ bioObj.getUserName());
                }
            }
        });

    }

    //prove the email address is correct, then return true or false
    public boolean emailValidation(CharSequence email){
        if (email.length() != 0){
            String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(email);
            return matcher.matches();
        }
        return false;
    }

    //Move the LoginFragment to RegisterFragment
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);
        registerPageStep2TextView = view.findViewById(R.id.registerPageStep2TextView);
        registerPageStep2TextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_loginFragment_to_registerFragment);
            }
        });
    }
    private void customizeSnackBarFunction(View view, String snackMessage, String buttonMessage) {
        Snackbar snackbar = Snackbar.make(view, "", BaseTransientBottomBar.LENGTH_LONG);

        View custonSnackBar = getLayoutInflater().inflate(R.layout.customsnackbar, null);
        snackbar.getView().setBackgroundColor(Color.TRANSPARENT);
        Snackbar.SnackbarLayout snackbarLayout = (Snackbar.SnackbarLayout) snackbar.getView();
        TextView snackTextView = custonSnackBar.findViewById(R.id.snackTextView);
        snackTextView.setText(snackMessage);
        Button btnSnackBar = custonSnackBar.findViewById(R.id.snackButton);
        btnSnackBar.setText(buttonMessage);
        snackbarLayout.setPadding(0,0,0,0);

        snackbarLayout.addView(custonSnackBar, 0);
        snackbar.show();
        //check, if Account exists would invoke SnackBar and Action Have Button for jump to Registering Fragment
        btnSnackBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_loginFragment_to_registerFragment);
            }
        });

    }



}