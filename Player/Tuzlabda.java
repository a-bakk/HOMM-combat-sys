package Player;

import JatekPalya.*;

/**
 * Az tűzlabda varázslatot valósítja meg az osztály.
 *
 * <p>Egyetlen metódusa a különlegesHatás, mely egy adott mező körüli összes egységet sebzi a varázserőnek a húszszorosával.</p>
 */

public class Tuzlabda extends Varazslat {

    public Tuzlabda() {
        this.rendelkezik = false;
        this.ar = 120;
        this.mannaKoltseg = 9;
    }

    /**
     * Az tűzlabda varázslat hatását valósítja meg, egy mezőt körbejárva sebzi az egységeket.
     *
     * @param kiTamad ki használja a varázslatot
     * @param kitTamad ki az elszenvedő
     * @param koordX melyik mezőn szeretnénk használni, X koordináta
     * @param koordY melyik mezőn szeretnénk használni, Y koordináta
     * @return a sikeres varázslás szöveges formában
     */
    @Override
    public String kulonlegesHatas(Jatekos kiTamad, Jatekos kitTamad, int koordX, int koordY) { // konvertalt koordinatakkal mukodik
        int sebzes = kiTamad.jatekosHose.getVarazsero() * 20;
        if (koordX-1 >= 0 && koordY-1 >=0) {
            foglaltMezoSebzodik(koordX-1, koordY-1, sebzes);
        }
        if (koordX-1 >= 0) {
            foglaltMezoSebzodik(koordX-1, koordY, sebzes);
        }
        if (koordX-1 >= 0 && koordY+1 <= 11) {
            foglaltMezoSebzodik(koordX-1, koordY+1, sebzes);
        }
        if (koordY-1 >= 0) {
            foglaltMezoSebzodik(koordX, koordY-1, sebzes);
        }
        if (koordY+1 <= 11) {
            foglaltMezoSebzodik(koordX, koordY+1, sebzes);
        }
        if (koordX+1 <= 9 && koordY-1 >= 0) {
            foglaltMezoSebzodik(koordX+1, koordY-1, sebzes);
        }
        if (koordX+1 <= 9) {
            foglaltMezoSebzodik(koordX+1, koordY, sebzes);
        }
        if (koordX+1 <= 9 && koordY+1 <= 11) {
            foglaltMezoSebzodik(koordX+1, koordY-1, sebzes);
        }
        foglaltMezoSebzodik(koordX, koordY, sebzes);
        return "[~] Sikeres tuzlabda magia! Az egysegek a megadott mezo korul sebzodtek!";
    }

    /**
     * A sebzés utáni egységek darabszámát szabályozza.
     *
     * @param koordX melyik egységről van szó, X koordináta
     * @param koordY melyik egységről van szó, Y koordináta
     * @param sebzes mennyivel lett sebezve az egység
     */
    private void foglaltMezoSebzodik(int koordX, int koordY, int sebzes) {
        if (Palya.getMezok()[koordX][koordY].isFoglalt()) {
            Palya.getMezok()[koordX][koordY].getTartalomEgyseg().setOsszEletero(Palya.getMezok()[koordX][koordY].getTartalomEgyseg().getOsszEletero() - sebzes);
            if (Palya.getMezok()[koordX][koordY].getTartalomEgyseg().getOsszEletero() % Palya.getMezok()[koordX][koordY].getTartalomEgyseg().getEletero() != 0) {
                Palya.getMezok()[koordX][koordY].getTartalomEgyseg().setHanyVan(Palya.getMezok()[koordX][koordY].getTartalomEgyseg().getOsszEletero() / Palya.getMezok()[koordX][koordY].getTartalomEgyseg().getEletero() + 1);
            }
            else {
                Palya.getMezok()[koordX][koordY].getTartalomEgyseg().setHanyVan(Palya.getMezok()[koordX][koordY].getTartalomEgyseg().getOsszEletero() / Palya.getMezok()[koordX][koordY].getTartalomEgyseg().getEletero());
            }
            if (Palya.getMezok()[koordX][koordY].getTartalomEgyseg().getOsszEletero() <= 0) {
                Palya.getMezok()[koordX][koordY].getTartalomEgyseg().setHanyVan(0);
            }
        }
    }
}
