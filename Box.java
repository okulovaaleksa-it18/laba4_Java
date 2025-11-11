import java.util.Scanner;

public class Box{
    static Scanner scanner = new Scanner(System.in);
    public static class GenericBox<T> {
        // Поле для хранения объекта.
        private T content;
        private static Scanner scanner = new Scanner(System.in);
        static check check = new check();
        
         //Конструктор по умолчанию
        public GenericBox() {
            this.content = null;
        }

        //Конструктор с параметром 
        public GenericBox(T content) {
            this.content = content; 
        }

        //Помещает объект в коробку
        public void put(T item) {
            if (this.content != null) {
                throw new IllegalStateException("Коробка уже занята. Сначала извлеките текущий объект.");
            }
            this.content = item; 
        }

        //Извлечение объекта из коробки
        public T get() {
            if (this.content == null) {
                throw new IllegalStateException("Коробка пуста!");
            }
            T item = this.content;
            this.content = null;
            return item;
        }

        //посмотреть на объект без извлечения
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
        
        public boolean isFull() {
            return this.content != null; // Обратная проверка isEmpty()
        }
        
        @Override
        public String toString() {
            if (content == null) {
                return "Коробка[пусто]"; // Сообщение для пустой коробки
            } else {
                return "Коробка[" + content + "]"; // Сообщение с содержимым
            }
        }
    }

    public static class BoxDemo {
        private static Scanner scanner = new Scanner(System.in);
        private static GenericBox<Object> currentBox = new GenericBox<>();
        private static Class<?> currentType = null;

        public static void processIntegerBox(GenericBox<Integer> box) {
            if (!box.isEmpty()) {
                Integer value = box.get();
                System.out.println("Извлеченное значение: " + value);
            } else {
                System.out.println("Коробка пуста!");
            }
        }
        
        public static void showMenu() {
            System.out.println("\n=== МЕНЮ РАБОТЫ С КОРОБКАМИ ===");
            System.out.println("1. Заполнить коробку с целым числом");
            System.out.println("2. Заполнить коробку со строкой");
            System.out.println("3. Извлечь объект из коробки");
            System.out.println("4. Показать информацию о коробке");
            System.out.println("5. Выполнить задание (число 3)");
            System.out.println("6. Выход");
            System.out.print("Выберите действие (1-6): ");
        }

        public static void run() {
            System.out.println("=== ДЕМОНСТРАЦИЯ РАБОТЫ ОБОБЩЕННОЙ КОРОБКИ ===");

            boolean running = true;

            while (running) {
                showMenu();
                String choice = scanner.nextLine().trim();

                try {
                    switch (choice) {
                        case "1":
                            System.out.println("=== ЗАПОЛНЕНИЕ КОРОБКИ ЦЕЛЫМ ЧИСЛОМ ===");
                            check check = new check();
                            int number = check.inputNum("Введите целое число для коробки: ");
                            currentBox.put(number);
                            currentType = Integer.class; // Запоминает тип
                            System.out.println("Число " + number + " успешно помещено в коробку!");
                            break;

                        case "2":
                            System.out.println("=== ЗАПОЛНЕНИЕ КОРОБКИ СТРОКОЙ ===");
                            System.out.print("Введите строку для коробки: ");
                            String text = scanner.nextLine();
                            if (text.trim().isEmpty()) {
                                throw new IllegalArgumentException("Строка не может быть пустой!");
                            }
                            currentBox.put(text);
                            currentType = String.class;
                            System.out.println("Строка \"" + text + "\" успешно помещена в коробку!");
                            break;

                        case "3":
                            if (!currentBox.isEmpty()) {
                                // Извлекаем объект и проверяем его тип
                                Object value = currentBox.get();
                                currentType = null;

                                if (value instanceof Integer) {
                                    System.out.println("Извлеченное число: " + value);
                                } else if (value instanceof String) {
                                    System.out.println("Извлеченная строка: " + value);
                                } else {
                                    System.out.println("Извлеченный объект: " + value + " (тип: " + value.getClass().getSimpleName() + ")");
                                }
                            } else {
                                System.out.println("Коробка пуста!");
                            }
                            break;

                        case "4":
                            System.out.println("=== ИНФОРМАЦИЯ О КОРОБКЕ ===");
                            System.out.println("Текущая коробка: " + currentBox);
                            System.out.println("Коробка пуста: " + currentBox.isEmpty());
                            System.out.println("Коробка заполнена: " + currentBox.isFull());
                            if (currentType != null) {
                                System.out.println("Тип объекта в коробке: " + currentType.getSimpleName());
                            }
                            break;

                        case "5":
                            System.out.println("\n=== ВЫПОЛНЕНИЕ ЗАДАНИЯ ===");
                            GenericBox<Integer> taskBox = new GenericBox<>();
                            taskBox.put(3);
                            System.out.println("Создана коробка: " + taskBox);
                            processIntegerBox(taskBox);
                            break;

                        case "6":
                            running = false;
                            System.out.println("Выход из программы. До свидания!");
                            break;

                        default:
                            System.out.println("Неверный выбор! Введите число от 1 до 6.");
                    }
                } catch (Exception e) {
                    System.out.println("Ошибка: " + e.getMessage());
                }
            }

            scanner.close();
        }
    }
}
