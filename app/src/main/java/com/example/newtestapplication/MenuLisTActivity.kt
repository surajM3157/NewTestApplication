package com.example.newtestapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.newtestapplication.databinding.ActivityMenuLisTactivityBinding

class MenuLisTActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMenuLisTactivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuLisTactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)




    }
}