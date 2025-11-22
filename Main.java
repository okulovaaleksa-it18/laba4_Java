import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        check check = new check();

        System.out.print("""
                           ========================
                           ======СПИСОК ЗАДАЧ======
                           ========================
                           1. Задание 1.1 Обобщенная коробка
                           2. Задание 1.2 Без null
                           3. Задание 2.2 Поиск максимума
                           4. Задание 3.1 Функция
                           5. Задание 3.2 Фильтр
                           6. Задание 3.3 Сокращение
                           7. Задание 3.4 Коллекционирование
                           """);
        int choice = check.inputNum("Выберите номер задачи: ");
        System.out.println("Задача №" + choice + ":");

        switch (choice) {
            case(1): {
                Box.BoxDemo.run();
                break;
            }
            case(2): {
                StorageApp.StorageDemo.run();
                break;
            }
            case(3): {
                Box2.BoxDemo.run();
                break;
            }
            case(4): {
                GenericFunction.demo();
                break;
            }
            case(5): {
                GenericFilter.demo();
                break;
            }
            case(6): {
                GenericReducer.demo();
                break;
            }
            case(7): {
                GenericCollector.demo();
                break;
            }
        }
    }
}
