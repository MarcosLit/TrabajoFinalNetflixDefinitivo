package com.example.netflix

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class Pelicula : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pelicula)

        //Vuelvo a recoger el icono de perfil del usuario
        var frase = intent.getStringExtra("perfil")
        var perfil = findViewById<ImageView>(R.id.perfil)

        when(frase){
            "tigre" -> perfil.setImageResource(R.drawable.tigre)
            "hija" -> perfil.setImageResource(R.drawable.laura)
            "papa" -> perfil.setImageResource(R.drawable.papa)
            "mama" -> perfil.setImageResource(R.drawable.mama)
        }

        var volver = findViewById<ImageView>(R.id.volver)
        var cambio : Intent
        volver.setOnClickListener(){
            cambio = Intent(this, PaginaPrincipal::class.java)
            cambio.putExtra("perfil", frase)
            startActivity(cambio)
        }
    }
}