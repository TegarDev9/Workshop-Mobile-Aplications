package com.rtegar.laundryku.API;

import com.rtegar.laundryku.Model.ResponseModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIRequestData {

    @GET("retrieve.php")
    Call<ResponseModel> ardRetrieveData();

}
