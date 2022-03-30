package Player;

import GameManager.*;

public abstract class Varazslat {

    protected int ar;
    protected int mannaKoltseg;
    protected boolean rendelkezik = false;

    public abstract String kulonlegesHatas(Jatekos kiTamad, Jatekos kitTamad, int koordX, int koordY);

    protected void setRendelkezik() {
        this.rendelkezik = true;
    }

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

    public static void listVarazslatok(Jatekos kinek) {
        System.out.println("[~] Varazslatok: ");
        for (int i = 0; i < kinek.varazslatok.length; i++) {
            if (kinek.varazslatok[i].rendelkezik) {
                switch (i) {
                    case 0 -> {
                        System.out.println("[~] Tuzlabda");
                    }
                    case 1 -> {
                        System.out.println("[~] Villamcsapas");
                    }
                    case 2 -> {
                        System.out.println("[~] Feltamasztas");
                    }
                    case 3 -> {
                        System.out.println("[~] Armageddon");
                    }
                    case 4 -> {
                        System.out.println("[~] Varazsszarnyak");
                    }
                }
            }
        }
        System.out.println();
    }

    public static int countVarazslatok(Jatekos kinek) {
        int res = 0;
        for (int i = 0; i < kinek.varazslatok.length; i++) {
            if (kinek.varazslatok[i].rendelkezik) {
                res++;
            }
        }
        return res;
    }

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
