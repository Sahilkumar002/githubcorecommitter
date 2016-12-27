package com.sauloaguiar.githubcorecommitter.fragments

import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
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

    var progressDialog: Dialog? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_graph_committer, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        fetchContributorsData(activity.intent.extras.getString("full_name") ?: "error")
    }

    fun showLoadingDialog() {
        progressDialog = Dialog(context, android.R.style.Theme_Translucent)
        progressDialog?.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setContentView(R.layout.loading_progress_dialog)
            setCancelable(false)
            show()
        }
    }

    fun dismissDialog(){
        progressDialog?.dismiss()
    }

    fun displayViews(){
        committers.visibility = View.VISIBLE
        graph.visibility = View.VISIBLE
        separator.visibility = View.VISIBLE
    }

    fun fetchContributorsData(reponame: String) {
        showLoadingDialog()
        GithubDataManager().getRepoContributors(reponame)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { list ->
                            dismissDialog()
                            displayViews()
                            graph.adapter = BarGraphAdapter(list)

                            committers.layoutManager = LinearLayoutManager(context)
                            committers.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
                            committers.adapter = CommitterListAdapter(list, { committer ->
                                Log.d("com.sauloaguiar.committer", committer.toString())
                                Toast.makeText(context, committer.username ?: "error", Toast.LENGTH_SHORT).show()
                            })
                        },
                        { error ->
                            dismissDialog()
                            Toast.makeText(context, error.message ?: "error", Toast.LENGTH_SHORT).show()
                        })
    }
}
