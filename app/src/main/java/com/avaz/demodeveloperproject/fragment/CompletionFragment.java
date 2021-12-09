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

import com.avaz.demodeveloperproject.databinding.FragmentCompletionBinding;
import com.avaz.demodeveloperproject.model.DishModel;
import com.avaz.demodeveloperproject.utility.DishAdapter;

import java.util.ArrayList;

public class CompletionFragment extends Fragment {

    private static final String TAG = "Completion Fragment";

    private static final String ARG_SELECTED_LIST = "selected list";

    private FragmentCompletionBinding binding;

    private ArrayList<DishModel> selectedList;
    private RecyclerView rvList;

    public CompletionFragment() {
        // Required empty public constructor
    }


    public static CompletionFragment newInstance(ArrayList<DishModel> models) {
        CompletionFragment fragment = new CompletionFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(ARG_SELECTED_LIST, models);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

            selectedList = getArguments().getParcelableArrayList(ARG_SELECTED_LIST);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentCompletionBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        DishAdapter adapter = new DishAdapter();
        adapter.setTextOnly(true);

        adapter.updateDishList(selectedList);

        rvList = binding.rvDishes;
        rvList.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        rvList.setAdapter(adapter);

    }
}