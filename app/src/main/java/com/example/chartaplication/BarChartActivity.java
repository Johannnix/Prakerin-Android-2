package com.example.chartaplication;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class BarChartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_chart);
        BarChart chart = findViewById(R.id.bar);

        ArrayList visitors = new ArrayList();
        visitors.add(new BarEntry(2014, 420));
        visitors.add(new BarEntry(2015, 475));
        visitors.add(new BarEntry(2016, 508));
        visitors.add(new BarEntry(2017, 660));
        visitors.add(new BarEntry(2018, 550));
        visitors.add(new BarEntry(2019, 630));
        visitors.add(new BarEntry(2020, 470));

        BarDataSet bardataset = new BarDataSet(visitors, "Visitors");
        bardataset.setColors(ColorTemplate.COLORFUL_COLORS);
        bardataset.setValueTextColor(Color.BLACK);
        bardataset.setValueTextSize(16f);

        BarData barData = new BarData(bardataset);
        chart.setData(barData);
        chart.animateY(2000);
    }
}
