package databaze_osob;

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
    private final long rodneCislo;

    /**
     * Konstruktor objektu Osoba
     *
     * @param jmeno
     * @param prijmeni
     * @param rodneCislo
     */
    public Osoba(String jmeno, String prijmeni, long rodneCislo) {
        this.jmeno = jmeno;
        this.prijmeni = prijmeni;
        this.rodneCislo = rodneCislo;
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
    public long getRodneCislo() {
        return rodneCislo;
    }

    /**
     * Metoda pro výpočet věku Osoby
     *
     * @return
     */
    public int getAge() {
        return 0;
    }
}
