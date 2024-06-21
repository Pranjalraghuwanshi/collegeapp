package com.example.collegeapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class Sign_up : AppCompatActivity() {

    private lateinit var edtemailsignup: EditText
    private lateinit var edtpasswordsignup: EditText
    private lateinit var signupbtn: Button
    private lateinit var maAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_signup_page)

        edtemailsignup=findViewById(R.id.Email_signup)
        edtpasswordsignup=findViewById(R.id.signup_password)
        signupbtn=findViewById(R.id.sign_up_btn)
        maAuth=Firebase.auth

        signupbtn.setOnClickListener(){
            val edtemailsignup=edtemailsignup.text.toString()
            val edtpasswordsignup=edtpasswordsignup.text.toString()
            SignUP(edtemailsignup,edtpasswordsignup)
        }
    }
    private fun SignUP(edtemailsignup: String, edtpasswordsignup: String) {
        maAuth.createUserWithEmailAndPassword(edtemailsignup, edtpasswordsignup)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Toast.makeText(this, "SignedUp Succesfully", Toast.LENGTH_SHORT).show()
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(this, "Some Error", Toast.LENGTH_SHORT).show()
                }
            }}}