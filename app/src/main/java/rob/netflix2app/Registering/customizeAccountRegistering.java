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
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.polyak.iconswitch.IconSwitch;

import rob.netflix2app.R;


public class customizeAccountRegistering extends Fragment {

    Button btn;
    NavController navController;
    TextView helpCenterTextView;
    IconSwitch customizeIconSwitch;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public customizeAccountRegistering() {
        // Required empty public constructor
    }

    public static customizeAccountRegistering newInstance(String param1, String param2) {
        customizeAccountRegistering fragment = new customizeAccountRegistering();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_customize_account_registering, container, false);
        btn = view.findViewById(R.id.buttonCustome);
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
        btn.setOnClickListener(new View.OnClickListener() {
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
                       Log.i("TAG", "onCheckChanged: left");
                       break;
                   case RIGHT:
                       Log.i("TAG", "onCheckChanged: right");
                       break;

               }
            }
        });
    }
}