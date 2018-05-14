package co.basavraj

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import co.basavraj.Interface.CommonResponseListener
import co.basavraj.Network.NetworkAccessor
import co.basavraj.Network.WebUrls
import com.google.gson.JsonElement
import org.json.JSONObject

class MainActivity : AppCompatActivity(),CommonResponseListener {
    private val TAG = "MainActivity"
    private lateinit var networkAccessor: NetworkAccessor

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getNetWorkData()

    }
    private fun getNetWorkData() {
        networkAccessor = NetworkAccessor().Instance()
        networkAccessor.CommonNetworkCall(this, JSONObject(),this, WebUrls.BASE_URL, WebUrls.GET_MOVIES )
    }
    override fun onSuccessResult(obj: JsonElement)
    {
        //parse Data
        Log.d(TAG,"Result :-"+obj.toString())
    }
    override fun onErrorResult(msg: String) {
        Log.d(TAG,"Error:-"+msg)
    }
}
