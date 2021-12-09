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
    private boolean isFinalList;

    public DishAdapter() {
        dishArrayList = new ArrayList<>();
    }

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
        holder.bind(dishArrayList.get(position),isDefault, isFinalList);
    }

    @Override
    public int getItemCount() {
        return dishArrayList.size();
    }

    public void setFinalList(boolean finalList) {
        isFinalList = finalList;
    }

    public void updateDishList(final List<DishModel> dishModelList) {
        this.dishArrayList.addAll(dishModelList);
        notifyItemRangeInserted(getItemCount(), dishModelList.size());
    }

    public ArrayList<DishModel> getSelected() {
        ArrayList<DishModel> selected = new ArrayList<>();

        for (int i = 0; i < dishArrayList.size(); ++i) {
            if (dishArrayList.get(i).isChecked())
                selected.add(dishArrayList.get(i));
            else selected.remove(dishArrayList.get(i));
        }
        return selected;
    }

    public class Viewholder extends RecyclerView.ViewHolder{
        private final LayoutDishItemBinding binding;

        public Viewholder(@NonNull LayoutDishItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(DishModel model, boolean isDefault, boolean isFinalList) {

            binding.cvItem.setChecked(model.isChecked());

            binding.setIsDefault(isDefault);
            binding.setIsFinalList(isFinalList);
            binding.setModel(model);
            binding.executePendingBindings();

            binding.cvItem.setOnClickListener(v -> {
                model.setChecked(!model.isChecked());
                binding.cvItem.setChecked(model.isChecked());
            });

        }

    }
}
