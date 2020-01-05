package com.rahmanarifofficial.validationpage

import android.app.Dialog
import android.content.Context
import android.view.View
import android.view.Window
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar

class Utils {
    companion object {
        fun showSnackbar(view: View, message: String) {
            Snackbar.make(view, message, Snackbar.LENGTH_LONG).show()
        }

        private var loadingDialog: Dialog? = null
        fun showLoadingDialog(context: Context) {
            loadingDialog = Dialog(context)
            loadingDialog!!.window
            loadingDialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
            loadingDialog!!.setContentView(R.layout.dialog_loading)
            loadingDialog!!.setCancelable(true)
            loadingDialog!!.setCanceledOnTouchOutside(true)
            val loadingTxt = loadingDialog!!.findViewById<TextView>(R.id.loadingTxt)

            loadingDialog!!.show()
        }

        fun hideLoadingDialog() {
            if (loadingDialog != null && loadingDialog!!.isShowing)
                loadingDialog!!.dismiss()
        }

    }
}