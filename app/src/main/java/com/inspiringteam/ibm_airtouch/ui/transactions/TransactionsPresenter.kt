package com.inspiringteam.ibm_airtouch.ui.transactions

import android.util.Log
import com.inspiringteam.ibm_airtouch.data.source.IbmRepository
import com.inspiringteam.ibm_airtouch.di.scopes.ActivityScoped
import com.inspiringteam.ibm_airtouch.mvp.BasePresenter
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

@ActivityScoped
class TransactionsPresenter @Inject constructor(val repository: IbmRepository) :
        BasePresenter<TransactionsContract.View>(),
        TransactionsContract.Presenter {
    val disposable: CompositeDisposable = CompositeDisposable()

    fun getProducts(){
        disposable.add(

                repository.getProducts().subscribe(
                // onNext
                {
                    list -> view?.showProducts(removeDuplicates(ArrayList(list)))
                },
                // onError
                {
                    error -> Log.d("ERROR PARSING DATA ", error.toString())

                }))
    }

    private fun removeDuplicates(list: ArrayList<String>): List<String> {
        val set = HashSet(list)
        list.clear()
        list.addAll(set)
        return list
    }
}