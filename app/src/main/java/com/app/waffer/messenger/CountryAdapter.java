package com.app.waffer.messenger;



import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;



import com.app.waffer.messenger.Models.Country;

import java.util.ArrayList;


public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<Country> countryList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, currency, language;
        public ImageView thumbnail;
        public RelativeLayout viewBackground, viewForeground;

        public MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.name);
            currency = view.findViewById(R.id.currency);
            language = view.findViewById(R.id.language);
            thumbnail = view.findViewById(R.id.thumbnail);
            viewBackground = view.findViewById(R.id.view_background);
            viewForeground = view.findViewById(R.id.view_foreground);
        }
    }


    public CountryAdapter(Context context, ArrayList<Country> countryList) {
        this.context = context;
        this.countryList = countryList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.country_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final Country item = countryList.get(position);
        holder.name.setText(item.getName());
        holder.currency.setText("Currency: " + item.getCurrencies().get(0).getCode());
        holder.language.setText("Language:" + item.getLanguages().get(0).getName());
    }

    @Override
    public int getItemCount() {
        return countryList.size();
    }

    public void removeItem(int position) {
        countryList.remove(position);
        // notify the item removed by position
        // to perform recycler view delete animations
        // NOTE: don't call notifyDataSetChanged()
        notifyItemRemoved(position);
    }

}
