package com.avaz.demodeveloperproject.utility;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.avaz.demodeveloperproject.databinding.LayoutDishItemBinding;
import com.avaz.demodeveloperproject.model.DishModel;

import java.util.ArrayList;
import java.util.List;

public class DishAdapter extends RecyclerView.Adapter<DishAdapter.Viewholder> {

    private ArrayList<DishModel> dishArrayList;
    private boolean isDefault;

    public DishAdapter(boolean isDefault) {
        this.isDefault = isDefault;
        dishArrayList = new ArrayList<>();
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Viewholder(
                LayoutDishItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false)
       );
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        holder.bind(dishArrayList.get(position),isDefault);
    }

    @Override
    public int getItemCount() {
        return dishArrayList.size();
    }

    public void updateDishList(final List<DishModel> dishModelList) {
        this.dishArrayList.addAll(dishModelList);
        notifyItemRangeInserted(getItemCount(), dishModelList.size());
    }

    public class Viewholder extends RecyclerView.ViewHolder{
        private final LayoutDishItemBinding binding;

        public Viewholder(@NonNull LayoutDishItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(DishModel model, boolean isDefault) {
            binding.setIsDefault(isDefault);
            binding.setModel(model);
            binding.executePendingBindings();
        }

    }
}
