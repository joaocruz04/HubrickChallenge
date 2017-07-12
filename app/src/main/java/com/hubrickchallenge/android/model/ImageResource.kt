package com.hubrickchallenge.android.model

/**
 * Created by joaocruz04 on 10/07/2017.
 */
class ImageResource {
    var url: String?=null
    var mimeType: String?=null

    constructor(){}

    constructor(url: String?, mimeType: String?) {
        this.url = url
        this.mimeType= mimeType
    }
}