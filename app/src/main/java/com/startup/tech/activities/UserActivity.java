package com.startup.tech.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.startup.tech.Object.CallObject;
import com.startup.tech.R;
import com.startup.tech.RegisterAPI;
import com.startup.tech.adapter.CallsAdapter;

import org.json.JSONObject;

import java.io.IOError;
import java.util.ArrayList;
import java.util.Map;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.OkClient;
import retrofit.client.Response;

import static com.startup.tech.Object.Constants.settings;

public class UserActivity extends AppCompatActivity {
    RegisterAPI api;
    ListView list;
    private Toolbar toolbar;
    ArrayList<CallObject> calls = new ArrayList<CallObject>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity);
        list = (ListView) findViewById(R.id.list);

        RestAdapter adapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setClient(new OkClient(new OkHttpClient()))
                .setEndpoint("http://ec2-52-37-115-198.us-west-2.compute.amazonaws.com/tech_post.php") //Setting the Root URL
                .build();
        api = adapter.create(RegisterAPI.class);

        toolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_add:
                        Toast.makeText(UserActivity.this, "Add Clicked", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_history:
                        Toast.makeText(UserActivity.this, "History Clicked", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.action_message:
                        Toast.makeText(UserActivity.this, "Message Clicked", Toast.LENGTH_LONG).show();
                        break;
                }
                return true;
            }
        });
        api.getCalls("getAllCalls", settings.getString("Login", ""), new Callback<ArrayList<Object>>() {
            @Override
            public void success(ArrayList<Object> s, Response response) {
                if (response.getStatus() == 200) {
                    Log.d("getAllCalls", s + " " + response.getBody().toString());

                    for (int i = 0; i < s.size() - 1; i++) {
                        try {
                            Map<String, String> str = (Map<String, String>) s.get(i);
                            JSONObject jsonObj = new JSONObject(str);
                            GsonBuilder builder = new GsonBuilder();
                            Gson obj = builder.create();
                            CallObject call = obj.fromJson(String.valueOf(jsonObj), CallObject.class);
                            Log.d("jsonObject", i + " = " + call.toString());
                            calls.add(call);
                        } catch (IOError error) {

                        }

                        CallsAdapter adapter = new CallsAdapter(UserActivity.this, calls);
                        list.setAdapter(adapter);
                        adapter.notifyDataSetChanged();

                    }
                }
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("TEst", "getAllCalls Error " + error.toString());
            }
        });

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(UserActivity.this,CallActivity.class);
                intent.putExtra("call", (Parcelable) calls.get(position));
                startActivity(intent);
            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.tbSpareParts){
            Intent intent = new Intent(UserActivity.this,DetaliesListActivity.class);

            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}

