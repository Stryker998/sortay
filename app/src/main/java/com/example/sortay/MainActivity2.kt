package com.example.sortay

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.sortay.databinding.ActivityMain2Binding
import com.google.gson.GsonBuilder
import com.trustwallet.walletconnect.WCClient
import okhttp3.OkHttpClient


class MainActivity2 : AppCompatActivity() {

    private lateinit var binding: ActivityMain2Binding
    private val WALLET_CONNECT_URI = ""
    private val ADDRESS = ""
    private val CHAIN_ID = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun bind() {
        binding.apply {

        }
    }
}