package example.weather.data.network.responces;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DarkSkyForecast {

    @SerializedName("latitude")
    @Expose
    public double latitude;

    @SerializedName("longitude")
    @Expose
    public double longitude;

    @SerializedName("currently")
    @Expose
    public DarkSkyCurrently darkSkyCurrently;

}
