package com.startup.tech.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.squareup.okhttp.OkHttpClient;
import com.startup.tech.Object.Constants;
import com.startup.tech.R;
import com.startup.tech.RegisterAPI;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.OkClient;
import retrofit.client.Response;

import static com.startup.tech.Object.Constants.editor;
import static com.startup.tech.Object.Constants.settings;

public class LoginActivity extends Activity {
    EditText etUserName;
    EditText etPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        etUserName = (EditText) findViewById(R.id.login);
        etPass = (EditText) findViewById(R.id.password);
        Constants.getPreference(this);
        if (settings.getBoolean("isLogin", false)) {
            Intent intent = new Intent(LoginActivity.this, UserActivity.class);
            startActivity(intent);
        } else {
            String login = settings.getString("Login", "");
            String password = settings.getString("Password", "");
            etUserName.setText(login);
            etPass.setText(password);

            Button btn = (Button) findViewById(R.id.btn_login);

            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    RestAdapter adapter = new RestAdapter.Builder()
                            .setLogLevel(RestAdapter.LogLevel.FULL)
                            .setClient(new OkClient(new OkHttpClient()))
                            .setEndpoint("http://ec2-52-37-115-198.us-west-2.compute.amazonaws.com/tech_post.php") //Setting the Root URL
                            .build();
                    RegisterAPI api = adapter.create(RegisterAPI.class);
                    api.createUser("login", etUserName.getText().toString(), etPass.getText().toString(), new Callback<String>() {
                        @Override
                        public void success(String s, Response response) {
                            Log.d("test", s + " " + response.getStatus());
                            if (response.getStatus() == 200) {
                                Log.d("test", s + " " + response.getBody().toString());
                                if (s.equals("1")) {
                                    String login = etUserName.getText().toString().trim();
                                    String password = etPass.getText().toString().trim();
                                    editor.putString("Login", login);
                                    editor.putString("Password", password);

                                    editor.putBoolean("isLogin", true);
                                    editor.commit();
                                    Intent intent = new Intent(LoginActivity.this, UserActivity.class);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(LoginActivity.this, "Login or Password uncorrectly", Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(LoginActivity.this, UserActivity.class);
                                    startActivity(intent);
                                }
                            }
                        }

                        @Override
                        public void failure(RetrofitError error) {
                            Log.d("Test", "Error " + error.toString());
                        }
                    });
                }
            });
        }
    }

    @Override
    protected void onStop() {
        super.onStop();


    }
}
