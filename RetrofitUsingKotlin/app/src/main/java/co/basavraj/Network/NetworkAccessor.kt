package co.basavraj.Network

import android.content.Context
import android.util.Log
import co.basavraj.Interface.CommonResponseListener

import com.google.gson.Gson
import com.google.gson.JsonElement
import okhttp3.OkHttpClient
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import com.google.gson.GsonBuilder



class NetworkAccessor {

    private val TAG = "NetworkAccessor"
    private var instance: NetworkAccessor? = null
    private var gson = Gson()
    fun Instance(): NetworkAccessor
    {
        if (instance == null) {
            instance = NetworkAccessor()
        }
        return instance as NetworkAccessor
    }

    fun CommonNetworkCall(listener : CommonResponseListener, data :JSONObject, context: Context, baseUrl:String, fromUrl :String )
     {
         val gson = GsonBuilder()
                 .setLenient()
                 .create()

        // Retrofit Client
        val okHttpClient = OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(40, TimeUnit.SECONDS)
                .build()

        val retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()


         // prepare call in Retrofit 2.0
         val retroUrlApi = retrofit.create(RetrofitApi::class.java)

         //Prepare empty
         var call: Call<JsonElement>? = null

         //Call And Convert
         call = retroUrlApi.getMovies()


         call.enqueue(object : Callback<JsonElement>
         {
             override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>)
             {
                 if (response.body() != null)
                 {
                     listener.onSuccessResult(response.body()!!)
                 }
             }
             override fun onFailure(call: Call<JsonElement>, t: Throwable)
             {
                 listener.onErrorResult("Error")
                 Log.e("Error",""+t.message)
             }
         })

     }


}