package com.example.chartaplication;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
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

public class MainActivity extends AppCompatActivity {
    private String[] Item = {"Pie Chart","Bar Chart","Line Chart"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Spinner List = findViewById(R.id.listItem);

        //Inisialiasi Array Adapter dengan memasukkan String Array
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item,Item);

        //Memasukan Adapter pada Spinner
        List.setAdapter(adapter);

        //Mengeset listener untuk mengetahui event/aksi saat item dipilih
        List.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView adapterView, View view, int i, long l) {
                String selectedItem = adapterView.getItemAtPosition(i).toString();
                if(selectedItem.equals("Pie Chart")) {
                    Toast.makeText(getApplicationContext(),"Saya Memilih "+adapter.getItem(i), Toast.LENGTH_SHORT).show();
                }else if (selectedItem.equals("Bar Chart")){
                    Toast.makeText(getApplicationContext(),"Saya Memilih "+adapter.getItem(i), Toast.LENGTH_SHORT).show();
                }else if (selectedItem.equals("Line Chart")){
                    Toast.makeText(getApplicationContext(),"Saya Memilih "+adapter.getItem(i), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView adapterView) {

            }
        });
    }
}