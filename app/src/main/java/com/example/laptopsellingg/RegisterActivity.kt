package com.example.laptopsellingg

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.laptopsellingg.databinding.ActivityRegisterBinding
import com.example.laptopsellingg.room.SellingDatabase
import com.example.laptopsellingg.room.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.registerBtn.setOnClickListener {
            val email = binding.emailEdt.text.toString()
            val password = binding.passwordEdt.text.toString()

            if (email.isBlank() || password.isBlank()) {
                Toast.makeText(this, "Fields not filled", Toast.LENGTH_SHORT).show()
            } else {
                lifecycleScope.launch {
                    val userDao = SellingDatabase.getInstance(this@RegisterActivity)?.userDao()
                    val isExist = userDao?.checkEmailIsExist(email)
                    if (isExist == false) {
                        userDao.register(User(0, email, password))
                        withContext(Dispatchers.Main) {
                            Toast.makeText(this@RegisterActivity, "Register Success", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        withContext(Dispatchers.Main) {
                            Toast.makeText(this@RegisterActivity, "Email already exists", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }

        binding.loginTxt.setOnClickListener {
            finish()
        }
    }
}
