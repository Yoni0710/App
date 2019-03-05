package com.example.app_academiaticinf.ConexionesRemotas

import com.example.app_academiaticinf.Model.CarreraItem
import com.example.app_academiaticinf.Model.RespuestaGenerica
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface I_API {

        @GET("sait/carrera_listar.php")
        fun listar(): Observable<CarreraItem>

        @FormUrlEncoded
        @POST("sait/participante_agregar.php")
        fun enviarPI(@Field("nocontrol") nocontrol:String,
                   @Field("apellidos") apellidos:String,
                   @Field("nombres") nombres:String,
                   @Field("correo") correo:String,
                   @Field("cuenta") cuenta:String,
                   @Field("password") password:String,
                   @Field("carrera_id") carrera_id:String): Observable<RespuestaGenerica>

    @FormUrlEncoded
    @POST("ce/participante_registrar.php")
    fun enviarPE(@Field("apellidos") apellidos:String,
               @Field("nombres") nombres:String,
               @Field("correo") correo:String,
               @Field("cuenta") cuenta:String,
               @Field("password") password:String,
               @Field("procedencia") procedencia:String)
}