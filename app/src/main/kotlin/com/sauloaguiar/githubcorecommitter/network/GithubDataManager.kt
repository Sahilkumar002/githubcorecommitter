package com.sauloaguiar.githubcorecommitter.network

import com.sauloaguiar.githubcorecommitter.model.GithubRepo
import com.sauloaguiar.githubcorecommitter.model.GithubUser
import rx.Observable

/**
 * Created by sauloaguiar on 12/17/16.
 */
class GithubDataManager(private val api: RestAPI = RestAPI()) {

    fun getTopRepos(): Observable<List<GithubRepo>> {
        return Observable.create {
            subscriber ->
            val response = api.getTopRepos().execute()
            if(response.isSuccessful){
                subscriber.onNext(response.body().repos)
                subscriber.onCompleted()
            } else {
                subscriber.onError(Throwable(response.message()))
            }
        }
    }

    fun getRepoContributors(reponame: String): Observable<List<GithubUser>> {
        return Observable.create {
            subscriber ->
            val response = api.getContributors(reponame).execute()
            if(response.isSuccessful){
                subscriber.onNext(response.body())
                subscriber.onCompleted()
            } else {
                subscriber.onError(Throwable(response.message()))
            }
        }
    }
}