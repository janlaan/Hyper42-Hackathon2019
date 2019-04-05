package nl.hyper42.kim.android;


import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Kamere on 4/18/2018.
 */

public class Tab1 extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
//inflating layout
        return inflater.inflate(R.layout.tab1,container,false);
    }
}

