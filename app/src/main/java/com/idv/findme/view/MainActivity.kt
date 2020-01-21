package com.idv.findme.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.idv.findme.MainController
import com.idv.findme.R

class MainActivity : AppCompatActivity() {

    private lateinit var controller: MainController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        this.controller = MainController.Builder().build()

        controller.checkAuth("igordmv1995@gmail.com", "Malucao101!")
    }
}
