package com.example.chartaplication;

import com.example.chartaplication.Model.ModelDataIndonesia;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Service {
    @GET("indonesia")
    Call<List<ModelDataIndonesia>> getData();
}
