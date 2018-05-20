#How to use Retrofit on android with Kotlin

#Sample code how to get interacts with the app server using retrofit network library.

#let's start dirty our hands

#Go to Android Studio and create a new project,
add the following in build.gradle: file

dependencies 
{
implementation "com.squareup.retrofit2:retrofit:2.3.0"

implementation "com.squareup.retrofit2:converter-gson:2.3.0"
}

#Create an interface
interface RetrofitApi
{
    @GET(WebUrls.GET_MOVIES)
    fun getMovies(): Call<JsonElement>
}
Here is return object Call<JsonElement>
The @GET annotation tells the client which HTTP method to use and on which resource

#Create a client, initialization and create service retrofit 

#val gson = GsonBuilder()
                 .setLenient()
                 .create()

 
 #// Retrofit Client
 
 
  val okHttpClient = OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(40, TimeUnit.SECONDS)
                .build()


#

val retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

#// prepare call in Retrofit 2.0
 
 
  val retroUrlApi = retrofit.create(RetrofitApi::class.java)


#//Prepare empty
 
 var call: Call<JsonElement>? = null

#//Call And Convert 

call = retroUrlApi.getMovies()

#Conclusion

In this example, I used common network class for a network request.

Using common network class you can call from any activity and get the response with same.
