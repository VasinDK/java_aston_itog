package five.car;

import five.exception.CarIllegalArgumentException;
import five.validation.CarValidator;
import five.validation.InputValidator;

public class CarBuilderImpl implements CarBuilder {
    private String brand;
    private int power;
    private int year;

    private final CarValidator carValidator;

    private final InputValidator inputValidator;

    public CarBuilderImpl() {
        this.carValidator = new CarValidator();
        this.inputValidator = new InputValidator();
    }

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
        carValidator.validate(car);
        return car;
    }
}
