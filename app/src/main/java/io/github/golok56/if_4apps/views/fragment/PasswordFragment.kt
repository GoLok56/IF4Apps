package io.github.golok56.if_4apps.views.fragment

import android.app.ProgressDialog
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.JsonObject
import io.github.golok56.if_4apps.R
import io.github.golok56.if_4apps.models.Student
import io.github.golok56.if_4apps.services.ApiService
import io.github.golok56.if_4apps.services.api.StudentApi
import io.github.golok56.if_4apps.viewmodels.LoginViewModel
import io.github.golok56.if_4apps.views.activity.BaseActivity
import io.github.golok56.if_4apps.views.activity.MainActivity
import kotlinx.android.synthetic.main.fragment_password.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * @author Satria Adi Putra
 */
class PasswordFragment : Fragment() {
    private lateinit var viewModel : LoginViewModel
    private lateinit var bActivity : BaseActivity

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_password, null)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(activity).get(LoginViewModel::class.java)
        bActivity = activity as BaseActivity

        val studentApi = ApiService.getInstance().create(StudentApi::class.java)

        val student = viewModel.student
        val welcomeText = "Hai ${student?.name}"
        tvWelcome.text = welcomeText
        if(student?.password == null) {
            tvEnterPass.text = getString(R.string.enter_password_not_logged_in_label)
        } else {
            tlConfirmPassword.visibility = View.GONE
            tvEnterPass.text = getString(R.string.enter_password_logged_in_label)
        }

        btnLogin.setOnClickListener {
            val password = etPassword.text.toString()

            val jsonObject = JsonObject()
            jsonObject.addProperty("nim", student?.nim)
            jsonObject.addProperty("password", password)

            if(student?.password == null) {
                val confirmPassword = etConfirmPassword.text.toString()
                if(password != confirmPassword){
                    etPassword.error = "Passwordnya engga sama masa"
                    etConfirmPassword.error = "Passwordnya engga sama masa"
                    return@setOnClickListener
                }

                bActivity.showProgressDialog("Disimpen dulu ya passwordnya.")

                studentApi.addPassword(jsonObject).enqueue(AddPasswordCallback())
            } else {
                bActivity.showProgressDialog("Dicek dulu ya passwordnya.")
                studentApi.login(jsonObject).enqueue(LoginCallback())
            }
        }
    }

    private inner class AddPasswordCallback : Callback<Student> {
        override fun onResponse(call: Call<Student>?, response: Response<Student>?) {
            Log.i(TAG, "Response Code: ${response?.code()}")
            bActivity.dismissProgressDialog()
            when(response?.code()){
                500 -> bActivity.showToast(getString(R.string.server_error_notif))
                200 -> {
                    val student = response.body()
                    bActivity.showToast("Terimakasih telah menggunakan aplikasi ini. Selamat belajar ~")
                    bActivity.navigateTo(MainActivity::class.java, student, MainActivity.STUDENT_EXTRA)
                }
            }
        }

        override fun onFailure(call: Call<Student>?, t: Throwable?) {
            bActivity.handleNoInternet(TAG, t?.message)
        }
    }

    private inner class LoginCallback : Callback<Student> {
        override fun onResponse(call: Call<Student>?, response: Response<Student>?) {
            Log.i(TAG, "Response code: ${response?.code()}")
            bActivity.dismissProgressDialog()
            when(response?.code()){
                500 -> bActivity.showToast(getString(R.string.server_error_notif))
                401 -> etPassword.error = "Passwordnya salah tuh"
                200 -> {
                    val student = response.body()
                    bActivity.navigateTo(MainActivity::class.java, student, MainActivity.STUDENT_EXTRA)
                    bActivity.showToast("Selamat belajar ~")
                }
            }
        }

        override fun onFailure(call: Call<Student>?, t: Throwable?) {
            bActivity.handleNoInternet(TAG, t?.message)
        }
    }

    companion object {
        val TAG : String = PasswordFragment::class.java.simpleName
    }
}