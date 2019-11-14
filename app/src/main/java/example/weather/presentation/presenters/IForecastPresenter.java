package example.weather.presentation.presenters;

import android.location.Location;

import example.weather.presentation.presenters.base.BasePresenter;

public interface IForecastPresenter extends BasePresenter {

    void onLocationRetrieved(Location location);
}
