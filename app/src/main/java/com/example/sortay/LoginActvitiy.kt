package com.example.sortay

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.sortay.databinding.ActivityLoginBinding


class LoginActvitiy : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val WALLET_CONNECT_URI = ""
    private val ADDRESS = ""
    private val CHAIN_ID = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun bind() {
        binding.apply {

        }
    }
}