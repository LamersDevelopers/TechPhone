package com.startup.tech.activities;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.startup.tech.Object.CallObject;
import com.startup.tech.Object.DetalObject;
import com.startup.tech.R;
import com.startup.tech.RegisterAPI;
import com.startup.tech.adapter.CallsAdapter;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.OkClient;
import retrofit.client.Response;

/**
 * Created by AlekseyG on 8/7/2017.
 */

public class DetaliesListActivity extends Activity{
        ListView list;
    ArrayList<DetalObject> detalies = new ArrayList<>();
    RegisterAPI api;
    DetaliesAdapter detaliesAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalies_activity);
        list = (ListView)findViewById(R.id.list_detalies);
         detaliesAdapter = new DetaliesAdapter();
        list.setAdapter(detaliesAdapter);
        RestAdapter adapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setClient(new OkClient(new OkHttpClient()))
                .setEndpoint("http://ec2-52-37-115-198.us-west-2.compute.amazonaws.com/tech_post.php") //Setting the Root URL
                .build();
        api = adapter.create(RegisterAPI.class);
        api.getDetalies("getAllParts", new Callback<ArrayList<Object>>() {
            @Override
            public void success(ArrayList<Object> detalObjects, Response response) {
                Log.d("getAllCalls", response + " " + response.getBody().toString());

                for (int i=0;i<detalObjects.size()-1;i++){
                    Map<String, String> str = (Map<String, String>) detalObjects.get(i);
                    JSONObject jsonObj = new JSONObject(str);
                    GsonBuilder builder = new GsonBuilder();
                    Gson obj = builder.create();
                    DetalObject detalObject = obj.fromJson(String.valueOf(jsonObj), DetalObject.class);
                    detalies.add(detalObject);
                }
                update();
               /* detalies = detalObjects;
                */
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });


    }

    void update(){
        detaliesAdapter.notifyDataSetChanged();
    }

    class DetaliesAdapter extends BaseAdapter{
        LayoutInflater inflanter;

        DetaliesAdapter(){
            inflanter = (LayoutInflater) DetaliesListActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        @Override
        public int getCount() {
            return detalies.size();
        }

        @Override
        public Object getItem(int position) {
            return detalies.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View view = convertView;
            ViewHolder vh = new ViewHolder();
            if (view == null) {
                view = inflanter.inflate(R.layout.detalies_item, parent, false);
            }


            vh.code = (TextView) view.findViewById(R.id.code_detal);
            vh.unit = (TextView) view.findViewById(R.id.unit);
            vh.barCode = (TextView) view.findViewById(R.id.bar_code);


            vh.code.setText(detalies.get(position).getCode());
            vh.unit.setText(detalies.get(position).getUnit());
            vh.barCode.setText(detalies.get(position).getBarcode());
            return view;
        }
        class ViewHolder{
            TextView code, unit, barCode;
        }
    }


}
