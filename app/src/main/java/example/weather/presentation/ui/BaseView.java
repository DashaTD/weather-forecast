package example.weather.presentation.ui;

import com.arellomobile.mvp.MvpView;

public interface BaseView extends MvpView {

    void showProgress();

    void hideProgress();
}
