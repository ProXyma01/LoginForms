package com.example.loginform

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth

class MainPage : AppCompatActivity() {
    private lateinit var SignOutBtn: Button
    private lateinit var ChangePassBtn: Button
    private lateinit var textView: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_page)

        import()
        OnclickListeners()
        textView.text = FirebaseAuth.getInstance().currentUser?.uid

    }

    private fun OnclickListeners() {
        SignOutBtn.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            startActivity(Intent(this, SignInActivity::class.java))
            finish()
        }
        ChangePassBtn.setOnClickListener {
            startActivity(Intent(this, ForgotPass::class.java))
        }

    }

    private fun import() {
        SignOutBtn = findViewById(R.id.SignOutBtn)
        ChangePassBtn = findViewById(R.id.ChangePassBtn)
        textView = findViewById(R.id.textView)
    }

}