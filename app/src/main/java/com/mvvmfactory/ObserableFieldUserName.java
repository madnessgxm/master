package com.mvvmfactory;

import android.databinding.ObservableField;
import android.databinding.ObservableInt;

public class ObserableFieldUserName {
    public ObserableFieldUserName()
    {
        username=new ObservableField<String>();
        userage = new ObservableInt();
    }
    public ObservableField<String> getUsername() {
        return username;
    }

    public void setUsername(ObservableField<String> username) {
        this.username = username;
    }

    public ObservableInt getUserage() {
        return userage;
    }

    public void setUserage(ObservableInt userage) {
        this.userage = userage;
    }

    private ObservableField<String> username ;
    private ObservableInt userage;


}
