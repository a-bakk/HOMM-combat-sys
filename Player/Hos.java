package Player;

import GameManager.*;

/**
 * A hőst reprezentáló osztály, mely leginkább annak tudáspontjaiért, megfelelő elosztásukért felel.
 */

public class Hos {

    /**
     * A hős tamadás tulajdonsága.
     */
    private int tamadas = 1;
    /**
     * A hős védekezés tulajdonsága.
     */
    private int vedekezes = 1;
    /**
     * A hős varázserő tulajdonsága.
     */
    private int varazsero = 1;
    /**
     * A hős tudás tulajdonsága.
     */
    private int tudas = 1;
    /**
     * A hős morál tulajdonsága.
     */
    private int moral = 1;
    /**
     * A hős szerencse tulajdonsága.
     */
    private int szerencse = 1;

    //region getterek
    public int getTamadas() {
        return tamadas;
    }

    public int getVedekezes() {
        return vedekezes;
    }

    public int getVarazsero() {
        return varazsero;
    }

    public int getTudas() {
        return tudas;
    }

    public int getMoral() {
        return moral;
    }

    public int getSzerencse() {
        return szerencse;
    }
    //endregion

    /**
     * Tulajdonságok vásárlásáért felelő metódus. Ellenőrzi, hogy van-e elég arany, és hogy adott tulajdonság elérte-e a maximális értékét.
     *
     * @param kinek melyik játékos vásárol tulajdonságot
     * @param tulajdonsag milyen tulajdonságról van szó, szöveges formában
     */
    public void tulajdonsagNovelo(Jatekos kinek, String tulajdonsag) {
        System.out.println();
        boolean sikeres = false;
        switch (tulajdonsag) {
            case "tamadas" -> {
                if (kinek.jatekosHose.tamadas < 10 && kinek.arany > kinek.tulajdonsagAr) {
                    kinek.jatekosHose.tamadas++;
                    System.out.println("[!] " + tulajdonsag + " novelve! Jelenlegi erteke: " + kinek.jatekosHose.getTamadas());
                    sikeres = true;
                }
            }
            case "vedekezes" -> {
                if (kinek.jatekosHose.vedekezes < 10 && kinek.arany > kinek.tulajdonsagAr) {
                    kinek.jatekosHose.vedekezes++;
                    System.out.println("[!] " + tulajdonsag + " novelve! Jelenlegi erteke: " + kinek.jatekosHose.getVedekezes());
                    sikeres = true;
                }
            }
            case "varazsero" -> {
                if (kinek.jatekosHose.varazsero < 10 && kinek.arany > kinek.tulajdonsagAr) {
                    kinek.jatekosHose.varazsero++;
                    System.out.println("[!] " + tulajdonsag + " novelve! Jelenlegi erteke: " + kinek.jatekosHose.getVarazsero());
                    sikeres = true;
                }
            }
            case "tudas" -> {
                if (kinek.jatekosHose.tudas < 10 && kinek.arany > kinek.tulajdonsagAr) {
                    kinek.jatekosHose.tudas++;
                    System.out.println("[!] " + tulajdonsag + " novelve! Jelenlegi erteke: " + kinek.jatekosHose.getTudas());
                    sikeres = true;
                }
            }
            case "moral" -> {
                if (kinek.jatekosHose.moral < 10 && kinek.arany > kinek.tulajdonsagAr) {
                    kinek.jatekosHose.moral++;
                    System.out.println("[!] " + tulajdonsag + " novelve! Jelenlegi erteke: " + kinek.jatekosHose.getMoral());
                    sikeres = true;
                }
            }
            case "szerencse" -> {
                if (kinek.jatekosHose.szerencse < 10 && kinek.arany > kinek.tulajdonsagAr) {
                    kinek.jatekosHose.szerencse++;
                    System.out.println("[!] " + tulajdonsag + " novelve! Jelenlegi erteke: " + kinek.jatekosHose.getSzerencse());
                    sikeres = true;
                }
            }
        }
        if (sikeres) {
            kinek.arany -= kinek.tulajdonsagAr;
            kinek.tulajdonsagAr = (int)Math.ceil(kinek.tulajdonsagAr * 1.1);
            System.out.println("[~] A kovetkezo tulajdonsagpont ara: " + kinek.tulajdonsagAr);
            GameManager.info("arany", kinek);
        }
        else {
            System.out.println("[!] Sikertelen! A tulajdonsag mar elerte a maximum erteket vagy nincs aranyad, az egyenlegedbol nem tortent levonas!");
            GameManager.info("arany", kinek);
        }
    }

    public void setTamadas(int tamadas) { this.tamadas = tamadas; }
    public void setTudas(int tudas) {
        this.tudas = tudas;
    }
    public void setMoral(int moral) {
        this.moral = moral;
    }

}
