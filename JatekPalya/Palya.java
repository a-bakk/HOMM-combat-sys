package jatekpalya;

import player.*;

import java.util.LinkedList;

/**
 * Az osztály valósítja meg magát a játékpályát, kiegészítve kirajzoló, feltöltő és keresési metódusokkal.
 */

public class Palya {

    /**
     * A pálya sorainak száma, állandó.
     */
    private static final int PALYAMERET_S = 10;
    /**
     * A pálya oszlopainak a száma, állandó.
     */
    private static final int PALYAMERET_O = 12;
    /**
     * A kiegészített pálya sorainak a száma. Olyan információkkal egészül ki, mint például a játékosok aranyának és mannájának a megjelenítése, vagy kitöltő mezők.
     */
    private static final int PALYAMERET_S_EXTENDED = 11;
    /**
     * A kiegészített pálya oszlopainak a száma.
     */
    private static final int PALYAMERET_O_EXTENDED = 16;
    /**
     * Magát a pályát tároló kétdimenziós, Mezőkből álló tömb.
     */
    static Mezo[][] mezok = new Mezo[10][12];

    /**
     * Létrehozza a kétdimenziós tömb mezőit.
     */
    public static void setMezok() {
        for (int i = 0; i < PALYAMERET_S; i++) {
            for (int j = 0; j < PALYAMERET_O; j++) {
                mezok[i][j] = new Mezo();
            }
        }
    }

    /**
     * A mezők tartalmát frissíti.
     */
    public static void updateMezok() {
        for (int i = 0; i < PALYAMERET_S; i++) {
            for (int j = 0; j < PALYAMERET_O; j++) {
                if (mezok[i][j].isFoglalt()) {
                    mezok[i][j].setMezo(mezok[i][j].getMilyenEgyseg(), mezok[i][j].getKiBirtokolja(), mezok[i][j].getTartalomEgyseg());
                }
            }
        }
    }

    /**
     * Újra rajzolja a pályát úgy, hogy előtte frissíti a megjelenítendő mezőket.
     *
     * @param jatekos1 a bal oldali játékos
     * @param jatekos2 a jobb oldali játékos
     */
    public static void repaintPalya(Jatekos jatekos1, Jatekos jatekos2) {
        updateMezok();
        paintPalya(jatekos1, jatekos2);
    }

    /**
     * Foglalt-e a lekérdezett mező, az egyszerűbb hívásért.
     *
     * @param koordX a lekérdezett mező X koordinátája
     * @param koordY a lekérdezett mező Y koordinátája
     * @return foglalt-e a mező
     */
    public static boolean foglalt(int koordX, int koordY) {
        return mezok[koordX][koordY].isFoglalt();
    }

    /**
     * Gyakorlatilag elhelyez egy új egységet a pályán, lefoglalja neki a helyet.
     *
     * @param koordX hol legyen az egység, X koordináta
     * @param koordY hol legyen az egység, Y koordináta
     * @param milyenEgyseg milyen egység érkezik, szöveges forma
     * @param kinek melyik játékosé az egység
     * @param melyikEgyseg milyen egység objektum érkezik
     */
    public static void mezoFoglal(int koordX, int koordY, String milyenEgyseg, Jatekos kinek, Egyseg melyikEgyseg) {
        mezok[koordX][koordY].setMezo(milyenEgyseg, kinek, melyikEgyseg);
    }

    /**
     * A számítógép minimális intelligenciájához, megkeresi a hozzá legközelebbi egységet, hátulról.
     *
     * @param jatekos az ellenfélt jelöli, övé-e a keresett egység
     * @return az egység, amely hátulról az első és az ellenfélé
     */
    public static Egyseg getLastPlayerEgyseg(Jatekos jatekos) {
        for (int i = PALYAMERET_S - 1; i >= 0; i--) {
            for (int j = PALYAMERET_O - 1; j >= 0; j--) {
                if (mezok[i][j].isFoglalt() && mezok[i][j].getKiBirtokolja() == jatekos) {
                    return mezok[i][j].getTartalomEgyseg();
                }
            }
        }
        return null;
    }

    /**
     * Van-e olyan egység, amelyet a számítógép meg tud támadni.
     *
     * @param jatekos az ellenfélé-e az egység
     * @param koordX melyik egység körül keressen, X koordináta
     * @param koordY melyik egység körül keressen, Y koordináta
     * @return ha talált, az egység ami támadható, amúgy null
     */
    public static Egyseg vanSzGepKozelbenEgyseg(Jatekos jatekos, int koordX, int koordY) {
        if (koordX - 1 >= 0 && koordY - 1 >= 0) {
            if (mezok[koordX-1][koordY-1].getKiBirtokolja() == jatekos)
                return mezok[koordX-1][koordY-1].getTartalomEgyseg();
        }
        if (koordX - 1 >= 0) {
            if (mezok[koordX-1][koordY].getKiBirtokolja() == jatekos)
                return mezok[koordX-1][koordY].getTartalomEgyseg();
        }
        if (koordX - 1 >= 0 && koordY + 1 <= 11) {
            if (mezok[koordX-1][koordY+1].getKiBirtokolja() == jatekos)
                return mezok[koordX-1][koordY+1].getTartalomEgyseg();
        }
        if (koordY - 1 >= 0) {
            if (mezok[koordX][koordY-1].getKiBirtokolja() == jatekos)
                return mezok[koordX][koordY-1].getTartalomEgyseg();
        }
        if (koordY + 1 <= 11) {
            if (mezok[koordX][koordY+1].getKiBirtokolja() == jatekos)
                return mezok[koordX][koordY+1].getTartalomEgyseg();
        }
        if (koordX + 1 <= 9 && koordY - 1 >= 0) {
            if (mezok[koordX+1][koordY-1].getKiBirtokolja() == jatekos)
                return mezok[koordX+1][koordY-1].getTartalomEgyseg();
        }
        if (koordX + 1 <= 9) {
            if (mezok[koordX+1][koordY].getKiBirtokolja() == jatekos)
                return mezok[koordX+1][koordY].getTartalomEgyseg();
        }
        if (koordX + 1 <= 9 && koordY + 1 <= 11) {
            if (mezok[koordX+1][koordY+1].getKiBirtokolja() == jatekos)
                return mezok[koordX+1][koordY+1].getTartalomEgyseg();
        }
        return null;
    }

    /**
     * A játékos általt választott mező megfelel-e a követelményeknek, valóban a számítógépé-e, esetleg üres.
     *
     * @param koordX a választott egység X koordinátája
     * @param koordY a választott egység Y koordinátája
     * @param kitTamad a játékos, akinek a támadható egységét keresi, itt a számítógép
     * @return ha megfelelő az egység, akkor ő, ha nem, null
     */
    public static Egyseg chooseMezoEnemyLetezik(int koordX, int koordY, Jatekos kitTamad) {

        if (!foglalt(koordX, koordY)) {
            return null;
        }

        if (mezok[koordX][koordY].getKiBirtokolja() == kitTamad) {
            return mezok[koordX][koordY].getTartalomEgyseg();
        }

        return null;
    }

    /**
     * Kirajzolja a teljes, kibővített pályát a játékosok információival, egységeivel.
     *
     * @param jatekos1 a bal oldali játékos
     * @param jatekos2 a jobb oldali játékos
     */
    public static void paintPalya(Jatekos jatekos1, Jatekos jatekos2) {
        final String ures = "               ";
        final String hatosUres = "      ";
        final String kisUres = " --------- ";
        final String jatekos1Megjelenit = jatekos1.isSzamitoGep() ? "Szamitogep" : "Jatekos";
        final String jatekos2Megjelenit = jatekos2.isSzamitoGep() ? "Szamitogep" : "Jatekos";
        int oszlopCounter = 1;

        System.out.println("[!] Jelenleg igy nez ki a csatater: ");
        System.out.println();

        for (int i = 0; i < PALYAMERET_S_EXTENDED; i++) {
            for (int j = 0; j < PALYAMERET_O_EXTENDED; j++) {
                switch (i) {
                    case 0 -> {
                        switch (j) {
                            case 0, 15 -> System.out.print(ures);

                            case 1 -> System.out.print(kisUres);
                            case 14 -> System.out.print(hatosUres);
                            default -> {
                                if (String.valueOf(oszlopCounter).length() == 1) {
                                    System.out.print(" ----" + oszlopCounter + "---- ");
                                }
                                else {
                                    System.out.print(" ----" + oszlopCounter + "--- ");
                                }
                                oszlopCounter++;
                            }
                        }
                    }
                    case 4 -> {
                        switch (j) {
                            case 0 -> System.out.print(jatekos1Megjelenit + "        ");
                            case 1 -> System.out.print(" ----4---- ");
                            case 14 -> System.out.print(hatosUres);
                            case 15 -> System.out.print(jatekos2Megjelenit);
                            default -> {
                                if (mezok[i-1][j-2].getTartalomEgyseg() != null && mezok[i-1][j-2].getTartalomEgyseg().getOsszEletero() > 0) System.out.print(mezok[i-1][j-2].getTartalom());
                                else System.out.print(mezok[i-1][j-2].getDefaultTartalom());
                            }
                        }
                    }
                    case 5 -> {
                        switch (j) {
                            case 0 -> {
                                if (String.valueOf(jatekos1.getManna()).length() == 2) {
                                    System.out.print("Manna: " + jatekos1.getManna() + "      ");
                                }
                                else if (String.valueOf(jatekos1.getManna()).length() == 1) {
                                    System.out.print("Manna: " + jatekos1.getManna() + "       ");
                                }
                                else {
                                    System.out.print("Manna: " + jatekos1.getManna() + "     ");
                                }
                            }
                            case 1 -> System.out.print(" ----5---- ");
                            case 14 -> System.out.print(hatosUres);
                            case 15 -> System.out.print("Manna: " + jatekos2.getManna());
                            default -> {
                                if (mezok[i-1][j-2].getTartalomEgyseg() != null && mezok[i-1][j-2].getTartalomEgyseg().getOsszEletero() > 0) System.out.print(mezok[i-1][j-2].getTartalom());
                                else System.out.print(mezok[i-1][j-2].getDefaultTartalom());
                            }
                        }
                    }
                    case 6 -> {
                        switch (j) {
                            case 0 -> {
                                if (String.valueOf(jatekos1.getArany()).length() == 3) {
                                    System.out.print("Arany: " + jatekos1.getArany() + "     ");
                                }
                                else if (String.valueOf(jatekos1.getArany()).length() == 2) {
                                    System.out.print("Arany: " + jatekos1.getArany() + "      ");
                                }
                                else {
                                    System.out.print("Arany: " + jatekos1.getArany() + "       ");
                                }
                            }
                            case 1 -> System.out.print(" ----6---- ");
                            case 14 -> System.out.print(hatosUres);
                            case 15 -> System.out.print("Arany: " + jatekos2.getArany());
                            default -> {
                                if (mezok[i-1][j-2].getTartalomEgyseg() != null && mezok[i-1][j-2].getTartalomEgyseg().getOsszEletero() > 0) System.out.print(mezok[i-1][j-2].getTartalom());
                                else System.out.print(mezok[i-1][j-2].getDefaultTartalom());
                            }
                        }
                    }
                    default -> {
                        switch (j) {
                            case 0, 15 -> System.out.print(ures);
                            case 1 -> {
                                if (i <= 9) {
                                    System.out.print(" ----" + (i) + "---- ");
                                }
                                else {
                                    System.out.print(" ----" + (i) + "--- ");
                                }
                            }
                            case 14 -> System.out.print(hatosUres);
                            default -> {
                                if (mezok[i-1][j-2].getTartalomEgyseg() != null && mezok[i-1][j-2].getTartalomEgyseg().getOsszEletero() > 0) System.out.print(mezok[i-1][j-2].getTartalom());
                                else System.out.print(mezok[i-1][j-2].getDefaultTartalom());
                            }
                        }
                    }
                }
            }
            System.out.println();
        }

    }

    public static Mezo[][] getMezok() {
        return mezok;
    }

    /**
     * A lekérdezett egység aktuális, X koordinátája.
     *
     * @param egyseg melyik egységről van szó
     * @return ha megtalálja, a megfelelő koordináta, ha nem, 0
     */
    public static int getIndexX(Egyseg egyseg) {
        for (int i = 0; i < PALYAMERET_S; i++) {
            for (int j = 0; j < PALYAMERET_O; j++) {
                if (mezok[i][j].getTartalomEgyseg() == egyseg) return i;
            }
        }
        return 0;
    }

    /**
     * A lekérdezett egység aktuális, Y koordinátája.
     *
     * @param egyseg melyik egységről van szó
     * @return ha megtalálja, a megfelelő koordináta, ha nem, 0
     */
    public static int getIndexY(Egyseg egyseg) {
        for (int i = 0; i < PALYAMERET_S; i++) {
            for (int j = 0; j < PALYAMERET_O; j++) {
                if (mezok[i][j].getTartalomEgyseg() == egyseg) return j;
            }
        }
        return 0;
    }

    /**
     * Eldönti, hogy létezik-e a kezdő és vég pont között járható út. Ha igen, akkor ezek közül a legrövidebbet adja vissza.
     *
     * <p>A metódus a Breadth First Search algoritmust próbálja implementálni, 8-szomszédossággal, kétdimenziós tömbben. A bejárt mezőket számontartva mindig bővíti
     * a keresési teret, egészen addig, ameddig megfelelő útvonalat talál, ha van ilyen. A megfelelő mezőket a varakozasiSor láncolt listában
     * tárolja, ami gyakorlatilag egy Queue, viszont, ha minden igaz, a láncolt lista alapból a Queue interfacet (is) implementálja, ezért
     * működés szempontjából itt megfelelő.</p>
     *
     * @param mezok milyen mezők között keresi az útvonalat
     * @param startX kezdő X koordináta
     * @param startY kezdő Y koordináta
     * @param vegX az út végének X koordinátája
     * @param vegY az út végének Y koordinátája
     * @return ha létezik út, a legrövidebb úthoz szükséges lépések száma, ha nincs, -1
     */
    public static int palyaBreadthFirstSearch(Mezo[][] mezok, int startX, int startY, int vegX, int vegY) {

        if (!mezok[startX][startY].isFoglalt() || mezok[vegX][vegY].isFoglalt())
            return -1;

        int[][] korbeJar = {{0,1},{0,-1},{1,0},{-1,0},{1,-1},{-1,1},{-1,-1},{1,1}};

        boolean[][] voltMar = new boolean[PALYAMERET_S][PALYAMERET_O];
        voltMar[startX][startY] = true;

        LinkedList<int[]> varakozasiSor = new LinkedList<>(); //Queue
        varakozasiSor.add(new int[]{startX, startY});

        int szamolo = 1;

        while (!varakozasiSor.isEmpty()) {

            for (int i = 0; i < varakozasiSor.size(); i++) {

                int[] elem = varakozasiSor.poll();
                int elemX = elem != null ? elem[0] : 0;
                int elemY = elem != null ? elem[1] : 0;

                if (elemX == vegX && elemY == vegY) {
                    return szamolo;
                }

                for (int j = 0; j < 8; j++) {

                    int kovetkezoX = elemX + korbeJar[j][0];
                    int kovetkezoY = elemY + korbeJar[j][1];

                    if (kovetkezoX >= 0 && kovetkezoX < PALYAMERET_S && kovetkezoY >= 0 && kovetkezoY < PALYAMERET_O &&
                        !voltMar[kovetkezoX][kovetkezoY] && !mezok[kovetkezoX][kovetkezoY].isFoglalt()) {

                        varakozasiSor.add(new int[]{kovetkezoX, kovetkezoY});
                        voltMar[kovetkezoX][kovetkezoY] = true;
                    }
                }
            }
            szamolo++;
        }
        return -1;
    }

}
