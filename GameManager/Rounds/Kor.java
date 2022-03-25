package GameManager.Rounds;

import Player.*;

import java.util.Scanner;

public class Kor {

    private static Egyseg[] egysegLista;
    private static final Scanner scanner = new Scanner(System.in);
    private static String input;

    public static void ujKor(Jatekos jatekos, Jatekos szGep) {
        System.out.println();
        System.out.println("[~] Uj kor kezdodott!");
        System.out.println();

        boolean jatekosHosAction = false, szGepHosAction = false;
        Jatekos[] lepesLista = feltoltLepesLista(jatekos, szGep);

        for (int lepesIndex = 0; lepesIndex < lepesLista.length; lepesIndex++) {

            if (lepesLista[lepesIndex] == jatekos) { // jatekos lepese
                System.out.println("[!] Te kovetkezel, ezzel az egyseggel: " + Egyseg.egysegNev(jatekos, egysegLista[lepesIndex]));

                if (!jatekosHosAction) {
                    System.out.println("[~] Lehetoseged van a hosoddel is lepni ebben a korben!");
                    System.out.print("[!] Valassz: tamadas/varazslas vagy TOVABB - azaz nem most lepsz vele: ");
                    input = scanner.nextLine();
                    while((!"TOVABB".equals(input))) {
                        if ("tamadas".equals(input)) {
                            //TODO Hos tamadas
                            System.out.println("A hos tamadott!");
                            jatekosHosAction = true;
                            break;
                        }
                        else if ("varazslas".equals(input)) {
                            //TODO Hos varazslas
                            System.out.println("A hos varazsolt!");
                            jatekosHosAction = true;
                            break;
                        }
                        else {
                            System.out.print("[!] Hibas input! Adj meg helyes erteket: ");
                        }
                        input = scanner.nextLine();
                    }
                }

                System.out.print("[!] Mit szeretnel az egyseggel csinalni? mozgas, varakozas vagy tamadas: ");
                input = scanner.nextLine();
                while(true) {
                    if ("mozgas".equals(input)) {
                        //TODO egyseg tamadas
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

}
