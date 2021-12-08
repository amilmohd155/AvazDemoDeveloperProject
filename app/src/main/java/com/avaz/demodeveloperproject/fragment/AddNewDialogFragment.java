package com.avaz.demodeveloperproject.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.avaz.demodeveloperproject.databinding.FragmentAddNewDialogBinding;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.textfield.TextInputLayout;

public class AddNewDialogFragment extends BottomSheetDialogFragment {

    private FragmentAddNewDialogBinding binding;
    private RecyclerView rvOptions;
    private TextInputLayout etOptions;

    public AddNewDialogFragment() {
    }

    public static AddNewDialogFragment newInstance() {
        return new AddNewDialogFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = FragmentAddNewDialogBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.txtSave.setOnClickListener(v -> this.dismiss());

        rvOptions = binding.rvOptions;
        etOptions = binding.etAddOption;

        setupRVOptions();

    }

    private void setupRVOptions() {



    }

}
