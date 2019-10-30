package com.zhuravlevmikhail.a65appshomeproject.domain.map

import com.google.android.gms.maps.model.LatLng
import io.reactivex.Single

interface MapInteractor {

    fun getCurrentUserLocation() : Single<LatLng>
}