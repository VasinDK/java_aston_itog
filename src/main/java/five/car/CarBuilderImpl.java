package five.car;

import five.exception.CarIllegalArgumentException;

public class CarBuilderImpl implements CarBuilder {

    private String brand;
    private int power;
    private int year;

    @Override
    public CarBuilderImpl brand(String brand) {
        this.brand = brand;
        return this;
    }

    @Override
    public CarBuilderImpl power(int power) {
        this.power = power;
        return this;
    }

    @Override
    public CarBuilderImpl year(int year) {
        this.year = year;
        return this;
    }

    @Override
    public Car build() throws CarIllegalArgumentException {
        CarImpl car = new CarImpl(brand, power, year);
        return car;
    }
}
