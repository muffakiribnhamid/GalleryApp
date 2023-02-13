package com.example.galleryapp

class Image {
    var ImageName : String? = null
    var ImagePath : String? = null

    constructor(ImageName: String?, ImagePath: String?) {
        this.ImageName = ImageName
        this.ImagePath = ImagePath
    }
    constructor(){}

}