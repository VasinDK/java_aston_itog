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

    /* @Override
    public void validate() throws CarIllegalArgumentException {
        if (brand == null || brand.isBlank()) {
            throw new CarIllegalArgumentException(CarMessages.ERROR_EMPTY_BRAND);
        }

        if (power <= 0) {
            throw new CarIllegalArgumentException(CarMessages.ERROR_INVALID_POWER);
        }

        int currentYear = java.time.LocalDate.now().getYear();
        if (year < 1900 || year > currentYear) {
            throw new CarIllegalArgumentException(CarMessages.ERROR_INVALID_YEAR);
        }
    }
    Под валидатор заготовка*/

    @Override
    public String toString() {
        return "Автомобиль: бренд = " + brand +
                ", мощность = " + power + " лс" +
                ", год = " + year;
    }
}
