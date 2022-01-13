package rob.netflix2app.Registering;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import rob.netflix2app.R;
import rob.netflix2app.Screen.NavigationDrawerMainActivity;

public class CreatingAccountSignUpRegistering extends Fragment {

    EditText nameEditTextSignUp;
    EditText phoneOrEmailEditTextSignUp;
    EditText dataOfBirthEditTextSignUp;
    Button signUpButton;


    public CreatingAccountSignUpRegistering() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_create_your_account_sign_up_registering, container, false);
        nameEditTextSignUp = view.findViewById(R.id.nameEditTextSignUp);
        phoneOrEmailEditTextSignUp = view.findViewById(R.id.phoneOrEmailEditTextSignUp);
        dataOfBirthEditTextSignUp = view.findViewById(R.id.dataOfBirthEditTextSignUp);
        signUpButton = view.findViewById(R.id.singUp_Button);
        signUpButtonFunction();


        return view;
    }

    private void signUpButtonFunction() {
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentToScreenActivity = new Intent(getActivity(), NavigationDrawerMainActivity.class);
                startActivity(intentToScreenActivity);
            }
        });
    }
    
}