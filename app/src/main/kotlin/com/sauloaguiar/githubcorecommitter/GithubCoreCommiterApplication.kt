package com.sauloaguiar.githubcorecommitter

import android.app.Application
import com.squareup.leakcanary.LeakCanary

/**
 * Created by sauloaguiar on 3/19/17.
 */
class GithubCoreCommiterApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        if(LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return
        }
        LeakCanary.install(this)
    }
}