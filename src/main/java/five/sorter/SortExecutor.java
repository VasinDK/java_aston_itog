package five.sorter;

import java.util.List;


/**
 * Класс SortExecutor выполняет сортировку списка автомобилей
 * с использованием выбранной стратегии сортировки (паттерн «Стратегия»).
 *
 * <p><b>Пример использования:</b></p>
 * <pre>
 * SortStrategy byBrand = new ByBrand();
 * List<Car> sortedList = new SortExecutor(carList)
 *         .setStrategy(byBrand)
 *         .exec();
 * </pre>
 */
public class SortExecutor {

    /**
     * Стратегия сортировки, реализующая интерфейс SortStrategy.
     */
    private SortStrategy strategy;

    /**
     * Список автомобилей, который нужно отсортировать.
     */
    private final List<Car> cars;

    /**
     * Создаёт SortExecutor и принимает список автомобилей,
     * который будет использоваться при сортировке.
     *
     * @param cars список автомобилей
     */
    public SortExecutor(List<Car> cars) {
        this.cars = cars;
    }

    /**
     * Устанавливает стратегию сортировки.
     *
     * @param strategy стратегия сортировки
     * @return текущий SortExecutor (для цепочного вызова)
     */
    public SortExecutor setStrategy(SortStrategy strategy) {
        this.strategy = strategy;
        return this;
    }

    /**
     * Выполняет сортировку согласно выбранной стратегии.
     *
     * @return новый отсортированный список автомобилей
     * @throws IllegalStateException если стратегия не установлена
     */
    public List<Car> exec() {
        if (strategy == null) {
            throw new IllegalStateException("Стратегия сортировки не установлена.");
        }
        return strategy.sort(cars);
    }
}

