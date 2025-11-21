package five.validation;

public class InputValidator {
    /**
     * Преобразует строку в положительное число или выбрасывает ошибку
     */

    public static int parsePositiveInt(String value, String fieldName) {
        try {
            int number = Integer.parseInt(value);
            if (number <= 0) {
                throw new IllegalArgumentException(fieldName + " должен быть больше 0");
            }
            return number;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(fieldName + " должен быть целым числом");
        }
    }
}
