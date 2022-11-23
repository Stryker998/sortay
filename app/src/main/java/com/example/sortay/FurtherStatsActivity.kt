package com.example.sortay

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sortay.databinding.ActivityFurtherStatsBinding
import com.example.sortay.databinding.ActivityStatsBinding

class FurtherStatsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFurtherStatsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFurtherStatsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun bind() {

    }
}