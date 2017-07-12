package com.hubrickchallenge.android.model

/**
 * Created by jcruz on 12.07.17.
 */
class ReactionStats {

    var counts: ReactionCount?= null

    constructor()

    constructor(counts: ReactionCount?) {
        this.counts = counts
    }

}