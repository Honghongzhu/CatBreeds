package com.example.catbreeds;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CatBreedAdapter extends RecyclerView.Adapter<CatBreedAdapter.CatBreedViewHolder>{
    private List<CatBreed> breedsToAdapt;

    public void setData(List<CatBreed> breedsToAdapt) {
        this.breedsToAdapt = breedsToAdapt;
    }

    @NonNull
    @Override
    public CatBreedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cat_breed, parent, false);
        CatBreedViewHolder catBreedViewHolder = new CatBreedViewHolder(view);
        return catBreedViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CatBreedViewHolder holder, int position) {
        CatBreed breedAtPosition = breedsToAdapt.get(position);
        holder.bind(breedAtPosition);
    }

    @Override
    public int getItemCount() {
        return breedsToAdapt.size();
    }

    public class CatBreedViewHolder extends RecyclerView.ViewHolder {
        private View view;
        private TextView breedTextView;

        public CatBreedViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            breedTextView = view.findViewById(R.id.breedName);
        }

        public void bind(final CatBreed catBreed) {
            breedTextView.setText(catBreed.getName());
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, CatBreedDetailActivity.class);
                    intent.putExtra("CatBreedID", catBreed.getId());
                    context.startActivity(intent);
                }
            });
        }
    }

}
