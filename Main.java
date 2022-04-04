import gamemanager.*;
import gamemanager.rounds.*;
import player.*;
import jatekpalya.*;

/**
 * A Heroes of Might & Magic harcrendszerének leegyszerűsített változata.
 *
 * <p>A Main osztály kizárólag a main metódust valósítja meg, ez egyben a program belépési pontja is.
 * Leginkább a GameManager osztály különböző metódusait hívja meg, de időrendi sorrendbe helyezi például
 * a pálya felépítését is.</p>
 * <p>Fontos lehet megjegyezni, hogy a programban rengeteg helyen van nem túl kényelmes - esetenként sokkal hosszab -
 * megoldás a várhatónal. Külön magyarázat nem illeti ezeknek az okát (általánosan: a program megírásának ideje vs az aktuális
 * anyag a kurzuson), ezért fordulhatnak elő nem túl megfontolt reprezentációk, például a Map teljes hiánya, ami rengeteg
 * switch caset megspórolt volna, talán olvashatóbbá tette volna a kódot.</p>
 *
 * @author unknown
 * @version 1.2.0 (2022. április 5.)
 *
 */

public class Main {

    public static void main(String[] args) {

        GameManager.welcomeMessage();

        GameManager.chooseDifficulty();

        Jatekos jatekos = new Jatekos(false);
        Jatekos szGep = new Jatekos(true);

        GameManager.tulajdonsagPhase(jatekos);
        GameManager.varazslatPhase(jatekos);
        GameManager.egysegPhase(jatekos);

        GameManager.feltoltSzGep(szGep);

        GameManager.info("all", jatekos);
        GameManager.szGepStats(szGep);

        Palya.setMezok();

        GameManager.updateTudas(jatekos, szGep);
        GameManager.updateOsszEletero(jatekos, szGep);
        GameManager.updateMaxEletero(jatekos, szGep);

        Palya.paintPalya(jatekos, szGep);
        GameManager.taktikaiPhase(jatekos);
        Palya.paintPalya(jatekos, szGep);

        GameManager.elhelyezSzGep(szGep);
        Palya.paintPalya(jatekos, szGep);

        while (!Kor.isGyozelemKondicio()) {
            Kor.ujKor(jatekos, szGep);
        }

        System.out.println();
        System.out.println(Kor.getKiNyert() == jatekos ? "[!] Gyoztel!!!" : (Kor.getKiNyert() == szGep ? "[!] Sajnos az ellenfel gyozott!" : "[!] Dontetlen!"));
        System.out.println();

    }

}
