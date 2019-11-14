package example.weather.domain.interactors;


import android.location.Location;

import example.weather.domain.interactors.base.Interactor;
import example.weather.domain.model.Forecast;

public interface IForecastInteractor extends Interactor {

    interface Callback {
        void onForecastRetrieved(Forecast forecast);

        void requestLocationUpdate();
    }

    void onLocationUpdate(Location location);

    Forecast getCachedForecast();
}