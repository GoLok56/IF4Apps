package io.github.golok56.if_4apps.views.activity

import android.support.v7.app.AppCompatActivity
import android.widget.Toast

/**
 * @author Satria Adi Putra
 */
open class BaseActivity : AppCompatActivity() {
    fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}