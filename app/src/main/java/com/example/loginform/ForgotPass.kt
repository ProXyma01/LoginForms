package com.example.loginform

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class ForgotPass : AppCompatActivity() {
    private lateinit var EmailInput : EditText
    private lateinit var SenderBtn : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_pass)

        imports()
        OnClickListeners()
    }

    private fun imports() {
        EmailInput = findViewById(R.id.EmailInput)
        SenderBtn = findViewById(R.id.SenderBtn)
    }

    private fun OnClickListeners() {
        SenderBtn.setOnClickListener {
            val Email = EmailInput.text.toString()
            if(Email.isEmpty()) {
                Toast.makeText(this, "Fill Email Input", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            FirebaseAuth.getInstance().sendPasswordResetEmail(Email)
                .addOnCompleteListener { task ->
                    if(task.isSuccessful){
                        Toast.makeText(this, "Recovery Code has been sent to your email address", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "Try Again later", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }
}