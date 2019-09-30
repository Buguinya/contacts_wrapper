package com.zhuravlevmikhail.a65appshomeproject.fragments.detail

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface DetailedView : MvpView {
    fun onReceivedContact(contact: ContactDetailed)
    fun showError(error : String)
    fun showError(error: Int)
    fun requestContactsPermisson()
}