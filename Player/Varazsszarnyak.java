package Player;

public class Varazsszarnyak extends Varazslat {

    public Varazsszarnyak() {
        this.rendelkezik = false;
        this.ar = 110;
        this.mannaKoltseg = 8;
    }

    @Override
    public String kulonlegesHatas(Jatekos kiTamad, Jatekos kitTamad, int koordX, int koordY) {
        /* direkt hivasnal megvalositva, ez most sajnos ilyen */
        return null;
    }
}
