package Player;

import GameManager.*;

public class Varazslat {

    protected int ar;
    protected int mannaKoltseg;
    protected boolean rendelkezik = false;
    //TODO hatas

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
        }
        if (sikeres) {
            GameManager.info("arany", kinek);
        } else {
            System.out.println("[!] Sikertelen! Nincs eleg penzed vagy mar megvasaroltad a varazslatot!");
            GameManager.info("arany", kinek);
        }
    }

    public static void listVarazslatok(Jatekos kinek) {
        System.out.println("[~] Ezekkel a varazslatokkal rendelkezel: ");
        for (int i = 0; i < 3; i++) {
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
                }
            }
        }
        System.out.println();
    }
}
