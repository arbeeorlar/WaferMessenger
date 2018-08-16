package com.app.waffer.messenger;



import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;


import com.app.waffer.messenger.Models.Country;
import com.app.waffer.messenger.Models.Currency;
import com.app.waffer.messenger.Models.Language;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RecyclerItemTouchHelper.RecyclerItemTouchHelperListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private RecyclerView recyclerView;
    private ArrayList<Country> countryList;
    private CountryAdapter mAdapter;
    private CoordinatorLayout coordinatorLayout;
    ProgressDialog pDialog = null;
    Context context  =  this  ;
    LinearLayout connectionLayout ;
    RecyclerView.LayoutManager mLayoutManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getString(R.string.title));

      //  getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        connectionLayout = (LinearLayout) findViewById(R.id.connectionLayout);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        coordinatorLayout = findViewById(R.id.coordinator_layout);
        countryList = new ArrayList<>();

        //MySSLCertificate.nuke();

        if(Util.IsconnectionPresent(MainActivity.this)){
            recyclerView.setVisibility(View.VISIBLE);
            new GetCountryRequest().execute();
        }else{
            connectionLayout.setVisibility(View.VISIBLE);
            Toast.makeText(MainActivity.this, getResources().getString(R.string.text), Toast.LENGTH_LONG).show();
        }


        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new RecyclerItemTouchHelper(0, ItemTouchHelper.LEFT , this);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView);


        connectionLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(Util.IsconnectionPresent(MainActivity.this)){
                    recyclerView.setVisibility(View.VISIBLE);
                    new GetCountryRequest().execute();
                }else{
                    connectionLayout.setVisibility(View.VISIBLE);
                    Toast.makeText(MainActivity.this, getResources().getString(R.string.text), Toast.LENGTH_LONG).show();
                }
            }
        });


    }

    public  class GetCountryRequest extends AsyncTask<Void, Void, String> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = ProgressDialog.show(context, "", "Loading. Please wait...", true);
        }
        @Override
        protected String doInBackground(Void... params) {
            String result  =  null;
            try{
                result = Util.GetCountries();
            }catch(Exception exp){
                System.out.println(exp.toString());
                result = ""  ;
            }
            return result;
        }
        @Override
        protected void onPostExecute(final String resp) {
            if(!TextUtils.isEmpty(resp)){
                countryList =  LoadFromJsonObject(resp);
                 if(countryList != null){
                     mLayoutManager = new LinearLayoutManager(getApplicationContext());
                     recyclerView.setLayoutManager(mLayoutManager);
                     recyclerView.setItemAnimator(new DefaultItemAnimator());
                     recyclerView.addItemDecoration(new DividerItemDecoration(MainActivity.this, DividerItemDecoration.VERTICAL));
                     mAdapter = new CountryAdapter(MainActivity.this, countryList);
                     recyclerView.setAdapter(mAdapter);
                 }else{
                     Toast.makeText(context, "Oops, Something went  wrong, try again later.", Toast.LENGTH_SHORT).show();
                 }
            }else{
               //countryList = null;
                Toast.makeText(context, "Oops, Something went  wrong, try again later.", Toast.LENGTH_SHORT).show();
            }
            pDialog.dismiss();
        }
        @Override
        protected void onCancelled() {

        }
    }

     private ArrayList<Country> LoadFromJsonObject(String jsonObject){
         ArrayList<Country>  countryArrayList =  new ArrayList<>();
         try {
             JSONArray jsonarray = new JSONArray(jsonObject);

             for (int i = 0; i < jsonarray.length(); i++) {
                 Country country =  new Country();
                 JSONObject jsonobject = jsonarray.getJSONObject(i);
                 country.setName(jsonobject.getString("name"));
                 ArrayList<Currency> curList  =  new ArrayList<>();
                 JSONArray currencyArray = jsonobject.getJSONArray("currencies");
                 if (currencyArray != null) {

                     for (int k = 0; k < currencyArray.length(); k++) {
                         Currency cur  =  new Currency();
                         JSONObject curItem = currencyArray.getJSONObject(k);
                         cur.setCode(curItem.getString("name"));
                         curList.add(cur);

                     }
                     country.setCurrencies(curList);
                 }
                 // get currency
                 ArrayList<Language> langList  =  new ArrayList<>();
                 JSONArray languageArray= jsonobject.getJSONArray("languages");
                 if(languageArray != null) {
                     System.out.println("language Array size " + languageArray.length());
                     for (int j = 0; j < languageArray.length(); j++) {
                         Language lang =  new Language();
                         JSONObject _lang = languageArray.getJSONObject(j);
                         lang.setName(_lang.getString("name"));
                         langList.add(lang);
                     }
                     country.setLanguages(langList);
                 }
                 countryArrayList.add(country);
             }

         }catch (Exception ex){
             ex.toString();
             Log.i("WAFFER", ex.toString());
             countryArrayList = null;
         }
        return countryArrayList;
     }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        if (viewHolder instanceof CountryAdapter.MyViewHolder) {
//            // get the removed item name to display it in snack bar
//            String name = countryList.get(viewHolder.getAdapterPosition()).getName();
//
//            // backup of removed item for undo purpose
//            final Country deletedItem = countryList.get(viewHolder.getAdapterPosition());
//            final int deletedIndex = viewHolder.getAdapterPosition();

            // remove the item from recycler view
            mAdapter.removeItem(viewHolder.getAdapterPosition());

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds cartList to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
