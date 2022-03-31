package GameManager.Rounds;

import JatekPalya.Palya;
import Player.*;

import java.util.Scanner;

public class Kor {

    private static Egyseg[] egysegLista;
    private static final Scanner scanner = new Scanner(System.in);
    private static String input;
    private static int korCounter = 1;
    private static boolean gyozelemKondicio = false;
    private static Jatekos[] lepesLista;
    public static Jatekos kiNyert;

    public static void ujKor(Jatekos jatekos, Jatekos szGep) {
        System.out.println();
        System.out.println("[~] Uj kor kezdodott! Ez a(z) " + korCounter + ". kor!");
        korCounter++;
        System.out.println();

        boolean jatekosHosAction = false, szGepHosAction = false;
        lepesLista = feltoltLepesLista(jatekos, szGep);

        for (int i = 0; i < lepesLista.length; i++) {
            if (lepesLista[i] == jatekos) {
                System.out.println("[~] " + (i+1) + ". lepes: jatekos; egyseg: " + Egyseg.egysegNev(jatekos, egysegLista[i]));
            }
            else {
                System.out.println("[~] " + (i+1) + ". lepes: szamitogep; egyseg: " + Egyseg.egysegNev(szGep, egysegLista[i]));
            }
        }
        System.out.println();

        korFutasa:
        for (int lepesIndex = 0; lepesIndex < lepesLista.length; lepesIndex++) {

            if (lepesLista[lepesIndex] != null) {

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
                                    updateLepesLista(celpont);
                                    if (nyertValaki(jatekos, szGep) != 0) {
                                        kiNyert = nyertValaki(jatekos, szGep) == 1 ? jatekos : szGep;
                                        gyozelemKondicio = true;
                                        break korFutasa;
                                    }
                                    jatekosHosAction = true;
                                    repaint(jatekos, szGep);
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
                                    sikeresvarazslas:
                                    while(true) {
                                        if ("villamcsapas".equals(varazslat) || "tuzlabda".equals(varazslat) || "feltamasztas".equals(varazslat) || "armageddon".equals(varazslat) || "varazsszarnyak".equals(varazslat) || "TOVABB".equals(varazslat)) {
                                            if (Varazslat.vanVarazslat(jatekos, varazslat)) {
                                                switch (varazslat) {
                                                    case "tuzlabda" -> {
                                                        if (jatekos.varazslatok[0].getMannaKoltseg() <= jatekos.getManna()) {
                                                            break sikeresvarazslas;
                                                        }
                                                        else {
                                                            System.out.println("[~] Nincs eleg mannad a varazslathoz!");
                                                        }
                                                    }
                                                    case "villamcsapas" -> {
                                                        if (jatekos.varazslatok[1].getMannaKoltseg() <= jatekos.getManna()) {
                                                            break sikeresvarazslas;
                                                        }
                                                        else {
                                                            System.out.println("[~] Nincs eleg mannad a varazslathoz!");
                                                        }
                                                    }
                                                    case "feltamasztas" -> {
                                                        if (jatekos.varazslatok[2].getMannaKoltseg() <= jatekos.getManna()) {
                                                            break sikeresvarazslas;
                                                        }
                                                        else {
                                                            System.out.println("[~] Nincs eleg mannad a varazslathoz!");
                                                        }
                                                    }
                                                    case "armageddon" -> {
                                                        if (jatekos.varazslatok[3].getMannaKoltseg() <= jatekos.getManna()) {
                                                            break sikeresvarazslas;
                                                        }
                                                        else {
                                                            System.out.println("[~] Nincs eleg mannad a varazslathoz!");
                                                        }
                                                    }
                                                    case "varazsszarnyak" -> {
                                                        if (jatekos.varazslatok[4].getMannaKoltseg() <= jatekos.getManna()) {
                                                            break sikeresvarazslas;
                                                        }
                                                        else {
                                                            System.out.println("[~] Nincs eleg mannad a varazslathoz!");
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                        if ("TOVABB".equals(varazslat)) break;
                                        System.out.print("[!] Hibas input vagy nem rendelkezel ezzel a varazslattal! Adj meg helyes erteket/TOVABB: ");
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
                                        jatekos.setManna(jatekos.getManna() - jatekos.varazslatok[0].getMannaKoltseg());
                                        repaint(jatekos, szGep);
                                        if (nyertValaki(jatekos, szGep) != 0) {
                                            kiNyert = nyertValaki(jatekos, szGep) == 1 ? jatekos : szGep;
                                            break korFutasa;
                                        }
                                        jatekosHosAction = true;
                                    }
                                    case "villamcsapas" -> {
                                        if (Palya.getMezok()[convertKoordinata(koordX)][convertKoordinata(koordY)].getKiBirtokolja() == szGep) {
                                            System.out.println();
                                            System.out.println(jatekos.varazslatok[1].kulonlegesHatas(jatekos, szGep, convertKoordinata(koordX), convertKoordinata(koordY)));
                                            System.out.println();
                                            updateLepesLista(Palya.getMezok()[convertKoordinata(koordX)][convertKoordinata(koordY)].getTartalomEgyseg());
                                            jatekos.setManna(jatekos.getManna() - jatekos.varazslatok[1].getMannaKoltseg());
                                            repaint(jatekos, szGep);
                                            if (nyertValaki(jatekos, szGep) != 0) {
                                                kiNyert = nyertValaki(jatekos, szGep) == 1 ? jatekos : szGep;
                                                gyozelemKondicio = true;
                                                break korFutasa;
                                            }
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
                                            jatekos.setManna(jatekos.getManna() - jatekos.varazslatok[2].getMannaKoltseg());
                                            repaint(jatekos, szGep);
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
                                        jatekos.setManna(jatekos.getManna() - jatekos.varazslatok[3].getMannaKoltseg());
                                        repaint(jatekos, szGep);
                                        if (nyertValaki(jatekos, szGep) != 0) {
                                            kiNyert = nyertValaki(jatekos, szGep) == 1 ? jatekos : szGep;
                                            gyozelemKondicio = true;
                                            break korFutasa;
                                        }
                                        jatekosHosAction = true;
                                    }
                                    case "varazsszarnyak" -> {
                                        int jelenlegiX = chooseX("[!] Melyik egysegedet szeretned elrepiteni?");
                                        int jelenlegiY = chooseY();
                                        int hovaX = chooseX("[!] Hova szeretned helyezni az egyseget?");
                                        int hovaY = chooseY();
                                        if (mozgasMezore(jelenlegiX, jelenlegiY, hovaX, hovaY, jatekos)) {
                                            jatekosHosAction = true;
                                            jatekos.setManna(jatekos.getManna() - jatekos.varazslatok[4].getMannaKoltseg());
                                            repaint(jatekos, szGep);
                                        }
                                        else {
                                            System.out.println("[~] Az altalad valasztott egyseg nem a tied, vagy nem ures mezore probaltad helyezni!");
                                        }
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

                    if ("hobgoblin".equals(Egyseg.egysegNev(jatekos, egysegLista[lepesIndex]))) {
                        System.out.println("[~] A hobgoblin egyseged keszul lepni! Lehetoseged van egy enemy egyseg egy peldanyat megmergezni!");
                        int tamadX = chooseX("[~] Melyik egyseget szeretned megmergezni: ");
                        int tamadY = chooseY();
                        Egyseg celpont = Palya.chooseMezoEnemyLetezik(convertKoordinata(tamadX), convertKoordinata(tamadY), jatekos, szGep);
                        if (celpont != null) {
                            System.out.println(Egyseg.hobgoblinMergezestKap(celpont));
                            updateLepesLista(celpont);
                            if (nyertValaki(jatekos, szGep) != 0) {
                                kiNyert = nyertValaki(jatekos, szGep) == 1 ? jatekos : szGep;
                                gyozelemKondicio = true;
                                break;
                            }
                            repaint(jatekos, szGep);
                        }
                        else {
                            System.out.println("[~] Sikertelen mergezes!");
                        }

                    }

                    System.out.print("[!] Mit szeretnel az egyseggel csinalni? mozgas, varakozas vagy tamadas: ");
                    input = scanner.nextLine();
                    while(true) {

                        if (egysegLista[lepesIndex].getEletero() <= 0) {
                            System.out.println("[~] Az egyseged a kor kozben meghalt, nem tudsz lepni vele!");
                            break;
                        }

                        if ("mozgas".equals(input)) {

                            int startX = Palya.getIndexX(egysegLista[lepesIndex]); // konvertalt koordinatak
                            int startY = Palya.getIndexY(egysegLista[lepesIndex]);
                            int hovaX = chooseX("[!] Hova szeretnel lepni az egyseggel?");
                            int hovaY = chooseY();

                            if (Palya.palyaBreadthFirstSearch(Palya.getMezok(), startX, startY, convertKoordinata(hovaX), convertKoordinata(hovaY)) <= egysegLista[lepesIndex].getSebesseg()) {
                                mozgasMezore(startX + 1, startY + 1, hovaX, hovaY, jatekos);
                                System.out.println("[!] Sikeresen mozgott az egyseg!");
                                repaint(jatekos, szGep);
                                break;
                            }
                            else {
                                System.out.println("[!] Nem tudsz oda lepni, az egyseg sebessege nem eleg magas!");
                            }
                        }
                        else if ("varakozas".equals(input)) {
                            System.out.println("[!] Az egyseg varakozott!");
                            break;
                        }
                        else if ("tamadas".equals(input)) {

                            int sajatX = Palya.getIndexX(egysegLista[lepesIndex]); // konvertalt koordinatak
                            int sajatY = Palya.getIndexY(egysegLista[lepesIndex]);
                            int tamadX = chooseX("[!] Melyik egyseget szeretned megtamadni?");
                            int tamadY = chooseY();

                            if ("ijasz".equals(Egyseg.egysegNev(jatekos, egysegLista[lepesIndex]))) {
                                Egyseg celpont = Palya.chooseMezoEnemyLetezik(convertKoordinata(tamadX), convertKoordinata(tamadY), jatekos, szGep);
                                if (celpont != null) {
                                    System.out.println(Palya.getMezok()[convertKoordinata(tamadX)][convertKoordinata(tamadY)].getTartalomEgyseg().sebzestKap(szGep, jatekos, sajatX, sajatY)); // tamadas
                                    updateLepesLista(Palya.getMezok()[convertKoordinata(tamadX)][convertKoordinata(tamadY)].getTartalomEgyseg());
                                    if (Egyseg.szomszedosEllenfel(jatekos, szGep, sajatX, sajatY, convertKoordinata(tamadX), convertKoordinata(tamadY))) {
                                        if (!Palya.getMezok()[convertKoordinata(tamadX)][convertKoordinata(tamadY)].getTartalomEgyseg().isVisszatamadott() || "griff".equals(Egyseg.egysegNev(jatekos, Palya.getMezok()[convertKoordinata(tamadX)][convertKoordinata(tamadY)].getTartalomEgyseg())) || "demon".equals(Egyseg.egysegNev(jatekos, Palya.getMezok()[convertKoordinata(tamadX)][convertKoordinata(tamadY)].getTartalomEgyseg()))) {
                                            System.out.println(Palya.getMezok()[sajatX][sajatY].getTartalomEgyseg().visszatamadasSebzestKap(jatekos, szGep, convertKoordinata(tamadX), convertKoordinata(tamadY)) + " "); // visszatamadas
                                            Palya.getMezok()[convertKoordinata(tamadX)][convertKoordinata(tamadY)].getTartalomEgyseg().setVisszatamadott(true);
                                            updateLepesLista(Palya.getMezok()[sajatX][sajatY].getTartalomEgyseg());
                                            if (nyertValaki(jatekos, szGep) != 0) {
                                                kiNyert = nyertValaki(jatekos, szGep) == 1 ? jatekos : szGep;
                                                gyozelemKondicio = true;
                                                break korFutasa;
                                            }
                                        }
                                    }
                                    repaint(jatekos, szGep);
                                    break;
                                }
                            }

                            if (Egyseg.szomszedosEllenfel(jatekos, szGep, sajatX, sajatY, convertKoordinata(tamadX), convertKoordinata(tamadY))) {
                                System.out.println(Palya.getMezok()[convertKoordinata(tamadX)][convertKoordinata(tamadY)].getTartalomEgyseg().sebzestKap(szGep, jatekos, sajatX, sajatY)); // tamadas
                                updateLepesLista(Palya.getMezok()[convertKoordinata(tamadX)][convertKoordinata(tamadY)].getTartalomEgyseg());
                                if (!Palya.getMezok()[convertKoordinata(tamadX)][convertKoordinata(tamadY)].getTartalomEgyseg().isVisszatamadott() || "griff".equals(Egyseg.egysegNev(jatekos, Palya.getMezok()[convertKoordinata(tamadX)][convertKoordinata(tamadY)].getTartalomEgyseg())) || "demon".equals(Egyseg.egysegNev(jatekos, Palya.getMezok()[convertKoordinata(tamadX)][convertKoordinata(tamadY)].getTartalomEgyseg()))) {
                                    System.out.println(Palya.getMezok()[sajatX][sajatY].getTartalomEgyseg().visszatamadasSebzestKap(jatekos, szGep, convertKoordinata(tamadX), convertKoordinata(tamadY))); // visszatamadas
                                    Palya.getMezok()[convertKoordinata(tamadX)][convertKoordinata(tamadY)].getTartalomEgyseg().setVisszatamadott(true);
                                    updateLepesLista(Palya.getMezok()[sajatX][sajatY].getTartalomEgyseg());
                                    if (nyertValaki(jatekos, szGep) != 0) {
                                        kiNyert = nyertValaki(jatekos, szGep) == 1 ? jatekos : szGep;
                                        gyozelemKondicio = true;
                                        break korFutasa;
                                    }
                                }
                                repaint(jatekos, szGep);
                                break;
                            }
                            else {
                                System.out.println("[!] Nem tamadhato az egyseg!");
                            }
                        }
                        System.out.print("[!] Hibas input vagy sikertelen action! tamadas/mozgas/varakozas: ");
                        input = scanner.nextLine();
                    }

                }
                else { // gep lepese

                    if (!szGepHosAction) { //csak tamadni tud
                        Egyseg celpont = Palya.getLastPlayerEgyseg(jatekos);
                        if (celpont != null) {
                            System.out.println();
                            Egyseg.tisztaHosSebzestKap(szGep, jatekos, celpont);
                            System.out.println("[!] Az ellenfel hose sikeresen megtamadta az egyik egysegedet!");
                            System.out.println();
                            updateLepesLista(celpont);
                            if (nyertValaki(jatekos, szGep) != 0) {
                                kiNyert = nyertValaki(jatekos, szGep) == 1 ? jatekos : szGep;
                                gyozelemKondicio = true;
                                break;
                            }
                            szGepHosAction = true;
                            repaint(jatekos, szGep);
                        }
                    }

                    int koordX = Palya.getIndexX(egysegLista[lepesIndex]);
                    int koordY = Palya.getIndexY(egysegLista[lepesIndex]);

                    Egyseg potencialisCelpont = Palya.vanSzGepKozelbenEgyseg(jatekos, koordX, koordY);

                    if (potencialisCelpont != null) {
                        potencialisCelpont.sebzestKap(jatekos, szGep, koordX, koordY);
                        updateLepesLista(potencialisCelpont);
                        System.out.println("[~] Az ellenfel megtamadta az egyik egysegedet!");
                        if (!potencialisCelpont.isVisszatamadott() || "griff".equals(Egyseg.egysegNev(jatekos, potencialisCelpont)) || "demon".equals(Egyseg.egysegNev(jatekos, potencialisCelpont))) {

                            int visszaX = Palya.getIndexX(potencialisCelpont);
                            int visszaY = Palya.getIndexY(potencialisCelpont);

                            egysegLista[lepesIndex].visszatamadasSebzestKap(szGep, jatekos, visszaX, visszaY); // visszatamadas
                            potencialisCelpont.setVisszatamadott(true);
                            updateLepesLista(egysegLista[lepesIndex]);
                            if (nyertValaki(jatekos, szGep) != 0) {
                                kiNyert = nyertValaki(jatekos, szGep) == 1 ? jatekos : szGep;
                                gyozelemKondicio = true;
                                break;
                            }
                            System.out.println("[~] Az egyseged sikeresen visszatamadott!");
                        }
                        repaint(jatekos, szGep);
                    }
                    else {
                        if (koordY - 3 >= 0) {
                            mozgasMezore(koordX + 1, koordY + 1, koordX + 1, koordY - 2, szGep);
                            System.out.println("[~] Az ellenfel egysege mozgott!");
                            repaint(jatekos, szGep);
                        }
                        else if (koordY + 3 <= 11) {
                            mozgasMezore(koordX + 1, koordY + 1, koordX + 1, koordY + 4, szGep);
                            System.out.println("[~] Az ellenfel egysege mozgott!");
                            repaint(jatekos, szGep);
                        }
                        else {
                            System.out.println("[~] Az ellenfel egysege varakozott!");
                        }
                    }

                }

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
            if (jatekos.egysegek[i].getHanyVan() != 0 && jatekos.egysegek[i].getOsszEletero() != 0) {
                resEgysegSzamLista[segedLepesIndex] = jatekos.egysegek[i].getKezdemenyezes() + jatekos.jatekosHose.getMoral();
                egysegLista[segedLepesIndex] = jatekos.egysegek[i];
                resLepesLista[segedLepesIndex] = jatekos;
                segedLepesIndex++;
            }
            if (szGep.egysegek[i].getHanyVan() != 0 && szGep.egysegek[i].getOsszEletero() != 0) {
                resEgysegSzamLista[segedLepesIndex] = szGep.egysegek[i].getKezdemenyezes() + szGep.jatekosHose.getMoral();
                egysegLista[segedLepesIndex] = szGep.egysegek[i];
                resLepesLista[segedLepesIndex] = szGep;
                segedLepesIndex++;
            }
        }

        Egyseg.setVisszatamadottFalse(jatekos, szGep);

        /* bubble sort csokkeno sorrendbe */
        int n = resEgysegSzamLista.length;
        int temp;
        Jatekos tempJatekos;
        Egyseg tempEgyseg;
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

    public static void repaint(Jatekos jatekos, Jatekos szGep) {
        System.out.println();
        Palya.repaintPalya(jatekos, szGep);
        System.out.println();
    }

    public static boolean mozgasMezore(int jelenlegiX, int jelenlegiY, int hovaX, int hovaY, Jatekos kiLep) { // nem konvertalt koordinatat var
        if (Palya.getMezok()[convertKoordinata(jelenlegiX)][convertKoordinata(jelenlegiY)].getKiBirtokolja() == kiLep && !Palya.getMezok()[convertKoordinata(hovaX)][convertKoordinata(hovaY)].isFoglalt()) {
            Palya.getMezok()[convertKoordinata(hovaX)][convertKoordinata(hovaY)].setMezo(
                    Palya.getMezok()[convertKoordinata(jelenlegiX)][convertKoordinata(jelenlegiY)].getMilyenEgyseg(),
                    Palya.getMezok()[convertKoordinata(jelenlegiX)][convertKoordinata(jelenlegiY)].getKiBirtokolja(),
                    Palya.getMezok()[convertKoordinata(jelenlegiX)][convertKoordinata(jelenlegiY)].getTartalomEgyseg());
            Palya.getMezok()[convertKoordinata(jelenlegiX)][convertKoordinata(jelenlegiY)].resetMezo();
            return true;
        }
        return false;
    }

    public static boolean isGyozelemKondicio() {
        return gyozelemKondicio;
    }

    public static void updateLepesLista(Egyseg kerdesesEgyseg) {
        if (getLepesIndikator(kerdesesEgyseg) != -1 && egysegLista[getLepesIndikator(kerdesesEgyseg)] != null && egysegLista[getLepesIndikator(kerdesesEgyseg)].getOsszEletero() <= 0) lepesLista[getLepesIndikator(kerdesesEgyseg)] = null;
    }

    public static int getLepesIndikator(Egyseg egyseg) {
        for (int i = 0; i < egysegLista.length; i++) {
            if (egysegLista[i] == egyseg) return i;
        }
        return -1;
    }

    public static int nyertValaki(Jatekos jatekos, Jatekos szGep) {
        // 1 - jatekos nyert
        // 2 - szamitogep nyert
        boolean mindenOK = false;
        for (int i = 0; i < jatekos.egysegek.length; i++) {
            if (jatekos.egysegek[i].getOsszEletero() > 0) { mindenOK = true; break; }
        }
        if (!mindenOK) return 2;

        mindenOK = false;
        for (int j = 0; j < szGep.egysegek.length; j++) {
            if (szGep.egysegek[j].getOsszEletero() > 0) { mindenOK = true; break; }
        }
        if (!mindenOK) return 1;

        return 0;
    }

    public static Jatekos getKiNyert() {
        return kiNyert;
    }
}
