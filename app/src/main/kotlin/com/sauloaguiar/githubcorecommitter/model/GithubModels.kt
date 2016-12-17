package com.sauloaguiar.githubcorecommitter.model

import com.google.gson.annotations.SerializedName

/**
 * Created by sauloaguiar on 12/16/16.
 */
class GithubUser(
        @SerializedName("html_url")
        val website: String,
        @SerializedName("avatar_url")
        val imageUrl: String,

        val contributions: Int,
        @SerializedName("site_admin")
        val admin: Boolean,

        val type: String
)

class GithubRepo(
        val name: String,
        val id: Int,

        @SerializedName("html_url")
        val website: String,

        val description: String,
        @SerializedName("default_branch")
        val defaultBranch: String,
        val user: GithubUser
)

class GithubRepoWrapper(
        @SerializedName("total_count")
        val totalCount: Int,

        @SerializedName("incomplete_results")
        val incompleteResults: Boolean,
        @SerializedName("items")
        val repos: List<GithubRepo>
)

