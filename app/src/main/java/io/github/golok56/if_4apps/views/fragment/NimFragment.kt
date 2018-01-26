package io.github.golok56.if_4apps.views.fragment

import android.app.ProgressDialog
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.github.golok56.if_4apps.R
import io.github.golok56.if_4apps.models.Student
import io.github.golok56.if_4apps.services.ApiService
import io.github.golok56.if_4apps.services.api.StudentApi
import io.github.golok56.if_4apps.viewmodels.LoginViewModel
import io.github.golok56.if_4apps.views.activity.BaseActivity
import io.github.golok56.if_4apps.views.activity.LoginActivity
import kotlinx.android.synthetic.main.fragment_nim.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * @author Satria Adi Putra
 */
class NimFragment : Fragment() {
    private var progressDialog : ProgressDialog? = null
    private lateinit var viewModel : LoginViewModel

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_nim, null)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val studentApi = ApiService.getInstance().create(StudentApi::class.java)

        viewModel = ViewModelProviders.of(activity).get(LoginViewModel::class.java)

        btnLogin.setOnClickListener {
            val nim = etNim.text.toString()

            if(nim.isEmpty()) {
                etNim.error = getString(R.string.nim_empty_error)
                return@setOnClickListener
            }

            progressDialog = ProgressDialog.show(
                    context,
                    null,
                    getString(R.string.login_progress_label),
                    true
            )
            studentApi.getStudent(nim).enqueue(StudentCallback())
        }
    }

    private inner class StudentCallback : Callback<Student> {
        val bActivity = activity as BaseActivity

        override fun onResponse(call: Call<Student>?, response: Response<Student>?) {
            Log.i(TAG, "Response Code: ${response?.code()}")
            when(response?.code()){
                500 -> bActivity.showToast(getString(R.string.server_error_notif))
                404 -> etNim.error = getString(R.string.nim_not_found_error)
                200 -> {
                    viewModel.student = response.body()
                    (activity as LoginActivity).show(PasswordFragment(), PasswordFragment.TAG)
                }
            }
            progressDialog?.dismiss()
        }

        override fun onFailure(call: Call<Student>?, t: Throwable?) {
            Log.e(TAG, "Something when wrong! ${t?.message}")
            bActivity.showToast(getString(R.string.internet_not_found_error))
            progressDialog?.dismiss()
        }
    }

    companion object {
        val TAG: String = NimFragment::class.java.simpleName
    }
}