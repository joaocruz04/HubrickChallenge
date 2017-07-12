package com.hubrickchallenge.android.model

/**
 * Created by jcruz on 12.07.17.
 */
class Payload {
    var plainTitle: String?=null
    var plainContentPreview: String?=null
    var path: String?=null
    var headlineImage: ImageResource?=null
    var stats: Stats?=null

    constructor()
    constructor(plainTitle: String?, plainContentPreview: String?, path: String?, headlineImage: ImageResource?, stats: Stats?) {
        this.plainTitle = plainTitle
        this.plainContentPreview = plainContentPreview
        this.path = path
        this.headlineImage = headlineImage
        this.stats = stats
    }


}