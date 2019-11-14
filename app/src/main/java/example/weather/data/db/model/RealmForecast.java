package example.weather.data.db.model;

import io.realm.RealmObject;

public class RealmForecast extends RealmObject {

    public double latitude;

    public double longitude;

    public RealmCurrently realmCurrently;

    public RealmForecast() {
    }

    public RealmForecast(Double latitude, Double longitude, RealmCurrently realmCurrently) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.realmCurrently = realmCurrently;
    }
}
