package es.hetfield.pruebatalentomobile.setup


import android.content.pm.PackageManager
import android.content.res.Configuration
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import es.hetfield.pruebatalentomobile.R
import es.hetfield.pruebatalentomobile.setup.network.Repository
import es.hetfield.pruebatalentomobile.setup.utils.extensions.hideProgressDialog
import es.hetfield.pruebatalentomobile.setup.utils.extensions.isEmail
import es.hetfield.pruebatalentomobile.setup.utils.extensions.isPhoneNumber
import es.hetfield.pruebatalentomobile.setup.utils.extensions.showProgressDialog
import org.koin.android.ext.android.inject


abstract class BaseFragment : Fragment() {

    protected val repository by inject<Repository>()

    var fragmentTitle: String? = null
    open val navigationBackEnabled: Boolean = false
    val prefs: Prefs by inject()


    fun checkAndRequestPermission(permission: String, codeRequest: Int): Boolean {
        activity?.let {
            if (ContextCompat.checkSelfPermission(
                    it,
                    permission
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(it, permission)) {
                    return true
                } else {
                    ActivityCompat.requestPermissions(it, arrayOf(permission), codeRequest)
                }
            } else {
                return true
            }
            return false
        }
        return false
    }

    fun showMessage(message: String?) {
        activity?.findViewById<View>(android.R.id.content)?.let {
            with(Snackbar.make(it, message ?: "", Snackbar.LENGTH_LONG)) {
                view.setBackgroundResource(R.drawable.bg_message)
                val tv = view.findViewById(R.id.snackbar_text) as TextView
                tv.setTextColor(ContextCompat.getColor(context, R.color.colorOnError))
                show()
            }
        }
    }

    protected fun showInfoDialog (title: String, message: String, onDismiss: (() -> Unit)? = null) {
        val baseActivity = activity as BaseActivity
        baseActivity.showInfoDialog(title, message, onDismiss)
    }

    protected fun showConfirmDialog (title: String, message: String, onDismiss: (() -> Unit)? = null) {
        val baseActivity = activity as BaseActivity
        baseActivity.showConfirmDialog(title, message, onDismiss)
    }

    fun showMessageAction(
        message: String,
        view: View,
        actionTitle: String,
        onClickAction: View.OnClickListener
    ) {
        Snackbar.make(view, message, Snackbar.LENGTH_LONG)
            .setAction(actionTitle, onClickAction)
            .show()
    }

    fun showError(error: String) {
        activity?.findViewById<View>(android.R.id.content)?.let {
            with(Snackbar.make(it, error, Snackbar.LENGTH_LONG)) {
                view.setBackgroundResource(R.drawable.bg_error_message)
                val tv = view.findViewById(R.id.snackbar_text) as TextView
                tv.setTextColor(ContextCompat.getColor(context, R.color.colorOnError))
                show()
            }
        }
    }

    fun showProgressDialog() {
        if (activity != null) {
            (activity as BaseActivity).showProgressDialog()
        }
    }

    fun hideProgressDialog() {
        if (activity != null) {
            (activity as BaseActivity).hideProgressDialog()
        }
    }

    fun hideKeyboard() {
        if (activity != null) {
            (activity as BaseActivity).hideKeyboard()
        }
    }

    fun isCurrentNightMode(): Boolean {
        return when (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
            Configuration.UI_MODE_NIGHT_NO -> {
                true
            } // Night mode is not active, we're using the light theme
            Configuration.UI_MODE_NIGHT_YES -> {
                false
            } // Night mode is active, we're using dark theme
            else -> false
        }
    }

    fun addTextWatcherEmail(inputLayout: TextInputLayout, required: Boolean = false) : TextWatcher {
        return object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s.toString().isEmail()) {
                    inputLayout.isErrorEnabled = false
                }
                if(s.toString().isEmpty()) {
                    inputLayout.isErrorEnabled = required
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                if (s.toString().isEmail()) {
                    inputLayout.isErrorEnabled = false
                }
                if(s.toString().isEmpty()) {
                    inputLayout.isErrorEnabled = required
                }
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (!s.toString().isEmail()) {
                    inputLayout.isErrorEnabled = true
                    inputLayout.error = getString(R.string.error_email)
                }
                if(s.toString().isEmpty()) {
                    inputLayout.isErrorEnabled = required
                }
            }
        }
    }

    fun addTextWatcherPhone(inputLayout: TextInputLayout, required: Boolean = false) : TextWatcher {
        return object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (String.format("+34%s", s.toString()).isPhoneNumber()) {
                    inputLayout.isErrorEnabled = false
                }
                if(s.toString().isEmpty()) {
                    inputLayout.isErrorEnabled = required
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                if (String.format("+34%s", s.toString()).isPhoneNumber()) {
                    inputLayout.isErrorEnabled = false
                }
                if(s.toString().isEmpty()) {
                    inputLayout.isErrorEnabled = required
                }
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (!String.format("+34%s", s.toString()).isPhoneNumber()) {
                    inputLayout.isErrorEnabled = true
                    inputLayout.error = getString(R.string.error_phone)
                }
                if(s.toString().isEmpty()) {
                    inputLayout.isErrorEnabled = required
                }
            }
        }
    }


    fun addTextWatcherRequired(inputLayout: TextInputLayout) : TextWatcher {
        return object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                inputLayout.isErrorEnabled = s.toString().isEmpty()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                inputLayout.isErrorEnabled = s.toString().isEmpty()
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                inputLayout.isErrorEnabled = s.toString().isEmpty()
            }
        }
    }

    fun checkInputs(inputsArray : ArrayList<TextInputLayout>) : Boolean {
        inputsArray.forEach {
            if (it.editText?.text.isNullOrEmpty()) {
                showError(getString(R.string.error_empty))
                return false
            }
            if(it.isErrorEnabled) {
                showError(it.error.toString())
                return false
            }
        }
        return true
    }
}
