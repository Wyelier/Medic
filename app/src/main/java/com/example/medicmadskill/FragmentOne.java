package com.example.medicmadskill;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentOne extends Fragment {

    private ImageView mImageView;
    private int mImageId;


    public FragmentOne() {

    }

    // TODO: Rename and change types and number of parameters
    public static FragmentOne newInstance(int imageId) {
        FragmentOne fragment = new FragmentOne();
        Bundle args = new Bundle();
        args.putInt("image_id", imageId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mImageId = getArguments().getInt("image_id");
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_one, container, false);
        mImageView = view.findViewById(R.id.imageFrag1);
        mImageView.setImageResource(mImageId);
        return view;
    }
}