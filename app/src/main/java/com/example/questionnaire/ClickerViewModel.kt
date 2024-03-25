package com.example.questionnaire

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ClickerViewModel: ViewModel() {

    val clickerValue: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

}