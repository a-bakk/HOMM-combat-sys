package Player;

import JatekPalya.Palya;

public class Villamcsapas extends Varazslat {

    public Villamcsapas() {
        this.ar = 60;
        this.rendelkezik = false;
        this.mannaKoltseg = 5;
    }

    @Override
    public String kulonlegesHatas(Jatekos kiTamad, Jatekos kitTamad, int koordX, int koordY) {
        int sebzes = kiTamad.jatekosHose.getVarazsero() * 30;
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
        return "[~] Sikeres villamcsapas magia! Az ellenseges egyseg sebzodott!";
    }
}
