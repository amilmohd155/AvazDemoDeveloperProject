package com.avaz.demodeveloperproject.fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.avaz.demodeveloperproject.R;
import com.avaz.demodeveloperproject.databinding.FragmentAddNewDialogBinding;
import com.avaz.demodeveloperproject.model.DishModel;
import com.avaz.demodeveloperproject.model.Icon;
import com.avaz.demodeveloperproject.model.ResponseModel;
import com.avaz.demodeveloperproject.utility.DishAdapter;
import com.avaz.demodeveloperproject.utility.DishImageAdapter;
import com.avaz.demodeveloperproject.viewmodel.DishViewModel;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

public class AddNewDialogFragment extends BottomSheetDialogFragment {

    private static final String TAG = "AddNewDialogFragment";

    private DishViewModel dishViewModel;

    private FragmentAddNewDialogBinding binding;
    private RecyclerView rvOptions;
    private TextInputLayout etOptions;
    private String term;
    private DishAdapter adapter;
    private ArrayList<DishModel> defaultDishes;

    public AddNewDialogFragment() {
    }

    public static AddNewDialogFragment newInstance() {
        return new AddNewDialogFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        assert getParentFragment() != null;
        dishViewModel = new ViewModelProvider(getParentFragment()).get(DishViewModel.class);

        binding = FragmentAddNewDialogBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.txtSave.setOnClickListener(v -> this.dismiss());

        rvOptions = binding.rvOptions;
        etOptions = binding.etAddOption;

        intiDefaultDishesValue();
        setupRVOptions();
        setupETOptions();

    }

    private void setupETOptions() {

        etOptions.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString().trim().length() >= 3) {

                    etOptions.setErrorEnabled(false);
                    term = etOptions.getEditText().getText().toString().trim();

                    dishViewModel.getIconFromTerm(term);

                    Log.d(TAG, "afterTextChanged: " + term);

                    dishViewModel.getError().observe(getViewLifecycleOwner(), error -> {
                        Log.d(TAG, "afterTextChanged: error = " + error);
                    });

                    adapter.updateDishList(defaultDishes);
//                dishViewModel.getModel().observe(getViewLifecycleOwner(), responseModel ->  {
//                    List<Icon> icons = responseModel.getIcons();
//                    adapter.updateList(icons);
//                });

                }
                else {
                    etOptions.setErrorEnabled(true);
                    etOptions.setError(getResources().getString(R.string.error_length));
                }

            }
        });

    }

    private void setupRVOptions() {

        Log.d(TAG, "setupRVOptions: " + term);

        adapter = new DishAdapter(true, true);
        adapter.setSelectionMode(DishAdapter.SINGLE_SELECTION);

        rvOptions.setLayoutManager(new GridLayoutManager(getContext(), 3));

        rvOptions.setAdapter(adapter);

    }

    private void intiDefaultDishesValue() {

        defaultDishes = new ArrayList<>();

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
    }

}
