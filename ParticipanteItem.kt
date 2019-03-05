package com.example.app_academiaticinf.Model

import com.google.gson.annotations.SerializedName

class ParticipanteItem {
    @SerializedName("participantes")
    lateinit var estudiantes: List<Participante>
}