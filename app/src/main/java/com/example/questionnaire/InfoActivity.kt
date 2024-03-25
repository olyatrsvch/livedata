package com.example.questionnaire

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.questionnaire.databinding.ActivityInfoBinding

class InfoActivity : AppCompatActivity() {

    private lateinit var bindingInfo: ActivityInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingInfo = ActivityInfoBinding.inflate(layoutInflater)
        setContentView(bindingInfo.root)

        // Personal info declaration
        val firstName: String? = intent.getStringExtra(MainActivity.NAME_KEY)
        val lastName: String? = intent.getStringExtra(MainActivity.LASTNAME_KEY)
        val patronymic: String? = intent.getStringExtra(MainActivity.PATRONYMIC_KEY)
        val age: String? = intent.getStringExtra(MainActivity.AGE_KEY)
        val hobby: String? = intent.getStringExtra(MainActivity.HOBBY_KEY)

        // Questionnaire template due to user`s age
        val questionnaireText = when ((age?:"0").toInt()) {

            in 0..17 -> getString(
                R.string.tvQuestionnaireYoung,
                lastName, firstName, patronymic, firstName, age, hobby
            )

            in 18..22 -> getString(
                R.string.tvQuestionnaireAdult,
                lastName, firstName, patronymic, firstName, age, hobby
            )
            in 23..Int.MAX_VALUE -> getString(
                R.string.tvQuestionnaireOld,
                lastName, firstName, patronymic, age, hobby
            )
            else -> getString(
                R.string.tvQuestionnaireOld,
                lastName, firstName, patronymic, age, hobby
            )
        }
        bindingInfo.tvInfo.text = questionnaireText
    }
}