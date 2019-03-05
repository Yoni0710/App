package com.example.app_academiaticinf.Model

import com.google.gson.annotations.SerializedName

class CarreraItem {
    @SerializedName("carreras")
    lateinit var carreras: List<Carrera>
}