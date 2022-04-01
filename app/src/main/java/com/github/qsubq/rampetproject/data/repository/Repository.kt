package com.github.qsubq.rampetproject.data.repository

import com.github.qsubq.rampetproject.data.retrofit.RetrofitInstance
import com.github.qsubq.rampetproject.model.CharacterModel
import retrofit2.Response

class Repository {

    suspend fun getCharacters() : Response<CharacterModel>{
        val randomList = mutableListOf<Int>()

        for (i in 0..20){
            randomList.add((1..860).random())
        }

        return RetrofitInstance.api.getAllCharacters(randomList.toString())
    }


}