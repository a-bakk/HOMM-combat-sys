package Player;

/**
 * Az varázsszárnyak varázslatot valósítja meg az osztály.
 *
 * <p>Egyetlen metódusa a különlegesHatás, mely elrepít egy tetszőleges egységet A-ból B-be.</p>
 */

public class Varazsszarnyak extends Varazslat {

    public Varazsszarnyak() {
        this.rendelkezik = false;
        this.ar = 110;
        this.mannaKoltseg = 8;
    }

    /**
     * Nem itt lett végül megvalósítva, viszont örökli a Varázslat osztályt, ami absztrakt, tehát muszáj volt.
     *
     * @param kiTamad -
     * @param kitTamad -
     * @param koordX -
     * @param koordY -
     * @return -
     */
    @Override
    public String kulonlegesHatas(Jatekos kiTamad, Jatekos kitTamad, int koordX, int koordY) {
        /* direkt hivasnal megvalositva, ez most sajnos ilyen */
        return null;
    }
}
