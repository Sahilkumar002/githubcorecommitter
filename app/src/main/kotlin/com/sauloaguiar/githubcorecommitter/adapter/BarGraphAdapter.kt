package com.sauloaguiar.githubcorecommitter.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import br.com.renanbandeira.bargraphlib.adapter.BaseGraphBarAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.sauloaguiar.githubcorecommitter.R
import com.sauloaguiar.githubcorecommitter.model.GithubUser
import de.hdodenhof.circleimageview.CircleImageView

/**
 * Created by sauloaguiar on 12/16/16.
 */

class BarGraphAdapter(var values: List<GithubUser>) : BaseGraphBarAdapter<BarGraphAdapter.ViewHolder>() {
    init {
        values = values.sortedDescending()
    }

    override fun getHighestValue(): Double {
        return values[0].contributions.toDouble()
    }

    override fun onBindViewHolder(holder: BarGraphAdapter.ViewHolder, position: Int) {
        val value = values[position]
        holder.value.text = value.contributions.toString()

        val valueHeight = getItemBarHeight(value.contributions.toDouble())
        holder.line?.layoutParams?.height = valueHeight

        holder.photo.setImageResource(R.drawable.womam_1)
        Glide.with(holder.itemView.context)
                .load(value.imageUrl)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .crossFade()
                .error(R.drawable.github)
                .into(holder.photo)
    }

    override fun getItemCount(): Int {
        return this.values.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): BarGraphAdapter.ViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.recycler_item_graph, parent, false)
        return ViewHolder(view)
    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        val value:TextView = itemView?.findViewById(R.id.value) as TextView
        val line = itemView?.findViewById(R.id.line)
        val photo:CircleImageView = itemView?.findViewById(R.id.friendPhoto) as CircleImageView
    }

}
