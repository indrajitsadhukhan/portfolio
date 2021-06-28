package com.example.indrajit.Adapters;

import android.app.Application;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.indrajit.Model.Country;
import com.example.indrajit.R;
import com.example.indrajit.Utils;

import java.util.ArrayList;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.ViewHolder> {

    private Country[] localDataSet;
    public CountryAdapter(Country[] dataSet) {
        localDataSet = dataSet;
    }

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
//        private final TextView textView;
private final TextView name,capital,border,region,subregion,population,language;
private final ImageView flag;
        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            name = view.findViewById(R.id.name);
            capital = view.findViewById(R.id.capital);
            region = view.findViewById(R.id.region);
            subregion = view.findViewById(R.id.subregion);
            population = view.findViewById(R.id.population);
            language = view.findViewById(R.id.language);
            flag=view.findViewById(R.id.img);
            border=view.findViewById(R.id.border);


        }
    }
    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.country_row, viewGroup, false);

        return new ViewHolder(view);
    }
    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
//        viewHolder.getTextView().setText(localDataSet[position]);
        viewHolder.name.setText(localDataSet[position].getName());
        viewHolder.capital.setText(localDataSet[position].getCapital());
        viewHolder.region.setText(localDataSet[position].getRegion());
        viewHolder.subregion.setText(localDataSet[position].getSubregion());
        viewHolder.population.setText(localDataSet[position].getPopulation());
        viewHolder.language.setText(localDataSet[position].getLanguages());

        Context context= viewHolder.itemView.getContext();
        Utils.fetchSvg(context , localDataSet[position].getFlag(), viewHolder.flag);

        System.out.println("FJK"+localDataSet[position].getFlag());
        String bordertxt = "";
      ArrayList<String> borderslist = localDataSet[position].getBorders();
        for(int i=0;i<borderslist.size();i++)
        {
            bordertxt+=borderslist.get(i);
            bordertxt+=", ";
        }
        viewHolder.border.setText(bordertxt);
    }
    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return localDataSet.length;
    }
}
