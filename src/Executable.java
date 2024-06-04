import java.util.Scanner;

public class Executable {
    public static void main(String[] args) {
        MotMystere mot = new MotMystere("coucou", 1, 3);
        Scanner sc = new Scanner(System.in);
        String lettre = sc.nextLine();

        boolean quitte = false;
        while (!(quitte)) {
            System.out.println("Le mot que vous chercher contient " + lettre.length() + "lettres");
            System.out.println("");
        }
    }
}
