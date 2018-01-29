package io.github.golok56.if_4apps.views.activity

import android.os.Bundle
import io.github.golok56.if_4apps.R
import io.github.golok56.if_4apps.managers.PreferenceManager
import io.github.golok56.if_4apps.models.Student
import kotlinx.android.synthetic.main.activity_main.*

/**
 * @author Satria Adi Putra
 */
class MainActivity : BaseActivity() {
    private var name = ""
    private var nim = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val pref = PreferenceManager.getInstance(this)
        name = pref.getStudentName()
        nim = pref.getStudentNim()

        var student: Student? = null
        if (name.isEmpty()) {
            if (intent == null) {
                navigateTo(LoginActivity::class.java, null, null)
            } else {
                student = intent.getParcelableExtra(STUDENT_EXTRA)
            }
        } else {
            student = Student(nim, name, "")
        }
        tes.text = "NIM: ${student?.nim}\nNama: ${student?.name}"
    }

    companion object {
        val STUDENT_EXTRA = "student"
    }
}