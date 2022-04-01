package player;

import gamemanager.*;

/**
 * A varázslatoknak általános megvalósítása. Tekintettel arra, hogy minden varázslat máshogyan működik, absztrakt.
 */

public abstract class Varazslat {

    /**
     * A varázslatok ára.
     */
    protected int ar;
    /**
     * A varázslatok mannaköltsége.
     */
    protected int mannaKoltseg;
    /**
     * A játékos rendelkezik-e adott varázslattal.
     */
    protected boolean rendelkezik = false;

    /**
     * A varázslatok különleges hatása, mely absztrakt metódus, minden varázslat maga valósítja meg.
     *
     * @param kiTamad ki használja fel a varázslatot
     * @param kitTamad ki szenvedi el a következményeket
     * @param koordX melyik egységen/mezőn történik a varázslás, X koordináta
     * @param koordY melyik egységen/mezőn történik a varázslás, Y koordináta
     * @return a varázslat sikeressége, szöveges formában
     */
    public abstract String kulonlegesHatas(Jatekos kiTamad, Jatekos kitTamad, int koordX, int koordY);

    protected void setRendelkezik() {
        this.rendelkezik = true;
    }

    /**
     * Varázslatok vásárlásának a hátoldala, ez már csak minimálisan kommunikál a felhasználóval.
     *
     * <p>Csak azt jelzi, hogy sikeres-e a vásárlás vagy valamilyen hiba merült fel. Ellenőrzi, hogy elég aranya
     * van-e a vásárolónak, rendelkezik-e a már a varázslattal. Ha megfelelőek a körülmények, megvásárolja a
     * varázslatot, az árát levonja az aranyból.</p>
     *
     * @param kinek ki vásárol jelenleg varázslatokat
     * @param melyikVarazslat melyik varázslatról van szó, szöveges formában (pl. itt lett volna jó valamilyen Map, hogy ne szövegesen kelljen kezelni)
     */
    public static void setVarazslatok(Jatekos kinek, String melyikVarazslat) {
        System.out.println();
        boolean sikeres = false;
        switch (melyikVarazslat) {
            case "tuzlabda" -> {
                if (!kinek.varazslatok[0].rendelkezik && kinek.arany > kinek.varazslatok[0].ar) {
                    kinek.varazslatok[0].setRendelkezik();
                    System.out.println("[!] " + melyikVarazslat + " megvasarolva!");
                    sikeres = true;
                    kinek.arany -= kinek.varazslatok[0].ar;
                }
            }
            case "villamcsapas" -> {
                if (!kinek.varazslatok[1].rendelkezik && kinek.arany > kinek.varazslatok[1].ar) {
                    kinek.varazslatok[1].setRendelkezik();
                    System.out.println("[!] " + melyikVarazslat + " megvasarolva!");
                    sikeres = true;
                    kinek.arany -= kinek.varazslatok[1].ar;
                }
            }
            case "feltamasztas" -> {
                if (!kinek.varazslatok[2].rendelkezik && kinek.arany > kinek.varazslatok[2].ar) {
                    kinek.varazslatok[2].setRendelkezik();
                    System.out.println("[!] " + melyikVarazslat + " megvasarolva!");
                    sikeres = true;
                    kinek.arany -= kinek.varazslatok[2].ar;
                }
            }
            case "armageddon" -> {
                if (!kinek.varazslatok[3].rendelkezik && kinek.arany > kinek.varazslatok[3].ar) {
                    kinek.varazslatok[3].setRendelkezik();
                    System.out.println("[!] " + melyikVarazslat + " megvasarolva!");
                    sikeres = true;
                    kinek.arany -= kinek.varazslatok[3].ar;
                }
            }
            case "varazsszarnyak" -> {
                if (!kinek.varazslatok[4].rendelkezik && kinek.arany > kinek.varazslatok[4].ar) {
                    kinek.varazslatok[4].setRendelkezik();
                    System.out.println("[!] " + melyikVarazslat + " megvasarolva!");
                    sikeres = true;
                    kinek.arany -= kinek.varazslatok[4].ar;
                }
            }
        }
        if (sikeres) {
            GameManager.info("arany", kinek);
        } else {
            System.out.println("[!] Sikertelen! Nincs eleg penzed vagy mar megvasaroltad a varazslatot!");
            GameManager.info("arany", kinek);
        }
    }

    /**
     * Adott játékos varázslatait listázza, ha rendelkezik velük.
     *
     * @param kinek melyik játékosról van szó
     */
    public static void listVarazslatok(Jatekos kinek) {
        System.out.println("[~] Varazslatok: ");
        for (int i = 0; i < kinek.varazslatok.length; i++) {
            if (kinek.varazslatok[i].rendelkezik) {
                switch (i) {
                    case 0 -> System.out.println("[~] Tuzlabda");
                    case 1 -> System.out.println("[~] Villamcsapas");
                    case 2 -> System.out.println("[~] Feltamasztas");
                    case 3 -> System.out.println("[~] Armageddon");
                    case 4 -> System.out.println("[~] Varazsszarnyak");
                }
            }
        }
        System.out.println();
    }

    /**
     * Megszámolja, hogy adott játékosnak hány varázslata van, ezzel tér vissza.
     *
     * @param kinek melyik játékosról van szó
     * @return hány varázslattal rendelkezik, ha nincs akkor 0
     */
    public static int countVarazslatok(Jatekos kinek) {
        int res = 0;
        for (int i = 0; i < kinek.varazslatok.length; i++) {
            if (kinek.varazslatok[i].rendelkezik) {
                res++;
            }
        }
        return res;
    }

    /**
     * Eldönti, hogy adott játékos rendelkezik-e a lekérdezett varázslattal.
     *
     * @param kinek melyik játékosról van szó
     * @param milyenVarazslat milyen varázslatról van szó
     * @return igaz, ha a játékos rendelkezik a kérdéses varázslattal, ellenkező esetben hamis
     */
    public static boolean vanVarazslat(Jatekos kinek, String milyenVarazslat) {
        switch (milyenVarazslat) {
            case "tuzlabda" -> {
                if (kinek.varazslatok[0].rendelkezik) {
                    return true;
                }
            }
            case "villamcsapas" -> {
                if (kinek.varazslatok[1].rendelkezik) {
                    return true;
                }
            }
            case "feltamasztas" -> {
                if (kinek.varazslatok[2].rendelkezik) {
                    return true;
                }
            }
            case "armageddon" -> {
                if (kinek.varazslatok[3].rendelkezik) {
                    return true;
                }
            }
            case "varazsszarnyak" -> {
                if (kinek.varazslatok[4].rendelkezik) {
                    return true;
                }
            }
        }
        return false;
    }

    public void setRendelkezik(boolean rendelkezik) {
        this.rendelkezik = rendelkezik;
    }

    public int getMannaKoltseg() {
        return mannaKoltseg;
    }
}
