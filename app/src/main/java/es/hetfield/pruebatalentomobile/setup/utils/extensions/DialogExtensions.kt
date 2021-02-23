package es.hetfield.pruebatalentomobile.setup.utils.extensions

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import es.hetfield.pruebatalentomobile.R
import es.hetfield.pruebatalentomobile.setup.*

fun BaseActivity.showProgressDialog() {
    hideProgressDialog()
    Dialog(this).apply {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setCancelable(false)
        setContentView(R.layout.dialog_progress)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        show()
        this@showProgressDialog.progressDialog = this
    }
}

fun BaseActivity.hideProgressDialog() {
    this.progressDialog?.dismiss()
}

