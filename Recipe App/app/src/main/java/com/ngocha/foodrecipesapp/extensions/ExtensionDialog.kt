package com.ngocha.foodrecipesapp.extensions

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface

fun Context.alert(
    dialogBuilder: AlertDialog.Builder.() -> Unit
) {
    AlertDialog.Builder(this).apply {
        setCancelable(false)
        dialogBuilder()
        create()
        show()
    }
}

fun AlertDialog.Builder.negativeButton(
    text: String = "No",
    handleClick: (dialogInterface: DialogInterface) -> Unit = { it.dismiss() }
) {
    this.setNegativeButton(text) { dialogInterface, _ -> handleClick(dialogInterface) }
}

fun AlertDialog.Builder.positiveButton(
    text: String = "Yes",
    handleClick: (dialogInterface: DialogInterface) -> Unit = { it.dismiss() }
) {
    this.setPositiveButton(text) { dialogInterface, _ -> handleClick(dialogInterface) }
}

fun AlertDialog.Builder.neutralButton(
    text: String = "OK",
    handleClick: (dialogInterface: DialogInterface) -> Unit = { it.dismiss() }
) {
    this.setNeutralButton(text) { dialogInterface, _ -> handleClick(dialogInterface) }
}
