package com.lucassimao.listadecompras.utils

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.lucassimao.listadecompras.ui.MainActivity
import org.junit.Rule
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
open class BaseUITest {
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)
}