package example.weather.data.mapper;

import example.weather.data.network.responces.DarkSkyForecast;
import example.weather.domain.model.Currently;
import example.weather.domain.model.Forecast;

public class DarkSkyForecastMapper implements Mapper<DarkSkyForecast, Forecast> {
    private DarkSkyCurrentlyMapper currentlyMapper;

    public DarkSkyForecastMapper() {
        currentlyMapper = new DarkSkyCurrentlyMapper();
    }

    @Override
    public Forecast mapFromEntity(DarkSkyForecast type) {
        Currently currently = currentlyMapper.mapFromEntity(type.darkSkyCurrently);
        return new Forecast(type.latitude, type.longitude, currently);
    }

    @Override
    public DarkSkyForecast mapToEntity(Forecast type) {
        return null;
    }
}
