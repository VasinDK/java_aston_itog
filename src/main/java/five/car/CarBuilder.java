package five.car;

import five.exception.CarIllegalArgumentException;

public interface CarBuilder {

    CarBuilder brand(String brand);
    CarBuilder power(int power);
    CarBuilder year(int year);

    Car build() throws CarIllegalArgumentException;
}
