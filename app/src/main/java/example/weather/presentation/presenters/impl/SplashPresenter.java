package example.weather.presentation.presenters.impl;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.concurrent.TimeUnit;

import example.weather.presentation.presenters.ISplashView;
import example.weather.presentation.presenters.base.BasePresenter;
import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class SplashPresenter extends MvpPresenter<ISplashView> implements BasePresenter {
    private static final int SPLASH_DURATION_MILLIS = 3000;
    private CompositeDisposable compositeDisposable;

    public SplashPresenter() {
        compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void resume() {
        finish();
    }

    private void finish() {
        compositeDisposable.add(
                Completable.timer(SPLASH_DURATION_MILLIS, TimeUnit.MILLISECONDS)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(() -> getViewState().startWeatherActivity())
        );
    }

    @Override
    public void pause() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void destroy() {
        compositeDisposable.clear();
    }

}
