package com.paulacr.blogviewer.details

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.paulacr.blogviewer.BaseViewModel
import com.paulacr.blogviewer.ViewState
import com.paulacr.data.common.logError
import com.paulacr.data.usecase.AuthorUseCase
import com.paulacr.domain.Post
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class PostsViewModel @ViewModelInject constructor(private val useCase: AuthorUseCase) :
    BaseViewModel() {

    val postsLiveData = MutableLiveData<ViewState<List<Post>>>()

    fun getPostsByAuthorId(authorId: Int) {
        useCase.getPostsByAuthorId(authorId)
            .doOnSubscribe {
                postsLiveData.postValue(ViewState.Loading())
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                postsLiveData.postValue(ViewState.Success(it))
            }, {
                logError("Authors error", it)
                postsLiveData.postValue(ViewState.Failure(it))
            }).addToDisposables()
    }
}
