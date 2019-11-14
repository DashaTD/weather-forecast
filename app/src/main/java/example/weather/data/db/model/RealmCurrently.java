package example.weather.data.db.model;

import io.realm.RealmObject;

public class RealmCurrently extends RealmObject {

    public String summary;

    public double temperature;

    public double apparentTemperature;

    public double cloudCover;

    public double windSpeed;

    public RealmCurrently() {
    }

    public RealmCurrently(String summary, double temperature, double apparentTemperature,
                          double cloudCover, double windSpeed) {
        this.summary = summary;
        this.temperature = temperature;
        this.apparentTemperature = apparentTemperature;
        this.cloudCover = cloudCover;
        this.windSpeed = windSpeed;
    }
}
