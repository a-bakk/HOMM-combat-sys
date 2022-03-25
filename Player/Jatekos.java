package Player;

import GameManager.*;

public class Jatekos {

    /* package private */ int arany;
    /* package private */ int manna;
    /* package private */ int tulajdonsagAr;
    private boolean szamitoGep = false;

    public Hos jatekosHose = new Hos();
    public Varazslat[] varazslatok = new Varazslat[5];
    public Egyseg[] egysegek = new Egyseg[5];

    public Jatekos(boolean szGep) {

        if (szGep) {
            this.szamitoGep = true;
        }

        if(!szGep) {
            switch (GameManager.getNehezseg()) {
                case "konnyu" -> this.arany = 1300;
                case "kozepes" -> this.arany = 1000;
                case "nehez" -> this.arany = 700;
            }
            this.szamitoGep = false;
        }

        this.manna = 10;
        this.tulajdonsagAr = 5;

        varazslatok[0] = new Tuzlabda();
        varazslatok[1] = new Villamcsapas();
        varazslatok[2] = new Feltamasztas();

        egysegek[0] = new Foldmuves();
        egysegek[1] = new Ijasz();
        egysegek[2] = new Griff();

    }

    public int getArany() { return arany; }

    public int getManna() { return manna; }

    public boolean isSzamitoGep() {
        return szamitoGep;
    }

    public void setArany(int arany) {
        this.arany = arany;
    }

    public void setManna(int manna) {
        this.manna = manna;
    }

    public void setTulajdonsagAr(int tulajdonsagAr) {
        this.tulajdonsagAr = tulajdonsagAr;
    }

    public void setSzamitoGep(boolean szamitoGep) {
        this.szamitoGep = szamitoGep;
    }

    public void setJatekosHose(Hos jatekosHose) {
        this.jatekosHose = jatekosHose;
    }

    public void setVarazslatok(Varazslat[] varazslatok) {
        this.varazslatok = varazslatok;
    }

    public void setEgysegek(Egyseg[] egysegek) {
        this.egysegek = egysegek;
    }
}
