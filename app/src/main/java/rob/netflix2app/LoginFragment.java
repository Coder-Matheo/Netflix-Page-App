package rob.netflix2app;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import rob.netflix2app.RoomDatabase.BioObj;
import rob.netflix2app.RoomDatabase.DatabaseViewModel;
import rob.netflix2app.RoomDatabase.MySingleton_Bio_DB;


public class LoginFragment extends Fragment {

    private AutoCompleteTextView emailEditText;
    private EditText passwordEditText;
    private ImageButton loginButton;
    private TextView registerTextView;
    DatabaseViewModel databaseViewModel;



    private static final String TAG = LoginFragment.class.getSimpleName();
    TextView  newAccountTextView;
    LinearLayout linearLayout3,linearLayout4;


    public LoginFragment() {
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
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        // Inflate the layout for this fragment


        initialization(view);
        emailCheckAutoCompleteTextView(this);
        return view;
    }




    private void initialization(View view) {
        emailEditText = view.findViewById(R.id.emailEditText);
        passwordEditText = view.findViewById(R.id.passwordEditText);
        loginButton = view.findViewById(R.id.login_btn);



    }

    private void emailCheckAutoCompleteTextView(LoginFragment loginFragment) {
        emailEditText.getText().toString();
        String[] COUNTRIES = new String[]{
                "Belgium", "France", "Italy", "Germany", "Spain"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(loginFragment.getContext(), android.R.layout.simple_dropdown_item_1line, COUNTRIES);
        emailEditText.setAdapter(adapter);
        emailEditText.setThreshold(1);

        emailEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Log.i(TAG, "onTextChanged: "+ charSequence);
                boolean boolEmailValidation = emailValidation( charSequence);
                if (boolEmailValidation){
                    loginButton.setEnabled(true);
                }else {
                    loginButton.setEnabled(false);
                }



                loginButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //HERE WORK
                        checkExistsUserInDatabase(emailEditText.getText().toString().trim(), passwordEditText.getText().toString().trim());

                    }
                });
            }

            @Override
            public void afterTextChanged(Editable editable) { }
        });

    }


    public void checkExistsUserInDatabase(String emailAddress, String password){
        List<BioObj> existsUserList = new ArrayList<>();

        if (!emailAddress.isEmpty() && !password.isEmpty()){
            BioObj login_username_password = new BioObj(emailEditText.getText().toString().trim().toUpperCase(),
                    passwordEditText.getText().toString().trim());
            InsertAsyncTask insertAsyncTask = new InsertAsyncTask();
            insertAsyncTask.execute(login_username_password);

        }

        LiveData<List<BioObj>> userList = MySingleton_Bio_DB.getInstance(getContext())
                .databaseBio_dao()
                .getAllUsers();

        userList.observe(LoginFragment.this, new Observer<List<BioObj>>() {
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



        LiveData<BioObj> userFind = MySingleton_Bio_DB.getInstance(getContext())
                .databaseBio_dao()
                .findUserByNamePass(emailEditText.getText().toString().trim(), passwordEditText.getText().toString().trim());

        userFind.observe(LoginFragment.this, new Observer<BioObj>() {
            @Override
            public void onChanged(BioObj bioObj) {
                if (bioObj != null){
                    Log.i(TAG, "onChanged11: "+ bioObj.getUserName());
                    existsUserList.add(new BioObj(bioObj.getUserName(), bioObj.getPassword()));
                    Log.i(TAG, "onChanged: "+ existsUserList.size());

                }

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

        NavController navController = Navigation.findNavController(view);
        registerTextView = view.findViewById(R.id.registerPageTextView);
        registerTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_loginFragment_to_registerFragment);
            }
        });
    }


    class InsertAsyncTask extends AsyncTask<BioObj, Void, Void>{

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