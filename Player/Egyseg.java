package player;

import gamemanager.GameManager;
import jatekpalya.Palya;

/**
 * Az egységeket leíró osztály. Metódusai tartalmaznak beállítást, lekérést, csatabeli sebzéseknek a számítását.
 */

public class Egyseg {

    /**
     * Az egység ára.
     */
    protected int ar;
    /**
     * A sebzésintervallum alja.
     */
    protected int minSebzes;
    /**
     * Egység egy példányának az élterereje.
     */
    protected int eletero;
    /**
     * Az egység sebessége.
     */
    protected int sebesseg;
    /**
     * Az egység kezdeményezése.
     */
    protected int kezdemenyezes;
    /**
     * Az egység speciális képessége szövegként.
     */
    protected String specKepesseg;
    /**
     * A sebzésintervallum teteje.
     */
    protected int maxSebzes;
    /**
     * Adott egységből hánnyal rendelkezik a játékos.
     */
    protected int hanyVan;
    /**
     * Az egység már el van-e helyezve a pályán.
     */
    protected boolean elhelyezett;
    /**
     * Az egység összes példányának az összesített életereje.
     */
    protected int osszEletero;
    /**
     * A maximális életerő, amivel az egység rendelkezhet, a játék elején állítódik be.
     */
    protected int maxEletero;
    /**
     * Az egység a körben táamdott-e vissza.
     */
    protected boolean visszatamadott;

    /**
     * Az egységek megvásárlásáért felelős metódus. A tranzakció sikerességéről tájékoztatja a játékost.
     *
     * <p>Emelett ellenőrzi, hogy a vásárláshoz elegendő aranya van-e a játékosnak. Ha sikeres, levonja
     * az egységek árát az aranyból, ha nem, hibaüzenettel jelez vissza. Ha negatív számút szeretnénk vásárolni,
     * abszolút értékkel folytatja a vásárlást.</p>
     *
     * @param kinek melyik játékos vásárol egységeket
     * @param milyenEgyseg milyen egységeket szeretne vásárolni, szöveges formában
     * @param hanyat hány egységet szeretne vásárolni
     * @return sikeres-e a tranzakció
     */
    public static boolean buyEgysegek(Jatekos kinek, String milyenEgyseg, int hanyat) {
        System.out.println();
        boolean sikeres = false;
        if (hanyat < 0) hanyat = Math.abs(hanyat);
        switch (milyenEgyseg) {
            case "foldmuves" -> {
                if (kinek.arany >= hanyat * kinek.egysegek[0].ar) {
                    kinek.egysegek[0].hanyVan += hanyat;
                    kinek.arany -= hanyat * kinek.egysegek[0].ar;
                    System.out.println("[~] Sikeresen vasaroltal " + hanyat +" darab " + milyenEgyseg + " egyseget!");
                    sikeres = true;
                }
            }
            case "ijasz" -> {
                if (kinek.arany >= hanyat * kinek.egysegek[1].ar) {
                    kinek.egysegek[1].hanyVan += hanyat;
                    kinek.arany -= hanyat * kinek.egysegek[1].ar;
                    System.out.println("[~] Sikeresen vasaroltal " + hanyat +" darab " + milyenEgyseg + " egyseget!");
                    sikeres = true;
                }
            }
            case "griff" -> {
                if (kinek.arany >= hanyat * kinek.egysegek[2].ar) {
                    kinek.egysegek[2].hanyVan += hanyat;
                    kinek.arany -= hanyat * kinek.egysegek[2].ar;
                    System.out.println("[~] Sikeresen vasaroltal " + hanyat +" darab " + milyenEgyseg + " egyseget!");
                    sikeres = true;
                }
            }
            case "hobgoblin" -> {
                if (kinek.arany >= hanyat * kinek.egysegek[3].ar) {
                    kinek.egysegek[3].hanyVan += hanyat;
                    kinek.arany -= hanyat * kinek.egysegek[3].ar;
                    System.out.println("[~] Sikeresen vasaroltal " + hanyat +" darab " + milyenEgyseg + " egyseget!");
                    sikeres = true;
                }
            }
            case "demon" -> {
                if (kinek.arany >= hanyat * kinek.egysegek[4].ar) {
                    kinek.egysegek[4].hanyVan += hanyat;
                    kinek.arany -= hanyat * kinek.egysegek[4].ar;
                    System.out.println("[~] Sikeresen vasaroltal " + hanyat +" darab " + milyenEgyseg + " egyseget!");
                    sikeres = true;
                }
            }
            default -> {
                System.out.println("[!] Sikertelen! Nem letezik ilyen egyseg!");
                return false;
            }
        }
        if (sikeres) {
            GameManager.info("arany", kinek);
            return true;
        }
        else {
            System.out.println("[!] Sikertelen! Nincs eleg aranyad adott szamu egyseg megvasarlasahoz!");
            GameManager.info("arany", kinek);
            return false;
        }

    }

    /**
     * Ismert egységből a nevének a lekérdezése.
     *
     * @param jatekos melyik játékos egységéről van szó
     * @param egyseg milyen egységnek a neve szükséges
     * @return az egység neve szöveges formában
     */
    public static String egysegNev(Jatekos jatekos, Egyseg egyseg) {
        if (egyseg == jatekos.egysegek[0]) {
            return "foldmuves";
        }
        if (egyseg == jatekos.egysegek[1]) {
            return "ijasz";
        }
        if (egyseg == jatekos.egysegek[2]) {
            return "griff";
        }
        if (egyseg == jatekos.egysegek[3]) {
            return "hobgoblin";
        }
        if (egyseg == jatekos.egysegek[4]) {
            return "demon";
        }
        return null;
    }

    /**
     * A pályán való kiíratás szövegezését valósítja meg.
     *
     * <p>A kiíratásban szerepel például, hogy melyik játékosé az egység, milyen egység, hány van belőle, mind rövidített formában.</p>
     *
     * @param kinek melyik játékosról van szó
     * @param milyenEgyseg milyen egységről van szó
     * @return a kiírandó szöveg
     */
    public static String toString(Jatekos kinek, String milyenEgyseg) {

        char kezdobetu = '-'; // - HIBA
        int maxHossz = 0;
        int szam = 0;
        char valodiJatekos = kinek.isSzamitoGep() ? 'G' : 'J';

        switch (milyenEgyseg) {
            case "foldmuves" -> {
                kezdobetu = 'F';
                szam = kinek.egysegek[0].hanyVan;
                maxHossz = String.valueOf(kinek.egysegek[0].hanyVan).length();
            }
            case "ijasz" -> {
                kezdobetu = 'I';
                szam = kinek.egysegek[1].hanyVan;
                maxHossz = String.valueOf(kinek.egysegek[1].hanyVan).length();
            }
            case "griff" -> {
                kezdobetu = 'G';
                szam = kinek.egysegek[2].hanyVan;
                maxHossz = String.valueOf(kinek.egysegek[2].hanyVan).length();
            }
            case "hobgoblin" -> {
                kezdobetu = 'H';
                szam = kinek.egysegek[3].hanyVan;
                maxHossz = String.valueOf(kinek.egysegek[3].hanyVan).length();
            }
            case "demon" -> {
                kezdobetu = 'D';
                szam = kinek.egysegek[4].hanyVan;
                maxHossz = String.valueOf(kinek.egysegek[4].hanyVan).length();
            }
        }

        switch (maxHossz) {
            case 3 -> {
                return " -" + valodiJatekos + "-" + kezdobetu + "-" + szam + "- ";
            }
            case 2 -> {
                return " -" + valodiJatekos + "-" + kezdobetu + "-" + szam + "-- ";
            }
            case 1 -> {
                return " -" + valodiJatekos + "-" + kezdobetu + "-" + szam + "--- ";
            }
        }
        return " --------- ";
    }

    /**
     * A játékos egységeit listázza, ha rendelkezik velük, illetve, hogy hány van belőlük.
     *
     * @param kinek melyik játékosnak az egységeik szeretnénk listázni
     */
    public static void listEgysegek(Jatekos kinek) {
        for (int i = 0; i < kinek.egysegek.length; i++) {
            if (kinek.egysegek[i].hanyVan != 0) {
                switch (i) {
                    case 0 -> {
                        if (kinek.egysegek[0].hanyVan != 0)
                            System.out.println("[~] Foldmuves: " + kinek.egysegek[0].hanyVan);
                    }
                    case 1 -> {
                        if (kinek.egysegek[1].hanyVan != 0)
                            System.out.println("[~] Ijasz: " + kinek.egysegek[1].hanyVan);
                    }
                    case 2 -> {
                        if (kinek.egysegek[2].hanyVan != 0)
                            System.out.println("[~] Griff: " + kinek.egysegek[2].hanyVan);
                    }
                    case 3 -> {
                        if (kinek.egysegek[3].hanyVan != 0)
                            System.out.println("[~] Hobgoblin: " + kinek.egysegek[3].hanyVan);
                    }
                    case 4 -> {
                        if (kinek.egysegek[4].hanyVan != 0)
                            System.out.println("[~] Demon: " + kinek.egysegek[4].hanyVan);
                    }
                }
            }
        }
        System.out.println();
    }

    /**
     * Megmondja, hogy van az egység, amellyel a játékos ugyan rendelkezik, el van-e helyezve a pályán.
     *
     * @param kinek melyik játékosról van szó
     * @param milyenEgyseg milyen egység, amiről szeretnénk megtudni az információt
     * @return igen, ha még nincs elhelyezve, nem ellenkező esetben
     */
    public static boolean vanEgyseg(Jatekos kinek, String milyenEgyseg) {
        for (int i = 0; i < kinek.egysegek.length; i++) {
            if ("foldmuves".equals(milyenEgyseg) || "ijasz".equals(milyenEgyseg) || "griff".equals(milyenEgyseg) || "hobgoblin".equals(milyenEgyseg) || "demon".equals(milyenEgyseg)) {
                switch (milyenEgyseg) {
                    case "foldmuves" -> {
                        if (kinek.egysegek[0].hanyVan != 0 && !kinek.egysegek[0].elhelyezett) {
                            return true;
                        }
                    }
                    case "ijasz" -> {
                        if (kinek.egysegek[1].hanyVan != 0 && !kinek.egysegek[1].elhelyezett) {
                            return true;
                        }
                    }
                    case "griff" -> {
                        if (kinek.egysegek[2].hanyVan != 0 && !kinek.egysegek[2].elhelyezett) {
                            return true;
                        }
                    }
                    case "hobgoblin" -> {
                        if (kinek.egysegek[3].hanyVan != 0 && !kinek.egysegek[3].elhelyezett) {
                            return true;
                        }
                    }
                    case "demon" -> {
                        if (kinek.egysegek[4].hanyVan != 0 && !kinek.egysegek[4].elhelyezett) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * Hány egységgel rendelkezik a játékos.
     *
     * @param kinek melyik játékosról van szó
     * @return hány egységgel rendelkezik a játékos
     */
    public static int hanyEgyseg(Jatekos kinek) {
        int res = 0;
        for (int i = 0; i < kinek.egysegek.length; i++) {
                if (kinek.egysegek[i].hanyVan != 0) res++;
        }
        return res;
    }

    /**
     * Adott egységnek átállítja az elhelyezettségét.
     *
     * @param milyenEgyseg milyen egységet szeretnénk beállítani
     * @param kinek melyik játékos rendelkezik az egységgel
     */
    public static void setElhelyezettTrue(String milyenEgyseg, Jatekos kinek) {
        switch (milyenEgyseg) {
            case "foldmuves" -> kinek.egysegek[0].elhelyezett = true;
            case "ijasz" -> kinek.egysegek[1].elhelyezett = true;
            case "griff" -> kinek.egysegek[2].elhelyezett = true;
            case "hobgoblin" -> kinek.egysegek[3].elhelyezett = true;
            case "demon" -> kinek.egysegek[4].elhelyezett = true;
        }
    }

    /**
     * Az összes egység visszatámadott attribútumát hamisra állítja, a körök elején van meghívva.
     *
     * @param jatekos a nem-gépi játékos
     * @param szGep a számítógép-ellenfél
     */
    public static void setVisszatamadottFalse(Jatekos jatekos, Jatekos szGep) {
        for (int i = 0; i < jatekos.egysegek.length; i++) {
            jatekos.egysegek[i].visszatamadott = false;
        }
        for (int i = 0; i < szGep.egysegek.length; i++) {
            szGep.egysegek[i].visszatamadott = false;
        }
    }

    public boolean isVisszatamadott() {
        return visszatamadott;
    }

    public void setVisszatamadott(boolean visszatamadott) {
        this.visszatamadott = visszatamadott;
    }

    /**
     * Hasonló az egységNév()-hez, viszont fordítva működik: ha ismert az egység neve, visszaadja, hogy az milyen egység.
     *
     * @param milyenEgyseg milyen egységről van szó, szöveges formában
     * @param kinek melyik játékosról van szó
     * @return az egység, melyet a neve alapján keresünk
     */
    public static Egyseg resolveEgyseg(String milyenEgyseg, Jatekos kinek) {
        switch (milyenEgyseg) {
            case "foldmuves" -> {
                return kinek.egysegek[0];
            }
            case "ijasz" -> {
                return kinek.egysegek[1];
            }
            case "griff" -> {
                return kinek.egysegek[2];
            }
            case "hobgoblin" -> {
                return kinek.egysegek[3];
            }
            case "demon" -> {
                return kinek.egysegek[4];
            }
        }
        return null;
    }

    /**
     * Egy egység támadását valósítja meg.
     *
     * @param tamado melyik játékos támad
     * @param melyikEgyseg melyik egység szenvedi el a támadást
     * @return a támadás sikeressége szövegesen
     */
    public static String tisztaHosSebzestKap(Jatekos tamado, Egyseg melyikEgyseg) {
        int teljesSebzes = tamado.jatekosHose.getTamadas() * 10;
        melyikEgyseg.setOsszEletero(melyikEgyseg.getOsszEletero() - teljesSebzes);
        if (melyikEgyseg.getOsszEletero() <= 0) {
            melyikEgyseg.setHanyVan(0);
            return "[~] A tamadas sikeres, az ellenfel egysege meghalt!";
        }
        else {
            if (melyikEgyseg.getOsszEletero() % melyikEgyseg.getEletero() != 0) {
                melyikEgyseg.setHanyVan(melyikEgyseg.getOsszEletero() / melyikEgyseg.getEletero() + 1);
            }
            else {
                melyikEgyseg.setHanyVan(melyikEgyseg.getOsszEletero() / melyikEgyseg.getEletero());
            }
            return "[~] A tamadas sikeres! Az ellenfelnek maradt " + melyikEgyseg.getOsszEletero() / melyikEgyseg.getEletero() + " teljes eletereju egysege es meg egy "
                    + melyikEgyseg.getOsszEletero() % melyikEgyseg.getEletero() + " eletereju egysege!";
        }
    }

    /**
     * A hobgoblin speciális képességét valósítja meg, végzetesen megmérgezi az egység egy példányát.
     *
     * @param celpont melyik egység szenvedi el a mérgezést
     * @return a mérgezés sikeressége szövegesen
     */
    public static String hobgoblinMergezestKap(Egyseg celpont) {
        if (celpont == null) return null;
        celpont.setOsszEletero(celpont.getOsszEletero() - celpont.getEletero());
        celpont.setHanyVan(celpont.getHanyVan() - 1);
        if (celpont.getOsszEletero() <= 0) {
            celpont.setHanyVan(0);
            return "[~] A mergezes sikeres, az ellenfel egysege meg is halt!";
        }
        return "[~] A mergezes sikeres, az ellenfel egysegenek egy peldanya meghalt!";
    }

    /**
     * Eldönti, hogy van-e szomszédos ellenfél a lekérdezett egység közvetlen környezetében.
     *
     * @param szenvedo ki szenvedi el a támadást
     * @param koordX a lekérdezett egység X koordinátája
     * @param koordY a lekérdezett egység Y koordinátája
     * @param tamadX a támadó egység X koordinátája
     * @param tamadY a támadó egység Y koordinátája
     * @return ha van ilyen, igaz, ha nincs, hamis
     */
    public static boolean szomszedosEllenfel(Jatekos szenvedo, int koordX, int koordY, int tamadX, int tamadY) {

        if (Palya.getMezok()[tamadX][tamadY].getKiBirtokolja() == szenvedo && Palya.getMezok()[koordX][koordY].getTartalomEgyseg().getEletero() != 0) {
            if (koordX - 1 >= 0 && koordY - 1 >= 0) {
                if (koordX - 1 == tamadX && koordY - 1 == tamadY)
                    return true;
            }
            if (koordX - 1 >= 0) {
                if (koordX - 1 == tamadX && koordY == tamadY)
                    return true;
            }
            if (koordX - 1 >= 0 && koordY + 1 <= 11) {
                if (koordX - 1 == tamadX && koordY + 1 == tamadY)
                    return true;
            }
            if (koordY - 1 >= 0) {
                if (koordX == tamadX && koordY - 1 == tamadY)
                    return true;
            }
            if (koordY + 1 <= 11) {
                if (koordX == tamadX && koordY + 1 == tamadY)
                    return true;
            }
            if (koordX + 1 <= 9 && koordY - 1 >= 0) {
                if (koordX + 1 == tamadX && koordY - 1 == tamadY)
                    return true;
            }
            if (koordX + 1 <= 9) {
                if (koordX + 1 == tamadX && koordY == tamadY)
                    return true;
            }
            if (koordX + 1 <= 9 && koordY + 1 <= 11) {
                return koordX + 1 == tamadX && koordY + 1 == tamadY;
            }
        }

        return false;
    }

    /**
     * Az adott egységet sebzik, a metódus kiszámítja a megfelelő sebzéseket és levonja a támadott egység életerejéből, frissíti azt, hogy hány marad.
     *
     * @param kitTamad ki szenvedi el a támadást
     * @param tamado ki a támadó
     * @param koordX a támadó egységének X koordinátája
     * @param koordY a támadó egységének Y koordinátája
     * @return a támadás sikeressége szövegesen
     */
    public String sebzestKap(Jatekos kitTamad, Jatekos tamado, int koordX, int koordY) {
        Egyseg tamadoEgysege = Palya.getMezok()[koordX][koordY].getTartalomEgyseg();
        int alapSebzes = GameManager.mathRandom(tamadoEgysege.getMinSebzes(), tamadoEgysege.getMaxSebzes()) * tamadoEgysege.getHanyVan();
        //System.out.println("[ezcsakteszt] alapsebzes: " + alapSebzes);
        double tamadoSebzes = GameManager.applyTamadas(tamado, alapSebzes);
        //System.out.println("[ezcsakteszt] tamadosebzes: " + tamadoSebzes);
        double sajatVedekezes = 1.0 - (kitTamad.jatekosHose.getVedekezes() * 0.05);
        int vegsoSebzes = (int)Math.round((tamadoSebzes * sajatVedekezes));
        //System.out.println("[ezcsakteszt] vegsosebzes: " + vegsoSebzes);

        double kritEsely = tamado.jatekosHose.getSzerencse() * 0.05;
        if (Math.random() <= kritEsely) {
            vegsoSebzes *= 2;
            System.out.println("[~] Az egyseged kritikus sebzest is okoz!");
        }

        this.setOsszEletero(this.getOsszEletero() - vegsoSebzes);

        if (this.getOsszEletero() % this.getEletero() != 0) {
            this.setHanyVan(this.getOsszEletero() / this.getEletero() + 1);
        }
        else {
            this.setHanyVan(this.getOsszEletero() / this.getEletero());
        }
        if (this.getOsszEletero() <= 0) {
            this.setHanyVan(0);
            return "[~] Sikeres tamadas es megolted az egyseget!";
        }

        return "[~] Sikeres tamadas!";
    }

    /**
     * Az egységet visszatámadják. Kiszámítja a megfelelő sebzést, igazít a visszatámadott egység életerején, darabszámán.
     *
     * <p>Tekintettel arra, hogy a dokumentáció nem specifikálta, visszatámadásnál is lehetséges kritikus sebzést okozni.</p>
     *
     * @param kitTamad a visszatámadást elszenvedő játékos
     * @param tamado a visszatámadó játékos
     * @param koordX a visszatámadó játékos egységének X koordinátája
     * @param koordY a visszatámadó játékos egységének Y koordinátája
     * @return a visszatámadás sikeressége szövegesen
     */
    public String visszatamadasSebzestKap(Jatekos kitTamad, Jatekos tamado, int koordX, int koordY) {
        Egyseg tamadoEgysege = Palya.getMezok()[koordX][koordY].getTartalomEgyseg();
        int alapSebzes = GameManager.mathRandom(tamadoEgysege.getMinSebzes(), tamadoEgysege.getMaxSebzes()) * tamadoEgysege.getHanyVan();
        double tamadoSebzes = GameManager.applyTamadas(tamado, alapSebzes);
        double sajatVedekezes = 1 - (kitTamad.jatekosHose.getVedekezes() * 0.05);
        int vegsoSebzes = (int)Math.floor((tamadoSebzes * sajatVedekezes));

        double kritEsely = tamado.jatekosHose.getSzerencse() * 0.05;
        if (Math.random() <= kritEsely) {
            vegsoSebzes *= 2;
            System.out.println("[~] A visszatamado egyseg kritikusan sebez!");
        }

        vegsoSebzes /= 2;

        this.setOsszEletero(this.getOsszEletero() - vegsoSebzes);

        if (this.getOsszEletero() % this.getEletero() != 0) {
            this.setHanyVan(this.getOsszEletero() / this.getEletero() + 1);
        }
        else {
            this.setHanyVan(this.getOsszEletero() / this.getEletero());
        }
        if (this.getOsszEletero() <= 0) {
            this.setHanyVan(0);
            return "[~] Az egyseg visszatamadott es megolte az egysegedet!";
        }

        return "[~] Az egyseg visszatamadott!";
    }

    //region getters

    public int getAr() {
        return ar;
    }

    public int getMinSebzes() {
        return minSebzes;
    }

    public int getEletero() {
        return eletero;
    }

    public int getSebesseg() {
        return sebesseg;
    }

    public int getKezdemenyezes() {
        return kezdemenyezes;
    }

    public int getMaxSebzes() {
        return maxSebzes;
    }

    public int getHanyVan() {
        return hanyVan;
    }

    public int getOsszEletero() {
        return osszEletero;
    }

    public int getMaxEletero() {
        return maxEletero;
    }

    //endregion

    //region setters

    public void setOsszEletero(int osszEletero) {
        this.osszEletero = osszEletero;
    }

    public void setMaxEletero(int maxEletero) {
        this.maxEletero = maxEletero;
    }

    public void setHanyVan(int hanyVan) {
        this.hanyVan = hanyVan;
    }

    //endregion

}