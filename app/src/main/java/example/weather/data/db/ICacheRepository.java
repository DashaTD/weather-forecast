package example.weather.data.db;

import example.weather.data.db.model.RealmForecast;

public interface ICacheRepository {

    void insertForecast(RealmForecast realmForecast);

    RealmForecast getForecast();

    void deleteAll();

    void close();

}
