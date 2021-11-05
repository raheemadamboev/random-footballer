package xyz.teamgravity.randomfootballer.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import xyz.teamgravity.randomfootballer.common.Resource
import xyz.teamgravity.randomfootballer.data.remote.dto.toFootballer
import xyz.teamgravity.randomfootballer.domain.model.FootballerModel
import xyz.teamgravity.randomfootballer.domain.repository.FootballerRepository
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: FootballerRepository
) : ViewModel() {

    private val _state = mutableStateOf(FootballerState())
    val state: State<FootballerState> = _state

    init {
        getRandomFootballer().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = FootballerState(footballer = result.data, loading = false, error = null)
                }

                is Resource.Loading -> {
                    _state.value = FootballerState(footballer = null, loading = true, error = null)
                }

                is Resource.Error -> {
                    _state.value = FootballerState(footballer = null, loading = false, error = result.message)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getRandomFootballer(): Flow<Resource<FootballerModel>> = flow {
        try {
            emit(Resource.Loading())
            val footballer = repository.getRandomFootballer().toFootballer()
            emit(Resource.Success(footballer))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.message ?: "Error"))
        }
    }

}