package com.example.collegeuser.ui.home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.collegeuser.R;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {


    ImageSlider imageSlider;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


       View view= inflater.inflate(R.layout.fragment_home, container, false);
        imageSlider=view.findViewById(R.id.slider);
        List<SlideModel> imageList=new ArrayList<>();
        imageList.add(new SlideModel(R.drawable.image1, ScaleTypes.FIT));
        imageList.add(new SlideModel(R.drawable.image2, ScaleTypes.FIT));
        imageList.add(new SlideModel(R.drawable.image3, ScaleTypes.FIT));
        imageList.add(new SlideModel(R.drawable.image4, ScaleTypes.FIT));
        imageList.add(new SlideModel(R.drawable.image5, ScaleTypes.FIT));
        imageSlider.setImageList(imageList,ScaleTypes.FIT);
       return view;

    }
}