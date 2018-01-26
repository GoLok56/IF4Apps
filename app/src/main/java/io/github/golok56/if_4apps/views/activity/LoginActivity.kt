package io.github.golok56.if_4apps.views.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import io.github.golok56.if_4apps.R
import io.github.golok56.if_4apps.views.fragment.NimFragment

/**
 * @author Satria Adi Putra
 */
class LoginActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainer, NimFragment(), NimFragment.TAG)
                .commit()
    }

    fun show(fragment : Fragment, tag : String) {
        supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, fragment, tag)
                .commit()
    }
}