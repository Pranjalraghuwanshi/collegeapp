package com.example.collegeapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.initialize


class MainActivity : AppCompatActivity() {

    private lateinit var edtemaillogin:EditText
    private lateinit var edtpasswordlogin:EditText
    private lateinit var loginbtn: Button
    private lateinit var mAuth:FirebaseAuth
    private lateinit var signupfromlogon:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        Firebase.initialize(this)
        setContentView(R.layout.activity_login_page)


        signupfromlogon=findViewById(R.id.signup_from_login)
        edtemaillogin=findViewById(R.id.Email_edt)
        edtpasswordlogin=findViewById(R.id.login_password_edt)
        loginbtn=findViewById(R.id.login_btn)
        mAuth= Firebase.auth

        signupfromlogon.setOnClickListener(){
            startActivity(
            Intent(this,Sign_up::class.java)
            )
        }

        loginbtn.setOnClickListener {
            val loginemail=edtemaillogin.text.toString()
            val loginpassword=edtpasswordlogin.text.toString()
            login(loginemail,loginpassword)
        }
    }

    private fun login(loginemail: String, loginpassword: String) {
        mAuth.signInWithEmailAndPassword(loginemail,loginpassword)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    startActivity(
                        Intent(this, AfterLoginPage::class.java)
                    )
                    Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(this, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }
}