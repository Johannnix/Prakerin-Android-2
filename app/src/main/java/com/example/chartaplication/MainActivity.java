package com.example.chartaplication;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.eazegraph.lib.models.PieModel;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    final int[] MY_COLORS = {Color.parseColor("#FFB259"), Color.parseColor("#ff5959"), Color.parseColor("#43d79b"), Color.parseColor("#4db5ff")};
    private String[] Item = {"Choose Chart","Pie Chart","Bar Chart","Line Chart"};
    private TextView txtName,txtCases,txtDeaths,txtRecovered,txtActive,tgl;
    ProgressDialog dialog;
    PieChart pieChart;
    CardView cv1,cv2,cv3,cv4,cv5;
    ShimmerFrameLayout shimmerbohong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pieChart = findViewById(R.id.piechart);
        txtCases = findViewById(R.id.txtCases);
        txtDeaths = findViewById(R.id.txtDeaths);
        txtRecovered = findViewById(R.id.txtRecovered);
        txtActive = findViewById(R.id.txtActive);
        tgl = findViewById(R.id.tgl);
        shimmerbohong = findViewById(R.id.shimmerFrameLayout);
        cv1 = findViewById(R.id.card1);
        cv2 = findViewById(R.id.card2);
        cv3 = findViewById(R.id.card3);
        cv4 = findViewById(R.id.card4);
        cv5 = findViewById(R.id.cardChart);

        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading");
        dialog.setCancelable(false);;
        dialog.show();
        shimmerbohong.startShimmerAnimation();

        getData();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                dialog.show();
                shimmerbohong.startShimmerAnimation();
                shimmerbohong.setVisibility(View.GONE);
                cv1.setVisibility(View.VISIBLE);
                cv2.setVisibility(View.VISIBLE);
                cv3.setVisibility(View.VISIBLE);
                cv4.setVisibility(View.VISIBLE);
                cv5.setVisibility(View.VISIBLE);
                dialog.dismiss();

            }
        }, 3000); // 3000 milliseconds delay
    }

    private String getDate(long milliSecond){
        //Mon, 23 Mar 2020 02:01:04 PM
        SimpleDateFormat formatter = new SimpleDateFormat("EEE, dd MMMM yyyy hh:mm:ss aaa");

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSecond);
        return formatter.format(calendar.getTime());
    }

    private void getData() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "https://corona.lmao.ninja/v2/countries/indonesia";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response.toString());
                    txtCases.setText(jsonObject.getString("cases"));
                    txtDeaths.setText(jsonObject.getString("deaths"));
                    txtRecovered.setText(jsonObject.getString("recovered"));
                    txtActive.setText(jsonObject.getString("active"));
                    tgl.setText(getDate(jsonObject.getLong("updated")));
                    
                    PieChart pieChart = findViewById(R.id.piechart);
                    ArrayList<Integer> colors = new ArrayList<Integer>();
                    for(int c: MY_COLORS) colors.add(c);
                    ArrayList<PieEntry> Produser = new ArrayList<>();
                    Produser.add(new PieEntry(Float.parseFloat(jsonObject.getString("cases"))));
                    Produser.add(new PieEntry(Float.parseFloat(jsonObject.getString("deaths"))));
                    Produser.add(new PieEntry(Float.parseFloat(jsonObject.getString("recovered"))));
                    Produser.add(new PieEntry(Float.parseFloat(jsonObject.getString("active"))));
                    PieDataSet pieDataSet = new PieDataSet(Produser, "");
                    pieDataSet.setColors(colors);
                    pieDataSet.setValueTextSize(0f);
                    PieData pieData = new PieData(pieDataSet);
                    pieChart.setData(pieData);
                    pieChart.getDescription().setEnabled(false);
                    pieChart.animate();

                    dialog.cancel();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error Response", error.toString());
                dialog.cancel();
            }
        });
        requestQueue.add(stringRequest);
    }
}