package io.github.golok56.if_4apps.views.activity

import android.os.Bundle
import io.github.golok56.if_4apps.R

/**
 * @author Satria Adi Putra
 */
class MainActivity: BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    companion object {
        val STUDENT_EXTRA = "student"
    }
}