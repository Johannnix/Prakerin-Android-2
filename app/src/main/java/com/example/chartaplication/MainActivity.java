package com.example.chartaplication;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.chartaplication.Model.ModelDataIndonesia;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import java.util.ArrayList;
import java.util.List;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private String[] Item = {"Choose Chart","Pie Chart","Bar Chart","Line Chart"};
    LineChart lineChart;
    TextView tName,tPositif,tSembuh,tMati,tRawat;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        tName = findViewById(R.id.txtName);
//        tPositif = findViewById(R.id.txtPositif);
//        tSembuh = findViewById(R.id.txtSembuh);
//        tMati = findViewById(R.id.txtMati);
//        tRawat = findViewById(R.id.txtRawat);
        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading");
        dialog.setCancelable(false);;
        dialog.show();
        Call<List<ModelDataIndonesia>> call = Api.service().getData();
        call.enqueue(new Callback<List<ModelDataIndonesia>>() {
            @Override
            public void onResponse(Call<List<ModelDataIndonesia>> call, Response<List<ModelDataIndonesia>> response) {
                dialog.cancel();
                BarChart chart = findViewById(R.id.barchart);

                ArrayList Pembeli = new ArrayList();
                Pembeli.add(new BarEntry(0, ModelDataIndonesia.getPositif()));
                Pembeli.add(new BarEntry(1, ModelDataIndonesia.getSembuh()));
                Pembeli.add(new BarEntry(2, ModelDataIndonesia.getMeninggal()));
                Pembeli.add(new BarEntry(3, ModelDataIndonesia.getDirawat()));
                BarDataSet bardataset = new BarDataSet(Pembeli, "");
                bardataset.setColors(ColorTemplate.COLORFUL_COLORS);
                bardataset.setValueTextColor(Color.BLACK);
                bardataset.setValueTextSize(14f);

                BarData barData = new BarData(bardataset);
                chart.setData(barData);
                chart.animateY(2000);

                PieChart pieChart = findViewById(R.id.piechart);
                ArrayList<PieEntry> Produser = new ArrayList<>();
                Produser.add(new PieEntry(ModelDataIndonesia.getPositif(), 0));
                Produser.add(new PieEntry(ModelDataIndonesia.getSembuh(), 1));
                Produser.add(new PieEntry(ModelDataIndonesia.getMeninggal(), 2));
                Produser.add(new PieEntry(ModelDataIndonesia.getDirawat(), 3));

                PieDataSet pieDataSet = new PieDataSet(Produser, "");
                pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
                pieDataSet.setValueTextColor(Color.BLACK);
                pieDataSet.setValueTextSize(14f);

                PieData pieData = new PieData(pieDataSet);
                pieChart.setData(pieData);
                pieChart.getDescription().setEnabled(false);
                pieChart.setCenterText("");
                pieChart.animate();

                lineChart = findViewById(R.id.linechart);
                LineDataSet lineDataSet = new LineDataSet(lineChartDataSet(),"");
                ArrayList<ILineDataSet> iLineDataSets = new ArrayList<>();
                iLineDataSets.add(lineDataSet);

                LineData lineData = new LineData(iLineDataSets);
                lineChart.setData(lineData);
                lineChart.invalidate();


                //if you want set background color use below method
                //lineChart.setBackgroundColor(Color.RED);

                // set text if data are are not available
                lineChart.setNoDataText("Data not Available");

                //you can modify your line chart graph according to your requirement there are lots of method available in this library

                //now customize line chart

                lineDataSet.setColor(Color.CYAN);
                lineDataSet.setCircleColor(Color.MAGENTA);
                lineDataSet.setDrawCircles(true);
                lineDataSet.setDrawCircleHole(true);
                lineDataSet.setLineWidth(5);
                lineDataSet.setCircleRadius(10);
                lineDataSet.setCircleHoleRadius(10);
                lineDataSet.setValueTextSize(10);
                lineDataSet.setValueTextColor(Color.BLACK);
            }
            private ArrayList<Entry> lineChartDataSet(){
                ArrayList<Entry> dataSet = new ArrayList<Entry>();
                dataSet.add(new Entry(0,ModelDataIndonesia.getPositif()));
                dataSet.add(new Entry(1,ModelDataIndonesia.getSembuh()));
                dataSet.add(new Entry(2,ModelDataIndonesia.getMeninggal()));
                dataSet.add(new Entry(3,ModelDataIndonesia.getDirawat()));
                return  dataSet;
            }

            @Override
            public void onFailure(Call<List<ModelDataIndonesia>> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                dialog.cancel();
            }
        });

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
    }
}