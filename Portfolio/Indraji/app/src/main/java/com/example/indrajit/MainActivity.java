package com.example.indrajit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.indrajit.Adapters.CountryAdapter;
import com.example.indrajit.Model.Country;
import com.example.indrajit.Room.ViewModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

ViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList<Country> countries = new ArrayList<Country>();
        RequestQueue queue = Volley.newRequestQueue(this);
        RecyclerView countrylist;
        countrylist = findViewById(R.id.countrylist);
viewModel = new ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())).get(ViewModel.class);
viewModel.allCountry.observe(this, new Observer<List<Country>>() {
    @Override
    public void onChanged(List<Country> countries) {

    }
});

JsonArrayRequest jsonArrayRequest = new JsonArrayRequest
                (Request.Method.GET, Utils.url, null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray jsonArray) {

                        try {
                            for (int i = 0; i < jsonArray.length(); i++) {
                                Country temp = new Country();
                                temp.setName(jsonArray.getJSONObject(i).getString("name"));
                                temp.setCapital(jsonArray.getJSONObject(i).getString("capital"));
                                temp.setRegion(jsonArray.getJSONObject(i).getString("region"));
                                temp.setSubregion(jsonArray.getJSONObject(i).getString("subregion"));
                                temp.setPopulation(jsonArray.getJSONObject(i).getString("population"));
                                temp.setFlag(jsonArray.getJSONObject(i).getString("flag"));
                                JSONArray borders = jsonArray.getJSONObject(i).getJSONArray("borders");
                                ArrayList<String> borderslist=new ArrayList<>();
                                for (int j = 0; j < borders.length(); j++) {
                                    borderslist.add(borders.getString(j));
                                }
                                temp.setBorders(borderslist);
                                countries.add(temp);
                            }
                            Country[] countries1 = new Country[countries.size()];
                            for(int jj=0;jj<countries.size();jj++)
                            {
                                countries1[jj]=countries.get(jj);
                            }
                            CountryAdapter countryAdapter = new CountryAdapter(countries1);
                            countrylist.setAdapter(countryAdapter);
                            countrylist.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                         //   System.out.println(countries.get(9).getBorders().get(0));

                        } catch (JSONException e) {
                            e.printStackTrace();
                            System.out.println(e.getMessage());
                        }

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        System.out.println(error.getMessage());

                    }
                });
        queue.add(jsonArrayRequest);

    }
}