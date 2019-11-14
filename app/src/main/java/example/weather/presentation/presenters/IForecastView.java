package example.weather.presentation.presenters;

import example.weather.domain.model.Forecast;
import example.weather.presentation.ui.BaseView;

public interface IForecastView extends BaseView {
    void displayForecast(Forecast forecast);

    void requestLocation();
}
