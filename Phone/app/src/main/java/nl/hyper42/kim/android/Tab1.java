package nl.hyper42.kim.android;


import android.app.AlertDialog;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * Created by Kamere on 4/18/2018.
 */

public class Tab1 extends Fragment implements View.OnClickListener {

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab1, container, false);
        View button = view.findViewById(R.id.button1);
        button.setOnClickListener(this);
        button = view.findViewById(R.id.button2);
        button.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.button1) {
            Toast.makeText(getContext(), "you choose button 1",
                    Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), "you choose button 2",
                    Toast.LENGTH_SHORT).show();
        }
    }
}

