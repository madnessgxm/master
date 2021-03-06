package com.mvvmfactory

import android.databinding.BaseObservable
import android.databinding.Bindable

class UserModel :BaseObservable {
    constructor()

    var username:String=""
        @Bindable
        get() = field
        @Bindable
    set(value) {
        field =value
        notifyPropertyChanged(com.emvl3kt.BR.username)
    }

    var userpwd:String=""
    get() = field
    set(value) {field = value}

}