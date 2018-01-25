package io.github.golok56.if_4apps.services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @author Satria Adi Putra
 */
class BackendApi {
    companion object {
        fun getInstance() : Retrofit {
            return Retrofit.Builder()
                    .baseUrl("https://if4aassistantbackend.herokuapp.com")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
        }
    }
}