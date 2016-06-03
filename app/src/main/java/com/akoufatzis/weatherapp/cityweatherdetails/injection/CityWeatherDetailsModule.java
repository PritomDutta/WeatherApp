package com.akoufatzis.weatherapp.cityweatherdetails.injection;

import com.akoufatzis.weatherapp.application.injection.scopes.PerActivity;
import com.akoufatzis.weatherapp.cityweatherdetails.CityWeatherDetailsContract;
import com.akoufatzis.weatherapp.cityweatherdetails.presenter.CityWeatherDetailsPresenter;
import com.akoufatzis.weatherapp.communication.DataManager;

import dagger.Module;
import dagger.Provides;

/**
 * Created by alexk on 03/06/16.
 */
@Module
public class CityWeatherDetailsModule {

    @PerActivity
    @Provides
    CityWeatherDetailsContract.Presenter providesPresenter(DataManager dataManager) {

        return new CityWeatherDetailsPresenter(dataManager);
    }
}
