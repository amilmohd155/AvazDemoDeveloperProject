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

    //selection modes
    public static final int SINGLE_SELECTION = 1;
    public static final int MULTI_SELECTION = 2;

    private ArrayList<DishModel> dishArrayList;
    ArrayList<DishModel> selected;

    private boolean isDefault;                  // to decide if the icons are local resources
    private boolean isTextOnly;                 // to decide if the item view is text only
    private boolean isIconOnly;                 // boolean to decide if the item view contains only icon



    private int checkedPos = -1;

    private int selectionMode;                  //Selection mode:: 1-> Single || 2 -> Multi


    public DishAdapter() {
        dishArrayList = new ArrayList<>();
    }

    public DishAdapter(boolean isDefault, boolean isIconOnly) {
        this.isDefault = isDefault;
        this.isIconOnly = isIconOnly;
        dishArrayList = new ArrayList<>();
    }

    public DishAdapter(boolean isDefault) {
        this.isDefault = isDefault;
        dishArrayList = new ArrayList<>();
    }

    public void setSelectionMode(int selectionMode) {
        this.selectionMode = selectionMode;
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
        holder.bind(dishArrayList.get(position),isDefault, isTextOnly, isIconOnly);
    }

    @Override
    public int getItemCount() {
        return dishArrayList.size();
    }

    public void setTextOnly(boolean textOnly) {
        isTextOnly = textOnly;
    }

    public void updateDishList(final List<DishModel> dishModelList) {
        this.dishArrayList.addAll(dishModelList);
        notifyItemRangeInserted(getItemCount(), dishModelList.size());
    }

    public void addDish(final DishModel model) {
        dishArrayList.add(model);
        notifyItemInserted(dishArrayList.size());
    }

    public ArrayList<DishModel> getSelectedList() {
        if(selectionMode == MULTI_SELECTION) {
            selected = new ArrayList<>();

            for (int i = 0; i < dishArrayList.size(); ++i) {
                if (dishArrayList.get(i).isChecked())
                    selected.add(dishArrayList.get(i));
                else selected.remove(dishArrayList.get(i));
            }
            return selected;
        }
        return null;
    }

    public DishModel getSelectedItem() {
        if (selectionMode == SINGLE_SELECTION)
            if (checkedPos != -1)
                return dishArrayList.get(checkedPos);
            return null;
    }

    public class Viewholder extends RecyclerView.ViewHolder{
        private final LayoutDishItemBinding binding;

        public Viewholder(@NonNull LayoutDishItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(final DishModel model,
                         final boolean isDefault,
                         final boolean isFinalList,
                         final boolean isIconOnly) {

            if (selectionMode == SINGLE_SELECTION) {            //Single Selection

                if (checkedPos == -1) {
                    binding.cvItem.setChecked(false);
                    model.setChecked(false);
                }
                else {
                    if (checkedPos == getAdapterPosition()) {
                        binding.cvItem.setChecked(true);
                        model.setChecked(true);
                    } else {
                        binding.cvItem.setChecked(false);
                        model.setChecked(false);
                    }
                }

                binding.cvItem.setOnClickListener(v -> {
                    binding.cvItem.setChecked(true);
                    model.setChecked(true);
                    if (checkedPos != getAdapterPosition()) {
                        notifyItemChanged(checkedPos);
                        checkedPos = getAdapterPosition();
                    }
                });

            }else  if(selectionMode == MULTI_SELECTION){        //Multi selection

                binding.cvItem.setChecked(model.isChecked());

                binding.cvItem.setOnClickListener(v -> {
                    model.setChecked(!model.isChecked());
                    binding.cvItem.setChecked(model.isChecked());
                });

            }

            binding.setIsDefault(isDefault);
            binding.setIsFinalList(isFinalList);
            binding.setIsIconOnly(isIconOnly);
            binding.setModel(model);
            binding.executePendingBindings();

        }

    }
}
