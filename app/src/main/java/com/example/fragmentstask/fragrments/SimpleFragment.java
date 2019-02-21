package com.example.fragmentstask.fragrments;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.fragmentstask.R;

public class SimpleFragment extends Fragment {

    private static final String FRAGMENT_NUM = "fragmentNumber";

    public static SimpleFragment newInstance(int fragmentsCounter) {
        Bundle args = new Bundle();
        args.putInt(FRAGMENT_NUM, fragmentsCounter);

        SimpleFragment fragment = new SimpleFragment();
        fragment.setArguments(args);

        return fragment;
    }

    private int fragmentsCounter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getArguments() != null){
            fragmentsCounter = getArguments().getInt(FRAGMENT_NUM);
        }

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.simple_fragment, container, false);

        TextView fragmentTitle = fragmentView.findViewById(R.id.fragmentTitle);
        fragmentTitle.setText(getString(R.string.documentTitleName) + fragmentsCounter);
        return fragmentView;
    }
}
