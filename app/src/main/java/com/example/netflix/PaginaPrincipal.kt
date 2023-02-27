package com.example.netflix

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView

class PaginaPrincipal : AppCompatActivity() {
    @SuppressLint("WrongViewCast", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pagina_principal)

        var perfil = findViewById<ImageView>(R.id.perfil2)
        var frase = intent.getStringExtra("perfil")
        var cambio : Intent
        var botonRepro = findViewById<ImageView>(R.id.botonReproducir)
        var imagenRepro = findViewById<ImageButton>(R.id.imagenReproducir)

        when(frase){
            "tigre" -> perfil.setImageResource(R.drawable.tigre)
            "hija" -> perfil.setImageResource(R.drawable.laura)
            "papa" -> perfil.setImageResource(R.drawable.papa)
            "mama" -> perfil.setImageResource(R.drawable.mama)
        }

        perfil.setOnClickListener(){
            cambio= Intent(this, MainActivity::class.java)
            startActivity(cambio)
        }

        botonRepro.setOnClickListener(){
            cambio = Intent(this, Pelicula::class.java)
            cambio.putExtra("perfil", frase)
            startActivity(cambio)
        }
        imagenRepro.setOnClickListener(){
            cambio = Intent(this, Pelicula::class.java)
            cambio.putExtra("perfil", frase)
            startActivity(cambio)
        }
    }
}