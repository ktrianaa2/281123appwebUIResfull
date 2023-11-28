package com.example.a281123appwebuiresfull;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import WebServices.Asynchtask;
import WebServices.WebService;

public class ListadeBancos extends AppCompatActivity implements Asynchtask {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listadebancos);

        Map<String, String> datos = new HashMap<String, String>();

        WebService ws= new
                WebService("https://api-uat.kushkipagos.com/transfer/v1/bankList",
                datos, ListadeBancos.this, ListadeBancos.this);
        ws.execute("GET","Public-Merchant-Id","84e1d0de1fbf437e9779fd6a52a9ca18");
    }


    @Override
    public void processFinish(String result) throws JSONException {
        TextView txtResp = (TextView) findViewById(R.id.txtBancos);

        String lstBancos="";
        JSONArray JSONlista = new JSONArray(result);
        for(int i=0; i< JSONlista.length();i++){
            JSONObject banco= JSONlista.getJSONObject(i);
            lstBancos = lstBancos + "\n" + banco.getString("code") + "-" +
                    banco.getString("name").toString();
        }
        txtResp.setText("Respuesta WS Lista de Bancos" + lstBancos);
    }
}