package GameManager;

import JatekPalya.Palya;
import Player.*;

import java.util.Scanner;

public class GameManager {

    private static final Scanner scanner = new Scanner(System.in);

    private static String nehezseg = null;

    public static String getNehezseg() {
        return nehezseg;
    }

    public static void chooseDifficulty() {
        String input;
        System.out.print("[!] Valassz nehezsegi szintet! Lehetseges ertekek: konnyu, kozepes, nehez: ");
        input = scanner.nextLine();
        nehezseg = input;
        while((!"konnyu".equals(input)) && (!"kozepes".equals(input)) && (!"nehez".equals(input))) {
            System.out.print("[!] Rossz input! Adj meg helyes erteket: ");
            input = scanner.nextLine();
            nehezseg = input;
        }
        System.out.println("[~] A valasztott nehezsegi szint: " + input);
    }

    public static void welcomeMessage() {
        System.out.println("*************************************************\n*    Definitely not Heroes of Might & Magic     *\n*************************************************");
    }

    public static void info(String mod, Jatekos kinek) {

        if ("arany".equals(mod)) {
            System.out.println("[~] Jelenlegi egyenleged: " + kinek.getArany());
            System.out.println();
        }

        if ("aranyEsMana".equals(mod)) {
            System.out.println();
            System.out.println("[~] Jelenleg " + kinek.getArany() + " aranyad es " + kinek.getManna() + " mannad van!");
            System.out.println();
        }

        if ("all".equals(mod)) {
            System.out.println();
            System.out.println("[~] A csata elott osszesitve lathatod statjaidat!");
            System.out.println("[~] A jatek nehezsegi szintje: " + getNehezseg());
            System.out.println("[~] Tulajdonsagok: ");
            listTulajdonsagok(kinek);
            Varazslat.listVarazslatok(kinek);
            System.out.println("[~] Ezekkel az egysegekkel rendelkezel: ");
            Egyseg.listEgysegek(kinek);
            System.out.print("[!] A tovabblepeshez a TOVABB parancsot hasznald: ");
            String input = scanner.nextLine();
            while((!"TOVABB".equals(input))) {
                System.out.print("[!] Hibas input! Tovabblepni a TOVABB paranccsal tudsz: ");
                input = scanner.nextLine();
            }
            System.out.println();
            System.out.println();
        }

    }

    private static void listTulajdonsagok(Jatekos kinek) {
        System.out.println("[~] Tamadas: " + kinek.jatekosHose.getTamadas());
        System.out.println("[~] Vedekezes: " + kinek.jatekosHose.getVedekezes());
        System.out.println("[~] Varazsero: " + kinek.jatekosHose.getVarazsero());
        System.out.println("[~] Tudas: " + kinek.jatekosHose.getTudas());
        System.out.println("[~] Moral: " + kinek.jatekosHose.getMoral());
        System.out.println("[~] Szerencse: " + kinek.jatekosHose.getSzerencse());
        System.out.println();
    }

    public static void tulajdonsagPhase(Jatekos jatekos) {

        GameManager.info("arany", jatekos);

        System.out.println("[~] Osszd el a tulajdonsagpontokat! Az elso 5 aranyba kerul.");
        System.out.println("[~] Ha elegedett vagy, a tovabblepes a TOVABB paranccsal tortenik - a nagybetuk szamitanak!");
        System.out.println("[~] Jelenleg minden tulajdonsagra 1 - 1 tulajdonsagpont van elosztva!");
        System.out.println("[~] Lehetseges valasztasok: ");
        System.out.println("[~] tamadas, vedekezes, varazsero, tudas, moral, szerencse");
        System.out.print("[!] Valassz: ");

        String input = scanner.nextLine();

        while((!"TOVABB".equals(input))) {

            if ("tamadas".equals(input) || "vedekezes".equals(input) || "varazsero".equals(input) || "tudas".equals(input) || "moral".equals(input) || "szerencse".equals(input)) {
                jatekos.jatekosHose.tulajdonsagNovelo(jatekos, input);
                System.out.print("[!] Valassz: ");
            }
            else {
                System.out.print("[!] Hibas input! Adj meg helyes erteket: ");
            }

            input = scanner.nextLine();
        }

        System.out.println();
        System.out.println("[~] Sikeresen elosztottad a tulajdonsagpontokat!");
        listTulajdonsagok(jatekos);
        System.out.println();
    }

    public static void varazslatPhase(Jatekos jatekos) {

        GameManager.info("arany", jatekos);

        System.out.println("[~] Donthetsz arrol, hogy milyen varazslatok vasarolsz - vagy egyaltalan vasarolsz-e!");
        System.out.println("[~] Ha elegedett vagy, a tovabblepes a TOVABB paranccsal tortenik - a nagybetuk szamitanak!");
        System.out.println("[~] Jelenleg nincs egy varazslatod sem!");
        System.out.println("[~] Lehetseges valasztasok: ");
        System.out.println("[~] villamcsapas - ar: 60 arany - mannakoltseg: 5 - hatas: egy kivalasztott ellenseges egysegre (varazsero * 30) sebzes okozasa");
        System.out.println("[~] tuzlabda - ar: 120 arany - mannakoltseg: 9 - hatas: egy kivalasztott mezo koruli 3x3-as teruleten levo osszes (sajat, illetve ellenseges) egysegre (varazsero * 20) sebzes okozasa");
        System.out.println("[~] feltamasztas - ar: 120 arany - mannakoltseg: 6 - hatas: egy kivalasztott sajat egyseg feltamasztasa, maximalis gyogyitas erteke (varazsero * 50)");
        System.out.println("[~] armageddon - ar: 100 arany - mannakoltseg: 3 - hatas: minden egysegre (sajatra is) varazsero * 5 sebzes okozasa");
        System.out.println("[~] varazsszarnyak - ar: 110 arany - mannakoltseg: 8 - hatas: egy egyseget akarmelyik ures mezore repit");
        System.out.print("[!] Valassz: ");

        String input = scanner.nextLine();

        while((!"TOVABB".equals(input))) {

            if ("villamcsapas".equals(input) || "tuzlabda".equals(input) || "feltamasztas".equals(input) || "armageddon".equals(input) || "varazsszarnyak".equals(input)) {
                Varazslat.setVarazslatok(jatekos, input);
                System.out.print("[!] Valassz: ");
            }
            else {
                System.out.print("[!] Hibas input! Adj meg helyes erteket: ");
            }

            input = scanner.nextLine();
        }

        System.out.println();

        Varazslat.listVarazslatok(jatekos);

    }

    public static void egysegPhase(Jatekos jatekos) {

        GameManager.info("arany", jatekos);

        if (jatekos.getArany() <= 1) {
            System.out.println("[!] Sajnos nem maradt eleg aranyad, ezert nem tudsz egyseget vasarolni. A jatek veget er.");
            System.exit(0);
        }

        System.out.println("[~] Es vegul, egysegek! Legalabb egy egyseget vasarolnod kell a tovabblepeshez.");
        System.out.println("[~] Ha ez megtortent, es elegedett vagy, a tovabblepes a TOVABB paranccsal tortenik.");
        System.out.println("[~] Jelenleg nincs egy egyseged sem!");
        System.out.println("[~] Lehetseges valasztasok: ");
        System.out.println("[~] foldmuves - ar: 2 - sebzes: 1-1 - eletero: 3 - sebesseg: 4 - kezdemenyezes: 8 - specialis kepesseg: nincs");
        System.out.println("[~] ijasz - ar: 6 - sebzes: 2-4 - eletero: 7 - sebesseg: 4 - kezdemenyezes: 9 - specialis kepesseg: loves");
        System.out.println("[~] griff - ar: 15 - sebzes: 5-10 - eletero: 30 - sebesseg: 7 - kezdemenyezes: 15 - specialis kepesseg: vegtelen visszatamadas");
        System.out.println("[~] hobgoblin - ar: 3 - sebzes: 1-2 - eletero: 1 - sebesseg: 5 - kezdemenyezes: 10 - specialis kepesseg: mergezes");
        System.out.println("[~] demon: - ar: 20 - sebzes: 15-25 - eletero: 10 - sebesseg: 3 - kezdemenyezes: 12 - specialis kepesseg: vegtelen visszatamadas");
        System.out.println("[~] mergezes magyarazata: vegzetesen megmergezi egy tetszoleges enemy egyseg egy peldanyat");
        System.out.print("[!] Valassz: ");

        String input = scanner.nextLine();
        int hanyat = 0;
        boolean legalabbEgyEgyseg = false;

        while(!legalabbEgyEgyseg || (!"TOVABB".equals(input))) {

            if ("foldmuves".equals(input) || "ijasz".equals(input) || "griff".equals(input) || "hobgoblin".equals(input) || "demon".equals(input)) {
                String saveInput = input;
                
                System.out.print("[!] Hanyat szeretnel az egysegbol vasarolni (egesz szam): ");
                input = scanner.nextLine();

                boolean szamotkap = false;
                while (!szamotkap) {
                    try {
                        hanyat = Math.abs(Integer.parseInt(input));
                        szamotkap = true;
                    } catch (NumberFormatException e) {
                        System.out.println("[!] Hibas input! Szamot adj meg!");
                        System.out.print("[!] Hanyat szeretnel az egysegbol vasarolni (egesz szam): ");
                        input = scanner.nextLine();
                    }
                }

                boolean sikeresTranzakcio = Egyseg.buyEgysegek(jatekos, saveInput, hanyat);
                if (hanyat != 0 && sikeresTranzakcio) {
                    legalabbEgyEgyseg = true;
                }
                System.out.print("[!] Valassz: ");
            }
            else {
                System.out.print("[!] Hibas input! Adj meg helyes erteket (vagy vasarolj legalabb egy egyseget): ");
            }

            input = scanner.nextLine();
        }
        System.out.println();
    }

    public static void taktikaiPhase(Jatekos jatekos) {
        System.out.println();
        System.out.println("[!] Helyezd el az egysegeidet, az elso ket oszlop all rendelkezesedre!");
        System.out.println("[~] Ezeket az egysegeket kell elhelyezd: ");
        Egyseg.listEgysegek(jatekos);

        int vanMegEgyseg = Egyseg.hanyEgyseg(jatekos);

        while(vanMegEgyseg != 0) {
            System.out.print("[!] Melyik egyseget szeretned helyezni? Valasztashoz add meg a tipusat, kis karakterekkel - pl. foldmuves: ");
            String input = scanner.nextLine();

            while (!Egyseg.vanEgyseg(jatekos, input)) {
                System.out.print("[!] Helytelen input vagy nincs ilyen egyseged/mar elhelyezted, probald ujra: ");
                input = scanner.nextLine();
            }
            String helyezendoTipus = input;

            int koordX = 0, koordY = 0;

            System.out.println("[!] Melyik mezore szeretned helyezni az egyseget?  (csak az elso 2 oszlop lehet)");
            System.out.print("[!] X koordinata (melyik sor): ");
            input = scanner.nextLine();

            boolean szabadMezo = false;
            while(!szabadMezo) {

                boolean szamotkap = false;
                while (!szamotkap) {
                    try {
                        koordX = Math.abs(Integer.parseInt(input));
                        if ((koordX - 1) >= 0 && (koordX - 1) <= 9) {
                            szamotkap = true;
                        } else {
                            System.out.print("[!] A koordinata nem megfelelo, probalkozz ujra: ");
                            input = scanner.nextLine();
                        }
                    } catch (NumberFormatException e) {
                        System.out.print("[!] Hibas input! Szamot adj meg: ");
                        input = scanner.nextLine();
                    }
                }

                System.out.print("[!] Y koordinata (melyik oszlop): ");
                input = scanner.nextLine();

                szamotkap = false;
                while (!szamotkap) {
                    try {
                        koordY = Math.abs(Integer.parseInt(input));
                        if ((koordY - 1) >= 0 && (koordY - 1) <= 1) {
                            szamotkap = true;
                        } else {
                            System.out.print("[!] A koordinata nem megfelelo, probalkozz ujra: ");
                            input = scanner.nextLine();
                        }
                    } catch (NumberFormatException e) {
                        System.out.print("[!] Hibas input! Szamot adj meg: ");
                        input = scanner.nextLine();
                    }
                }

                if (!Palya.foglalt(convertKoordinata(koordX), convertKoordinata(koordY))) {
                    Palya.mezoFoglal(convertKoordinata(koordX), convertKoordinata(koordY), helyezendoTipus, jatekos, Egyseg.resolveEgyseg(helyezendoTipus, jatekos));
                    Egyseg.setElhelyezettTrue(helyezendoTipus, jatekos);
                    vanMegEgyseg--;
                    szabadMezo = true;
                    System.out.println("[~] Egyseg sikeresen elhelyezve a(z) " + koordX +". sor " + koordY + ". oszlopaba!");
                }
                else {
                    System.out.println("[!] Ez a mezo mar foglalt!");
                    System.out.print("[!] Probald ujra! X koordinata (melyik sor): ");
                    input = scanner.nextLine();
                }

            }
        }
    }

    public static int convertKoordinata(int koordinata) {
        return --koordinata;
    }

    public static void feltoltSzGep (Jatekos szGep) {

        szGep.setArany(1000);

        szGep.jatekosHose.setTamadas(4);
        szGep.setArany(szGep.getArany() - (5 + 6 + 7));
        szGep.jatekosHose.setTudas(5);
        szGep.setArany(szGep.getArany() - (8 + 9 + 10 + 11));
        szGep.jatekosHose.setMoral(2);
        szGep.setArany(szGep.getArany() - 13);

        szGep.varazslatok[0].setRendelkezik(true);
        szGep.setArany(szGep.getArany() - 120);
        szGep.varazslatok[1].setRendelkezik(true);
        szGep.setArany(szGep.getArany() - 60);
        szGep.varazslatok[2].setRendelkezik(true);
        szGep.setArany(szGep.getArany() - 120);

        szGep.egysegek[0].setHanyVan(106);
        szGep.setArany(szGep.getArany() - 212);
        szGep.egysegek[1].setHanyVan(29);
        szGep.setArany(szGep.getArany() - 174);
        szGep.egysegek[2].setHanyVan(15);
        szGep.setArany(szGep.getArany() - 225);

    }

    public static void szGepStats(Jatekos szGep) {
        System.out.println();
        System.out.println("[~] Az szamitogep statjai jelenleg: ");
        System.out.println("[~] Tulajdonsagok: ");
        listTulajdonsagok(szGep);
        Varazslat.listVarazslatok(szGep);
        System.out.println("[~] Egysegek: ");
        Egyseg.listEgysegek(szGep);
        System.out.print("[!] A tovabblepeshez a TOVABB parancsot hasznald: ");
        String input = scanner.nextLine();
        while((!"TOVABB".equals(input))) {
            System.out.print("[!] Hibas input! Tovabblepni a TOVABB paranccsal tudsz: ");
            input = scanner.nextLine();
        }
    }

    public static void elhelyezSzGep (Jatekos szGep) {
        Palya.mezoFoglal(convertKoordinata(2), convertKoordinata(12), "griff", szGep, Egyseg.resolveEgyseg("griff", szGep));
        Egyseg.setElhelyezettTrue("griff", szGep);
        Palya.mezoFoglal(convertKoordinata(5), convertKoordinata(11), "foldmuves", szGep, Egyseg.resolveEgyseg("foldmuves", szGep));
        Egyseg.setElhelyezettTrue("foldmuves", szGep);
        Palya.mezoFoglal(convertKoordinata(9), convertKoordinata(12), "ijasz", szGep, Egyseg.resolveEgyseg("ijasz", szGep));
        Egyseg.setElhelyezettTrue("ijasz", szGep);
        System.out.println();
        System.out.println("[~] Az ellenfel is elhelyezte az egysegeit!");
    }

    public static void updateTudas(Jatekos jatekos, Jatekos szGep) {
        jatekos.setManna(jatekos.jatekosHose.getTudas() * 10);
        szGep.setManna(szGep.jatekosHose.getTudas() * 10);
    }

    public static void updateOsszEletero(Jatekos jatekos, Jatekos szGep) {
        for (int i = 0; i < jatekos.egysegek.length; i++) {
            jatekos.egysegek[i].setOsszEletero(jatekos.egysegek[i].getEletero() * jatekos.egysegek[i].getHanyVan());
            szGep.egysegek[i].setOsszEletero(szGep.egysegek[i].getEletero() * szGep.egysegek[i].getHanyVan());
        }
    }

    public static void updateMaxEletero(Jatekos jatekos, Jatekos szGep) {
        for (int i = 0; i < jatekos.egysegek.length; i++) {
            jatekos.egysegek[i].setMaxEletero(jatekos.egysegek[i].getOsszEletero());
            szGep.egysegek[i].setMaxEletero(szGep.egysegek[i].getOsszEletero());
        }
    }

    public static int mathRandom(int min, int max) {
        return min + (int)(Math.random() * ((max - min) + 1));
    }

    public static double applyTamadas(Jatekos jatekos, int alapSebzes) {
        return (alapSebzes * ((jatekos.jatekosHose.getTamadas() + 10.0) / 10.0));
    }

}
