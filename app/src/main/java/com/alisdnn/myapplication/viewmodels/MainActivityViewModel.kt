package com.alisdnn.myapplication.viewmodels

import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.databinding.PropertyChangeRegistry
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel(), Observable {

    private val callbacks: PropertyChangeRegistry by lazy { PropertyChangeRegistry() }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        callbacks.add(callback)
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        callbacks.remove(callback)
    }

    @Bindable
    val knownWordsNum = MutableLiveData<String>()

    private val _progressPercentage = MutableLiveData<Int>()
    val progressPercentage: LiveData<Int>
        get() = _progressPercentage


    private val _level = MutableLiveData<Int>()
    val level: LiveData<Int>
        get() = _level

    private val _toGoal = MutableLiveData<Int>()
    val toGoal: LiveData<Int>
        get() = _toGoal


    fun makeDecision() {

        val knownWordsNum = knownWordsNum.value?.toInt()
        if (knownWordsNum != null) {
            when (knownWordsNum) {
                in 0..99 -> {
                    _level.value = 0
                    setValue(knownWordsNum, 0, 99)

                }
                in 100..499 -> {
                    _level.value = 1
                    setValue(knownWordsNum, 100, 499)

                }
                in 500..1999 -> {
                    _level.value = 2
                    setValue(knownWordsNum, 500, 1999)

                }
                in 2000..5999 -> {
                    _level.value = 3
                    setValue(knownWordsNum, 2000, 5999)

                }
                in 6000..11999 -> {
                    _level.value = 4
                    setValue(knownWordsNum, 6000, 11999)

                }
                in 12000..19999 -> {
                    _level.value = 5
                    setValue(knownWordsNum, 12000, 19999)

                }
            }


        }
    }

    private fun setValue(knownWordsNum: Int, min: Int, max: Int) {
        _progressPercentage.value =
            (((knownWordsNum - min).toDouble() / (max - min).toDouble()) * 100).toInt()
        _toGoal.value = max - knownWordsNum
    }


}