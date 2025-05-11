package vending.machine.money.exchange;

import vending.machine.models.Currency;

public class MoneyExchangeRateProvider implements IMoneyExchangeRateProvider {

    @Override
    public double getExchangeRate(Currency from, Currency to) {
        return Math.random();
    }
}
