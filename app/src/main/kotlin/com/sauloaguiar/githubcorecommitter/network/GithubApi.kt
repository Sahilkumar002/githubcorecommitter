package com.sauloaguiar.githubcorecommitter.network

import com.sauloaguiar.githubcorecommitter.model.GithubRepoWrapper
import com.sauloaguiar.githubcorecommitter.model.GithubUser
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

/**
 * Created by sauloaguiar on 12/16/16.
 */

interface GithubApi {
    @GET("/search/repositories")
    fun fetchRepos(@Query("q") stars: String): Call<GithubRepoWrapper>

    @GET//("/repos/{full_name}/contributors")
    fun fetchContributors(@Url repo: String): Call<List<GithubUser>>
}