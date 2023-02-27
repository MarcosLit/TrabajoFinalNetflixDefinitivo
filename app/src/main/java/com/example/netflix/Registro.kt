package com.example.netflix

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth

class Registro : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        //Correo
        val nomUsu = findViewById<EditText>(R.id.correoRegistro)
        //Contraseña
        val conUsu = findViewById<EditText>(R.id.passwordRegistro)
        //Repetir contraseña
        val repUsu = findViewById<EditText>(R.id.password2Registro)
        // Botón
        val bSign = findViewById<Button>(R.id.registro)


        val vuelta = findViewById<TextView>(R.id.vueltaInicio)
        var cambioRegistro : Intent
        title="Registro del usuario"

        vuelta.setOnClickListener(){
            cambioRegistro=Intent(this, Login::class.java)
            startActivity(cambioRegistro)
        }

        nomUsu.addTextChangedListener( object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                bSign.isEnabled = ((nomUsu.text.isNotEmpty()) && (conUsu.text.isNotEmpty()))
            } })
        conUsu.addTextChangedListener( object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                bSign.isEnabled = ((nomUsu.text.isNotEmpty()) && (conUsu.text.isNotEmpty()))
            } })


        bSign.setOnClickListener() {
            if (conUsu.text.toString().equals(repUsu.text.toString())) {
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                        nomUsu.text.toString(),
                        conUsu.text.toString()
                    ).addOnCompleteListener() {
                        if (it.isSuccessful) {
                            Toast.makeText(this, "Usuario creado", Toast.LENGTH_LONG).show()
                            Thread.sleep(2000)
                            cambioRegistro = Intent(this, Login::class.java)
                            startActivity(cambioRegistro)
                        } else if (conUsu.length()<6){

                            Toast.makeText(this, "La contraseña debe tener minimo 6 caracteres", Toast.LENGTH_LONG).show()

                        } else {
                            val builder = AlertDialog.Builder(this)
                            builder.setTitle("Error")
                            builder.setMessage("Introduce campos correctos")
                            builder.setPositiveButton("Entendido", null)
                            val dialog: AlertDialog = builder.create()
                            dialog.show()
                        }
                    }

            } else
                Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_LONG).show()


        }
    }
}