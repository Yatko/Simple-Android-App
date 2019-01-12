/*
 * project: Simple App, Android
 * app.h7.org/simple/Android
 * Copyright © 2018 H7 (h7.org).
 *
 * created by yatko.com
 */

package org.h7.simple.activity.main

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import org.h7.simple.R
import org.h7.simple.activity.main.adapter.MenuAdapter
import org.h7.simple.config.menu.INavViewModel
import org.h7.simple.data.menu.MenuItem
import org.h7.simple.data.menu.OnMenuClickListener
import org.h7.simple.databinding.ActivityMainBinding
import org.h7.simple.widget.EndDrawerToggle


abstract class MainActivity : AppCompatActivity() {
    abstract fun onMenuItemClick(item: MenuItem):Boolean
    abstract fun getNavViewModel():INavViewModel

    private lateinit var modelNavigation: INavViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding : ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        modelNavigation = getNavViewModel()
        binding.footer = modelNavigation.getMenuFooter(this)
        binding.header = modelNavigation.getMenuHeader(this)
        initMenu()
        setSupportActionBar(toolbar)

        val toggle = EndDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
    }

    private fun initMenu() {
        menuItems.adapter = MenuAdapter(modelNavigation.getMenuItems(),object:OnMenuClickListener{
            override fun onMenuItemClick(item: MenuItem) {
                if (this@MainActivity.onMenuItemClick(item))
                    hideMenuIfOpened()
            }

        })
        menuItems.layoutManager = LinearLayoutManager(this)
    }

    override fun onBackPressed() {
        if (!hideMenuIfOpened()){
            super.onBackPressed()
        }
    }

    private fun hideMenuIfOpened():Boolean{
        return if (drawerLayout.isDrawerOpen(GravityCompat.END)) {
            drawerLayout.closeDrawer(GravityCompat.END)
            true
        } else {
            false
        }
    }
}
