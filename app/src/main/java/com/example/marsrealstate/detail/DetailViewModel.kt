package com.example.marsrealstate.detail

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.marsrealstate.R
import com.example.marsrealstate.network.MarsProperty

class DetailViewModel(marsProperty: MarsProperty, app: Application): ViewModel() {

    private val _selectedProperty = MutableLiveData<MarsProperty>()
    val selectedProperty: LiveData<MarsProperty>
        get() = _selectedProperty

    init{
        _selectedProperty.value = marsProperty
    }

    val displayPropertyPrice = Transformations.map(selectedProperty){
        app.applicationContext.getString(
                when(it.isRental){
                    true -> R.string.display_price_monthly_rental
                    false -> R.string.display_price
                }, it.price
        )
    }

    val displayPropertyType = Transformations.map(selectedProperty){
        app.applicationContext.getString(R.string.display_type,
            app.applicationContext.getString(
                    when(it.isRental){
                        true -> R.string.type_rent
                        false -> R.string.type_sale
                    }
            )
                )
    }
}