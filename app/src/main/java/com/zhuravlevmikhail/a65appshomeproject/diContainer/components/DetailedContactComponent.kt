package com.zhuravlevmikhail.a65appshomeproject.diContainer.components

import com.zhuravlevmikhail.a65appshomeproject.diContainer.modules.presentation.PresentersModule
import com.zhuravlevmikhail.a65appshomeproject.diContainer.scopes.FragmentScope
import com.zhuravlevmikhail.a65appshomeproject.fragments.detail.DetailedFragment
import dagger.Subcomponent

@FragmentScope
@Subcomponent( modules = [PresentersModule::class])
interface DetailedContactComponent {
    fun inject(detailedFragment: DetailedFragment)
}