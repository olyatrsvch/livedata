package com.example.questionnaire

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.questionnaire.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var bindingMain: ActivityMainBinding
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingMain = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingMain.root)

        // Initializing viewModel
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        // Settings onClick listeners
        with (bindingMain) {

            // Button - opens InfoActivity
            btnApply.setOnClickListener {
                val intent = Intent(this@MainActivity, InfoActivity::class.java)

                if (etAge.text.toString().isNotEmpty()) {
                    intent.putExtra(NAME_KEY, etFirstName.text.toString())
                    intent.putExtra(LASTNAME_KEY, etLastName.text.toString())
                    intent.putExtra(PATRONYMIC_KEY, etPatronymic.text.toString())
                    intent.putExtra(AGE_KEY, etAge.text.toString())
                    intent.putExtra(HOBBY_KEY, etHobby.text.toString())
                    startActivity(intent)
                    tvAddInfo.text = getString(R.string.tvAddInfo)
                } else {
                    tvAddInfo.text = getString(R.string.warnUser)
                }
            }

            // button - clears all editTexts
            btnClear.setOnClickListener {
                etLastName.text.clear()
                etFirstName.text.clear()
                etPatronymic.text.clear()
                etAge.text.clear()
                etHobby.text.clear()
            }
        }

        // Loading data from shared prefs
        loadData()

        // Observing data from viewModel
        viewModel.currentConstraintBackground.observe(this, Observer {
            bindingMain.constraint.setBackgroundResource(it)
        })
    }

    // Inflating Menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.actionbar_menu, menu)
        return true
    }

    // ActionBar menu implementation
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {

            R.id.save -> saveData()
            R.id.changeBackground -> changeBackground()
            R.id.runClicker -> runClickerActivity()
        }
        return true
    }

    // Shared Preferences
    private fun saveData() {
        with (bindingMain) {
            val sharedPreferences = getSharedPreferences("userInfo", Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.apply {
                putString(NAME_KEY, etFirstName.text.toString())
                putString(LASTNAME_KEY, etLastName.text.toString())
                putString(PATRONYMIC_KEY, etPatronymic.text.toString())
                putString(AGE_KEY, etAge.text.toString())
                putString(HOBBY_KEY, etHobby.text.toString())
            }.apply()
        }
        Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show()
    }

    private fun loadData() {

        val sharedPreferences = getSharedPreferences("userInfo", Context.MODE_PRIVATE)

        with(bindingMain) {
            etFirstName.setText(sharedPreferences.getString(NAME_KEY, ""))
            etLastName.setText(sharedPreferences.getString(LASTNAME_KEY, ""))
            etPatronymic.setText(sharedPreferences.getString(PATRONYMIC_KEY, ""))
            etAge.setText(sharedPreferences.getString(AGE_KEY, ""))
            etHobby.setText(sharedPreferences.getString(HOBBY_KEY, ""))
        }
    }

    // Constraint background changing
    private fun changeBackground() {

        val backgrounds = listOf(
            R.drawable.constraint_background1,
            R.drawable.constraint_background2,
            R.drawable.constraint_background3,
            R.drawable.constraint_background4,
            R.drawable.constraint_background5,
            R.drawable.constraint_background6,
            R.drawable.constraint_background7
        )
        viewModel.currentConstraintBackground.value = backgrounds.random()
    }

    // Opening ClickerActivity
    private fun runClickerActivity() {
        val intent = Intent(this, ClickerActivity::class.java)
        startActivity(intent)
    }

    // keys used for intents
    companion object {
        const val NAME_KEY = "name"
        const val LASTNAME_KEY = "lastName"
        const val PATRONYMIC_KEY = "patronymic"
        const val AGE_KEY = "age"
        const val HOBBY_KEY = "hobby"
    }
}