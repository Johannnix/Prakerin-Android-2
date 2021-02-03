package com.example.chartaplication;

import android.graphics.Color;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import java.util.ArrayList;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

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
        bardataset.setValueTextSize(14f);

        BarData barData = new BarData(bardataset);
        chart.setData(barData);
        chart.animateY(2000);

        PieChart pieChart = findViewById(R.id.piechart);
        ArrayList<PieEntry> Produser = new ArrayList<>();
        Produser.add(new PieEntry(420, 2014));
        Produser.add(new PieEntry(475, 2015));
        Produser.add(new PieEntry(508, 2016));
        Produser.add(new PieEntry(660, 2017));
        Produser.add(new PieEntry(550, 2018));
        Produser.add(new PieEntry(530, 2019));
        Produser.add(new PieEntry(470, 2020));

        PieDataSet pieDataSet = new PieDataSet(Produser, "Pembeli");
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieDataSet.setValueTextColor(Color.BLACK);
        pieDataSet.setValueTextSize(14f);

        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.getDescription().setEnabled(false);
        pieChart.setCenterText("Pembeli");
        pieChart.animate();

    }
}