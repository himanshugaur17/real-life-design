package vending.machine.money.exchange;

import vending.machine.models.Currency;

public interface IMoneyExchangeRateProvider {
    double getExchangeRate(Currency from, Currency to);
}
