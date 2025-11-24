package five.car;

import five.exception.CarIllegalArgumentException;

public class CarImpl implements Car {

    private final String brand;
    private final int power;
    private final int year;

    CarImpl(String brand, int power, int year) {
        this.brand = brand;
        this.power = power;
        this.year = year;
    }

    @Override
    public String getBrand() {
        return brand;
    }

    @Override
    public int getPower() {
        return power;
    }

    @Override
    public int getYear() {
        return year;
    }

    @Override
    public String toString() {
        return "Автомобиль: бренд = " + brand +
                ", мощность = " + power + " лс" +
                ", год = " + year;
    }
}
