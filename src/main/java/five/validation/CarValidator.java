package five.validation;

import five.car.Car;
import five.exception.CarIllegalArgumentException;

public class CarValidator {
    private static final int MIN_VALID_YEAR = 1900;

    public void validate(Car car) throws CarIllegalArgumentException {
        if (car == null) {
            throw new CarIllegalArgumentException("Автомобиль не может быть null");
        }

        validateBrand(car.getBrand());
        validatePower(car.getPower());
        validateYear(car.getYear());
    }

    private void validateBrand(String brand) throws CarIllegalArgumentException {
        if (brand == null || brand.trim().isEmpty()) {
            throw new CarIllegalArgumentException("Бренд не может быть пустым значением");
        }
    }

    private void validatePower(int power) throws CarIllegalArgumentException {
        try {
            InputValidator.parsePositiveInt(String.valueOf(power), "Мощность");
        } catch (IllegalArgumentException e) {
            throw new CarIllegalArgumentException(e.getMessage());
        }
    }

    private void validateYear(int year) throws CarIllegalArgumentException {
        try {
            InputValidator.parsePositiveInt(String.valueOf(year), "Год");

            int currentYear = java.time.LocalDate.now().getYear();
            if (year < MIN_VALID_YEAR || year > currentYear) {
                throw new CarIllegalArgumentException(
                        "Год должен быть в диапазоне от " + MIN_VALID_YEAR + " до " + currentYear
                );
            }
        } catch (IllegalArgumentException e) {
            throw new CarIllegalArgumentException(e.getMessage());
        }
    }
}