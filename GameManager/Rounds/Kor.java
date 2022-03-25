package GameManager.Rounds;

import JatekPalya.Mezo;
import JatekPalya.Palya;
import Player.*;

public class Kor {

    public static void ujKor(Jatekos jatekos, Jatekos szGep) {
        Jatekos[] lepesLista = new Jatekos[hanyLepes(jatekos, szGep)];
        for (int lepesIndex = 0; lepesIndex < lepesLista.length; lepesIndex++) {


        }
    }

    public static Jatekos[] feltoltLepesLista(Jatekos jatekos, Jatekos szGep) {
        Jatekos[] resLepesLista = new Jatekos[hanyLepes(jatekos, szGep)];
        int segedLepesIndex = 0;
        /* feltoltes random a ket jatekossal */
        for (int i = 0; i < jatekos.egysegek.length; i++) {
            if (jatekos.egysegek[i].getHanyVan() != 0) {
                resLepesLista[segedLepesIndex] = jatekos;
                segedLepesIndex++;
            }
            if (szGep.egysegek[i].getHanyVan() != 0) {
                resLepesLista[segedLepesIndex] = szGep;
                segedLepesIndex++;
            }
        }
        /* bubble sort */
        int n = hanyLepes(jatekos, szGep);
        for (int i = 0; i < n-1; i++)
            for (int j = 0; j < n-i-1; j++)
                if (true)
                {
                    // swap arr[j+1] and arr[j]
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
