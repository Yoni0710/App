package com.example.app_academiaticinf.Utilerias

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.app_academiaticinf.ConexionesRemotas.EndPoint
import com.example.app_academiaticinf.ConexionesRemotas.I_API
import com.example.app_academiaticinf.ConexionesRemotas.RetrofitClient


object ApiUtils{
    val service: I_API
        get() = RetrofitClient.getClient(EndPoint.BASE_URL).create<I_API>(I_API::class.java)
}

fun ViewGroup.inflate(LayoutRes: Int): View {
    return LayoutInflater.from(context).inflate(LayoutRes, this, false)
}