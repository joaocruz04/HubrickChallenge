package com.hubrickchallenge.android.model

/**
 * Created by joaocruz04 on 10/07/2017.
 */

class Author {
    var name: String?=null
    var displayName: String?=null
    var avatarImage: ImageResource?=null

    constructor(){}

    constructor(name: String?, displayName: String?, avatarImage: ImageResource?) {
        this.name = name
        this.displayName = displayName
        this.avatarImage = avatarImage
    }
}