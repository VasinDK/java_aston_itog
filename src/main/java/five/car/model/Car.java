package five.car.model;

public class Car {
    private final String brand;
    private final int power;
    private final int year;

    private Car(Builder builder) {
        this.brand = builder.brand;
        this.power = builder.power;
        this.year = builder.year;
    }

    public String getBrand() { return brand; }
    public int getPower() { return power; }
    public int getYear() { return year; }

    @Override
    public String toString() {
        return "Автомобиль: бренд = " + brand + ", мощность = " + power + " лс" + ", год = " + year;
    }

    public static class Builder {
        private String brand;
        private int power;
        private int year;

        public Builder brand(String brand) {
            this.brand = brand;
            return this;
        }

        public Builder power(int power) {
            this.power = power;
            return this;
        }

        public Builder year(int year) {
            this.year = year;
            return this;
        }

        public Car build() {
            return new Car(this);
        }
    }
}
