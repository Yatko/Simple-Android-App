/*
 * project: Simple App, Android
 * app.h7.org/simple/Android
 * Copyright © 2018 H7 (h7.org).
 *
 * created by yatko.com
 */

package org.h7.simple.binding

import android.databinding.BindingAdapter
import android.graphics.drawable.Drawable
import android.text.TextUtils
import android.widget.ImageView
import com.squareup.picasso.Picasso
import com.squareup.picasso.RequestCreator
import org.h7.simple.widget.CircleTransformation


/**
 * Created by Alexey Rogovoy (lexapublic@gmail.com) on 29.06.2018.
 */
@BindingAdapter(value = ["logoUrl","borderWidth","borderColor","placeholder"], requireAll = false)
fun loadImage(imageView: ImageView, url: String?,borderWidth:Float?,borderColor:Int?,placeholder: Drawable?) {
    if (!TextUtils.isEmpty(url)) {
        val creator:RequestCreator =  Picasso.get().load(url)

        val circle: CircleTransformation = if (borderWidth!=null&&borderColor!=null) {
            CircleTransformation(borderColor,borderWidth)
        } else {
            CircleTransformation()
        }

        creator.centerCrop()
                .fit()
                .transform(circle)

        if (placeholder!=null) {
            creator.error(placeholder)
        }

        creator.into(imageView)
    } else if (placeholder!=null) {
        imageView.setImageDrawable(placeholder)
    }
}