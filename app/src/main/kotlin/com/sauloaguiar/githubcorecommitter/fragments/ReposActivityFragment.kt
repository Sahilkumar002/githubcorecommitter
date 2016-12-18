package com.sauloaguiar.githubcorecommitter.fragments

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.OrientationHelper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.sauloaguiar.githubcorecommitter.CommitterActivity
import com.sauloaguiar.githubcorecommitter.R
import com.sauloaguiar.githubcorecommitter.adapter.ReposRecyclerAdapter
import com.sauloaguiar.githubcorecommitter.network.GithubDataManager
import kotlinx.android.synthetic.main.fragment_repos.*
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * A placeholder fragment containing a simple view.
 */
class ReposActivityFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_repos, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fetchReposData()
    }

    fun fetchReposData() {
        GithubDataManager().getTopRepos()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {   data ->
                            reposRecyclerView.layoutManager = LinearLayoutManager(context)
                            reposRecyclerView.adapter = ReposRecyclerAdapter(data, { repo ->
                                val intent = Intent(context, CommitterActivity::class.java)
                                intent.putExtra("full_name", repo.full_name)
                                startActivity(intent)
                            })
                            reposRecyclerView.addItemDecoration(DividerItemDecoration(reposRecyclerView.context, OrientationHelper.VERTICAL))
                        },
                        {  error ->
                            Toast.makeText(context, error.message ?: "error", Toast.LENGTH_SHORT).show()
                        }
                )
    }
}
