package databaze_osob;

import java.util.HashSet;
import java.util.Scanner;

/**
 * Hlavní třída programu.
 */
public class Main {
    private static HashSet<Osoba> db = new HashSet<>();
    private static final Scanner SC = new Scanner(System.in);

    /**
     * Zaváděcí metoda programu
     *
     * @param args
     */
    public static void main(String[] args) {
        do {
            System.out.println("""
                    1 -> Přidat osobu
                    2 -> Odebrat osobu
                    3 -> Vyhledat osobu podle jména, příjmení nebo rodného čísla""");
            switch (SC.nextInt()) {
                case 1 -> create();
                case 2 -> delete();
                case 3 -> search();
                default -> System.out.println("Chybný vstup, použijte jednu z výše uvedených možností.");
            }
            System.out.println("""
                    Přejete si pokračovat ?\s
                    1 -> Ano\s
                    0 -> Ne""");
        } while (SC.nextInt() == 1);
    }

    /**
     *
     */
    private static void create() {
    }

    /**
     *
     */
    private static void delete() {
    }

    /**
     *
     */
    private static void search() {
    }
}
