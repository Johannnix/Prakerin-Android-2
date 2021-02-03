package com.example.chartaplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class PieChartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PieChart pieChart = findViewById(R.id.piechart);
        ArrayList<PieEntry> Pembeli = new ArrayList<>();
        Pembeli.add(new PieEntry(420, 2014));
        Pembeli.add(new PieEntry(475, 2015));
        Pembeli.add(new PieEntry(508, 2016));
        Pembeli.add(new PieEntry(660, 2017));
        Pembeli.add(new PieEntry(550, 2018));
        Pembeli.add(new PieEntry(530, 2019));
        Pembeli.add(new PieEntry(470, 2020));

        PieDataSet pieDataSet = new PieDataSet(Pembeli, "Pembeli");
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieDataSet.setValueTextColor(Color.BLACK);
        pieDataSet.setValueTextSize(16f);

        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.getDescription().setEnabled(false);
        pieChart.setCenterText("Pembeli");
        pieChart.animate();
    }
}