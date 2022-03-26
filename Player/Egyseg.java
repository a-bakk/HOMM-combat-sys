package Player;

import GameManager.GameManager;

public abstract class Egyseg {

    protected int ar;
    protected int minSebzes;
    protected int eletero;
    protected int sebesseg;
    protected int kezdemenyezes;
    protected String specKepesseg;
    protected int maxSebzes;
    protected int hanyVan; //adott egysegbol hany van
    protected boolean elhelyezett;
    protected int osszEletero;
    protected int maxEletero;

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

    public static int hanyEgyseg(Jatekos kinek) {
        int res = 0;
        for (int i = 0; i < kinek.egysegek.length; i++) {
                if (kinek.egysegek[i].hanyVan != 0) res++;
        }
        return res;
    }

    public static void setElhelyezettTrue(String milyenEgyseg, Jatekos kinek) {
        switch (milyenEgyseg) {
            case "foldmuves" -> kinek.egysegek[0].elhelyezett = true;
            case "ijasz" -> kinek.egysegek[1].elhelyezett = true;
            case "griff" -> kinek.egysegek[2].elhelyezett = true;
            case "hobgoblin" -> kinek.egysegek[3].elhelyezett = true;
            case "demon" -> kinek.egysegek[4].elhelyezett = true;
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
            case "hobgoblin" -> {
                return kinek.egysegek[3];
            }
            case "demon" -> {
                return kinek.egysegek[4];
            }
        }
        return null;
    }

    public static String tisztaHosSebzestKap(Jatekos tamado, Jatekos szenvedo, Egyseg melyikEgyseg) {
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

    public void tamadas() { //TODO make abstract

    }

    public void mozgas() {

    }

    public void varakozas() {

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

    public String getSpecKepesseg() {
        return specKepesseg;
    }

    public int getMaxSebzes() {
        return maxSebzes;
    }

    public int getHanyVan() {
        return hanyVan;
    }

    public boolean isElhelyezett() {
        return elhelyezett;
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