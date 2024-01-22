package com.example.projet.ui.utils
import android.app.AlertDialog
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.widget.Toast

object ConnectivityUtils {

    fun isInternetAvailable(context: Context): Boolean {
        var result: Boolean
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkCapabilities = connectivityManager.activeNetwork ?: return false
        val actNw =
            connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
        result = when {
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }

        return result
    }
    fun handleNoInternetConnection(context: Context,firstTime:Boolean) {
        // Handle the case where there is no internet connection
        // This method can be customized based on your app's requirements
        if(firstTime) {
            Toast.makeText(context, "No Internet Connection", Toast.LENGTH_SHORT).show()

            val builder = AlertDialog.Builder(context)
            builder.setTitle("No Internet Connection")
                .setMessage("Please check your internet connection and try again.")
                .setPositiveButton("OK") { _, _ -> /* Handle OK button click if needed */ }
                .show()
        }
    }
}
