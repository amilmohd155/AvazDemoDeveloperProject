package com.avaz.demodeveloperproject.utility;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.avaz.demodeveloperproject.databinding.LayoutDishImageItemBinding;
import com.avaz.demodeveloperproject.model.Icon;

import java.util.ArrayList;
import java.util.List;

public class DishImageAdapter extends RecyclerView.Adapter<DishImageAdapter.ViewHolder> {

    private ArrayList<Icon> icons;

    public DishImageAdapter() {
        icons = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutDishImageItemBinding.inflate(LayoutInflater.from(parent.getContext()),
                        parent,
                        false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(icons.get(position));
    }

    @Override
    public int getItemCount() {
        return icons.size();
    }

    public void updateList(final List<Icon> icons) {
        this.icons.addAll(icons);
        notifyItemRangeInserted(getItemCount(), icons.size());
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        LayoutDishImageItemBinding binding;

        public ViewHolder(@NonNull LayoutDishImageItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Icon icon) {
            binding.setIcon(icon);
            binding.executePendingBindings();
        }
    }
}
