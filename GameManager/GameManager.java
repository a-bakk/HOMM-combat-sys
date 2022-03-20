package GameManager;

import JatekPalya.Mezo;
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

        // TODO enemy statjai

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
        System.out.print("[!] Valassz: ");

        String input = scanner.nextLine();

        while((!"TOVABB".equals(input))) {

            if ("villamcsapas".equals(input) || "tuzlabda".equals(input) || "feltamasztas".equals(input) || "TODO".equals(input) || "TODO2".equals(input)) {
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
        System.out.print("[!] Valassz: ");

        String input = scanner.nextLine();
        int hanyat = 0;
        boolean legalabbEgyEgyseg = false;

        while(!legalabbEgyEgyseg || (!"TOVABB".equals(input))) {

            if ("foldmuves".equals(input) || "ijasz".equals(input) || "griff".equals(input)) {
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

            //TODO ide meg egy while, hogy szabad-e a mezo
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
                // TODO elhelyezett egysegnek elhelyezett boolean false

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
        //TODO fuggveny, hogy tenylegesen helyezze el, elhelyezes utan paintPalya()
    }

    public static int convertKoordinata(int koordinata) {
        return --koordinata;
    }
}