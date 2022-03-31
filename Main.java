import GameManager.*;
import GameManager.Rounds.*;
import Player.*;
import JatekPalya.*;

public class Main {

    public static void main(String[] args) {

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
        System.out.println(Kor.getKiNyert() == jatekos ? "[!] Gyoztel!!!" : "[!] Sajnos az ellenfel gyozott!");
        System.out.println();

    }

}
