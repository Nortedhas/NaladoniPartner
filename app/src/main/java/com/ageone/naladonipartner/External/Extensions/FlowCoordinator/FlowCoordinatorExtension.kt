package com.ageone.naladonipartner.External.Extensions.FlowCoordinator

import com.ageone.naladonipartner.Application.Coordinator.Flow.FlowCoordinator
import com.ageone.naladonipartner.Application.Coordinator.Router.TabBar.Stack
import com.ageone.naladonipartner.Application.api
import com.ageone.naladonipartner.Models.User.user
import io.realm.Realm
import timber.log.Timber

//TODO: replace in base

fun FlowCoordinator.logout() {
    //correct data
    user.isAuthorized = false
    //api.cashTime = 0

    //clear stack flows
    Stack.flows.forEach { flow ->
        Timber.i("de init flow")
        flow.onFinish?.invoke()
    }

    //clear realm
    Realm.getDefaultInstance().executeTransaction { realm ->
        realm.deleteAll()
    }

    //restart flow
    start()
}