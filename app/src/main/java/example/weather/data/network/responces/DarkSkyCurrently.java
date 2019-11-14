package example.weather.data.network.responces;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DarkSkyCurrently {
    @SerializedName("summary")
    @Expose
    public String summary;

    @SerializedName("temperature")
    @Expose
    public double temperature;

    @SerializedName("apparentTemperature")
    @Expose
    public double apparentTemperature;

    @SerializedName("cloudCover")
    @Expose
    public double cloudCover;

    @SerializedName("windSpeed")
    @Expose
    public double windSpeed;
}
