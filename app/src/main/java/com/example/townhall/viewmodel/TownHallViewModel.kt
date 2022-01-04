package com.example.townhall.viewmodel

import androidx.lifecycle.*
import com.example.townhall.model.database.TownHallRepository
import com.example.townhall.model.entities.TownHall
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class TownHallViewModel(private val repository: TownHallRepository):ViewModel() {
    fun insert(dish:TownHall) = viewModelScope.launch {
        repository.insertTownHallData(dish)
    }
    val allDishesList: LiveData<List<TownHall>> =repository.allDishesList.asLiveData()

    fun update(dish:TownHall) = viewModelScope.launch {
        repository.updateTownHallData(dish)
    }

    val favoriteDishes: LiveData<List<TownHall>> =repository.favoriteDishes.asLiveData()

    fun delete(dish:TownHall) = viewModelScope.launch {
        repository.deleteTownHallData(dish)
    }

    fun getFilteredList(value:String) : LiveData<List<TownHall>> =repository.filteredListDishes(value).asLiveData()

}
class TownHallViewModelFactory(private val repository: TownHallRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TownHallViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return TownHallViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}