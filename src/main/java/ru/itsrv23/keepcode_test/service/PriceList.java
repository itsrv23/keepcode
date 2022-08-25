package ru.itsrv23.keepcode_test.service;

import ru.itsrv23.keepcode_test.model.onlinesim.Country;

import java.util.List;

public interface PriceList {
    List<Country> getPriceList(boolean forceUpdate);

}
