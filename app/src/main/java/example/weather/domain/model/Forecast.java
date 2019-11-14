package example.weather.domain.model;

public class Forecast {

    public double latitude;

    public double longitude;

    public Currently currently;

    public Forecast(double latitude, double longitude, Currently currently) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.currently = currently;
    }
}
