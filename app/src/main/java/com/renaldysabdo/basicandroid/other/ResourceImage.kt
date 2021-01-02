package com.renaldysabdo.basicandroid.other

sealed class ResourceImage <T>(val status : T?){
    class Camera<T>(status : T) : ResourceImage<T>(status)
    class Gallery<T>(status: T) : ResourceImage<T>(status)
    class Empty<T>() : ResourceImage<T>(null)
}

enum class StatusImage {
    CAMERA,
    GALLERY,
    EMPTY
}