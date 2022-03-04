package rob.netflix2app;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import rob.netflix2app.RoomDatabase.DatabaseViewModel;
import rob.netflix2app.Screen.NavigationDrawerMainActivity;


public class LoginStep1Fragment extends Fragment {

    private EditText emailEditText;
    private Button loginButton;
    private TextView registerTextView;
    DatabaseViewModel databaseViewModel;
    NavController navController;

    private static final String TAG = LoginStep1Fragment.class.getSimpleName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        databaseViewModel = ViewModelProviders.of(this).get(DatabaseViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login_step1, container, false);
        // Inflate the layout for this fragment

        Intent intentToScreenActivity = new Intent(getActivity(), NavigationDrawerMainActivity.class);
        //startActivity(intentToScreenActivity);

        initialization(view);
        emailCheckAutoCompleteTextView(this);
        return view;
    }


    private void initialization(View view) {
        emailEditText = view.findViewById(R.id.emailLoginEditText);
        loginButton = view.findViewById(R.id.login_btn);

    }

    private void emailCheckAutoCompleteTextView(LoginStep1Fragment loginStep1Fragment) {


        emailEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                boolean boolEmailValidation = emailValidation( charSequence);


                if (boolEmailValidation){
                    loginButton.setEnabled(true);


                }else {
                    loginButton.setEnabled(false);
                }

                if (charSequence.length() == 0){
                    loginButton.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.disable_button_twitter));
                }
                if (charSequence.length() >= 1){
                    loginButton.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.enable_button_twitter));
                }

                loginButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Bundle mDataBundle = new Bundle();
                        mDataBundle.putString("usernameOrEmail", emailEditText.getText().toString().trim());

                        LoginStep2Fragment loginStep2Fragment = new LoginStep2Fragment();
                        loginStep2Fragment.setArguments(mDataBundle);
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, loginStep2Fragment).commit();


                    }
                });
            }

            @Override
            public void afterTextChanged(Editable editable) { }
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
        registerTextView = view.findViewById(R.id.registerPageTextView);
        registerTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_loginFragment_to_registerFragment);
            }
        });
    }



}