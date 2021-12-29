package com.example.loginform

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class ResetPass : AppCompatActivity() {

    private lateinit var NewPassInput : EditText
    private lateinit var ResetBtn : Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_pass)

        imports()
        OnClickListeners()
    }

    private fun imports() {
        NewPassInput = findViewById(R.id.NewPassInput)
        ResetBtn = findViewById(R.id.ResetBtn)
    }


    private fun OnClickListeners() {
        ResetBtn.setOnClickListener {
            val newpass = NewPassInput.text.toString()
            if(newpass.isEmpty() || newpass.length < 7) {
                Toast.makeText(this, "Fill Pass Input", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            FirebaseAuth.getInstance().currentUser?.updatePassword(newpass)
                ?.addOnCompleteListener { task ->
                    if(task.isSuccessful){
                        Toast.makeText(this, "Password Has Been Changed", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "Try Again",  Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }
}