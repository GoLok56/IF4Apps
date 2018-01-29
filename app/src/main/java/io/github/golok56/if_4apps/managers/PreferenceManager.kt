package io.github.golok56.if_4apps.managers

import android.content.Context

/**
 * @author Satria Adi Putra
 */
class PreferenceManager private constructor(ctx: Context) {

    private val sharedPref = ctx.getSharedPreferences("pref", Context.MODE_PRIVATE)

    fun setStudentRemembered(name: String, nim: String){
        sharedPref.edit()
                .putString(STUDENT_NAME, name)
                .putString(STUDENT_NIM, nim)
                .apply()
    }

    fun getStudentName() = sharedPref.getString(STUDENT_NAME, "")
    fun getStudentNim() = sharedPref.getString(STUDENT_NIM, "")

    companion object {
        val STUDENT_NAME = "name"
        val STUDENT_NIM = "nim"

        private var instance : PreferenceManager? = null

        fun getInstance(ctx: Context) : PreferenceManager {
            if(instance == null) {
                instance = PreferenceManager(ctx)
            }

            return instance as PreferenceManager
        }
    }

}