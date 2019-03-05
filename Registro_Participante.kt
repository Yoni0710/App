package com.example.app_academiaticinf.View

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.app_academiaticinf.Model.Carrera
import com.example.app_academiaticinf.Model.CarreraItem
import com.example.app_academiaticinf.Model.RespuestaGenerica
import com.example.app_academiaticinf.R
import com.example.app_academiaticinf.Utilerias.ApiUtils
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

import kotlinx.android.synthetic.main.activity_registro__participante.*
import kotlinx.android.synthetic.main.content_registro__participante.*
import org.jetbrains.anko.toast

class Registro_Participante : AppCompatActivity() {


    val TAG_CE = "CTRL_ESCOLAR"
    private lateinit var oCarrera: Observable<CarreraItem>
    private lateinit var oEnviar: Observable<RespuestaGenerica>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_registro__participante)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { _ ->enviarDatos()
            limpiarCajas()
        } // Fin fab


        oCarrera= ApiUtils.service.listar()
        oCarrera
            .subscribeOn(Schedulers.io())
            .unsubscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread()) //--- rxAndroid
            .subscribe(
                { ArrayAdapter(this,
                    android.R.layout.simple_spinner_item,
                    it.carreras).also { adapter ->
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    spCarrera.adapter = adapter
                    spCarrera.setSelection(0,true)}
                    //toast("TOTAL CARRERAS: " + it.carreras.count().toString())
                },
                {
                    toast("ERROR: hubo un error al recuperar: " + it.message )
                },
                {
                    toast("Termino la transferencia de datos")

                })

        spCarrera.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onNothingSelected(parent: AdapterView<*>?) { }

            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                // val elemento = parent.getItemAtPosition(position) as Carrera
                //val elemento = adaptadorCarrera.getItem(position)
                //toast(elemento.carrera_id.toString() + "=>" + elemento.carrera)
                //val carrera_id= elemento.carrera_id
            }
        }// Fin object

    } // Fin onCreate

    fun enviarDatos() {
        //---validar datos en esta sección de manera local

        //--- fin validacion local
        //--- RxJava
        oEnviar = ApiUtils.service.enviarPI(
            nocontrol = pNocontrol.text.toString(),
            apellidos = pApellidos.text.toString(),
            nombres = pNombres.text.toString(),
            carrera_id = (spCarrera.selectedItem as Carrera).carrera_id.toString(),
            correo = pCorreo.text.toString(),
            cuenta = pCuenta.text.toString(),
            password = pPassword.text.toString()
        )

        oEnviar.subscribeOn(Schedulers.io())
            .unsubscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { respuesta ->
                    toast(respuesta.mensaje)
                },
                { error -> Log.e(TAG_CE, "{$error.message}")},
                { Log.d(TAG_CE, "completado") }
            )
    } //--- fin enviarDatos

    fun limpiarCajas(){
        pNocontrol.requestFocus()
        pNocontrol.setText("")
        pApellidos.setText("")
        pNombres.setText("")
        pCorreo.setText("")
        pCuenta.setText("")
        pPassword.setText("")
    }// Fin de limpiarCajas()

}// Fin de la clase
