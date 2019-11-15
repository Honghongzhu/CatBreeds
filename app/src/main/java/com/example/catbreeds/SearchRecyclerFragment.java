package com.example.catbreeds;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchRecyclerFragment extends Fragment {
    private RecyclerView recyclerView;
    private Button searchButton;
    private EditText searchText;
    private String search;
    private String url;

    public SearchRecyclerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_recycler, container, false);
        recyclerView = view.findViewById(R.id.rv_main);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);

        searchText = view.findViewById(R.id.search_bar);
        searchButton = view.findViewById(R.id.search_button);

        final RequestQueue queue = Volley.newRequestQueue(getActivity());
        final CatBreedAdapter catBreedAdapter = new CatBreedAdapter();
        url = "https://api.thecatapi.com/v1/breeds?api_key="+getString(R.string.api_key);

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                CatBreed[] catsArray = gson.fromJson(response, CatBreed[].class);
                List<CatBreed> catsList = Arrays.asList(catsArray);

                AppDatabase db = AppDatabase.getInstance(getContext());
                db.catDao().insertAll(catsList);
                queue.stop();
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("error");
                queue.stop();
            }
        };
        System.out.println(url);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, responseListener, errorListener);
        queue.add(stringRequest);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search = "%"+searchText.getText().toString()+"%";
                AppDatabase db = AppDatabase.getInstance(getContext());
                List<CatBreed> listCatsFromDatabase = db.catDao().findCatsWithName(search);
                catBreedAdapter.setData(listCatsFromDatabase);
                recyclerView.setAdapter(catBreedAdapter);
            }
        });

        return view;
    }

}
