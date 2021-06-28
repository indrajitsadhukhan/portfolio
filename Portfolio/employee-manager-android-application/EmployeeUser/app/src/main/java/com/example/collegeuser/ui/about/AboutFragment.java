package com.example.collegeuser.ui.about;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.collegeuser.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AboutFragment extends Fragment {

    ViewPager viewPager;
    BranchAdapter branchAdapter;
    List<BranchModel> list;
    ImageView imageView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View view= inflater.inflate(R.layout.fragment_about, container, false);
        list=new ArrayList<>();
        list.add(new BranchModel(R.drawable.ic_comp,"Computer Science",getString(R.string.about_computer_science)));
        list.add(new BranchModel(R.drawable.mechanical,"Mechanical Engineering",getString(R.string.about_mechanical)));
        branchAdapter=new BranchAdapter(getContext(),list);
        viewPager=view.findViewById(R.id.view_pager);
        viewPager.setAdapter(branchAdapter);
        imageView=view.findViewById(R.id.college_image);
        try {
            Picasso.get().load(R.drawable.office_image).into(imageView);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return view;
    }
}