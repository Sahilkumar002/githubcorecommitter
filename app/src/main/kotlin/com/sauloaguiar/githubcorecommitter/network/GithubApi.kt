package com.sauloaguiar.githubcorecommitter.network

import com.sauloaguiar.githubcorecommitter.model.GithubRepoWrapper
import com.sauloaguiar.githubcorecommitter.model.GithubUser
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by sauloaguiar on 12/16/16.
 */

interface GithubApi {
    @GET("/search/repositories")
    fun fetchRepos(@Query("stars") stars: String): Call<GithubRepoWrapper>

    @GET("/repos/{full_name}/contributors")
    fun fetchContributors(@Path("full_name") repo: String): Call<List<GithubUser>>
}