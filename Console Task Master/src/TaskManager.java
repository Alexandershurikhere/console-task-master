import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaskManager {
    private List<Task> tasks;
    private Scanner scanner;


    public TaskManager(Scanner scanner) {
        this.tasks = new ArrayList<>();
        this.scanner=scanner;

    }
    public void addTask(){
        System.out.println("Введите название задачи: ");
        String title=scanner.nextLine();
        if(title.trim().isEmpty()){
            System.out.println("Название не должно быть пустое");
            return;
        }
        Priority priority=null;
        while (priority==null){
            System.out.println("Выбрите приоритет задачи(LOW,MEDIUM,HIGH): ");
            String priorityStr=scanner.nextLine().toUpperCase();
            try {
                priority=Priority.valueOf(priorityStr);
            } catch (IllegalArgumentException e) {
                System.out.println("Неверный формат.Попробуйте заново");

            }
        }
        LocalDate deadLine=null;
        System.out.println("Введите дедлайн задачи(ГГГГ-ММ-ДД) или оставьте пустым: ");
        String deadLineStr=scanner.nextLine();
        if (!deadLineStr.isEmpty()) {
            try {
                deadLine = LocalDate.parse(deadLineStr);
            } catch (DateTimeParseException e) {
                System.out.println("Неверный формат.Дедлайн не установлен");

            }
        }
        Task task=new Task(title,priority,deadLine);
        tasks.add(task);
        System.out.println("Задача успешно добавлена");
    }
    public void showAllTasks(){
        if(tasks.isEmpty()){
            System.out.println("Список пуст.Добавьте задачи");
            return;
        }
        for(int i=0;i< tasks.size();i++){
            printTask(tasks.get(i),i+1);
        }

    }
    private void  printTask(Task task,int index){
        String deadLineStr=(task.getTime()!=null)? task.getTime().toString(): "не указан";
        System.out.printf("%d. %s | статус: %s | приоритет: %s | дедлайн %s%n",
                index,
                task.getTitle(),
                task.getStatusEnum(),
                task.getPriorityEnum(),
                deadLineStr);
    }
    public void deleteTask(){
        if (tasks.isEmpty()) {
            System.out.println("Нет задач для удаления.");
            return;
        }
        showAllTasks();
        System.out.println("Введите номер задачи,которую хотите удалить");


        while (true){
            String number=scanner.nextLine();
        try {
            int numberInt=Integer.parseInt(number);
            if(numberInt>tasks.size() || numberInt<1){
                System.out.println("Неправильно набран номер задачи.Попробуйте снова: ");
                continue;
            }
            tasks.remove(numberInt-1);
            System.out.println("Задача удалена");
            break;
        }catch (NumberFormatException e){
            System.out.println("Неправильный формат.Введите число: ");

        }


        }

    }
    public void changeStatus(){
        if (tasks.isEmpty()) {
            System.out.println("Нет задач для изменения статуса.");
            return;
        }
        showAllTasks();
        Task task=null;



        while (true) {
            System.out.println("Введите номер задачи,у которой хотите изменить стасус");
            String number = scanner.nextLine();
            try {
                int numberInt = Integer.parseInt(number);
                if (numberInt > tasks.size() || numberInt < 1) {
                    System.out.println("Неправильно набран номер задачи.Попробуйте снова: ");
                    continue;
                }
                task = tasks.get(numberInt - 1);

                break;


            } catch (NumberFormatException e) {
                System.out.println("Неверный формат ввода.Попробуйте снова: ");
            }
        }
            System.out.println("Статус задачи сейчас: "+ task.getStatusEnum());
            while (true) {
                System.out.println("Введите новый статус для задачи(NEW, IN_PROGRESS, DONE): ");
                String status = scanner.nextLine().toUpperCase();
                try {
                Status newStatus=Status.valueOf(status);
                task.setStatusEnum(newStatus);
                    System.out.println("Статус задачи изменен на "+ newStatus);
                    break;


                } catch (IllegalArgumentException e) {
                    System.out.println("Неверный ввод.Такого статуса нет");
                }
            }
    }
    public void changePriority(){
        if (tasks.isEmpty()) {
            System.out.println("Нет задач для изменения приоритета.");
            return;
        }
        showAllTasks();
        Task task=null;



        while (true) {
            System.out.println("Введите номер задачи,у которой хотите изменить приоритет");
            String number = scanner.nextLine();
            try {
                int numberInt = Integer.parseInt(number);
                if (numberInt > tasks.size() || numberInt < 1) {
                    System.out.println("Неправильно набран номер задачи.Попробуйте снова: ");
                    continue;
                }
                task = tasks.get(numberInt - 1);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Неверный формат ввода.Попробуйте снова: ");
            }
        }
        System.out.println("Приоритет задачи сейчас: " + task.getPriorityEnum());
        while (true) {
            System.out.println("Введите новый приоритет для задачи(LOW, MEDIUM, HIGH): ");
            String status = scanner.nextLine().toUpperCase();
            try {
               Priority newPriority=Priority.valueOf(status);
                task.setPriorityEnum(newPriority);
                System.out.println("Приоритет задачи изменён на " + newPriority);
                break;


            } catch (IllegalArgumentException e) {
                System.out.println("Неверный ввод.Такого приоритета нет");
            }
        }
    }
    public void filterTasks(){
        if(tasks.isEmpty()){
            System.out.println("Задач в списке нет");
            return;
        }
        while (true){
            System.out.println("Фильтровать задачи: ");
            System.out.println("1. По статусу");
            System.out.println("2. По приоритету");
            System.out.println("3. По просроченным дедлайнам");
            System.out.println("Выберите пункт: ");
            String input=scanner.nextLine();
            int number;
            try {
                number=Integer.parseInt(input);
            }catch (NumberFormatException e){
                System.out.println("Неверный ввод попробуйте еще раз");
                continue;
            }
            if(number==0){
                return;
            }


        List<Task> filtered=new ArrayList<>();



            switch (number) {
                case 1:
                    System.out.println("Введитe статус(NEW, IN_PROGRESS, DONE): ");
                    String statusInput = scanner.nextLine().toUpperCase();
                    for (Task t : tasks) {
                        if (t.getStatusEnum().name().equals(statusInput)) {
                            filtered.add(t);
                        }

                    }
                    break;
                case 2:
                    System.out.println("Введитe приориет(LOW, MEDIUM, HIGH): ");
                    String statusInput2 = scanner.nextLine().toUpperCase();
                    for (Task t : tasks) {
                        if (t.getPriorityEnum().name().equals(statusInput2)) {
                            filtered.add(t);
                        }
                    }
                    break;
                case 3:
                    LocalDate today = LocalDate.now();
                    for (Task t : tasks) {
                        if (t.getTime().isBefore(today) && t.getTime() != null) {
                            filtered.add(t);
                        }
                    }
                    break;
                default:
                    System.out.println("Не верный ввод.Попрбуйте еще раз");
                    continue;

            }
            if(filtered.isEmpty()){
                System.out.println("Нет задач, соответствующих фильтру");
                continue;
            }else {
                printTasks(filtered);
            }
            return;
        }





    }
    private void printTasks(List<Task> filteredTasks){
        if(filteredTasks.isEmpty()){
            System.out.println("Задач в списке нет");
            return;
        }
        for(int i=0;i< filteredTasks.size();i++){
            printTask(filteredTasks.get(i),i+1);
        }
    }



}
