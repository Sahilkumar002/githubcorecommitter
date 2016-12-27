package com.sauloaguiar.githubcorecommitter.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.sauloaguiar.githubcorecommitter.R
import com.sauloaguiar.githubcorecommitter.model.GithubUser
import kotlinx.android.synthetic.main.recycler_item_committer.view.*
/**
 * Created by sauloaguiar on 12/18/16.
 */
class CommitterListAdapter(val committers: List<GithubUser>, val listener: (GithubUser) -> Unit ) : RecyclerView.Adapter<CommitterListAdapter.ViewHolder>() {

    override fun getItemCount(): Int {
        return committers.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CommitterListAdapter.ViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.recycler_item_committer, parent, false)
        return CommitterListAdapter.ViewHolder(view, listener)
    }

    override fun onBindViewHolder(holder: CommitterListAdapter.ViewHolder, position: Int) {
        holder.bindCommitter(committers[position])
    }


    class ViewHolder(itemView: View?, val itemClick: (GithubUser) -> Unit) : RecyclerView.ViewHolder(itemView) {
        fun bindCommitter(committer: GithubUser) {
            with(committer) {
                itemView.committerName.text = committer.username
                itemView.committerCommits.text = committer.contributions.toString() + " commits"
                Glide.with(itemView.context)
                        .load(committer.imageUrl)
                        .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                        .error(R.drawable.github)
                        .crossFade()
                        .into(itemView.committerPhoto)
                itemView.setOnClickListener { itemClick(this) }
            }
        }
    }
}