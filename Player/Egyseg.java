package Player;

import GameManager.GameManager;

public class Egyseg {

    protected int ar;
    protected int minSebzes;
    protected int eletero;
    protected int sebesseg;
    protected int kezdemenyezes;
    protected String specKepesseg;
    protected int maxSebzes;
    protected int hanyVan; //adott egysegbol hany van
    protected boolean elhelyezett;

    public static boolean buyEgysegek(Jatekos kinek, String milyenEgyseg, int hanyat) {
        System.out.println();
        boolean sikeres = false;
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

    public static String toString(Jatekos kinek, String milyenEgyseg) {

        char kezdobetu = 'H'; // H - HIBA
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
        return " ------- ";
    }

    public static void listEgysegek(Jatekos kinek) {
        for (int i = 0; i < 3; i++) {
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
                }
            }
        }
        System.out.println();
    }

    public static boolean vanEgyseg(Jatekos kinek, String milyenEgyseg) {
        for (int i = 0; i < 3; i++) {
            if ("foldmuves".equals(milyenEgyseg) || "ijasz".equals(milyenEgyseg) || "griff".equals(milyenEgyseg)) {
                // TODO teljesen folos for
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
                }
            }
        }
        return false;
    }

    public static int hanyEgyseg(Jatekos kinek) {
        int res = 0;
        for (int i = 0; i < 3; i++) {
                if (kinek.egysegek[i].hanyVan != 0) res++;
        }
        return res;
    }

    public static void setElhelyezettTrue(String milyenEgyseg, Jatekos kinek) {
        switch (milyenEgyseg) {
            case "foldmuves" -> kinek.egysegek[0].elhelyezett = true;
            case "ijasz" -> kinek.egysegek[1].elhelyezett = true;
            case "griff" -> kinek.egysegek[2].elhelyezett = true;
        }
    }

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
        }
        return null;
    }

    public void tamadas() {

    }

    public void mozgas() {

    }

    public void varakozas() {

    }

}