package com.example.catbreeds;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FavoritesRecyclerFragment extends Fragment {
    private RecyclerView recyclerView;

    public FavoritesRecyclerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favorites_recycler, container, false);
        recyclerView = view.findViewById(R.id.rv_main);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);

        final CatBreedAdapter catBreedAdapter = new CatBreedAdapter();
        final AppDatabase db = AppDatabase.getInstance(getContext());
        List<CatBreed> favoriteCatsList = db.catDao().findFavCats(1);
        catBreedAdapter.setData(favoriteCatsList);
        recyclerView.setAdapter(catBreedAdapter);

        return view;
    }

}
