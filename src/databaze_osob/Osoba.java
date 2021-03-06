package databaze_osob;

import java.time.LocalDate;
import java.time.Period;

/**
 * Objekt osoba.
 */
public class Osoba {
    /**
     * Privátní konstanta typu String uchovávající hodnotu jmeno
     */
    private final String jmeno;
    /**
     * Privátní konstanta typu String uchovávající hodnotu prijmeni
     */
    private final String prijmeni;
    /**
     * Privátní konstanta typu long uchovávající hodnotu rodné číslo
     */
    private final String rodneCislo;
    /**
     * Privátní konstanta věk
     */
    private final int vek;

    /**
     * Konstruktor objektu Osoba
     *
     * @param jmeno
     * @param prijmeni
     * @param rodneCislo
     */
    public Osoba(final String jmeno, final String prijmeni, final String rodneCislo) {
        this.jmeno = jmeno;
        this.prijmeni = prijmeni;
        this.rodneCislo = rodneCislo;

        LocalDate today = LocalDate.now();
        int day = (rodneCislo.charAt(4) - '0') * 10 + (rodneCislo.charAt(5) - '0');
        int month = (rodneCislo.charAt(2) - '0') * 10 + rodneCislo.charAt(3) - '0';
        if (month > 12) {
            month -= 50;
        }
        int year = (rodneCislo.charAt(0) - '0') * 10 + rodneCislo.charAt(1) - '0';
        year = (year) > 22 ? 1900 + year : 2000 + year;
        LocalDate birtDay = LocalDate.of(year, month, day);
        this.vek = Period.between(birtDay, today).getYears();
    }

    /**
     * Getter parametr jmeno typu String
     *
     * @return
     */
    public String getJmeno() {
        return jmeno;
    }

    /**
     * Getter parametr prijmeni typu String
     *
     * @return
     */
    public String getPrijmeni() {
        return prijmeni;
    }

    /**
     * Getter parametru rodne_cislo typu long
     *
     * @return
     */
    public String getRodneCislo() {
        return rodneCislo;
    }

    /**
     * Metoda pro výpočet věku Osoby
     *
     * @return
     */
    public int getVek() {
        return vek;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof Osoba osoba)) {
            return false;
        }
        return this.jmeno.equals(osoba.getJmeno())
                && this.prijmeni.equals(osoba.getPrijmeni())
                && this.rodneCislo.equals(osoba.getRodneCislo());
    }

    @Override
    public String toString() {
        return jmeno + " " + prijmeni + " s rodným číslem " + rodneCislo + ", kterému/které je " + vek;
    }
}
