package co.basavraj.Network
import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST

interface RetrofitApi
{
    @GET(WebUrls.GET_MOVIES)
    fun getMovies(): Call<JsonElement>
}