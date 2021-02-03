package com.example.chartaplication;

import android.graphics.Color;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import java.util.ArrayList;
import com.github.mikephil.charting.charts.PieChart;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BarChart chart = findViewById(R.id.barchart);

        ArrayList Pembeli = new ArrayList();
        Pembeli.add(new BarEntry(2014, 420));
        Pembeli.add(new BarEntry(2015, 475));
        Pembeli.add(new BarEntry(2016, 508));
        Pembeli.add(new BarEntry(2017, 660));
        Pembeli.add(new BarEntry(2018, 550));
        Pembeli.add(new BarEntry(2019, 630));
        Pembeli.add(new BarEntry(2020, 470));

        BarDataSet bardataset = new BarDataSet(Pembeli, "Pembeli");
        bardataset.setColors(ColorTemplate.COLORFUL_COLORS);
        bardataset.setValueTextColor(Color.BLACK);
        bardataset.setValueTextSize(16f);

        BarData barData = new BarData(bardataset);
        chart.setData(barData);
        chart.animateY(2000);
    }
}