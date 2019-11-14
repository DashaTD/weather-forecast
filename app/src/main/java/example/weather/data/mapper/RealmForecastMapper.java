package example.weather.data.mapper;

import example.weather.data.db.model.RealmCurrently;
import example.weather.data.db.model.RealmForecast;
import example.weather.domain.model.Currently;
import example.weather.domain.model.Forecast;

public class RealmForecastMapper implements Mapper<RealmForecast, Forecast> {
    private RealmCurrentlyMapper currentlyMapper;

    public RealmForecastMapper() {
        currentlyMapper = new RealmCurrentlyMapper();
    }

    @Override
    public Forecast mapFromEntity(RealmForecast type) {
        Currently currently = currentlyMapper.mapFromEntity(type.realmCurrently);
        return new Forecast(type.latitude, type.longitude, currently);
    }

    @Override
    public RealmForecast mapToEntity(Forecast type) {
        RealmCurrently realmCurrently = currentlyMapper.mapToEntity(type.currently);
        return new RealmForecast(type.latitude, type.longitude, realmCurrently);
    }
}
