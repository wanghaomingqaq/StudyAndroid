package com.example.xueba.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import android.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.xueba.R;

public class listFragment extends Fragment {


    public listFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        LoginDailogFragment login = new LoginDailogFragment();
//        login.show(getActivity().getFragmentManager(),"ff");

        return inflater.inflate(R.layout.fragment_list, container, false);
    }
}