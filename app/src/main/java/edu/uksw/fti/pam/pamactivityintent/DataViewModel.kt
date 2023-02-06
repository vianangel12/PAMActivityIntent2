package edu.uksw.fti.pam.pamactivityintent

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class DataViewModel: ViewModel (){
    var state by mutableStateOf(DataViewModelState())

    fun onAction(userAction: UserAction) {
        when(userAction) {
            UserAction.CloseIconClicked -> {
                state = state.copy(
                    isSearchBarVisible = false
                )
            }
            UserAction.SearchIconClicked -> {
                state = state.copy(
                    isSearchBarVisible = true
                )
            }
        }
    }
}

sealed class UserAction {
    object SearchIconClicked: UserAction()
    object CloseIconClicked: UserAction()
}

data class DataViewModelState(
    val isSearchBarVisible: Boolean = false
)