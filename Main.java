import GameManager.*;
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

        Palya jatekPalya = new Palya(); // csak statik methodusok vannak a palyaban
        Palya.setMezok();

        Palya.paintPalya(jatekos, szGep);
        GameManager.taktikaiPhase(jatekos);
        Palya.paintPalya(jatekos, szGep);

        GameManager.elhelyezSzGep(szGep);
        Palya.paintPalya(jatekos, szGep);

    }

}
