package org.h7.simple.widget

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.text.TextUtils

/**
 * Created by ruslan on 12/01/2019.
 */

fun openUrl(context: Context?, url: String?) {
    if (TextUtils.isEmpty(url) || context == null) {
        return
    }

    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))

    if (intent.resolveActivity(context.packageManager) != null) {
        context.startActivity(intent)
    }
}
