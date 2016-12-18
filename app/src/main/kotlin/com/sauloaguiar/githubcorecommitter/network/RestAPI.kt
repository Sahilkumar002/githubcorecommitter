package com.sauloaguiar.githubcorecommitter.network

import com.sauloaguiar.githubcorecommitter.model.GithubRepoWrapper
import com.sauloaguiar.githubcorecommitter.model.GithubUser
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by sauloaguiar on 12/16/16.
 */
class RestAPI() {
    private val githubApi: GithubApi

    init {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()


        val retrofit = Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()

        githubApi = retrofit.create(GithubApi::class.java)
    }

    fun getTopRepos(criteria: String = "stars:>1"): Call<GithubRepoWrapper> {
        return githubApi.fetchRepos(criteria)
    }

    fun getContributors(repo: String): Call<List<GithubUser>> {
        return githubApi.fetchContributors(repo)
    }
}