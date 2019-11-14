package example.weather.data.db;

import example.weather.data.db.model.RealmForecast;
import io.realm.Realm;

public class CacheRepository implements ICacheRepository {
    private static CacheRepository instance;
    private Realm realm;

    private CacheRepository() {
        realm = Realm.getDefaultInstance();
    }

    public static CacheRepository getInstance() {
        if (instance == null) {
            instance = new CacheRepository();
        }
        return instance;
    }

    @Override
    public void insertForecast(RealmForecast realmForecast) {
        deleteAll();
        realm.executeTransaction(realm -> {
            realm.insert(realmForecast.realmCurrently);
            realm.insert(realmForecast);
        });
    }

    @Override
    public RealmForecast getForecast() {
        return realm.where(RealmForecast.class).findFirst();
    }

    @Override
    public void deleteAll() {
        realm.executeTransaction(realm -> realm.deleteAll());
    }

    @Override
    public void close() {
        realm.close();
    }
}
