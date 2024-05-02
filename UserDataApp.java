import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class UserDataApp {

    public static void main(String[] args) {
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите данные в формате: Фамилия Имя Отчество дата_рождения номер_телефона пол");
        String input = scanner.nextLine();

        try {
            processInput(input);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void processInput(String input) throws IOException {
        String[] parts = input.split("\\s+");
        if (parts.length != 6) {
            System.out.println("Введено неверное количество данных. Требуется ввести Фамилия Имя Отчество дата_рождения номер_телефона пол");
            return;
        }

        String lastName = parts[0];
        String firstName = parts[1];
        String middleName = parts[2];
        String birthDate = parts[3];
        String phoneNumber = parts[4];
        String gender = parts[5];

        if (!birthDate.matches("\\d{2}\\.\\d{2}\\.\\d{4}")) {
            throw new IllegalArgumentException("Неверный формат даты рождения. Используйте формат dd.mm.yyyy");
        }

        if (!phoneNumber.matches("\\d+")) {
            throw new IllegalArgumentException("Номер телефона должен содержать только цифры");
        }

        if (!(gender.equals("f") || gender.equals("m"))) {
            throw new IllegalArgumentException("Пол должен быть указан как 'f' или 'm'");
        }

        saveToFile(lastName, firstName, middleName, birthDate, phoneNumber, gender);
    }

    private static void saveToFile(String lastName, String firstName, String middleName, String birthDate, String phoneNumber, String gender) throws IOException {
        String fileName = lastName + ".txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(String.format("%s %s %s %s %s %s%n", lastName, firstName, middleName, birthDate, phoneNumber, gender));
        } catch (IOException e) {
            System.out.println("Произошла ошибка при записи в файл:");
            e.printStackTrace();
            throw e;
        }
    }
}
