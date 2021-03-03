package com.example.chartaplication;

import android.app.ProgressDialog;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private String[] Item = {"Choose Chart","Pie Chart","Bar Chart","Line Chart"};
    private TextView txtName,txtCases,txtDeaths,txtRecovered,txtActive;
    ProgressDialog dialog;
    LineChart lineChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtName = findViewById(R.id.txtName);
        txtCases = findViewById(R.id.txtCases);
        txtDeaths = findViewById(R.id.txtDeaths);
        txtRecovered = findViewById(R.id.txtRecovered);
        txtActive = findViewById(R.id.txtActive);

        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading");
        dialog.setCancelable(false);;
        dialog.show();

        getData();

    }

    private void getData() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "https://corona.lmao.ninja/v2/countries/indonesia";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response.toString());
                    txtName.setText(jsonObject.getString("country"));
                    txtCases.setText(jsonObject.getString("cases"));
                    txtDeaths.setText(jsonObject.getString("deaths"));
                    txtRecovered.setText(jsonObject.getString("recovered"));
                    txtActive.setText(jsonObject.getString("active"));

                    BarChart chart = findViewById(R.id.barchart);
                    ArrayList Pembeli = new ArrayList();
                    Pembeli.add(new BarEntry(0, ));
                    Pembeli.add(new BarEntry(1, ));
                    Pembeli.add(new BarEntry(2, ));
                    Pembeli.add(new BarEntry(3, ));
                    BarDataSet bardataset = new BarDataSet(Pembeli, "");
                    bardataset.setColors(ColorTemplate.COLORFUL_COLORS);
                    bardataset.setValueTextColor(Color.BLACK);
                    bardataset.setValueTextSize(14f);

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
//                String data_positif = response.body().get(0).getPositif();
//                String data_sembuh = response.body().get(0).getSembuh();
//                String data_meninggal = response.body().get(0).getMeninggal();
//                String data_dirawat = response.body().get(0).getDirawat();

//                BarChart chart = findViewById(R.id.barchart);
//
//                ArrayList Pembeli = new ArrayList();
//                Pembeli.add(new BarEntry(0, Float.parseFloat(data_positif)));
//                Pembeli.add(new BarEntry(1, Float.parseFloat(data_sembuh)));
//                Pembeli.add(new BarEntry(2, Float.parseFloat(data_meninggal)));
//                Pembeli.add(new BarEntry(3, Float.parseFloat(data_dirawat)));
//                BarDataSet bardataset = new BarDataSet(Pembeli, "");
//                bardataset.setColors(ColorTemplate.COLORFUL_COLORS);
//                bardataset.setValueTextColor(Color.BLACK);
//                bardataset.setValueTextSize(14f);
//
//                BarData barData = new BarData(bardataset);
//                chart.setData(barData);
//                chart.animateY(2000);

//                PieChart pieChart = findViewById(R.id.piechart);
//                ArrayList Produser = new ArrayList<>();
//                Produser.add(new Entry());
//                Produser.add(new Entry());
//                Produser.add(new Entry());
//                Produser.add(new Entry());
//
//                PieDataSet pieDataSet = new PieDataSet(Produser, "");
//                pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
//                pieDataSet.setValueTextColor(Color.BLACK);
//                pieDataSet.setValueTextSize(14f);
//
//                PieData pieData = new PieData(pieDataSet);
//                pieChart.setData(pieData);
//                pieChart.getDescription().setEnabled(false);
//                pieChart.setCenterText("");
//                pieChart.animate();

//                lineChart = findViewById(R.id.linechart);
//                LineDataSet lineDataSet = new LineDataSet(lineChartDataSet(),"");
//                ArrayList<ILineDataSet> iLineDataSets = new ArrayList<>();
//                iLineDataSets.add(lineDataSet);
//
//                LineData lineData = new LineData(iLineDataSets);
//                lineChart.setData(lineData);
//                lineChart.invalidate();
//
//
//                //if you want set background color use below method
//                //lineChart.setBackgroundColor(Color.RED);
//
//                // set text if data are are not available
//                lineChart.setNoDataText("Data not Available");
//
//                //you can modify your line chart graph according to your requirement there are lots of method available in this library
//
//                //now customize line chart
//
//                lineDataSet.setColor(Color.CYAN);
//                lineDataSet.setCircleColor(Color.MAGENTA);
//                lineDataSet.setDrawCircles(true);
//                lineDataSet.setDrawCircleHole(true);
//                lineDataSet.setLineWidth(5);
//                lineDataSet.setCircleRadius(10);
//                lineDataSet.setCircleHoleRadius(10);
//                lineDataSet.setValueTextSize(10);
//                lineDataSet.setValueTextColor(Color.BLACK);
//            }
//            private ArrayList<Entry> lineChartDataSet(){
//                ArrayList<Entry> dataSet = new ArrayList<Entry>();
//                dataSet.add(new Entry(0,ModelDataIndonesia.getPositif()));
//                dataSet.add(new Entry(1,ModelDataIndonesia.getSembuh()));
//                dataSet.add(new Entry(2,ModelDataIndonesia.getMeninggal()));
//                dataSet.add(new Entry(3,ModelDataIndonesia.getDirawat()));
//                return  dataSet;

//        View a = findViewById(R.id.piechart);
//        View b = findViewById(R.id.barchart);
//        View c = findViewById(R.id.linechart);
//        final Spinner List = findViewById(R.id.listItem);
//
//        //Inisialiasi Array Adapter dengan memasukkan String Array
//        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
//                android.R.layout.simple_spinner_dropdown_item,Item);
//
//        //Memasukan Adapter pada Spinner
//        List.setAdapter(adapter);
//
//        //Mengeset listener untuk mengetahui event/aksi saat item dipilih
//        List.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView adapterView, View view, int i, long l) {
//                String selectedItem = adapterView.getItemAtPosition(i).toString();
//                if (selectedItem.equals("Choose Chart")){
//                    a.setVisibility(View.GONE);
//                    b.setVisibility(View.GONE);
//                    c.setVisibility(View.GONE);
//                } else if(selectedItem.equals("Pie Chart")) {
//                    a.setVisibility(View.VISIBLE);
//                    b.setVisibility(View.GONE);
//                    c.setVisibility(View.GONE);
//                    Toast.makeText(getApplicationContext(),"Saya Memilih "+adapter.getItem(i), Toast.LENGTH_SHORT).show();
//                } else if (selectedItem.equals("Bar Chart")){
//                    a.setVisibility(View.GONE);
//                    b.setVisibility(View.VISIBLE);
//                    c.setVisibility(View.GONE);
//                    Toast.makeText(getApplicationContext(),"Saya Memilih "+adapter.getItem(i), Toast.LENGTH_SHORT).show();
//                } else if (selectedItem.equals("Line Chart")){
//                    a.setVisibility(View.GONE);
//                    b.setVisibility(View.GONE);
//                    c.setVisibility(View.VISIBLE);
//                    Toast.makeText(getApplicationContext(),"Saya Memilih "+adapter.getItem(i), Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView adapterView) {
//
//            }
//        });
//                String data_positif = response.body().get(0).getPositif();
//                String data_sembuh = response.body().get(0).getSembuh();
//                String data_meninggal = response.body().get(0).getMeninggal();
//                String data_dirawat = response.body().get(0).getDirawat();
//                BarChart chart = findViewById(R.id.barchart);
//
//                ArrayList Pembeli = new ArrayList();
//                Pembeli.add(new BarEntry(0, Float.parseFloat(data_positif)));
//                Pembeli.add(new BarEntry(1, Float.parseFloat(data_sembuh)));
//                Pembeli.add(new BarEntry(2, Float.parseFloat(data_meninggal)));
//                Pembeli.add(new BarEntry(3, Float.parseFloat(data_dirawat)));
//                BarDataSet bardataset = new BarDataSet(Pembeli, "");
//                bardataset.setColors(ColorTemplate.COLORFUL_COLORS);
//                bardataset.setValueTextColor(Color.BLACK);
//                bardataset.setValueTextSize(14f);
//
//                BarData barData = new BarData(bardataset);
//                chart.setData(barData);
//                chart.animateY(2000);

//                PieChart pieChart = findViewById(R.id.piechart);
//                ArrayList Produser = new ArrayList<>();
//                Produser.add(new Entry());
//                Produser.add(new Entry());
//                Produser.add(new Entry());
//                Produser.add(new Entry());
//
//                PieDataSet pieDataSet = new PieDataSet(Produser, "");
//                pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
//                pieDataSet.setValueTextColor(Color.BLACK);
//                pieDataSet.setValueTextSize(14f);
//
//                PieData pieData = new PieData(pieDataSet);
//                pieChart.setData(pieData);
//                pieChart.getDescription().setEnabled(false);
//                pieChart.setCenterText("");
//                pieChart.animate();

//                lineChart = findViewById(R.id.linechart);
//                LineDataSet lineDataSet = new LineDataSet(lineChartDataSet(),"");
//                ArrayList<ILineDataSet> iLineDataSets = new ArrayList<>();
//                iLineDataSets.add(lineDataSet);
//
//                LineData lineData = new LineData(iLineDataSets);
//                lineChart.setData(lineData);
//                lineChart.invalidate();
//
//
//                //if you want set background color use below method
//                //lineChart.setBackgroundColor(Color.RED);
//
//                // set text if data are are not available
//                lineChart.setNoDataText("Data not Available");
//
//                //you can modify your line chart graph according to your requirement there are lots of method available in this library
//
//                //now customize line chart
//
//                lineDataSet.setColor(Color.CYAN);
//                lineDataSet.setCircleColor(Color.MAGENTA);
//                lineDataSet.setDrawCircles(true);
//                lineDataSet.setDrawCircleHole(true);
//                lineDataSet.setLineWidth(5);
//                lineDataSet.setCircleRadius(10);
//                lineDataSet.setCircleHoleRadius(10);
//                lineDataSet.setValueTextSize(10);
//                lineDataSet.setValueTextColor(Color.BLACK);
//            }
//            private ArrayList<Entry> lineChartDataSet(){
//                ArrayList<Entry> dataSet = new ArrayList<Entry>();
//                dataSet.add(new Entry(0,ModelDataIndonesia.getPositif()));
//                dataSet.add(new Entry(1,ModelDataIndonesia.getSembuh()));
//                dataSet.add(new Entry(2,ModelDataIndonesia.getMeninggal()));
//                dataSet.add(new Entry(3,ModelDataIndonesia.getDirawat()));
//                return  dataSet;