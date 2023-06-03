package com.example.laptopsellingg

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.laptopsellingg.databinding.ActivityLoginBinding
import com.example.laptopsellingg.room.SellingDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val preferences = Preferences(this)

        if (preferences.getSession() != -0) {
            val intent = Intent(this@LoginActivity, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.loginBtn.setOnClickListener {
            val email = binding.emailEdt.text.toString()
            val password = binding.passwordEdt.text.toString()

            if (email.isBlank() || password.isBlank()) {
                Toast.makeText(this, "Fields Not Filled", Toast.LENGTH_SHORT).show()
            } else {
                lifecycleScope.launch {
                    val userDao = SellingDatabase.getInstance(this@LoginActivity)?.userDao()
                    val isExist = userDao?.checkEmailIsExist(email)
                    if (isExist == true) {
                        val user = userDao.login(email)
                        if (user?.password == password) {
                            preferences.saveSession(user.id)
                            val intent = Intent(this@LoginActivity, HomeActivity::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            withContext(Dispatchers.Main) {
                                Toast.makeText(this@LoginActivity, "Wrong Password", Toast.LENGTH_SHORT).show()
                            }
                        }
                    } else {
                        withContext(Dispatchers.Main) {
                            Toast.makeText(this@LoginActivity, "Email Does Not Exist", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }

        binding.registerTxt.setOnClickListener {
            val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

}