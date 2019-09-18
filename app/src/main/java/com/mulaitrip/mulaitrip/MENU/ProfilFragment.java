package com.mulaitrip.mulaitrip.MENU;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.mulaitrip.mulaitrip.R;
import com.mulaitrip.mulaitrip.UI.LoginActivity;
import com.mulaitrip.mulaitrip.UTILS.AppPrefences;


public class ProfilFragment extends Fragment {

    private TextView first_name, last_name,setemail, setusername;
    private Button btnLogout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profil, container, false);

        setemail = (TextView) view.findViewById(R.id.email);
        first_name = (TextView) view.findViewById(R.id.profilefirstname);
        last_name = (TextView) view.findViewById(R.id.profilelastname);
        setusername = (TextView) view.findViewById(R.id.profileusername);

        btnLogout = view.findViewById(R.id.LogOut);

        String email = AppPrefences.get(getActivity()).getString("email", "Not define");
        setemail.setText(email);
        String firstname = AppPrefences.get(getActivity()).getString("first_name", "Not define");
        first_name.setText(firstname);
        String lastname = AppPrefences.get(getActivity()).getString("last_name", "Not define");
        last_name.setText(lastname);
        String username = AppPrefences.get(getActivity()).getString("username", "Not define");
        setusername.setText(username);


        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), LoginActivity.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                getActivity().finish();
            }
        });
        return view;
    }
}
