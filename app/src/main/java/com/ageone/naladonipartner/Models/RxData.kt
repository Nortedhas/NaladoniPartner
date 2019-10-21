package com.ageone.naladonipartner.Models

import com.ageone.naladonipartner.External.RxBus.RxBus
import com.ageone.naladonipartner.External.RxBus.RxEvent
import com.ageone.naladonipartner.SCAG.Stock
import timber.log.Timber
import kotlin.properties.Delegates


class RxData {
    var selectedFilter: Int by Delegates.observable(-1) { property, oldValue, newValue ->
        Timber.i("Change filter - New value: $newValue")
        RxBus.publish(RxEvent.EventChangeFilterIndex())
    }

    var currentStock: Stock? = null
}

