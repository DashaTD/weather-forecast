package example.weather.presentation.presenters.impl;

import android.location.Location;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import example.weather.domain.interactors.IForecastInteractor;
import example.weather.domain.interactors.impl.ForecastInteractor;
import example.weather.domain.model.Forecast;
import example.weather.domain.repository.IForecastRepository;
import example.weather.presentation.presenters.IForecastPresenter;
import example.weather.presentation.presenters.IForecastView;

@InjectViewState
public class ForecastPresenter extends MvpPresenter<IForecastView> implements IForecastPresenter,
        IForecastInteractor.Callback {

    private IForecastInteractor interactor;

    public ForecastPresenter(
            IForecastRepository repository) {
        interactor = new ForecastInteractor(this, repository);
    }

    @Override
    public void resume() {
        interactor.run();
    }

    @Override
    public void pause() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void destroy() {
        interactor.destroy();
    }

    @Override
    public void onForecastRetrieved(Forecast forecast) {
        getViewState().displayForecast(forecast);
    }

    @Override
    public void requestLocationUpdate() {
        getViewState().requestLocation();
    }

    @Override
    public void onLocationRetrieved(Location location) {
        interactor.onLocationUpdate(location);
    }
}