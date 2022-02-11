package rob.netflix2app.Registering;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.polyak.iconswitch.IconSwitch;

import rob.netflix2app.R;


public class RegisterStep2Fragment extends Fragment {

    private static final String TAG = RegisterStep2Fragment.class.getSimpleName();
    Button registerStep2button;
    NavController navController;
    TextView helpCenterTextView;
    IconSwitch customizeIconSwitch;



    public RegisterStep2Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register_step2, container, false);
        registerStep2button = view.findViewById(R.id.registerStep2button);
        helpCenterTextView = view.findViewById(R.id.help_center_id);
        customizeIconSwitch = view.findViewById(R.id.iconSwitchId);

        helpCenterFunction();
        iconSwitchFunction();


        return view;
    }




    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        registerStep2button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_customizeYourAccountRegistering_to_createYourAccountSignUpRegistering);
            }
        });

    }

    private void helpCenterFunction() {
        helpCenterTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Help Center", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void iconSwitchFunction() {
        customizeIconSwitch.setCheckedChangeListener(new IconSwitch.CheckedChangeListener() {
            @Override
            public void onCheckChanged(IconSwitch.Checked current) {
               switch (current){
                   case LEFT:
                       registerStep2button.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.disable_button_twitter));
                       break;
                   case RIGHT:
                       registerStep2button.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.enable_button_twitter));
                       break;

               }
            }
        });
    }
}