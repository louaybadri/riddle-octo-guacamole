import android.content.Context
import android.os.Handler
import com.example.projet.ui.utils.ConnectivityUtils
import com.example.projet.viewmodel.PlantsViewModel


private val handler = Handler()
fun isNetworkAvailable(milliseconds: Long, function:()->Unit, context: Context,firstTime:Boolean=true) {

    val runnable = Runnable {
        if (ConnectivityUtils.isInternetAvailable(context)) {

            function()
        } else {
            ConnectivityUtils.handleNoInternetConnection(context,firstTime)
            handler.postDelayed({ isNetworkAvailable(milliseconds,function,context,false) }, milliseconds)

        }
    }
    runnable.run()

}
fun stopNetworkAvailabilityCheck() {
    handler.removeCallbacksAndMessages(null)
}