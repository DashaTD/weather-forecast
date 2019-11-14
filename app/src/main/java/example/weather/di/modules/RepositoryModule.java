package example.weather.di.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import example.weather.data.ForecastRepository;
import example.weather.data.db.ICacheRepository;
import example.weather.data.network.DarkSkyApi;
import example.weather.domain.repository.IForecastRepository;

@Module
public class RepositoryModule {

    @Provides
    @Singleton
    IForecastRepository provideForecastRepository(ICacheRepository cacheRepository, DarkSkyApi darkSkyApi) {
        return ForecastRepository.getInstance(cacheRepository, darkSkyApi);
    }
}