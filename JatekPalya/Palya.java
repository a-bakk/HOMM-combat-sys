package JatekPalya;

import Player.*;
import GameManager.*;

import java.util.LinkedList;
import java.util.Queue;

//TODO 0 hp egysegeket nem kiirni
public class Palya {

    private static final int PALYAMERET_S = 10;
    private static final int PALYAMERET_O = 12;
    private static final int PALYAMERET_S_EXTENDED = 11;
    private static final int PALYAMERET_O_EXTENDED = 16;
    static Mezo[][] mezok = new Mezo[10][12];

    public static void setMezok() {
        for (int i = 0; i < PALYAMERET_S; i++) {
            for (int j = 0; j < PALYAMERET_O; j++) {
                mezok[i][j] = new Mezo();
            }
        }
    }

    public static void updateMezok() {
        for (int i = 0; i < PALYAMERET_S; i++) {
            for (int j = 0; j< PALYAMERET_O; j++) {
                if (mezok[i][j].isFoglalt()) {
                    mezok[i][j].setMezo(mezok[i][j].getMilyenEgyseg(), mezok[i][j].getKiBirtokolja(), mezok[i][j].getTartalomEgyseg());
                }
            }
        }
    }

    public static void repaintPalya(Jatekos jatekos1, Jatekos jatekos2) {
        updateMezok();
        paintPalya(jatekos1, jatekos2);
    }

    public static boolean foglalt(int koordX, int koordY) {
        return mezok[koordX][koordY].isFoglalt();
    }

    public static void mezoFoglal(int koordX, int koordY, String milyenEgyseg, Jatekos kinek, Egyseg melyikEgyseg) {
        mezok[koordX][koordY].setMezo(milyenEgyseg, kinek, melyikEgyseg);
    }

    public static void mezoFelszabadit(int koordX, int koordY) {
        mezok[koordX][koordY].resetMezo();
    }

    public static Egyseg chooseMezoEnemyLetezik(int koordX, int koordY, Jatekos kiTamad, Jatekos kitTamad) {

        if (!foglalt(koordX, koordY)) {
            return null;
        }

        if (mezok[koordX][koordY].getKiBirtokolja() == kitTamad) {
            return mezok[koordX][koordY].getTartalomEgyseg();
        }

        return null;
    }

    /*public static void hosTamad(int koordX, int koordY, Jatekos kiTamad, Jatekos kitTamad) {
        Egyseg target = mezok[koordX][koordY].getTartalomEgyseg();
        //
    }*/

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
                            case 0, 15 -> {
                                System.out.print(ures);
                            }
                            case 1 -> {
                                System.out.print(kisUres);
                            }
                            case 14 -> {
                                System.out.print(hatosUres);
                            }
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
                            case 0 -> {
                                System.out.print(jatekos1Megjelenit + "        ");
                            }
                            case 1 -> {
                                System.out.print(" ----4---- ");
                            }
                            case 14 -> {
                                System.out.print(hatosUres);

                            }
                            case 15 -> {
                                System.out.print(jatekos2Megjelenit);
                            }
                            default -> {
                                System.out.print(mezok[i-1][j-2].getTartalom());
                            }
                        }
                    }
                    case 5 -> {
                        switch (j) {
                            case 0 -> {
                                if (String.valueOf(jatekos1.getManna()).length() == 2) {
                                    System.out.print("Manna: " + jatekos1.getManna() + "      ");
                                }
                                else {
                                    System.out.print("Manna: " + jatekos1.getManna() + "     ");
                                }
                            }
                            case 1 -> {
                                System.out.print(" ----5---- ");
                            }
                            case 14 -> {
                                System.out.print(hatosUres);
                            }
                            case 15 -> {
                                System.out.print("Manna: " + jatekos2.getManna());
                            }
                            default -> {
                                System.out.print(mezok[i-1][j-2].getTartalom());
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
                            case 1 -> {
                                System.out.print(" ----6---- ");
                            }
                            case 14 -> {
                                System.out.print(hatosUres);
                            }
                            case 15 -> {
                                System.out.print("Arany: " + jatekos2.getArany());
                            }
                            default -> {
                                System.out.print(mezok[i-1][j-2].getTartalom());
                            }
                        }
                    }
                    default -> {
                        switch (j) {
                            case 0, 15 -> {
                                System.out.print(ures);
                            }
                            case 1 -> {
                                if (i <= 9) {
                                    System.out.print(" ----" + (i) + "---- ");
                                }
                                else {
                                    System.out.print(" ----" + (i) + "--- ");
                                }
                            }
                            case 14 -> {
                                System.out.print(hatosUres);
                            }
                            default -> {
                                System.out.print(mezok[i-1][j-2].getTartalom());
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

    public static int getIndexX(Egyseg egyseg) {
        for (int i = 0; i < PALYAMERET_S; i++) {
            for (int j = 0; j < PALYAMERET_O; j++) {
                if (mezok[i][j].getTartalomEgyseg() == egyseg) return i;
            }
        }
        return 0;
    }

    public static int getIndexY(Egyseg egyseg) {
        for (int i = 0; i < PALYAMERET_S; i++) {
            for (int j = 0; j < PALYAMERET_O; j++) {
                if (mezok[i][j].getTartalomEgyseg() == egyseg) return j;
            }
        }
        return 0;
    }

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

                int elem[] = varakozasiSor.poll();
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
