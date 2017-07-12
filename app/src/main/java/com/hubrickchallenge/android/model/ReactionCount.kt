package com.hubrickchallenge.android.model

/**
 * Created by jcruz on 12.07.17.
 */
class ReactionCount {
    var LIKE: Int=0
    var SHARE: Int=0


    constructor()
    constructor(LIKE: Int, SHARE: Int) {
        this.LIKE = LIKE
        this.SHARE = SHARE
    }


}