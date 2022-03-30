package Player;

import JatekPalya.*;

public class Tuzlabda extends Varazslat {

    public Tuzlabda() {
        this.rendelkezik = false;
        this.ar = 120;
        this.mannaKoltseg = 9;
    }


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
