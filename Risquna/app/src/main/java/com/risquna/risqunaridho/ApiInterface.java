package com.risquna.risqunaridho;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Tegar karunia ilham
 */

public interface ApiInterface {

    @POST("get_pets.php")
    Call<List<produk>> getPets();

    @FormUrlEncoded
    @POST("add_pet.php")
    Call<produk> insertPet(
            @Field("key") String key,
            @Field("namaproduk") String namaproduk,
            @Field("deskripsi") String deskripsi,
            @Field("rating") String rating,
            @Field("gender") int gender,
            @Field("tgl") String tgl,
            @Field("picture") String picture);

    @FormUrlEncoded
    @POST("update_pet.php")
    Call<produk> updatePet(
            @Field("key") String key,
            @Field("idproduk") int id,
            @Field("namaproduk") String namaproduk,
            @Field("deskripsi") String deskripsi,
            @Field("rating") String rating,
            @Field("gender") int gender,
            @Field("tgl") String tgl,
            @Field("picture") String picture);

    @FormUrlEncoded
    @POST("delete_pet.php")
    Call<produk> deletePet(
            @Field("key") String key,
            @Field("idproduk") int idproduk,
            @Field("picture") String picture);

    @FormUrlEncoded
    @POST("update_love.php")
    Call<produk> updateLove(
            @Field("key") String key,
            @Field("idproduk") int idproduk,
            @Field("love") boolean love);

}
