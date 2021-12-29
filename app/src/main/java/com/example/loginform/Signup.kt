package com.example.loginform

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import java.util.regex.Pattern

class Signup : AppCompatActivity() {

    private lateinit var EmailInput : EditText
    private lateinit var PasswordInput : EditText
    private lateinit var RePasswordInput : EditText
    private lateinit var SignUpBtn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        imports()
        OnClickListeners()
    }

    private fun RegexPassword(pass: String): Boolean {
        val PassRegex = Pattern.compile(
            "^" + "(?=.*[0-9])" + "(?= .*[a-z])" + "(?=.*[A-Z])" +
                    "(?=.*[a-zA-Z])" + "(?=S+$)" + ".{8,}" + "$"
        );
        return PassRegex.matcher(pass).matches()

    }

    private fun EmailRegex(email: String): Boolean {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }



    private fun imports() {
        EmailInput = findViewById(R.id.EmailInput)
        PasswordInput = findViewById(R.id.PasswordInput)
        RePasswordInput = findViewById(R.id.RePasswordInput)
        SignUpBtn = findViewById(R.id.SignOutBtn)
    }

    private fun OnClickListeners() {
        SignUpBtn.setOnClickListener {
            val Email = EmailInput.text.toString()
            val Pass = PasswordInput.text.toString()
            val RePass = RePasswordInput.text.toString()

            if(Email.isEmpty() || Pass.isEmpty() || RePass.isEmpty()) {
                Toast.makeText(this, "Fill All Inputs", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else if (Pass != RePass) {
                Toast.makeText(this, "Passwords Don't Match, Try Again", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (EmailRegex(Email) == false) {
                Toast.makeText(this, "Invalid Email", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (Pass.length < 9) {
                Toast.makeText(this, "Password Must Be At last 9 chars long", Toast.LENGTH_SHORT).show()
            } else if (!RegexPassword(Pass)) {
                Toast.makeText(this, "Password must contain Letters and Numbers", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            FirebaseAuth.getInstance()
                .createUserWithEmailAndPassword(Email, Pass)
                .addOnCompleteListener{ task ->
                    if(task.isSuccessful) {
                        startActivity(Intent(this, SignInActivity::class.java))
                        finish()
                    } else {
                        Toast.makeText(this, "Something went wrong, try again", Toast.LENGTH_SHORT).show()
                    }
                }

        }
    }
}