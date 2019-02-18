package com.inspiringteam.ibm_airtouch.mvp

abstract class BasePresenter<V> {
    protected var view: V? = null

    fun subscribe(view: V) {
        this.view = view
    }

    fun unsubscribe() {
        // drop the view reference
        view = null
    }
}