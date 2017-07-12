package com.hubrickchallenge.android.model

/**
 * Created by jcruz on 12.07.17.
 */
class Stats {

    var reactionStats: ReactionStats?=null
    var commentStats: CommentStats?=null

    constructor()
    constructor(reactionStats: ReactionStats?, commentStats: CommentStats?) {
        this.reactionStats = reactionStats
        this.commentStats = commentStats
    }

}