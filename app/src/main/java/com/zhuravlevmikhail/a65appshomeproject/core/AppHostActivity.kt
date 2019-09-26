package com.zhuravlevmikhail.a65appshomeproject.core

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.zhuravlevmikhail.a65appshomeproject.R
import com.zhuravlevmikhail.a65appshomeproject.appManagers.PageManager
import ru.terrakok.cicerone.android.support.SupportAppNavigator

class AppHostActivity : AppCompatActivity(R.layout.activity_host) {

    private lateinit var pageManager: PageManager
    
    private val navigator = SupportAppNavigator(this, R.id.fragmentsContainer)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pageManager = App.instance.pageManager
        if (savedInstanceState == null) {
            pageManager.onCreate(this)
            App.instance.cicerone.router.newRootScreen(ContactsScreen())
        }
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        App.instance.cicerone.navigatorHolder.setNavigator(navigator)
    }

    override fun onResume() {
        pageManager.onResume(this)
        super.onResume()
    }

    override fun onPause() {
        pageManager.onPause()
        super.onPause()
        App.instance.cicerone.navigatorHolder.removeNavigator()
    }

    override fun onDestroy() {
        pageManager.onDestroy()
        super.onDestroy()
    }
}
