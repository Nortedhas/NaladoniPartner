package com.ageone.naladonipartner.Modules.Auth

import com.ageone.naladonipartner.External.Interfaces.InterfaceModel
import com.ageone.naladonipartner.External.Interfaces.InterfaceViewModel
import com.ageone.naladonipartner.External.Libraries.Alert.alertManager
import com.ageone.naladonipartner.External.Libraries.Alert.single
import timber.log.Timber

class AuthViewModel : InterfaceViewModel {
    var model = AuthModel()

    enum class EventType {
        OnNextPressed
    }

    /*var realmData = listOf<>()
    fun loadRealmData() {
        realmData = utils.realm.product.getAllObjects()//TODO: change type data!
    }*/

    fun initialize(recievedModel: InterfaceModel, completion: () -> (Unit)) {
        if (recievedModel is AuthModel) {
            model = recievedModel
            completion.invoke()
        }
    }


    fun validate(competition: () -> Unit){
        if(model.code.length < 10){
                alertManager.single("Ошибка", "Неверный код", null, "Понятно"){_, _ ->}
                    return
        }
        competition.invoke()
    }
}

class AuthModel : InterfaceModel {

    var code = ""

}
