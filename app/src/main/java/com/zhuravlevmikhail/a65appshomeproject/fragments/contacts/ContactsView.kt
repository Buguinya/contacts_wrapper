package com.zhuravlevmikhail.a65appshomeproject.fragments.contacts

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface ContactsView : MvpView {
    fun onContactsAccessGranted()
    fun openDetailedContactPage(contactId: Long)
    fun onContactsReceived(contacts: ArrayList<ContactGeneral>)
    fun askContactsPermisson()
    fun showError(error : String)
    fun showError(error: Int)
}