package com.example.questionnaire

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {

    val currentConstraintBackground: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }

}