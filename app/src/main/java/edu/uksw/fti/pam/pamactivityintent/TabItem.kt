package edu.uksw.fti.pam.pamactivityintent

import androidx.compose.runtime.Composable


typealias ComposableFun = @Composable () -> Unit
sealed class TabItem(var icon:Int, var title:String, var screen:ComposableFun) {
    object Home : TabItem(R.drawable.ic_chat_24,"Chat", { HomeScreen()})
    object Status : TabItem(R.drawable.ic_person_24,"Status", { StatusScreen()})
    object Call : TabItem(R.drawable.ic_call_24,"Call", { CallScreen()})
}
