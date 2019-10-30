package com.zhuravlevmikhail.a65appshomeproject.fragments.map

import com.google.android.gms.maps.model.LatLng
import com.zhuravlevmikhail.a65appshomeproject.domain.map.MapInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class MapPresenter
    @Inject constructor(private val mapInteractor: MapInteractor) : MvpPresenter<MapView>() {

    private val compositeDisposable = CompositeDisposable()

    fun onMapClicked(latLng: LatLng) {
        viewState.addMarker(latLng)
    }

    fun noLocation() {
        getCurrentUserLocation()
    }

    private fun getCurrentUserLocation() {
        mapInteractor.getCurrentUserLocation()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { latlng -> viewState.moveCameraToPosition(latlng) },
                { throwable -> viewState.showError(throwable.localizedMessage) }
            ).addTo(compositeDisposable)
    }
}