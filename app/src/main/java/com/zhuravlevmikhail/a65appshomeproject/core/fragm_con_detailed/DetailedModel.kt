package com.zhuravlevmikhail.a65appshomeproject.core.fragm_con_detailed

import android.graphics.Bitmap

const val FRAGMENT_DATA_KEY_CONTACT_ID = "FRAGMENT_DATA_KEY_CONTACT_ID"
class DetailedModel : DetailedContract.TModel {

    data class ContactDetailed (
        val name : String,
        val phone: String,
        var email : String,
        val image : Bitmap? = null
    )
}