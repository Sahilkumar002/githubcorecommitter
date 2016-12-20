package com.sauloaguiar.githubcorecommitter.fragments

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.OrientationHelper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
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
class ReposFragment : Fragment() {
    var progressDialog: Dialog? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_repos, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showLoadingDialog()
        fetchReposData()
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

    fun fetchReposData() {
        GithubDataManager().getTopRepos()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {   data ->
                            dismissDialog()
                            reposRecyclerView.layoutManager = LinearLayoutManager(context)
                            reposRecyclerView.adapter = ReposRecyclerAdapter(data, { repo ->
                                val intent = Intent(context, CommitterActivity::class.java)
                                intent.putExtra("full_name", repo.full_name)
                                startActivity(intent)
                            })
                            reposRecyclerView.addItemDecoration(DividerItemDecoration(reposRecyclerView.context, OrientationHelper.VERTICAL))
                        },
                        {  error ->
                            dismissDialog()
                            Toast.makeText(context, error.message ?: "error", Toast.LENGTH_SHORT).show()
                        }
                )
    }
}
