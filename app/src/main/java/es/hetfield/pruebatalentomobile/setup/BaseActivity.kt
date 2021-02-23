package es.hetfield.pruebatalentomobile.setup

import android.app.Dialog
import android.content.Context
import android.content.pm.PackageManager
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import es.hetfield.pruebatalentomobile.R


abstract class BaseActivity : AppCompatActivity() {
    var progressDialog: Dialog? = null

    fun showMessage(message: String, view: View = this.findViewById(android.R.id.content)) {
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show()
    }

    fun showError(error: String, v: View = this.findViewById(android.R.id.content)) {
        with(Snackbar.make(v, error, Snackbar.LENGTH_SHORT)) {
            view.setBackgroundColor(ContextCompat.getColor(v.context, R.color.colorError))
            show()
        }
    }

    fun hideKeyboard() {
        with(getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager) {
            this.hideSoftInputFromWindow(
                currentFocus?.windowToken,
                InputMethodManager.SHOW_FORCED
            )
        }
    }

    fun setStatusBarColor() {
        val window = this.window
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this, R.color.colorPrimaryDark)
    }

    fun checkAndRequestPermission(permission: String, codeRequest: Int): Boolean {
        if(ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
            if(ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
                return true
            } else {
                ActivityCompat.requestPermissions(this, arrayOf(permission), codeRequest)
            }
        } else {
            return true
        }
        return false
    }

    fun showInfoDialog (title: String, message: String, onDismiss: (() -> Unit)? = null) {
        MaterialAlertDialogBuilder(this)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(R.string.ok) { _, _ ->
                onDismiss?.invoke()
            }.show()
    }

    fun showConfirmDialog (title: String, message: String, onDismiss: (() -> Unit)? = null) {
        MaterialAlertDialogBuilder(this)
            .setTitle(title)
            .setMessage(message)
            .setNegativeButton(R.string.no, null)
            .setPositiveButton(R.string.yes) { _, _ ->
                onDismiss?.invoke()
            }.show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> super.onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}
