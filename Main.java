import java.io.IOException;
import java.util.Scanner;

class Main {
    public static Scanner skaner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        while (true) {
            int opcja = 0;
            opcja = menu();

            switch (opcja) {
                case 1: {
                    System.out.println();
                    OP1();
                    break;
                }
                case 2: {
                    System.out.println();
                    OP2();
                    break;
                }
                case 3: {
                    System.out.println();
                    OP3();
                    break;
                }
                case 0: {
                    return;
                }
            }
        }

    }

    public static int menu() {
        int numer = 0;

        System.out.println();
        System.out.println();
        System.out.println("Menu: ");
        System.out.println("1. Dodaj studenta");
        System.out.println("2. Wypisz listę studentów");
        System.out.println("3. Wyszukaj po imieniu");
        System.out.println("0. Wyjście z programu");
        System.out.print("Wybierz opcję: ");

        numer = skaner.nextInt();
        return numer;
    }

    public static void OP1() throws IOException {

        Service1 s = new Service1();
        int wiek;
        String imie;
        String nazwisko;
        String data;

        skaner.nextLine();
        System.out.print("Podaj imie: ");
        imie = skaner.nextLine();
        System.out.print("Podaj nazwisko: ");
        nazwisko = skaner.nextLine();
        System.out.print("Podaj datę urodzenia DD-MM-YYYY: ");
        data = skaner.nextLine();
        System.out.print("Podaj wiek: ");
        wiek = skaner.nextInt();

        s.addStudent(new Student(imie, nazwisko, data, wiek));
        System.out.println("Dodano nowego studenta");

    }

    public static void OP2() throws IOException {

        Service1 s = new Service1();
        var students = s.getStudents();
        for (Student current : students) {
            System.out.println(current.ToString());
        }

    }

    public static void OP3() throws IOException {
        String imie;
        skaner.nextLine();
        System.out.print("Podaj imię: ");
        imie = skaner.nextLine();
        var student = (new Service1()).findStudentByName(imie);
        if (student == null) {
            System.out.println("Nie znaleziono");
        } else {
            System.out.print("Wynik: ");
            System.out.println(student.ToString());
        }
    }

}