package nl.hyper42.kim.android;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import nl.hyper42.kim.android.generated.info.FlightInfo;
import nl.hyper42.kim.android.info.InfoClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * The Onboard tab
 */

public class Tab1 extends Fragment implements View.OnClickListener {

    private String TAG = "Tab1";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab1, container, false);
        View button = view.findViewById(R.id.button1);
        button.setOnClickListener(this);
        button = view.findViewById(R.id.button2);
        button.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button1:
                onButtonOneClick(view);
                break;
            case R.id.button2:
                onButtonTwoClick();
                break;
        }
    }

    private void onButtonTwoClick() {
        Call<FlightInfo> flightInfoCall = InfoClient.getInfoService().getFlightInfo();
        flightInfoCall.enqueue(new Callback<FlightInfo>() {
            @Override
            public void onResponse(Call<FlightInfo> call, Response<FlightInfo> response) {
                Toast.makeText(getContext(), "success", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<FlightInfo> call, Throwable t) {
                Toast.makeText(getContext(), "failure", Toast.LENGTH_SHORT).show();
                Log.e(TAG, "onFailure: ", t);
            }
        });
        Toast.makeText(getContext(), "you choose button 2", Toast.LENGTH_SHORT).show();
    }

    private void onButtonOneClick(View view) {
        Toast.makeText(getContext(), "you choose button 1", Toast.LENGTH_SHORT).show();
    }
}
