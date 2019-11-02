package com.zhuravlevmikhail.a65appshomeproject.diContainer.components

import com.zhuravlevmikhail.a65appshomeproject.diContainer.modules.presentation.PresentersModule
import com.zhuravlevmikhail.a65appshomeproject.diContainer.scopes.FragmentScope
import com.zhuravlevmikhail.a65appshomeproject.fragments.detail.innerFragments.MapFragment
import dagger.Subcomponent

@FragmentScope
@Subcomponent( modules = [
    PresentersModule::class
])
interface MapComponent {
    fun inject(mapFragment: MapFragment)
}