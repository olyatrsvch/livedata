package com.example.questionnaire

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.questionnaire.databinding.ActivityClickerBinding

class ClickerActivity : AppCompatActivity() {

    private lateinit var bindingClicker: ActivityClickerBinding
    lateinit var viewModel: ClickerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingClicker = ActivityClickerBinding.inflate(layoutInflater)
        setContentView(bindingClicker.root)

        // ViewModel
        viewModel = ViewModelProvider(this)[ClickerViewModel::class.java]
        viewModel.clickerValue.observe(this, Observer {
            bindingClicker.tvClicker.text = it
        })

        // Settings onClick listeners
        with(bindingClicker) {

            btnClicker.setOnClickListener {
                viewModel.clickerValue.value = etClicker.text.toString()
                Toast.makeText(this@ClickerActivity, "Updated!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}