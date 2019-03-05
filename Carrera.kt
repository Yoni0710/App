package com.example.app_academiaticinf.Model

data class Carrera (val carrera_id: Int, val descripcion: String) {

    override fun toString(): String {
        return descripcion
    }
}