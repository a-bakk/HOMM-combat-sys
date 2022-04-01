package Player;

import JatekPalya.Palya;

/**
 * Az feltámasztás varázslatot valósítja meg az osztály.
 *
 * <p>Egyetlen metódusa a különlegesHatás, mely egy egységnek növeli az életerejét, maximálisan a maxÉleterőig.</p>
 */


public class Feltamasztas extends Varazslat {

    public Feltamasztas() {
        this.ar = 120;
        this.rendelkezik = false;
        this.mannaKoltseg = 6;
    }

    /**
     * Az feltámasztás varászlat hatását valósítja meg, meggyógyíthat egy egységet.
     *
     * @param kiTamad ki használja a varázslatot
     * @param kitTamad ki az elszenvedő
     * @param koordX melyik egységen szeretnénk használni, X koordináta
     * @param koordY melyik egységen szeretnénk használni, Y koordináta
     * @return a sikeres varázslás szöveges formában
     */
    @Override
    public String kulonlegesHatas(Jatekos kiTamad, Jatekos kitTamad, int koordX, int koordY) {
        int gyogyitas = kiTamad.jatekosHose.getVarazsero() * 50;
        if (Palya.getMezok()[koordX][koordY].isFoglalt()) {
            if (Palya.getMezok()[koordX][koordY].getTartalomEgyseg().getOsszEletero() + gyogyitas > Palya.getMezok()[koordX][koordY].getTartalomEgyseg().getMaxEletero()) {
                Palya.getMezok()[koordX][koordY].getTartalomEgyseg().setOsszEletero(Palya.getMezok()[koordX][koordY].getTartalomEgyseg().getMaxEletero());
                Palya.getMezok()[koordX][koordY].getTartalomEgyseg().setHanyVan(Palya.getMezok()[koordX][koordY].getTartalomEgyseg().getOsszEletero() / Palya.getMezok()[koordX][koordY].getTartalomEgyseg().getEletero());
            }
            else {
                Palya.getMezok()[koordX][koordY].getTartalomEgyseg().setOsszEletero(Palya.getMezok()[koordX][koordY].getTartalomEgyseg().getOsszEletero() + gyogyitas);
                if (Palya.getMezok()[koordX][koordY].getTartalomEgyseg().getOsszEletero() % Palya.getMezok()[koordX][koordY].getTartalomEgyseg().getEletero() == 0) {
                    Palya.getMezok()[koordX][koordY].getTartalomEgyseg().setHanyVan(Palya.getMezok()[koordX][koordY].getTartalomEgyseg().getOsszEletero() / Palya.getMezok()[koordX][koordY].getTartalomEgyseg().getEletero());
                }
                else {
                    Palya.getMezok()[koordX][koordY].getTartalomEgyseg().setHanyVan(Palya.getMezok()[koordX][koordY].getTartalomEgyseg().getOsszEletero() / Palya.getMezok()[koordX][koordY].getTartalomEgyseg().getEletero() + 1);
                }
            }
        }
        return "[~] Az egyseg sikeresen meg lett gyogyitva!";
    }
}
