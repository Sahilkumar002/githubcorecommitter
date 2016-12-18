package com.sauloaguiar.githubcorecommitter.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sauloaguiar.githubcorecommitter.R
import com.sauloaguiar.githubcorecommitter.model.GithubRepo
import kotlinx.android.synthetic.main.repo_recycler_item.view.*

/**
 * Created by sauloaguiar on 12/17/16.
 */
class ReposRecyclerAdapter(val repos: List<GithubRepo>, val listener: (GithubRepo) -> Unit ) : RecyclerView.Adapter<ReposRecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.repo_recycler_item, parent, false)
        return ViewHolder(view, listener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindRepo(repos[position])
    }

    override fun getItemCount(): Int {
        return repos.size
    }

    class ViewHolder(itemView: View?, val itemClick: (GithubRepo) -> Unit) : RecyclerView.ViewHolder(itemView) {
        fun bindRepo(repo: GithubRepo) {
            with(repo) {
               itemView.repoName.text = repo.name
               itemView.repoDescription.text = repo.description
                itemView.setOnClickListener { itemClick(this) }
            }
        }
    }
}