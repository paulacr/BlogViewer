package com.paulacr.blogviewer.feature.authors

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagingData
import com.paulacr.blogviewer.BaseViewModel
import com.paulacr.blogviewer.ViewState
import com.paulacr.data.common.logError
import com.paulacr.domain.Author
import com.paulacr.domain.usecase.PostsUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AuthorsListViewModel @ViewModelInject constructor(private val useCase: PostsUseCase) :
    BaseViewModel() {

    val authorsLiveData = MutableLiveData<ViewState<PagingData<Author>>>()

    fun getAuthors() {
        authorsLiveData.postValue(ViewState.Loading())
        useCase.getAuthor()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                authorsLiveData.postValue(ViewState.Success(it))
            }, {
                logError("Authors error", it)
                authorsLiveData.postValue(ViewState.Failure(it))
            }).addToDisposables()
    }
}
