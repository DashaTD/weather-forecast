package example.weather.domain.model;

public class Currently {

    public String summary;

    public double temperature;

    public double apparentTemperature;

    public double cloudCover;

    public double windSpeed;

    public Currently(String summary, double temperature, double apparentTemperature,
                     double cloudCover, double windSpeed) {
        this.summary = summary;
        this.temperature = temperature;
        this.apparentTemperature = apparentTemperature;
        this.cloudCover = cloudCover;
        this.windSpeed = windSpeed;
    }
}
