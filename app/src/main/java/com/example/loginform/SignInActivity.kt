package com.example.loginform

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class SignInActivity : AppCompatActivity() {

    private lateinit var EmailInput : EditText
    private lateinit var PasswordInput : EditText
    private lateinit var SignInBtn : Button
    private lateinit var SignUpBtn : Button
    private lateinit var ChangePassBtn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)

        import()
        OnclickListeners()
    }

    private fun import() {
        EmailInput = findViewById(R.id.EmailInput)
        PasswordInput = findViewById(R.id.PasswordInput)
        SignInBtn = findViewById(R.id.SignInBtn)
        SignUpBtn = findViewById(R.id.SignUpBtn)
        ChangePassBtn = findViewById(R.id.ChangePassBtn)
    }


    private fun Mainpage() {
        startActivity(Intent(this, MainPage()::class.java))
        finish()
    }


    private fun OnclickListeners() {

        SignInBtn.setOnClickListener {

            val Email = EmailInput.text.toString()
            val Pass = PasswordInput.text.toString()

            if(Email.isEmpty() || Pass.isEmpty()) {
                Toast.makeText(this, "Fill All Inputs", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            FirebaseAuth.getInstance()
                .signInWithEmailAndPassword(Email, Pass)
                .addOnCompleteListener { task ->
                    if(task.isSuccessful){
                        Mainpage()
                    } else {
                        Toast.makeText(this, "Try Again", Toast.LENGTH_SHORT).show()
                    }
                }
        }

        SignUpBtn.setOnClickListener{
            startActivity(Intent(this, Signup::class.java))
        }

        ChangePassBtn.setOnClickListener() {
            startActivity(Intent(this, ForgotPass::class.java))
        }

    }

}

