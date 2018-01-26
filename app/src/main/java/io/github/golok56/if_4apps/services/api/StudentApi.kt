package io.github.golok56.if_4apps.services.api

import io.github.golok56.if_4apps.models.Student
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @author Satria Adi Putra
 */
interface StudentApi {
    @GET("/student/{nim}")
    fun getStudent(@Path("nim") nim: String) : Call<Student>
}