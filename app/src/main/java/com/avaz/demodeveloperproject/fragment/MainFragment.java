package com.avaz.demodeveloperproject.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.avaz.demodeveloperproject.R;
import com.avaz.demodeveloperproject.databinding.FragmentMainBinding;
import com.avaz.demodeveloperproject.decorators.MarginItemDecorator;
import com.avaz.demodeveloperproject.model.DishModel;
import com.avaz.demodeveloperproject.utility.DishAdapter;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

public class MainFragment extends Fragment {

    private static final String TAG = "MainFragment";

    private FragmentMainBinding binding;
    private RecyclerView rvDefault, rvDynamic;
    private ArrayList<DishModel> defaultDishes;
    private MaterialButton continueButton;

    private ArrayList<DishModel> selectedList;
    private DishAdapter defaultAdapter;

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
        continueButton = binding.btnContinue;
        selectedList = new ArrayList<>();

        setupDefaultList();
        setupAddNewBtn();


        continueButton.setOnClickListener(v -> {

            selectedList.clear();
            selectedList.addAll(defaultAdapter.getSelected());

            if(selectedList.size() < 3 || selectedList.size() > 5) {
                
                //Error message
                binding.tvError.setVisibility(View.VISIBLE);
                binding.tvError.setText(getResources().getString(R.string.error_length));
                
            }else {
                //Next screen
                CompletionFragment fragment = CompletionFragment.newInstance(selectedList);
                getParentFragmentManager().beginTransaction()
                        .replace(this.getId(), fragment)
                        .commit();
            }
            

        });


    }

    private void setupAddNewBtn() {

        binding.llAdd.setOnClickListener(v ->{
            AddNewDialogFragment fragment = AddNewDialogFragment.newInstance();
            fragment.show(getChildFragmentManager(), fragment.getTag());
        });

    }

    private void setupDefaultList() {

        defaultAdapter = new DishAdapter(true);
        rvDefault.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        rvDefault.addItemDecoration(new MarginItemDecorator(getContext().getResources().getDimensionPixelOffset(R.dimen.card_margin)));

        intiDefaultDishesValue();

        defaultAdapter.updateDishList(defaultDishes);

        rvDefault.setAdapter(defaultAdapter);
    }

    private void intiDefaultDishesValue() {

        defaultDishes = new ArrayList<>();

        defaultDishes.add(new DishModel(
                        R.drawable.ic_apple,
                        getContext().getResources().getString(R.string.apple)
                )
        );
        defaultDishes.add(new DishModel(
                        R.drawable.ic_burger,
                        getContext().getResources().getString(R.string.burger)
                )
        );
        defaultDishes.add(new DishModel(
                        R.drawable.ic_cake,
                        getContext().getResources().getString(R.string.cake)
                )
        );
        defaultDishes.add(new DishModel(
                        R.drawable.ic_dimsum,
                        getContext().getResources().getString(R.string.dimsum)
                )
        );
        defaultDishes.add(new DishModel(
                        R.drawable.ic_dosa,
                        getContext().getResources().getString(R.string.dosa)
                )
        );
        defaultDishes.add(new DishModel(
                        R.drawable.ic_ice_cream,
                        getContext().getResources().getString(R.string.ice_cream)
                )
        );
        defaultDishes.add(new DishModel(
                        R.drawable.ic_pancake,
                        getContext().getResources().getString(R.string.pancake)
                )
        );
        defaultDishes.add(new DishModel(
                        R.drawable.ic_pizza,
                        getContext().getResources().getString(R.string.pizza)
                )
        );
        defaultDishes.add(new DishModel(
                        R.drawable.ic_soup,
                        getContext().getResources().getString(R.string.soup)
                )
        );
        defaultDishes.add(new DishModel(
                        R.drawable.ic_sushi,
                        getContext().getResources().getString(R.string.sushi)
                )
        );
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}