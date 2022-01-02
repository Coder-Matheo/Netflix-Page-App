package rob.netflix2app;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class LoginFragment extends Fragment {

    private AutoCompleteTextView emailEditText;
    private EditText passwordEditText;
    private ImageButton loginButton;
    private TextView registerTextView;

    private static final String TAG = LoginFragment.class.getSimpleName();
    TextView  newAccountTextView;
    LinearLayout linearLayout3,linearLayout4;


    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
                emailValidation( charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "onClick: "+ emailEditText.getText().toString());

            }
        });


    }

    public boolean emailValidation(CharSequence email){

        if (email.length() != 0){
            String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(email);
            return true;
        }
        return false;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        NavController navController = Navigation.findNavController(view);
        registerTextView = view.findViewById(R.id.registerTextView);
        registerTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_loginFragment_to_registerFragment);
            }
        });


    }
}