package com.hubrickchallenge.android.feed

import android.content.Context
import android.support.v7.widget.LinearLayoutManager



/**
 * Created by jcruz on 12.07.17.
 */
class CustomLinearLayoutManager(context: Context?) : LinearLayoutManager(context) {

    override fun supportsPredictiveItemAnimations(): Boolean {
        return true
    }


}