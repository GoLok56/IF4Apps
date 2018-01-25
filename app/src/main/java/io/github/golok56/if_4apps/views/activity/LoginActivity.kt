package io.github.golok56.if_4apps.views.activity

import android.os.Bundle
import io.github.golok56.if_4apps.R
import kotlinx.android.synthetic.main.login_activity.*

/**
 * @author Satria Adi Putra
 */
class LoginActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)

        btnLogin.setOnClickListener{ showToast("Aduh sakit") }
    }
}