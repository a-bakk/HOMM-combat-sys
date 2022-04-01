package Player;

import JatekPalya.Palya;

/**
 * Az armageddon varázslatot valósítja meg az osztály.
 *
 * <p>Egyetlen metódusa a különlegesHatás, mely <b>minden</b> egységet varázserő * 5 sebzéssel illet.</p>
 */

public class Armageddon extends Varazslat {

    public Armageddon() {
        this.rendelkezik = false;
        this.ar = 100;
        this.mannaKoltseg = 3;
    }

    /**
     * Az Armageddon varászlat hatását valósítja meg, minden egységre varázserő * 5 sebzést mér.
     *
     * @param kiTamad ki használja a varázslatot
     * @param kitTamad ki az elszenvedő
     * @param koordX kizárólag az öröklödés miatt, itt nem felhasznált
     * @param koordY kizárólag az öröklődés miatt, itt nem felhasznált
     * @return a sikeres varázslás szöveges formában
     */
    @Override
    public String kulonlegesHatas(Jatekos kiTamad, Jatekos kitTamad, int koordX, int koordY) {
        int sebzes = kiTamad.jatekosHose.getVarazsero() * 5;
        for (int i = 0; i < Palya.getMezok().length; i++) {
            for (int j = 0; j < Palya.getMezok()[koordX].length; j++) {
                Palya.getMezok()[i][j].getTartalomEgyseg().setOsszEletero(Palya.getMezok()[i][j].getTartalomEgyseg().getOsszEletero() - sebzes);
                if (Palya.getMezok()[i][j].getTartalomEgyseg().getOsszEletero() % Palya.getMezok()[i][j].getTartalomEgyseg().getEletero() != 0) {
                    Palya.getMezok()[i][j].getTartalomEgyseg().setHanyVan(Palya.getMezok()[i][j].getTartalomEgyseg().getOsszEletero() / Palya.getMezok()[i][j].getTartalomEgyseg().getEletero() + 1);
                }
                else {
                    Palya.getMezok()[i][j].getTartalomEgyseg().setHanyVan(Palya.getMezok()[i][j].getTartalomEgyseg().getOsszEletero() / Palya.getMezok()[i][j].getTartalomEgyseg().getEletero());
                }
                if (Palya.getMezok()[i][j].getTartalomEgyseg().getOsszEletero() <= 0) {
                    Palya.getMezok()[i][j].getTartalomEgyseg().setHanyVan(0);
                }
            }
        }
        return "[~] Sikeres armageddon magia! Minden egyseg sebzodott!";
    }
}
