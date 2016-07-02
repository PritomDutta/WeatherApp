package com.akoufatzis.weatherapp.data.remote;

import com.akoufatzis.weatherapp.BuildConfig;
import com.akoufatzis.weatherapp.data.memory.MemoryCache;
import com.akoufatzis.weatherapp.injection.scopes.PerApplication;
import com.akoufatzis.weatherapp.model.CityWeather;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by alexk on 02/05/16.
 */
@PerApplication
public class DataManager {

    private MemoryCache memoryCache;
    private IOpenWeatherMapApi openWeatherMapService;
    private final String apiKey = BuildConfig.OPENWEATHERMAP_API_KEY;

    @Inject
    public DataManager(IOpenWeatherMapApi openWeatherMapService) {

        this.openWeatherMapService = openWeatherMapService;
        memoryCache = new MemoryCache();
    }

    //TODO: Implement caching
    public Observable<CityWeather> getWeatherByCityName(String name) {

        return openWeatherMapService
                .getWeatherByCityName(name, apiKey)
                .compose(applySchedulers());
    }

    public Observable<CityWeather> getWeatherByCityId(long id) {

        return openWeatherMapService
                .getWeatherByCityId(id, apiKey)
                .compose(applySchedulers());
    }

    //Schedulers Transformer

    private <T> Observable.Transformer<T, T> applySchedulers() {

        return observable -> observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
