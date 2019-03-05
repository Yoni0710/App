package com.example.app_academiaticinf.Model

data class Participante(val paticipante_id: Int,
                      val nocontrol: String,
                      val apellidos:String,
                      val nombres:String,
                      val carrera_id:Int,
                        val correo: String,
                        val cuenta:String,
                        val password:String,
                        val procedencia:String
)