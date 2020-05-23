package com.iai.muslimdawah.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.iai.muslimdawah.R;
import com.iai.muslimdawah.utils.Adapter;
import com.iai.muslimdawah.utils.Data;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    RecyclerView recyclerView;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager layoutManager;

    List<Data> dataList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(layoutManager);
        dataList = new ArrayList<>();

        //Adding Data into ArrayList
        dataList.add(new Data("Todd Miller", "Project Manager"));
        dataList.add(new Data("Bradley Matthews", "Senior Developer"));
        dataList.add(new Data("Alian Hakim", "Android Developer"));
        dataList.add(new Data("Ilyas Yasin", "Web Developer"));
        dataList.add(new Data("Lala", "Accounting"));

        mAdapter = new Adapter(getActivity(),dataList);
        recyclerView.setAdapter(mAdapter);

        return view;
    }
}
