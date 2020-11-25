package com.paulacr.blogviewer

sealed class ViewState<T> {

    class Success<T>(val data: T) : ViewState<T>()

    class Failure<T>(throwable: Throwable) : ViewState<T>()

    class Loading<T> : ViewState<T>()
}
