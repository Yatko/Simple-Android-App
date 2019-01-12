/*
 * project: Simple App, Android
 * app.h7.org/simple/Android
 * Copyright © 2018 H7 (h7.org).
 *
 * created by yatko.com
 */

package org.h7.simple.activity.main

import android.arch.lifecycle.ViewModelProviders
import android.widget.Toast
import org.h7.simple.Constants
import org.h7.simple.R
import org.h7.simple.config.menu.INavViewModel
import org.h7.simple.config.menu.NavViewModel
import org.h7.simple.data.menu.MenuItem
import org.h7.simple.widget.openUrl

/**
 * Created by Alexey Rogovoy (lexapublic@gmail.com) on 30.06.2018.
 */
class MainActivityImpl: MainActivity() {

    override fun getNavViewModel(): INavViewModel {
        return ViewModelProviders.of(this).get(NavViewModel::class.java)
    }

    override fun onMenuItemClick(item: MenuItem):Boolean {
        when (item.id) {
            R.id.menu_social_media -> {
                openUrl(this, "${Constants.WEB_URL}/social")
            }
            R.id.menu_contact_us -> {
                openUrl(this, "${Constants.WEB_URL}/contact")
            }
            R.id.menu_support -> {
                openUrl(this, "${Constants.WEB_URL}/support")
            }
            R.id.menu_website -> {
                openUrl(this, Constants.WEB_URL)
            }
            R.id.menu_rate_us -> {
                openUrl(this, "${Constants.WEB_URL}/like")
            }
            else -> {
                Toast.makeText(this@MainActivityImpl, getString(item.title), Toast.LENGTH_LONG).show()
            }
        }

        return true
    }
}