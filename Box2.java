import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Box2 {

    public static class GenericBox<T> {
        private T content;

        //пустой конструктор
        public GenericBox() {
            this.content = null;
        }

        //конструктор с содержимым
        public GenericBox(T content) {
            this.content = content;
        }

        //помещение объекта в коробку
        public void put(T item) {
            if (this.content != null) {
                throw new IllegalStateException("Коробка уже занята. Сначала извлеките текущий объект.");
            }
            this.content = item;
        }

        //извлечение
        public T get() {
            if (this.content == null) {
                throw new IllegalStateException("Коробка пуста!");
            }
            T item = this.content;
            this.content = null;
            return item;
        }

        //просмотр содержимого
        public T peek() {
            if (this.content == null) {
                throw new IllegalStateException("Коробка пуста!");
            }
            return this.content;
        }

        //пуста ли коробка
        public boolean isEmpty() {
            return this.content == null;
        }

        //заполнена ли коробка
        public boolean isFull() {
            return this.content != null;
        }

        @Override
        public String toString() {
            return content == null ? "Коробка[пусто]" : "Коробка[" + content + "]";
        }
    }

    public static class BoxDemo {
        private static Scanner scanner = new Scanner(System.in);

        //список всех созданных коробок
        private static List<GenericBox<Object>> allBoxes = new ArrayList<>();

        public static void showMenu() {
            System.out.println("\n=== МЕНЮ РАБОТЫ С КОРОБОК ===");
            System.out.println("1. Создать коробку с целым числом");
            System.out.println("2. Создать коробку со строкой");
            System.out.println("3. Извлечь объект из коробки");
            System.out.println("4. Показать информацию о коробках");
            System.out.println("5. Найти максимальное число среди коробок");
            System.out.println("6. Выход");
            System.out.print("Выберите действие (1-6): ");
        }

        //поиск максимального
        public static void findMaxNumber() {
            double max = Double.NEGATIVE_INFINITY;
            boolean foundNumber = false;

            for (GenericBox<Object> box : allBoxes) {
                if (!box.isEmpty() && box.peek() instanceof Number) {
                    Number num = (Number) box.peek(); // Приведение к Number
                    max = Math.max(max, num.doubleValue());
                    foundNumber = true;
                }
            }

            if (foundNumber) {
                System.out.println("Максимальное число среди коробок: " + max);
            } else {
                System.out.println("Нет числовых коробок для вычисления максимума.");
            }
        }

        public static void run() {
            boolean running = true;

            while (running) {
                showMenu(); // Показываем меню
                String choice = scanner.nextLine().trim();

                try {
                    switch (choice) {
                        case "1":
                            // Создание коробки с целым числом
                            System.out.print("Введите целое число для новой коробки: ");
                            int number = Integer.parseInt(scanner.nextLine());
                            GenericBox<Object> intBox = new GenericBox<>();
                            intBox.put(number);
                            allBoxes.add(intBox); // Добавляем в список коробок
                            System.out.println("Создана коробка с числом: " + number);
                            break;

                        case "2":
                            // Создание коробки со строкой
                            System.out.print("Введите строку для новой коробки: ");
                            String text = scanner.nextLine();
                            if (text.trim().isEmpty()) {
                                throw new IllegalArgumentException("Строка не может быть пустой!");
                            }
                            GenericBox<Object> strBox = new GenericBox<>();
                            strBox.put(text);
                            allBoxes.add(strBox); // Добавляем в список коробок
                            System.out.println("Создана коробка со строкой: \"" + text + "\"");
                            break;

                        case "3":
                            // Извлечение содержимого из выбранной коробки
                            if (allBoxes.isEmpty()) {
                                System.out.println("Нет коробок для извлечения.");
                                break;
                            }

                            System.out.println("Выберите номер коробки для извлечения объекта:");
                            for (int i = 0; i < allBoxes.size(); i++) {
                                System.out.println(i + ": " + allBoxes.get(i));
                            }

                            int index = Integer.parseInt(scanner.nextLine());
                            if (index < 0 || index >= allBoxes.size()) {
                                System.out.println("Некорректный индекс.");
                                break;
                            }

                            GenericBox<Object> box = allBoxes.get(index);
                            if (box.isEmpty()) {
                                System.out.println("Коробка пуста!");
                            } else {
                                Object value = box.get();
                                System.out.println("Извлеченный объект: " + value);
                            }
                            break;

                        case "4":
                            // Показ состояния всех коробок
                            if (allBoxes.isEmpty()) {
                                System.out.println("Нет созданных коробок.");
                            } else {
                                System.out.println("Состояние всех коробок:");
                                for (int i = 0; i < allBoxes.size(); i++) {
                                    GenericBox<Object> b = allBoxes.get(i);
                                    String type = b.isEmpty() ? "пусто" : b.peek().getClass().getSimpleName();
                                    System.out.println(i + ": " + b + " (тип: " + type + ")");
                                }
                            }
                            break;

                        case "5":
                            // Найти максимальное число среди всех числовых коробок
                            findMaxNumber();
                            break;

                        case "6":

                            running = false;
                            System.out.println("Выход из программы");
                            break;

                        default:
                            System.out.println("Неверный выбор. Введите число от 1 до 6.");
                    }
                } catch (Exception e) {
                    System.out.println("Ошибка: " + e.getMessage());
                }
            }

            scanner.close();
        }
    }
}
