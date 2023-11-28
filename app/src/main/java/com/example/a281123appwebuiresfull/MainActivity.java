package com.example.a281123appwebuiresfull;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

import WebServices.Asynchtask;
import WebServices.WebService;

public class MainActivity extends AppCompatActivity implements Asynchtask {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Login (View view) {

        EditText usuario = findViewById(R.id.txtUsuario);
        EditText clave = findViewById(R.id.txtClave);

        String url = "https://revistas.uteq.edu.ec/ws/login.php?usr="+ usuario.getText().toString() +
                "&pass=" + clave.getText().toString();
        Toast.makeText(getApplicationContext(), "Hola", Toast.LENGTH_LONG).show();

        Snackbar.make(view, url, BaseTransientBottomBar.ANIMATION_MODE_FADE);

        Bundle bundle = this.getIntent().getExtras();
        Map<String, String> datos = new HashMap<String, String>();
        WebService ws= new WebService(url, datos, MainActivity.this, MainActivity.this);
        ws.execute("GET");

    }

    public void LoginwithV (View view) {
        RequestQueue queue = Volley.newRequestQueue(this);
        EditText usuario2 = findViewById(R.id.txtUsuario);
        EditText clave2 = findViewById(R.id.txtClave);

        TextView textResp = (TextView) findViewById(R.id.txtRespuesta);

        String url = "https://revistas.uteq.edu.ec/ws/login.php?usr="+ usuario2.getText().toString() +
                "&pass=" + clave2.getText().toString();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        textResp.setText("Response is: "+ response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        textResp.setText("That didn't work!");
                    }
                });
        queue.add(stringRequest);
    }

    @Override
    public void processFinish(String result) throws JSONException {

        TextView textResp = (TextView) findViewById(R.id.txtRespuesta);
        textResp.setText(result);
    }
}