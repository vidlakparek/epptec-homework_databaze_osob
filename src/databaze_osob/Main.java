package databaze_osob;

import java.util.HashSet;
import java.util.Scanner;

/**
 * Hlavní třída programu.
 */
public class Main {
    /**
     * Lokální "databáze" osob
     */
    private static final HashSet<Osoba> DB = new HashSet<>();
    /**
     * Pomocný objekt Scanner pro čtení vstupů
     */
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
     * Privátní metoda vytvářející novou osobu
     */
    private static boolean create() {
        System.out.print("Zadejte jméno: ");
        String jmeno = SC.next();
        System.out.print("Zadejte příjmení: ");
        String prijmeni = SC.next();
        System.out.print("Zadejte rodné číslo (bez /): ");
        long rodneCislo = SC.nextLong();
        DB.add(new Osoba(jmeno, prijmeni, rodneCislo));
        return true;
    }

    /**
     * Privátní metoda za jejiž pomoci můžeme osobu z databáze odstranit
     */
    private static boolean delete() {
        System.out.print("Zadejte jmeno, příjmení nebo rodné číslo: ");
        String input = SC.next();
        DB.removeIf(osoba -> osoba.getJmeno().equals(input)
                || osoba.getPrijmeni().equals(input)
                || Long.parseLong(input) == osoba.getRodneCislo());
        return true;
    }

    /**
     * Privátní metoda pro vyhledávání osob
     */
    private static Osoba search() {
        System.out.print("Zadejte jmeno, příjmení nebo rodné číslo: ");
        String input = SC.next();
        for (Osoba osoba : DB) {
            if (osoba.getJmeno().equals(input)
                    || osoba.getPrijmeni().equals(input)
                    || Long.parseLong(input) == osoba.getRodneCislo()) {
                System.out.println(osoba);
                return osoba;
            }
            System.out.println("Osoba se nenechází v databázi");
        }
        return null;
    }
}
