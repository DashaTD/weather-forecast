package example.weather.di.modules;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import example.weather.data.db.CacheRepository;
import example.weather.data.db.ICacheRepository;
import io.realm.Realm;
import io.realm.RealmConfiguration;

@Module
public class CacheModule {

    @Provides
    @Singleton
    ICacheRepository provideCacheRepository(Context context) {
        Realm.init(context);
        configureDefaultRealmConfig();
        return CacheRepository.getInstance();
    }

    private void configureDefaultRealmConfig() {
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("weather_forecast.realm")
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);
    }
}
