package com.example.fixneat.Viwes.ui.orders;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import com.example.fixneat.Model.Address;

import java.util.ArrayList;

public class HomeViewModel extends ViewModel {

    private final MutableLiveData<ArrayList<Address>> mList;

    public HomeViewModel() {
        mList = new MutableLiveData<>();
        //mText.setValue("This is slideshow fragment");

        ArrayList arrayList = new ArrayList<>();
        arrayList.add( new Address().setCity("0").setStreet("0").setHouseNum(1));
        arrayList.add( new Address().setCity("1").setStreet("1").setHouseNum(1));
        arrayList.add( new Address().setCity("2").setStreet("2").setHouseNum(1));
        arrayList.add( new Address().setCity("3").setStreet("3").setHouseNum(1));
        arrayList.add( new Address().setCity("4").setStreet("4").setHouseNum(1));
        arrayList.add( new Address().setCity("5").setStreet("5").setHouseNum(1));
        arrayList.add( new Address().setCity("6").setStreet("6").setHouseNum(1));
        arrayList.add( new Address().setCity("7").setStreet("7").setHouseNum(1));
        arrayList.add( new Address().setCity("8").setStreet("8").setHouseNum(1));
        arrayList.add( new Address().setCity("9").setStreet("9").setHouseNum(1));
        arrayList.add( new Address().setCity("10").setStreet("10").setHouseNum(1));
        arrayList.add( new Address().setCity("11").setStreet("11").setHouseNum(1));

        mList.setValue(arrayList);

    }

    public LiveData<ArrayList<Address>> getList() {
        return mList;
    }


}