package player;

import jatekpalya.Palya;

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

    /**
     * Úgy gondoltam, hogy a feltámasztást halott egységekre kell használni, de kurzusfórum alapján az élő egységek gyógyítására értendő.
     *
     * <p>Ez a halott egység feltámasztását valósítja meg, de akkor a végső verzióban csak az előbbi megvalósítás marad, amely képes
     * a pályán levő, még élő egységek gyógyítására.</p>
     *
     * @param kinek melyik játékosról van szó
     * @param milyenEgyseg milyen egységről van szó
     * @return ha sikeres true, amúgy false
     * @deprecated az első megvalósítás van használatban, viszont ezt a biztonság kedvéért nem törlöm
     */
    public static boolean feltamasztasHatas(Jatekos kinek, String milyenEgyseg) {
        Egyseg feltamasztando = Egyseg.resolveEgyseg(milyenEgyseg, kinek);
        if (!feltamasztando.rendelkezik || feltamasztando.getOsszEletero() > 0) {
            return false;
        }
        int gyogyitas = kinek.jatekosHose.getVarazsero() * 50;
        feltamasztando.setElhelyezett(false);
        if (feltamasztando.getOsszEletero() + gyogyitas > feltamasztando.getMaxEletero()) {
            feltamasztando.setOsszEletero(feltamasztando.getMaxEletero());
            feltamasztando.setHanyVan(feltamasztando.getOsszEletero() / feltamasztando.getEletero());
        }
        else {
            feltamasztando.setOsszEletero(0);
            feltamasztando.setOsszEletero(feltamasztando.getOsszEletero() + gyogyitas);
            if (feltamasztando.getOsszEletero() % feltamasztando.getEletero() == 0) {
                feltamasztando.setHanyVan(feltamasztando.getOsszEletero() / feltamasztando.getEletero());
            }
            else {
                feltamasztando.setHanyVan(feltamasztando.getOsszEletero() / feltamasztando.getEletero() + 1);
            }
        }
        return true;
    }

}
