import java.util.ArrayList;
import java.util.Scanner;

class Student {
    private int id;
    private String name;
    private double grade;

    public Student(int id, String name, double grade) {
        this.id = id;
        this.name = name;
        this.grade = grade;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public double getGrade() { return grade; }
    public void setGrade(double grade) { this.grade = grade; }

    @Override
    public String toString() {
        return String.format("ID: %d | Nome: %-15s | Voto: %.2f", id, name, grade);
    }
}

class StudentManager {
    private ArrayList<Student> students = new ArrayList<>();

    public void addStudent(Student s) {
        students.add(s);
    }

    public void displayStudents() {
        if (students.isEmpty()) {
            System.out.println("Nessun record presente.");
            return;
        }
        students.forEach(System.out::println);
    }

    public boolean updateGrade(int id, double newGrade) {
        for (Student s : students) {
            if (s.getId() == id) {
                s.setGrade(newGrade);
                return true;
            }
        }
        return false;
    }

    public boolean deleteStudent(int id) {
        return students.removeIf(s -> s.getId() == id);
    }
}

public class StudentSystemApp {
    public static void main(String[] args) {
        StudentManager manager = new StudentManager();
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n[1]Aggiungi [2]Lista [3]Aggiorna [4]Elimina [5]Esci");
            System.out.print("> ");
            choice = sc.nextInt();

            switch (choice) {
                case 1 -> {
                    System.out.print("ID: "); int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Nome: "); String name = sc.nextLine();
                    System.out.print("Voto: "); double g = sc.nextDouble();
                    manager.addStudent(new Student(id, name, g));
                }
                case 2 -> manager.displayStudents();
                case 3 -> {
                    System.out.print("ID: "); int id = sc.nextInt();
                    System.out.print("Nuovo Voto: "); double g = sc.nextDouble();
                    if(!manager.updateGrade(id, g)) System.out.println("ID non trovato.");
                }
                case 4 -> {
                    System.out.print("ID da eliminare: ");
                    if(!manager.deleteStudent(sc.nextInt())) System.out.println("ID non trovato.");
                }
            }
        } while (choice != 5);
        sc.close();
    }
}
