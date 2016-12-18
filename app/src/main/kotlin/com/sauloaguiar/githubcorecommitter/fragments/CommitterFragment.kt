package com.sauloaguiar.githubcorecommitter.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sauloaguiar.githubcorecommitter.R
import com.sauloaguiar.githubcorecommitter.adapter.BarGraphAdapter
import kotlinx.android.synthetic.main.fragment_graph_committer.*

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
        graph.adapter = BarGraphAdapter(listOf(1000, 1200, 200, 400))
    }
}
