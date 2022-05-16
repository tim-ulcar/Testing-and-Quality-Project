public class Drzavljan implements java.io.Serializable {
    protected String emso;
    protected String ime;
    protected String priimek;
    protected int starost;
    protected String cepivo;

    public Drzavljan() {}

    public Drzavljan(String emso, String ime, String priimek, int starost, String cepivo) {
        this.emso = emso;
        this.ime = ime;
        this.priimek = priimek;
        this.starost = starost;
        this.cepivo = cepivo;
    }

    public String getIme()
    {
        return ime;
    }

    public String getPriimek()
    {
        return priimek;
    }

    public String getEmso() {
        return emso;
    }

    @Override
    public String toString() {
        return emso + "\t" + priimek + ", " + ime + "\t" + starost + "\t" + cepivo;
    }
}


class DrzavljanPrimerjajPoImenu implements java.util.Comparator<Drzavljan> {
    @Override
    public int compare(Drzavljan o1, Drzavljan o2)
    {
        String ime1 = o1.getPriimek() + ", " + o1.getIme();
        String ime2 = o2.getPriimek() + ", " + o2.getIme();
        return ime1.compareToIgnoreCase(ime2);
    }
}


class DrzavljanPrimerjajPoEmso implements java.util.Comparator<Drzavljan> {
    @Override
    public int compare(Drzavljan o1, Drzavljan o2)
    {
        String s1 = o1.getEmso();
        String s2 = o2.getEmso();
        return s1.compareTo(s2);
    }
}
