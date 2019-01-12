/*
 * project: Simple App, Android
 * app.h7.org/simple/Android
 * Copyright © 2018 H7 (h7.org).
 *
 * created by yatko.com
 */

package org.h7.simple.widget

import android.app.Activity
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.graphics.drawable.DrawerArrowDrawable
import android.support.v7.widget.AppCompatImageButton
import android.support.v7.widget.Toolbar
import android.view.View
import org.h7.simple.R


/**
 * Created by Jason Lauri (pandasoft0@gmail.com) on 26.06.2018.
 */
class EndDrawerToggle(activity: Activity, private val drawerLayout: DrawerLayout, toolbar: Toolbar,
                      openDrawerContentDescRes: Int, closeDrawerContentDescRes: Int) : DrawerLayout.DrawerListener {
    private val arrowDrawable: DrawerArrowDrawable = DrawerArrowDrawable(toolbar.context)
    private val toggleButton: AppCompatImageButton
    private val openDrawerContentDesc: String = activity.getString(openDrawerContentDescRes)
    private val closeDrawerContentDesc: String = activity.getString(closeDrawerContentDescRes)

    init {

        arrowDrawable.direction = DrawerArrowDrawable.ARROW_DIRECTION_END

        toggleButton = AppCompatImageButton(toolbar.context, null,
                R.attr.toolbarNavigationButtonStyle)
        toolbar.addView(toggleButton, Toolbar.LayoutParams(GravityCompat.END))
        toggleButton.setImageDrawable(arrowDrawable)
        toggleButton.setOnClickListener { toggle() }
    }

    fun syncState() {
        if (drawerLayout.isDrawerOpen(GravityCompat.END)) {
            setPosition(1f)
        } else {
            setPosition(0f)
        }
    }

    @Suppress("MemberVisibilityCanBePrivate")
    fun toggle() {
        if (drawerLayout.isDrawerOpen(GravityCompat.END)) {
            drawerLayout.closeDrawer(GravityCompat.END)
        } else {
            drawerLayout.openDrawer(GravityCompat.END)
        }
    }

    @Suppress("MemberVisibilityCanBePrivate")
    fun setPosition(position: Float) {
        if (position == 1f) {
            arrowDrawable.setVerticalMirror(true)
            toggleButton.contentDescription = closeDrawerContentDesc
        } else if (position == 0f) {
            arrowDrawable.setVerticalMirror(false)
            toggleButton.contentDescription = openDrawerContentDesc
        }
        arrowDrawable.progress = position
    }

    override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
        setPosition(Math.min(1f, Math.max(0f, slideOffset)))
    }

    override fun onDrawerOpened(drawerView: View) {
        setPosition(1f)
    }

    override fun onDrawerClosed(drawerView: View) {
        setPosition(0f)
    }

    override fun onDrawerStateChanged(newState: Int) {}
}