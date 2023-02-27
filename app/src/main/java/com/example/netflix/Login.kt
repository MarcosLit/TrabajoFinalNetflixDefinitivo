package com.example.netflix

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        Thread.sleep(1500)
        setTheme(R.style.Theme_Netflix)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        title="Login de Usuario"

        var cambioRegistro : Intent
        var registroLink = findViewById<TextView>(R.id.registroLink)

        var login = findViewById<Button>(R.id.login)
        var correoLogin = findViewById<EditText>(R.id.correoLogin)
        var passwordLogin = findViewById<EditText>(R.id.passwordLogin)

        registroLink.setOnClickListener(){
            cambioRegistro = Intent(this, Registro::class.java)
            startActivity(cambioRegistro)
        }

        //Pulsamos boton
        login.setOnClickListener(){
            // Comprobamos que los campos no esta vacios
            if (correoLogin.text.isNotEmpty() && passwordLogin.text.isNotEmpty()){
                // Vemos que el usuario esta conectado con ese correo y contraseña a la base de datos.
                FirebaseAuth.getInstance().signInWithEmailAndPassword(correoLogin.text.toString(),passwordLogin.text.toString())
                    .addOnCompleteListener(){
                    // Si la comprobacion es correcta, emitiremos un pequeño mensaje de que se está conectando a Netflix
                    if(it.isSuccessful){
                        Toast.makeText(this, "Introduciendo a Netffix", Toast.LENGTH_LONG).show()
                        Thread.sleep(2000)
                        cambioRegistro=Intent(this, MainActivity::class.java)
                        startActivity(cambioRegistro)
                    }else{
                        // En caso de que alguno de los datos introducidos sea erroneo o el usuario no este conectado, le emitiremos un mensaje de alerta
                        val builder = AlertDialog.Builder(this)
                        builder.setTitle("Error")
                        builder.setMessage("No se ha podido conectar el ususario. Por favor compruebe que los datos estan bien introducidos." +
                                " Si usted no esta registrado, por favor, registrese.")
                        builder.setPositiveButton("Entendido", null)
                        val dialog: AlertDialog= builder.create()
                        dialog.show()
                    }
                }
                // Si alguno de los campos esta vacio, se emitira una alerta
            }else{
                val builder = AlertDialog.Builder(this)
                builder.setTitle("Error")
                builder.setMessage("Rellene los campos")
                builder.setPositiveButton("Entendido", null)
                val dialog: AlertDialog= builder.create()
                dialog.show()
            }
        }

    }
}