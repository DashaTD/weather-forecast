package example.weather.data.network;

import example.weather.data.network.responces.DarkSkyForecast;
import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface DarkSkyApi {

    @GET("{latitude},{longitude}?exclude=minutely,hourly,daily,flags&units=si")
    Single<Response<DarkSkyForecast>> getCurrentWeather(
            @Path("latitude") Double latitude,
            @Path("longitude") Double longitude
    );
}
