package com.clover.harish.utli

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

class AppBindingAdapters {

    companion object {
        @JvmStatic @BindingAdapter("avatar")
        fun setAvatar(view: ImageView, url: String) {
            Glide.with(view.context)
                .load(url)
                .into(view)
        }
    }


}