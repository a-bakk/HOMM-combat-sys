package GameManager.Rounds;

import JatekPalya.Palya;
import Player.*;

import java.util.Scanner;

public class Kor {

    private static Egyseg[] egysegLista;
    private static final Scanner scanner = new Scanner(System.in);
    private static String input;
    private static int korCounter = 1;

    public static void ujKor(Jatekos jatekos, Jatekos szGep) {
        System.out.println();
        System.out.println("[~] Uj kor kezdodott! Ez a(z) " + korCounter + ". kor!");
        korCounter++;
        System.out.println();

        boolean jatekosHosAction = false, szGepHosAction = false;
        Jatekos[] lepesLista = feltoltLepesLista(jatekos, szGep);

        for (int i = 0; i < lepesLista.length; i++) {
            if (lepesLista[i] == jatekos) {
                System.out.println((i+1) + ". lepes: jatekos; egyseg: " + Egyseg.egysegNev(jatekos, egysegLista[i]));
            }
            else {
                System.out.println((i+1) + ". lepes: szamitogep; egyseg: " + Egyseg.egysegNev(szGep, egysegLista[i]));
            }
        }
        System.out.println();

        for (int lepesIndex = 0; lepesIndex < lepesLista.length; lepesIndex++) {

            if (lepesLista[lepesIndex] == jatekos) { // jatekos lepese
                System.out.println("[!] Te kovetkezel, ezzel az egyseggel: " + Egyseg.egysegNev(jatekos, egysegLista[lepesIndex]));

                if (!jatekosHosAction) {
                    System.out.println("[~] Lehetoseged van a hosoddel is lepni ebben a korben!");
                    System.out.print("[!] Valassz: tamadas/varazslas vagy TOVABB - azaz nem most lepsz vele: ");
                    input = scanner.nextLine();
                    while((!"TOVABB".equals(input))) {

                        if ("tamadas".equals(input)) {

                            int koordX = chooseX("[!] Melyik egyseget szeretned megtamadni a hosoddel?");

                            int koordY = chooseY();

                            Egyseg celpont = Palya.chooseMezoEnemyLetezik(convertKoordinata(koordX), convertKoordinata(koordY), jatekos, szGep);
                            if (celpont != null) {
                                System.out.println();
                                System.out.println(Egyseg.tisztaHosSebzestKap(jatekos, szGep, celpont));
                                System.out.println();
                                jatekosHosAction = true;
                                break;
                            }
                            else {
                                System.out.println("[!] Sikertelen tamadas! Lehetseges, hogy ures mezot valasztottal, vagy sajat egyseget!");
                                continue;
                            }

                        }
                        else if ("varazslas".equals(input)) {
                            String varazslat;
                            if (Varazslat.countVarazslatok(jatekos) == 0) {
                                System.out.println("[!] Nem rendelkezel egy varazslattal sem!");
                                break;
                            }
                            else {
                                System.out.print("[!] Melyik varazslatot szeretned hasznalni: ");
                                varazslat = scanner.nextLine();
                                while(true) {
                                    if ("villamcsapas".equals(varazslat) || "tuzlabda".equals(varazslat) || "feltamasztas".equals(varazslat) || "armageddon".equals(varazslat) || "varazsszarnyak".equals(varazslat)) {
                                        if (Varazslat.vanVarazslat(jatekos, varazslat)) { // TODO van eleg mana?!?!?!
                                            break;
                                        }
                                    }
                                    System.out.print("[!] Hibas input vagy nem rendelkezel ezzel a varazslattal! Adj meg helyes erteket: "); //else
                                    varazslat = scanner.nextLine();
                                }
                            }

                            int koordX = 0, koordY = 0;

                            if (!"armageddon".equals(varazslat) && !"varazsszarnyak".equals(varazslat)) {
                                koordX = chooseX("[!] Melyik egysegen/mezon szeretned hasznalni a varazslatot?");
                                koordY = chooseY();
                            }


                            switch (varazslat) {
                                case "tuzlabda" -> {
                                    System.out.println();
                                    System.out.println(jatekos.varazslatok[0].kulonlegesHatas(jatekos, szGep, convertKoordinata(koordX), convertKoordinata(koordY)));
                                    System.out.println();
                                    jatekosHosAction = true;
                                }
                                case "villamcsapas" -> {
                                    if (Palya.getMezok()[convertKoordinata(koordX)][convertKoordinata(koordY)].getKiBirtokolja() == szGep) {
                                        System.out.println();
                                        System.out.println(jatekos.varazslatok[1].kulonlegesHatas(jatekos, szGep, convertKoordinata(koordX), convertKoordinata(koordY)));
                                        System.out.println();
                                        jatekosHosAction = true;
                                    }
                                    else {
                                        System.out.println("[~] Az altalad valasztott mezon nincs ellenseges egyseg, a villamcsapas sikertelen!");
                                    }
                                }
                                case "feltamasztas" -> {
                                    if (Palya.getMezok()[convertKoordinata(koordX)][convertKoordinata(koordY)].getKiBirtokolja() == jatekos) {
                                        System.out.println();
                                        System.out.println(jatekos.varazslatok[2].kulonlegesHatas(jatekos, szGep, convertKoordinata(koordX), convertKoordinata(koordY)));
                                        System.out.println();
                                        jatekosHosAction = true;
                                    }
                                    else {
                                        System.out.println("[~] Az altalad valasztott mezon nem a sajat egyseged van, a feltamasztas sikertelen!");
                                    }
                                }
                                case "armageddon" -> {
                                    System.out.println();
                                    System.out.println(jatekos.varazslatok[3].kulonlegesHatas(jatekos, szGep, 0, 0));
                                    System.out.println();
                                    jatekosHosAction = true;
                                }
                                case "varazsszarnyak" -> {
                                    System.out.println("varazsszarnyak");
                                    jatekosHosAction = true;
                                }
                            }

                            break;
                        }
                        else {
                            System.out.print("[!] Hibas input vagy sikertelen lepes! tamadas/varazslas/TOVABB: ");
                        }
                        input = scanner.nextLine();
                    }
                }

                System.out.print("[!] Mit szeretnel az egyseggel csinalni? mozgas, varakozas vagy tamadas: ");
                input = scanner.nextLine();
                while(true) {
                    if ("mozgas".equals(input)) {
                        //TODO egyseg mozgas
                        System.out.println("Az egyseg mozgott!");
                        break;
                    }
                    else if ("varakozas".equals(input)) {
                        //TODO egyseg varakozas
                        System.out.println("Az egyseg varakozott!");
                        break;
                    }
                    else if ("tamadas".equals(input)) {
                        //TODO egyseg tamadas
                        System.out.println("Az egyseg tamadott!");
                        break;
                    }
                    else {
                        System.out.print("[!] Hibas input! Adj meg helyes erteket: ");
                    }
                    input = scanner.nextLine();
                }

            }
            else { // gep lepese
                System.out.println("Lepett a gep, esku!");
            }

        }
    }

    public static int convertKoordinata(int koordinata) {
        return --koordinata;
    }

    public static Jatekos[] feltoltLepesLista(Jatekos jatekos, Jatekos szGep) {
        egysegLista = new Egyseg[hanyLepes(jatekos, szGep)];
        Jatekos[] resLepesLista = new Jatekos[hanyLepes(jatekos, szGep)];
        int segedLepesIndex = 0;
        int[] resEgysegSzamLista = new int[hanyLepes(jatekos, szGep)]; // ez alapjan szamolok
        /* egyseglista feltoltese a ket jatekos egysegeivel + kezdemenyezeseikkel, a sorrend mindegy */
        for (int i = 0; i < jatekos.egysegek.length; i++) {
            if (jatekos.egysegek[i].getHanyVan() != 0) {
                resEgysegSzamLista[segedLepesIndex] = jatekos.egysegek[i].getKezdemenyezes() + jatekos.jatekosHose.getMoral();
                egysegLista[segedLepesIndex] = jatekos.egysegek[i];
                resLepesLista[segedLepesIndex] = jatekos;
                segedLepesIndex++;
            }
            if (szGep.egysegek[i].getHanyVan() != 0) {
                resEgysegSzamLista[segedLepesIndex] = szGep.egysegek[i].getKezdemenyezes() + szGep.jatekosHose.getMoral();
                egysegLista[segedLepesIndex] = szGep.egysegek[i];
                resLepesLista[segedLepesIndex] = szGep;
                segedLepesIndex++;
            }
        }

        /* bubble sort csokkeno sorrendbe */
        int n = resEgysegSzamLista.length;
        int temp = 0;
        Jatekos tempJatekos = jatekos;
        Egyseg tempEgyseg = jatekos.egysegek[0];
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < (n - i); j++) {
                if (resEgysegSzamLista[j - 1] < resEgysegSzamLista[j]) { //jatekost reszesitem elonyben
                    temp = resEgysegSzamLista[j - 1];
                    resEgysegSzamLista[j - 1] = resEgysegSzamLista[j];
                    resEgysegSzamLista[j] = temp;
                    tempJatekos = resLepesLista[j - 1];
                    resLepesLista[j - 1] = resLepesLista[j];
                    resLepesLista[j] = tempJatekos;
                    tempEgyseg = egysegLista[j - 1];
                    egysegLista[j - 1] = egysegLista[j];
                    egysegLista[j] = tempEgyseg;
                }
            }
        }

        return resLepesLista;
    }

    public static int hanyLepes(Jatekos jatekos, Jatekos szGep) {
        int res = 0;
        for (int i = 0; i < jatekos.egysegek.length; i++) {
            if (jatekos.egysegek[i].getHanyVan() != 0) res++;
        }
        for (int i = 0; i < szGep.egysegek.length; i++) {
            if (szGep.egysegek[i].getHanyVan() != 0) res++;
        }
        return res;
    }

    public static int chooseX(String elsoSor) {
        int koordX = 0;
        System.out.println(elsoSor);
        System.out.print("[!] X koordinata (melyik sor): ");
        input = scanner.nextLine();
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
        return koordX;
    }

    public static int chooseY() {
        int koordY = 0;
        System.out.print("[!] Y koordinata (melyik oszlop): ");
        input = scanner.nextLine();

        boolean szamotkap = false;
        while (!szamotkap) {
            try {
                koordY = Math.abs(Integer.parseInt(input));
                if ((koordY - 1) >= 0 && (koordY - 1) <= 11) {
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
        return koordY;
    }

}
