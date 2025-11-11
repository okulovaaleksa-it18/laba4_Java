import java.util.Scanner;

public class check {
    private static Scanner in = new Scanner(System.in);

    public int inputNum(String message) { //проверка на целое число
        int x;
        while (true) {
            System.out.print(message);
            if (in.hasNextInt()) { //hasNextInt()- проверяет, что след. число в потоке ввода = целое число.
                x = in.nextInt(); //считывает след. целое число из потока и возвращает его.
                break;
            } else {
                System.out.println("Ошибка. Введите целое число");
                in.next(); //очищаем неверный ввод
            }
        }
        return x;
    }

    public boolean isSet(String value) { //проверка, что строка не пустая
        return value != null && !value.trim().isEmpty();
    }

    public boolean checkField(String input) {
        if (input.contains(" ")) {
            System.out.println("Ошибка, введите заново");
            return false;
        }
        return true;
    }
    public Integer inputNum2(String value) throws NumberFormatException {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Ошибка. Введите целое число!");
        }
    }

}
