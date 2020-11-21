package com.paulacr.blogviewer.authors

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagingData
import com.paulacr.blogviewer.BaseViewModel
import com.paulacr.blogviewer.ViewState
import com.paulacr.data.common.logError
import com.paulacr.data.usecase.AuthorUseCase
import com.paulacr.domain.Author
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AuthorsViewModel @ViewModelInject constructor(private val useCase: AuthorUseCase) :
    BaseViewModel() {

    val authorsLiveData = MutableLiveData<ViewState<PagingData<Author>>>()

    fun getAuthors() {
        useCase.getAuthor()
            .doOnSubscribe {
                authorsLiveData.postValue(ViewState.Loading())
            }
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
