import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner scanner=new Scanner(System.in);
        TaskManager maneger=new TaskManager(scanner);
        boolean running=true;
        while (running) {
            System.out.println("\n--- Console Task Master ---");
            System.out.println("1. Добавить задачу");
            System.out.println("2. Показать все задачи");
            System.out.println("3. Удалить задачу");
            System.out.println("4. Изменить статус");
            System.out.println("5. Изменить приоритет");
            System.out.println("6. Фильтровать задачи");
            System.out.println("7. Выход");
            System.out.print("Выберите действие: ");

            String input=scanner.nextLine();
            int number;
            try {
                number=Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Неверный формат ввода.Попробуте снова");
                continue;
            }
            switch (number){
                case 1:
                    maneger.addTask();
                    break;
                case 2:
                    maneger.showAllTasks();
                    break;
                case 3:
                    maneger.deleteTask();
                    break;
                case 4:
                    maneger.changeStatus();
                    break;
                case 5:
                    maneger.changePriority();
                    break;
                case 6:
                    maneger.filterTasks();
                    break;
                case 7:
                    running=false;
                    System.out.println("До свидания");
                    break;
                default:
                    System.out.println("Неверный ввод");

            }


        }
        scanner.close();

    }
}
