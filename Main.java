import java.io.IOException;
import java.util.Scanner;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.InputMismatchException;

class ZleImie extends Exception {} 
class ZleNazwisko extends Exception {}
class ZlaData extends Exception {} 
class ZlyWiek extends Exception {} 
class ZlaOpcja extends Exception {}

class Main {
    public static Scanner skaner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        while (true) {

          try{
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
          } catch (IOException e){
          } catch (ZleImie e){
            System.out.println("Wykryto błąd w imieniu!");
          } catch (ZleNazwisko e){
            System.out.println("Wykryto błąd w nazwisku!");
          } catch (ZlaData e){
            System.out.println("Wykryto błąd w dacie urodzenia!");
          } catch (ZlyWiek e){
            System.out.println("Wykryto błedny wiek!");
          } catch (ZlaOpcja e){
            System.out.println("Wpisz liczbę!");
          }
        }

    }

    public static int menu() throws ZlaOpcja {
        int numer = 0;

        System.out.println();
        System.out.println();
        System.out.println("Menu: ");
        System.out.println("1. Dodaj studenta");
        System.out.println("2. Wypisz listę studentów");
        System.out.println("3. Wyszukaj po imieniu");
        System.out.println("0. Wyjście z programu");
        System.out.print("Wybierz opcję: ");

        try{
            numer = skaner.nextInt();
          } catch (InputMismatchException e){
            throw new ZlaOpcja();
          }
        return numer;
    }

    public static void OP1() throws IOException, ZleImie,ZleNazwisko,ZlaData,ZlyWiek {

        Service1 s = new Service1();
        int wiek;
        String imie;
        String nazwisko;
        String data;

        imie = PI();
        nazwisko = PN();
        data = PD();
        wiek = PW();

        s.addStudent(new Student(imie, nazwisko, data, wiek));
        System.out.println("Dodano nowego studenta");

    }

    public static String PI() throws ZleImie{

      String imie;

      skaner.nextLine();
      System.out.print("Podaj imię: ");
      imie = skaner.nextLine();
      if(imie.contains(" ")){
        throw new ZleImie();
      }
      return imie;
    } 

    public static String PN() throws ZleNazwisko{

      String nazwisko;

      System.out.print("Podaj nazwisko: ");
      nazwisko = skaner.nextLine();
      if(nazwisko.contains(" ")){
        throw new ZleNazwisko();
      }
      return nazwisko;
    }

    public static String PD() throws ZlaData{

      String data;

      System.out.print("Podaj datę urodzenia DD-MM-YYYY: ");
      data = skaner.nextLine();
      try{
        DateFormat format = new SimpleDateFormat("dd-MM-yyy");
        format.setLenient(false);
        format.parse(data);

        String[] poprawnosc = data.split("-");
        if(Integer.parseInt(poprawnosc[0]) < 0 || Integer.parseInt(poprawnosc[0]) > 31){
          throw new ZlaData();
        }
        if(Integer.parseInt(poprawnosc[1]) < 0 || Integer.parseInt(poprawnosc[1]) > 12){
          throw new ZlaData();
        }
        if(Integer.parseInt(poprawnosc[2]) < 0 || Integer.parseInt(poprawnosc[2]) > 2022){
          throw new ZlaData();
        }
      } catch (ParseException e){
        throw new ZlaData();
      }
      
      return data;
    }

    public static int PW() throws ZlyWiek{

      int wiek;

      System.out.print("Podaj wiek: ");
      wiek = skaner.nextInt();
      if (wiek < 0 || wiek > 100){
        throw new ZlyWiek();
      }
      return wiek;
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