package com.paulacr.blogviewer.feature.comments

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.paulacr.blogviewer.BaseViewModel
import com.paulacr.blogviewer.ViewState
import com.paulacr.data.common.logError
import com.paulacr.domain.usecase.PostsUseCase
import com.paulacr.domain.Comment
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CommentsViewModel @ViewModelInject constructor(private val useCase: PostsUseCase) :
    BaseViewModel() {

    val postsLiveData = MutableLiveData<ViewState<List<Comment>>>()

    fun getCommentsByUsername(postId: Int) {
        useCase.getCommentsByPostId(postId)
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
