package co.basavraj.Interface
import com.google.gson.JsonElement
interface CommonResponseListener {
     fun onSuccessResult(obj: JsonElement)
     fun onErrorResult(msg: String)
}