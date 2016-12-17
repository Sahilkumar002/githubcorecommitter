package com.sauloaguiar.githubcorecommitter.network

import com.sauloaguiar.githubcorecommitter.model.GithubRepoWrapper
import com.sauloaguiar.githubcorecommitter.model.GithubUser
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by sauloaguiar on 12/16/16.
 */
class RestAPI() {
    private val githubApi: GithubApi

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        githubApi = retrofit.create(GithubApi::class.java)
    }

    fun getTopRepos(): Call<GithubRepoWrapper> {
        return githubApi.fetchRepos(":>1")
    }

    fun getContributors(repo: String): Call<List<GithubUser>> {
        return githubApi.fetchContributors(repo)
    }
}