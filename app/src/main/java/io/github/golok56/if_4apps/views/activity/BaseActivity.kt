package io.github.golok56.if_4apps.views.activity

import android.app.ProgressDialog
import android.content.Intent
import android.os.Parcelable
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import io.github.golok56.if_4apps.R
import io.github.golok56.if_4apps.views.fragment.NimFragment

/**
 * @author Satria Adi Putra
 */
open class BaseActivity : AppCompatActivity() {
    private var progressDialog : ProgressDialog? = null

    fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    fun navigateTo(activity: Class<*>, extra: Parcelable?, tag: String?) {
        val intent = Intent(this, activity)
        if(extra != null) {
            intent.putExtra(tag, extra)
        }
        startActivity(intent)
    }

    fun showProgressDialog(msg : String){
        progressDialog = ProgressDialog.show(
                this,
                null,
                msg,
                true
        )
    }

    fun dismissProgressDialog(){
        progressDialog?.dismiss()
    }

    fun handleNoInternet(tag: String, msg : String?){
        Log.e(tag, "Something when wrong! $msg")
        showToast(getString(R.string.internet_not_found_error))
        dismissProgressDialog()
    }
}