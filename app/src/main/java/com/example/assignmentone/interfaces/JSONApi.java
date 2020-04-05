package com.example.assignmentone.interfaces;

import com.example.assignmentone.modals.emergency.Emergency;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JSONApi {

    @GET("api/society/1/emergency-contact/")
    Call<List<Emergency>> getEmergency();

}
