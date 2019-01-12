/*
 * project: Simple App, Android
 * app.h7.org/simple/Android
 * Copyright © 2018 H7 (h7.org).
 *
 * created by yatko.com
 */

package org.h7.simple.config.menu

import android.content.Context
import org.h7.simple.BuildConfig
import org.h7.simple.R
import org.h7.simple.data.menu.Footer
import org.h7.simple.data.menu.Header
import org.h7.simple.data.menu.MenuItem

/**
 * Created by Alexey Rogovoy (lexapublic@gmail.com) on 29.06.2018.
 */
class NavViewModel: INavViewModel() {
    private var footer: Footer? = null
    private var header: Header? = null
    override fun getMenuFooter(context: Context): Footer {
        if (footer==null){
            footer = Footer(context.getString(R.string.nav_footer_text_template,context.getString(R.string.app_name)),context.getString(R.string.nav_footer_subtitle))
        }
        return footer!!
    }

    override fun getMenuHeader(context: Context):Header{
        if (header==null) {
            header = Header("https://yatko.com/logo.png", context.getString(R.string.nav_header_title_template, context.getString(R.string.app_name)), context.getString(R.string.nav_header_subtitle_template, BuildConfig.VERSION_NAME))
        }
        return header!!
    }

    override fun getMenuItems(): List<MenuItem> {
        return listOf(
                MenuItem(R.id.menu_social_media,R.drawable.ic_menu_social_media,R.string.menu_social_media_title,R.string.menu_social_media_description),
                MenuItem(R.id.menu_contact_us,R.drawable.ic_menu_contact_us,R.string.menu_contact_us_title,R.string.menu_contact_us_description),
                MenuItem(R.id.menu_support,R.drawable.ic_menu_support,R.string.menu_support_title,R.string.menu_support_description),
                MenuItem(R.id.menu_website,R.drawable.ic_menu_website,R.string.menu_website_title,R.string.menu_website_description),
                MenuItem(R.id.menu_rate_us,R.drawable.ic_menu_rate_us,R.string.menu_rate_us_title,R.string.menu_rate_us_description),
                MenuItem(R.id.menu_settings,R.drawable.ic_menu_settings,R.string.menu_settings_title,R.string.menu_settings_description)
        )
    }
}