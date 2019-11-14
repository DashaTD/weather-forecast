package example.weather.data.mapper;

import example.weather.data.network.responces.DarkSkyCurrently;
import example.weather.domain.model.Currently;

public class DarkSkyCurrentlyMapper implements Mapper<DarkSkyCurrently, Currently> {
    @Override
    public Currently mapFromEntity(DarkSkyCurrently type) {
        return new Currently(type.summary, type.temperature, type.apparentTemperature,
                type.cloudCover, type.windSpeed);
    }

    @Override
    public DarkSkyCurrently mapToEntity(Currently type) {
        return null;
    }
}
