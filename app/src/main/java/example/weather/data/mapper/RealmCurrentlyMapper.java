package example.weather.data.mapper;

import example.weather.data.db.model.RealmCurrently;
import example.weather.domain.model.Currently;

public class RealmCurrentlyMapper implements Mapper<RealmCurrently, Currently> {
    @Override
    public Currently mapFromEntity(RealmCurrently type) {
        return new Currently(type.summary, type.temperature, type.apparentTemperature,
                type.cloudCover, type.windSpeed);
    }

    @Override
    public RealmCurrently mapToEntity(Currently type) {
        return new RealmCurrently(type.summary, type.temperature, type.apparentTemperature,
                type.cloudCover, type.windSpeed);
    }
}
