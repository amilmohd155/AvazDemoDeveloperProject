package com.avaz.demodeveloperproject.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.avaz.demodeveloperproject.R;
import com.avaz.demodeveloperproject.databinding.FragmentMainBinding;
import com.avaz.demodeveloperproject.model.DishModel;
import com.avaz.demodeveloperproject.utility.DishAdapter;

import java.util.ArrayList;

public class MainFragment extends Fragment {

    private FragmentMainBinding binding;
    private RecyclerView rvDefault, rvDynamic;
    private ArrayList<DishModel> defaultDishes;

    public MainFragment() {

    }

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentMainBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvDefault = binding.rvDefaultList;

        setupDefaultList();


    }

    private void setupDefaultList() {

        DishAdapter defaultAdapter = new DishAdapter(true);
        rvDefault.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        intiDefaultDishesValue();

        defaultAdapter.updateDishList(defaultDishes);

        rvDefault.setAdapter(defaultAdapter);
    }

    private void intiDefaultDishesValue(){

        defaultDishes = new ArrayList<>();

        defaultDishes.add(new DishModel(
                R.drawable.ic_apple,
                getContext().getResources().getString(R.string.apple)
                )
        );

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}