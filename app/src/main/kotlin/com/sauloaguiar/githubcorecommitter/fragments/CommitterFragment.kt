package com.sauloaguiar.githubcorecommitter.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.sauloaguiar.githubcorecommitter.R
import com.sauloaguiar.githubcorecommitter.adapter.BarGraphAdapter
import com.sauloaguiar.githubcorecommitter.adapter.CommitterListAdapter
import com.sauloaguiar.githubcorecommitter.network.GithubDataManager
import kotlinx.android.synthetic.main.fragment_graph_committer.*
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * A placeholder fragment containing a simple view.
 */
class CommitterFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_graph_committer, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //graph.adapter = BarGraphAdapter(listOf(1000, 1200, 200, 400))
        fetchContributorsData(activity.intent.extras.getString("full_name") ?: "error")
        Toast.makeText(context, activity.intent.extras.getString("full_name") ?: "error", Toast.LENGTH_SHORT).show()
    }

    fun fetchContributorsData(reponame: String) {
        GithubDataManager().getRepoContributors(reponame)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { list ->
                            Log.d("com.sauloaguiar.githubcorecommitter", list.toString())
                            graph.adapter = BarGraphAdapter(list)

                            committers.layoutManager = LinearLayoutManager(context)
                            committers.adapter = CommitterListAdapter(list, { committer ->
                                //val intent = Intent(context, CommitterActivity::class.java)
                                //intent.putExtra("full_name", repo.full_name)
                                //startActivity(intent)
                                Toast.makeText(context, committer.username ?: "error", Toast.LENGTH_SHORT).show()
                            })
                        },
                        { error ->
                            Toast.makeText(context, error.message ?: "error", Toast.LENGTH_SHORT).show()
                        })
    }
}
