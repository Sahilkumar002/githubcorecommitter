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
        @SerializedName("login")
        val username: String,
        val contributions: Int,
        @SerializedName("site_admin")
        val admin: Boolean,
        val type: String
) : Comparable<GithubUser> {
    override fun compareTo(other: GithubUser): Int {
        if(this.contributions > other.contributions) {
            return 1
        } else if (this.contributions < other.contributions) {
            return -1
        } else {
            return 0
        }
    }

    override fun toString(): String {
        return "GithubUser(website='$website', imageUrl='$imageUrl', contributions=$contributions, admin=$admin, type='$type')"
    }
}

class GithubRepo(
        val name: String,
        val id: Int,

        @SerializedName("html_url")
        val website: String,

        val description: String,
        @SerializedName("default_branch")
        val defaultBranch: String,
        @SerializedName("owner")
        val user: GithubUser,
        val full_name: String,
        @SerializedName("stargazers_count")
        val stars: Int
) {
    override fun toString(): String {
        return "GithubRepo(name='$name', website='$website', user=$user)"
    }
}

class GithubRepoWrapper(
        @SerializedName("total_count")
        val totalCount: Int,

        @SerializedName("incomplete_results")
        val incompleteResults: Boolean,
        @SerializedName("items")
        val repos: List<GithubRepo>

) {
    override fun toString(): String {
        return "GithubRepoWrapper(totalCount=$totalCount, incompleteResults=$incompleteResults, repos=$repos)"
    }
}

