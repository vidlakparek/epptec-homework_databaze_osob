package databaze_osob;

import java.sql.*;
import java.util.Scanner;

/**
 * Hlavní třída programu.
 */
@SuppressWarnings("SqlResolve")
public class Main {

    ///**
    //* Lokální databáze osob
    //*/
    //private static final HashSet<Osoba> DB = new HashSet<>();

    /**
     * Pomocný objekt Scanner pro čtení vstupů
     */
    private static final Scanner SC = new Scanner(System.in);

    /**
     * Vytvoření spojení k SQLite databázi
     */
    private static Connection conn;

    static {
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:databaze_osob.db");
            Statement stmt = conn.createStatement();
            stmt.execute("CREATE TABLE IF NOT EXISTS osoba("
                    + "id integer PRIMARY KEY NOT NULL, "
                    + "jmeno text NOT NULL, "
                    + "prijmeni text NOT NULL, "
                    + "rodne_cislo text NOT NULL, "
                    + "vek text NOT NULL"
                    + ");");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

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
        if (jmeno.length() < 1) {
            System.out.println("Zadávej jméno ve správném formátu.");
            return false;
        }
        System.out.print("Zadejte příjmení: ");
        String prijmeni = SC.next();
        if (prijmeni.length() < 1) {
            System.out.println("Zadávej příjmení ve správném formátu.");
            return false;
        }
        System.out.print("Zadejte rodné číslo: ");
        String rodneCislo = SC.next();
        if (rodneCislo.length() != 12) {
            System.out.println("Zadávejte rodné číslo ve formátu RRMMDDXXXX");
            return false;
        }
        Osoba os = new Osoba(jmeno, prijmeni, rodneCislo);
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM osoba WHERE jmeno=? AND prijmeni=? AND rodne_cislo=?");
            ps.setString(1, os.getJmeno());
            ps.setString(2, os.getPrijmeni());
            ps.setString(3, os.getRodneCislo());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                System.out.println("Osoba s danými parametry se již v databázi nachází.");
                return false;
            }
            ps = conn.prepareStatement("INSERT INTO osoba(jmeno, prijmeni, rodne_cislo, vek) VALUES (?,?,?,?)");
            ps.setString(1, os.getJmeno());
            ps.setString(2, os.getPrijmeni());
            ps.setString(3, os.getRodneCislo());
            ps.setInt(4, os.getVek());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //DB.add(new Osoba(jmeno, prijmeni, rodneCislo));
        return true;
    }

    /**
     * Privátní metoda za jejiž pomoci můžeme osobu z databáze odstranit
     */
    private static boolean delete() {
        System.out.print("Zadejte jmeno, příjmení nebo rodné číslo: ");
        String input = SC.next();
        PreparedStatement ps;
        try {
            ps = conn.prepareStatement("DELETE FROM osoba WHERE jmeno=? OR prijmeni=? OR rodne_cislo=?");
            ps.setString(1, input);
            ps.setString(2, input);
            ps.setString(3, input);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        /*DB.removeIf(osoba -> osoba.getJmeno().equals(input)
                || osoba.getPrijmeni().equals(input)
                || Long.parseLong(input) == osoba.getRodneCislo());*/
        return true;
    }

    /**
     * Privátní metoda pro vyhledávání osob
     */
    private static Osoba search() {
        System.out.print("Zadejte jmeno, příjmení nebo rodné číslo: ");
        String input = SC.next();
        PreparedStatement ps;
        ResultSet rs;
        try {
            ps = conn.prepareStatement("SELECT * FROM osoba WHERE jmeno=? OR prijmeni=? OR rodne_cislo=?");
            ps.setString(1, input);
            ps.setString(2, input);
            ps.setString(3, input);
            rs = ps.executeQuery();
            if (!rs.next()) {
                System.out.println("Osoba se nenechází v databázi");
                return null;
            }

            Osoba os = new Osoba(rs.getString("jmeno"), rs.getString("prijmeni"), rs.getString("rodne_cislo"));
            System.out.println(os);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        /* for (Osoba osoba : DB) {
            if (osoba.getJmeno().equals(input)
                    || osoba.getPrijmeni().equals(input)
                    || Long.parseLong(input) == osoba.getRodneCislo()) {
                System.out.println(osoba);
                return osoba;
            }
            System.out.println("Osoba se nenechází v databázi");
        }*/
        return null;
    }
}
