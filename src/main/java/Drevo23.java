import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

class Element23<Tip> {
    public Tip a;
    public Tip b;
    public Element23<Tip> L;
    public Element23<Tip> S;
    public Element23<Tip> D;
    public Element23<Tip> oce;

    public Element23(Tip e) {
        this.a = e;
    }
}


class Ovoj<Tip> {
    public Tip vrednost;
    public Element23<Tip> levo;
    public Element23<Tip> desno;

    public Ovoj(Tip e) {
        this.vrednost = e;
    }
}


public class Drevo23<Tip> implements Seznam<Tip> {
    private Element23<Tip> vrh;
    private Comparator<Tip> comparator;

    Drevo23(Comparator<Tip> comparator) {
        this.vrh = null;
        this.comparator = comparator;
    }

    @Override
    public void add(Tip e) {
        if (vrh == null) {
            vrh = new Element23<>(e);
        }
        else {
            addToNode(vrh, e);
        }
    }


    public void addToNode(Element23<Tip> vozlisce, Tip e) {
        if (e.equals(vozlisce.a) || e.equals(vozlisce.b)) {
            throw new IllegalArgumentException("Dvojniki niso dovoljeni.");
        }

        //vozlišče ima naslednjike (notranje vozlišče)
        if (vozlisce.L != null) {
            //je dvovozlišče
            if (vozlisce.D == null) {
                if (comparator.compare(e, vozlisce.a) < 0) {
                    addToNode(vozlisce.L, e);
                }
                else {
                    addToNode(vozlisce.S, e);
                }
            }
            //je trovozlišče
            else {
                if (comparator.compare(e, vozlisce.a) < 0) {
                    addToNode(vozlisce.L, e);
                }
                else if (comparator.compare(e, vozlisce.b) < 0) {
                    addToNode(vozlisce.S, e);
                }
                else {
                    addToNode(vozlisce.D, e);
                }
            }
        }
        //element nima naslednjikov (je list)
        else {
            //v elementu je še prostor
            if (vozlisce.b == null) {
                if (comparator.compare(e, vozlisce.a) < 0) {
                    vozlisce.b = vozlisce.a;
                    vozlisce.a = e;
                }
                else {
                    vozlisce.b = e;
                }
            }
            //v elementu ni več prostora
            else {
                if (comparator.compare(e, vozlisce.a) < 0) {
                    Ovoj ovoj = new Ovoj(vozlisce.a);
                    ovoj.levo = new Element23(e);
                    ovoj.desno = new Element23(vozlisce.b);

                    if (vozlisce.oce != null) {
                        potisniGor(ovoj, vozlisce.oce);
                    }
                    else {
                        Element23 novOce = new Element23(ovoj.vrednost);
                        novOce.L = ovoj.levo;
                        novOce.L.oce = novOce;
                        novOce.S = ovoj.desno;
                        novOce.S.oce = novOce;
                        this.vrh = novOce;
                    }
                }
                else if (comparator.compare(e, vozlisce.b) < 0) {
                    Ovoj ovoj = new Ovoj(e);
                    ovoj.levo = new Element23(vozlisce.a);
                    ovoj.desno = new Element23(vozlisce.b);

                    if (vozlisce.oce != null) {
                        potisniGor(ovoj, vozlisce.oce);
                    }
                    else {
                        Element23 novOce = new Element23(ovoj.vrednost);
                        novOce.L = ovoj.levo;
                        novOce.L.oce = novOce;
                        novOce.S = ovoj.desno;
                        novOce.S.oce = novOce;
                        this.vrh = novOce;
                    }

                }
                else {
                    Ovoj ovoj = new Ovoj(vozlisce.b);
                    ovoj.levo = new Element23(vozlisce.a);
                    ovoj.desno = new Element23(e);

                    if (vozlisce.oce != null) {
                        potisniGor(ovoj, vozlisce.oce);
                    }
                    else {
                        Element23 novOce = new Element23(ovoj.vrednost);
                        novOce.L = ovoj.levo;
                        novOce.L.oce = novOce;
                        novOce.S = ovoj.desno;
                        novOce.S.oce = novOce;
                        this.vrh = novOce;
                    }
                }
            }
        }
    }


    public void potisniGor(Ovoj<Tip> ovoj, Element23<Tip> oce) {
        //oce je dvovozlišče
        if (oce.D == null) {
            if (comparator.compare(ovoj.vrednost, oce.a) < 0) {
                oce.b = oce.a;
                oce.a = ovoj.vrednost;
                oce.D = oce.S;
                oce.L = ovoj.levo;
                oce.L.oce = oce;
                oce.S = ovoj.desno;
                oce.S.oce = oce;
            }
            else {
                oce.b = ovoj.vrednost;
                oce.D = oce.S;
                oce.S = ovoj.levo;
                oce.S.oce = oce;
                oce.D = ovoj.desno;
                oce.D.oce = oce;
            }
        }
        //oce je trovozlišče
        else {
            if (comparator.compare(ovoj.vrednost, oce.a) < 0) {
                Ovoj<Tip> naslednjiOvoj = new Ovoj<>(oce.a);
                naslednjiOvoj.levo = new Element23<>(ovoj.vrednost);
                naslednjiOvoj.levo.L = ovoj.levo;
                naslednjiOvoj.levo.L.oce = naslednjiOvoj.levo;
                naslednjiOvoj.levo.S = ovoj.desno;
                naslednjiOvoj.levo.S.oce = naslednjiOvoj.levo;
                naslednjiOvoj.desno = new Element23<>(oce.b);
                naslednjiOvoj.desno.L = oce.S;
                naslednjiOvoj.desno.L.oce = naslednjiOvoj.desno;
                naslednjiOvoj.desno.S = oce.D;
                naslednjiOvoj.desno.S.oce = naslednjiOvoj.desno;

                if (oce.oce != null) {
                    potisniGor(naslednjiOvoj, oce.oce);
                }
                else {
                    Element23<Tip> novOce = new Element23<>(naslednjiOvoj.vrednost);
                    novOce.L = naslednjiOvoj.levo;
                    novOce.L.oce = novOce;
                    novOce.S = naslednjiOvoj.desno;
                    novOce.S.oce = novOce;
                    this.vrh = novOce;
                }
            }
            else if (comparator.compare(ovoj.vrednost, oce.b) < 0) {
                Ovoj naslednjiOvoj = new Ovoj(ovoj.vrednost);
                naslednjiOvoj.levo = new Element23(oce.a);
                naslednjiOvoj.levo.L = oce.L;
                naslednjiOvoj.levo.L.oce = naslednjiOvoj.levo;
                naslednjiOvoj.levo.S = oce.S;
                naslednjiOvoj.levo.S.oce = naslednjiOvoj.levo;
                naslednjiOvoj.desno = new Element23(oce.b);
                naslednjiOvoj.desno.L = oce.S;
                naslednjiOvoj.desno.L.oce = naslednjiOvoj.desno;
                naslednjiOvoj.desno.S = oce.D;
                naslednjiOvoj.desno.S.oce = naslednjiOvoj.desno;

                if (oce.oce != null) {
                    potisniGor(naslednjiOvoj, oce.oce);
                }
                else {
                    Element23 novOce = new Element23(naslednjiOvoj.vrednost);
                    novOce.L = naslednjiOvoj.levo;
                    novOce.L.oce = novOce;
                    novOce.S = naslednjiOvoj.desno;
                    novOce.S.oce = novOce;
                    this.vrh = novOce;
                }

            }
            else {
                Ovoj naslednjiOvoj = new Ovoj(oce.b);
                naslednjiOvoj.levo = new Element23(oce.a);
                naslednjiOvoj.levo.L = oce.L;
                naslednjiOvoj.levo.L.oce = naslednjiOvoj.levo;
                naslednjiOvoj.levo.S = oce.S;
                naslednjiOvoj.levo.S.oce = naslednjiOvoj.levo;
                naslednjiOvoj.desno = new Element23(ovoj.vrednost);
                naslednjiOvoj.desno.L = ovoj.levo;
                naslednjiOvoj.desno.L.oce = naslednjiOvoj.desno;
                naslednjiOvoj.desno.S = ovoj.desno;
                naslednjiOvoj.desno.S.oce = naslednjiOvoj.desno;

                if (oce.oce != null) {
                    potisniGor(naslednjiOvoj, oce.oce);
                }
                else {
                    Element23 novOce = new Element23(naslednjiOvoj.vrednost);
                    novOce.L = naslednjiOvoj.levo;
                    novOce.L.oce = novOce;
                    novOce.S = naslednjiOvoj.desno;
                    novOce.S.oce = novOce;
                    this.vrh = novOce;
                }
            }
        }
    }


    @Override
    public List<Tip> asList() {
        if (vrh == null) {
            throw new NoSuchElementException();
        }

        List<Tip> list = new ArrayList<>();
        list.addAll(elementAsList(vrh));
        return list;
    }

    public List<Tip> elementAsList(Element23 element) {
        List<Tip> list = new ArrayList<>();

        list.add((Tip) element.a);
        if (element.b != null) {
            list.add((Tip) element.b);
        }

        if (element.L != null) {
            list.addAll(elementAsList(element.L));
            list.addAll(elementAsList(element.S));
        }
        if (element.D != null) {
            list.addAll(elementAsList(element.D));
        }

        return list;
    }


    public List<String> asListWithBrackets() {
        if (vrh == null) {
            throw new NoSuchElementException("Seznam je prazen.");
        }

        List<String> list = new ArrayList<>();
        list.addAll(elementAsListWithBrackets(vrh));
        return list;
    }

    public List<String> elementAsListWithBrackets(Element23 element) {
        List<String> list = new ArrayList<>();

        if (element.b != null) {
            list.add("(" + element.a + ", " + element.b + ")");
        }
        else {
            list.add("(" + element.a + ")");
        }

        if (element.L != null) {
            list.addAll(elementAsListWithBrackets(element.L));
            list.addAll(elementAsListWithBrackets(element.S));
        }
        if (element.D != null) {
            list.addAll(elementAsListWithBrackets(element.D));
        }

        return list;
    }


    @Override
    public boolean isEmpty() {
        return vrh == null;
    }


    @Override
    public int size() {
        if (isEmpty()) {
            return 0;
        }
        else {
            return this.asList().size();
        }
    }


    @Override
    public int depth() {
        if (isEmpty()) {
            return 0;
        }

        int globina = 0;
        Element23 vozlisce = vrh;
        while (vozlisce != null) {
            globina++;
            vozlisce = vozlisce.L;
        }
        return globina;
    }


    @Override
    public Tip getFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        else {
            return vrh.a;
        }
    }


    @Override
    public boolean exists(Tip e) {
        if (isEmpty()) {
            return  false;
        }
        else {
            return existsInElement(vrh, e);
        }
    }

    public boolean existsInElement(Element23<Tip> element, Tip e) {
        if (comparator.compare(e, element.a) == 0) {
            return true;
        }
        if (element.b != null) {
            if (comparator.compare(e, element.b) == 0) {
                return true;
            }
        }
        if (element.L != null) {
            if (existsInElement(element.L, e)) {
                return true;
            }
            if (existsInElement(element.S, e)) {
                return true;
            }
        }
        if (element.D != null) {
            if (existsInElement(element.D, e)) {
                return true;
            }
        }

        return false;
    }


    public String search(Tip e) {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        else {
            return searchInElement(vrh, e);
        }
    }

    public String searchInElement(Element23<Tip> element, Tip e) {
        if (comparator.compare(e, element.a) == 0) {
            return element.a.toString();
        }
        if (element.b != null) {
            if (comparator.compare(e, element.b) == 0) {
                return element.b.toString();
            }
        }
        if (element.L != null) {
            if (existsInElement(element.L, e)) {
                return searchInElement(element.L, e);
            }
            if (existsInElement(element.S, e)) {
                return searchInElement(element.S, e);
            }
        }
        if (element.D != null) {
            if (existsInElement(element.D, e)) {
                return searchInElement(element.D, e);
            }
        }

        throw new NoSuchElementException();
    }


    @Override
    public Tip remove(Tip e) {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        else {
            return elementFindAndRemove(vrh, e);
        }
    }

    public Tip elementFindAndRemove(Element23<Tip> vozlisce, Tip e) {
        //če je e v tem vozlišču
        if (comparator.compare(e, vozlisce.a) == 0) {
            return elementRemove(vozlisce, e);
        }
        if (vozlisce.b != null) {
            if (comparator.compare(e, vozlisce.b) == 0) {
                return elementRemove(vozlisce, e);
            }
        }

        //e ni v tem vozlišču in smo v listu
        if (vozlisce.L == null) {
            throw new NoSuchElementException();
        }

        //e ni v tem vozlišču in smo v notranjem vozlišču
        if (comparator.compare(e, vozlisce.a) < 0) {
            return elementFindAndRemove(vozlisce.L, e);
        }
        if (vozlisce.b != null) {
            if (comparator.compare(e, vozlisce.b) < 0) {
                return elementFindAndRemove(vozlisce.S, e);
            }
            else {
                return elementFindAndRemove(vozlisce.D, e);
            }
        }
        else {
            return elementFindAndRemove(vozlisce.S, e);
        }
    }

    public Tip elementRemove(Element23<Tip> vozlisce, Tip e) {
        //smo v notranjem vozlišču
        if (vozlisce.L != null) {
            //smo v dvovozlišču - poiščemo najmanjšega desno (ND), z njim prepišemo tarčo, izbrišemo ND, vrnemo tarčo
            if (vozlisce.b == null) {
                Tip najmanjsiDesno = getNajmanjsiDesno(vozlisce.S);
                Tip odstranjenElement = vozlisce.a;
                vozlisce.a = najmanjsiDesno;
                elementFindAndRemove(vozlisce.S, najmanjsiDesno);
                return odstranjenElement;
            }
            //smo v trovozlišču
            else {
                //brišemo prvega - poiščemo najmanjšega desno (ND), z njim prepišemo tarčo, izbrišemo ND, vrnemo tarčo
                if (comparator.compare(e, vozlisce.a) == 0) {
                    Tip najmanjsiDesno = getNajmanjsiDesno(vozlisce.S);
                    Tip odstranjenElement = vozlisce.a;
                    vozlisce.a = najmanjsiDesno;
                    elementFindAndRemove(vozlisce.S, najmanjsiDesno);
                    return odstranjenElement;
                }
                //brišemo drugega - poiščemo najmanjšega desno (ND), z njim prepišemo tarčo, izbrišemo ND, vrnemo tarčo
                else {
                    Tip najmanjsiDesno = getNajmanjsiDesno(vozlisce.D);
                    Tip odstranjenElement = vozlisce.b;
                    vozlisce.b = najmanjsiDesno;
                    elementFindAndRemove(vozlisce.D, najmanjsiDesno);
                    return odstranjenElement;
                }
            }
        }
        //smo v listu
        else {
            //dve vrednosti v listu
            if (vozlisce.b != null) {
                //brišemo prvega
                if (comparator.compare(e, vozlisce.a) == 0) {
                    Tip odstranjenElement = vozlisce.a;
                    vozlisce.a = vozlisce.b;
                    vozlisce.b = null;
                    return odstranjenElement;
                }
                //brišemo drugega
                else {
                    Tip odstranjenElement = vozlisce.b;
                    vozlisce.b = null;
                    return odstranjenElement;
                }
            }
            //samo ena vrednost v listu
            else {
                //smo v vrhu z zadnjo vrednostjo
                if (vozlisce.oce == null) {
                    Tip odstranjenElement = vrh.a;
                    vrh = null;
                    return odstranjenElement;
                }
                //smo v levem otroku
                if (vozlisce.equals(vozlisce.oce.L)) {
                    //desni (srednji) brat ima dve vrednosti - leva rotacija
                    if (vozlisce.oce.S.b != null) {
                        Tip odstranjenElement = vozlisce.a;
                        vozlisce.a = vozlisce.oce.a;
                        vozlisce.oce.a = vozlisce.oce.S.a;
                        vozlisce.oce.S.a = vozlisce.oce.S.b;
                        vozlisce.oce.S.b = null;
                        return odstranjenElement;
                    }
                    //desni (srednji) brat nima dveh vrednosti
                    else {
                        //oce ima dve vrednosti - leva rotacija preko oceta
                        if (vozlisce.oce.b != null) {
                            Tip odstranjenElement = vozlisce.a;
                            vozlisce.a = vozlisce.oce.a;
                            vozlisce.b = vozlisce.oce.S.a;
                            vozlisce.oce.a = vozlisce.oce.b;
                            vozlisce.oce.b = null;
                            vozlisce.oce.S = vozlisce.oce.D;
                            vozlisce.oce.D = null;
                            return odstranjenElement;
                        }
                        //oce nima dveh vrednosti - premaknemo luknjo gor
                        else {
                            Tip odstranjenElement = vozlisce.a;
                            vozlisce.a = vozlisce.oce.a;
                            vozlisce.b = vozlisce.oce.S.a;
                            vozlisce.oce.a = null;
                            vozlisce.oce.S = vozlisce;
                            vozlisce.oce.L = null;
                            resiLuknjo(vozlisce.oce);
                            return odstranjenElement;
                        }
                    }
                }
                //smo v srednjem otroku
                else if (vozlisce.equals(vozlisce.oce.S)) {
                    //levi (L) brat ima dve vrednosti - desna rotacija
                    if (vozlisce.oce.L.b != null) {
                        Tip odstranjenElement = vozlisce.a;
                        vozlisce.a = vozlisce.oce.a;
                        vozlisce.oce.a = vozlisce.oce.L.b;
                        vozlisce.oce.L.b = null;
                        return odstranjenElement;
                    }
                    //smo v trovozlišču - imamo desnega (D) brata
                    else if (vozlisce.oce.D != null) {
                        //desni (D) brat ima dve vrednosti - leva rotacija
                        if (vozlisce.oce.D.b != null) {
                            Tip odstranjenElement = vozlisce.a;
                            vozlisce.a = vozlisce.oce.b;
                            vozlisce.oce.b = vozlisce.oce.D.a;
                            vozlisce.oce.D.a = vozlisce.oce.D.b;
                            vozlisce.oce.D.b = null;
                            return odstranjenElement;
                        }
                    }

                    //niti levi (L), niti desni (D) brat nima dveh vrednosti
                    //oce ima dve vrednosti - desna rotacija preko oceta
                    if (vozlisce.oce.b != null) {
                        Tip odstranjenElement = vozlisce.a;
                        vozlisce.oce.D.b = vozlisce.oce.D.a;
                        vozlisce.oce.D.a = vozlisce.oce.b;
                        vozlisce.oce.b = null;
                        vozlisce.oce.S = vozlisce.oce.D;
                        vozlisce.oce.D = null;
                        return odstranjenElement;
                    }
                    //oce nima dveh vrednosti
                    else {
                        Tip odstranjenElement = vozlisce.a;
                        vozlisce.a = vozlisce.oce.L.a;
                        vozlisce.b = vozlisce.oce.a;
                        vozlisce.oce.a = null;
                        vozlisce.oce.L = null;
                        resiLuknjo(vozlisce.oce);
                        return odstranjenElement;
                    }
                }
                //smo v desnem otroku
                else {
                    //levi (srednji) brat ima dve vrednosti - desna rotacija
                    if (vozlisce.oce.S.b != null) {
                        Tip odstranjenElement = vozlisce.a;
                        vozlisce.a = vozlisce.oce.b;
                        vozlisce.oce.b = vozlisce.oce.S.b;
                        vozlisce.oce.S.b = null;
                        return odstranjenElement;
                    }
                    //levi (srednji) brat nima dveh vrednosti
                    else {
                        //oce ima gotovo dve vrednosti, ker smo v njegovem desnem (D) otroku - desna rotacija preko oceta
                        Tip odstranjenElement = vozlisce.a;
                        vozlisce.a = vozlisce.oce.S.a;
                        vozlisce.b = vozlisce.oce.b;
                        vozlisce.oce.b = null;
                        vozlisce.oce.S = vozlisce;
                        vozlisce.oce.D = null;
                        return odstranjenElement;
                    }
                }
            }
        }


    }

    public void resiLuknjo(Element23<Tip> vozlisce) {
        //smo na vrhu
        if (vozlisce.oce == null) {
            vrh = vozlisce.S;
            vozlisce.S.oce = null;
            return;
        }

        //smo v levem otroku
        if (vozlisce.equals(vozlisce.oce.L)) {
            //desni (srednji) brat ima dve vrednosti - leva rotacija
            if (vozlisce.oce.S.b != null) {
                vozlisce.a = vozlisce.oce.a;
                vozlisce.oce.a = vozlisce.oce.S.a;
                vozlisce.oce.S.a = vozlisce.oce.S.b;
                vozlisce.oce.S.b = null;
                vozlisce.L = vozlisce.S;
                vozlisce.S = vozlisce.oce.S.L;
                vozlisce.oce.S.L.oce = vozlisce;
                vozlisce.oce.S.L = vozlisce.oce.S.S;
                vozlisce.oce.S.S = vozlisce.oce.S.D;
                vozlisce.oce.S.D = null;
                return;
            }
            //desni (srednji) brat nima dveh vrednosti
            else {
                //oce ima dve vrednosti - leva rotacija preko oceta
                if (vozlisce.oce.b != null) {
                    vozlisce.a = vozlisce.oce.a;
                    vozlisce.b = vozlisce.oce.S.a;
                    vozlisce.oce.a = vozlisce.oce.b;
                    vozlisce.oce.b = null;
                    vozlisce.L = vozlisce.S;
                    vozlisce.S = vozlisce.oce.S.L;
                    vozlisce.oce.S.L.oce = vozlisce;
                    vozlisce.D = vozlisce.oce.S.S;
                    vozlisce.oce.S.S.oce = vozlisce;
                    vozlisce.oce.S = vozlisce.oce.D;
                    vozlisce.oce.D = null;
                    return;
                }
                //oce nima dveh vrednosti - premaknemo luknjo gor
                else {
                    vozlisce.a = vozlisce.oce.a;
                    vozlisce.b = vozlisce.oce.S.a;
                    vozlisce.oce.a = null;
                    vozlisce.L = vozlisce.S;
                    vozlisce.S = vozlisce.oce.S.L;
                    vozlisce.oce.S.L.oce = vozlisce;
                    vozlisce.D = vozlisce.oce.S.S;
                    vozlisce.oce.S.S.oce = vozlisce;
                    vozlisce.oce.S = vozlisce;
                    vozlisce.oce.L = null;
                    resiLuknjo(vozlisce.oce);
                    return;
                }
            }
        }
        //smo v srednjem otroku
        else if (vozlisce.equals(vozlisce.oce.S)) {
            //levi (L) brat ima dve vrednosti - desna rotacija
            if (vozlisce.oce.L.b != null) {
                vozlisce.a = vozlisce.oce.a;
                vozlisce.oce.a = vozlisce.oce.L.b;
                vozlisce.oce.L.b = null;
                vozlisce.L = vozlisce.oce.L.D;
                vozlisce.oce.L.D.oce = vozlisce;
                return;
            }
            //smo v trovozlišču - imamo desnega (D) brata
            else if (vozlisce.oce.D != null) {
                //desni (D) brat ima dve vrednosti - leva rotacija
                if (vozlisce.oce.D.b != null) {
                    vozlisce.a = vozlisce.oce.b;
                    vozlisce.oce.b = vozlisce.oce.D.a;
                    vozlisce.oce.D.a = vozlisce.oce.D.b;
                    vozlisce.oce.D.b = null;
                    vozlisce.L = vozlisce.S;
                    vozlisce.S = vozlisce.oce.D.L;
                    vozlisce.oce.D.L.oce = vozlisce;
                    vozlisce.oce.D.L = vozlisce.oce.D.S;
                    vozlisce.oce.D.S = vozlisce.oce.D.D;
                    return;
                }
            }

            //niti levi (L), niti desni (D) brat nima dveh vrednosti
            //oce ima dve vrednosti - desna rotacija preko oceta
            if (vozlisce.oce.b != null) {
                vozlisce.oce.D.b = vozlisce.oce.D.a;
                vozlisce.oce.D.a = vozlisce.oce.b;
                vozlisce.oce.S = vozlisce.oce.D;
                vozlisce.oce.S.D = vozlisce.oce.S.S;
                vozlisce.oce.S.S = vozlisce.oce.S.L;
                vozlisce.oce.S.L = vozlisce.S;
                vozlisce.S.oce = vozlisce.oce.S;
                vozlisce.oce.D = null;
                return;
            }
            //oce nima dveh vrednosti - premaknemo luknjo gor
            else {
                vozlisce.a = vozlisce.oce.L.a;
                vozlisce.b = vozlisce.oce.a;
                vozlisce.oce.a = null;
                vozlisce.D = vozlisce.S;
                vozlisce.S = vozlisce.oce.L.S;
                vozlisce.oce.L.S.oce = vozlisce;
                vozlisce.L = vozlisce.oce.L.L;
                vozlisce.oce.L.L.oce = vozlisce;
                vozlisce.oce.L = null;
                resiLuknjo(vozlisce.oce);
                return;
            }
        }
        //smo v desnem otroku
        else {
            //levi (srednji) brat ima dve vrednosti - desna rotacija
            if (vozlisce.oce.S.b != null) {
                vozlisce.a = vozlisce.oce.b;
                vozlisce.oce.b = vozlisce.oce.S.b;
                vozlisce.oce.S.b = null;
                vozlisce.L = vozlisce.oce.S.D;
                vozlisce.oce.S.D.oce = vozlisce;
                vozlisce.oce.S.D = null;
                return;
            }
            //levi (srednji) brat nima dveh vrednosti
            else {
                //oce ima gotovo dve vrednosti, ker smo v njegovem desnem (D) otroku - desna rotacija preko oceta
                vozlisce.a = vozlisce.oce.S.a;
                vozlisce.b = vozlisce.oce.b;
                vozlisce.oce.b = null;
                vozlisce.D = vozlisce.S;
                vozlisce.S = vozlisce.oce.S.S;
                vozlisce.oce.S.S.oce = vozlisce;
                vozlisce.L = vozlisce.oce.S.L;
                vozlisce.oce.S.L.oce = vozlisce;
                vozlisce.oce.S = vozlisce;
                vozlisce.oce.D = null;
                return;
            }
        }
    }

    public Tip getNajmanjsiDesno(Element23<Tip> desniOtrok) {
        Element23<Tip> vozlisce = desniOtrok;
        Tip najmanjsiDesno = null;
        while (vozlisce != null) {
            najmanjsiDesno = (Tip) vozlisce.a;
            vozlisce = vozlisce.L;
        }
        return najmanjsiDesno;
    }


    @Override
    public Tip removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        else {
            return remove(vrh.a);
        }
    }

    @Override
    public void reset() {
        while (!isEmpty()) {
            removeFirst();
        }
    }

    @Override
    public String print() {
        try {
            List<Tip> list = asList();
            list.sort(comparator);
            String output = "";
            for (int i = 0; i < list.size(); i++) {
                output = output + list.get(i).toString() + "\n";
            }
            output = output.substring(0, output.length() - 1);
            return output;
        } catch (NoSuchElementException e) {
            return "";
        }
    }

    @Override
    public void save(OutputStream outputStream) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(outputStream);
        out.writeInt(this.size());

        try {
            List<Tip> list = asList();
            for (int i = 0; i < list.size(); i++) {
                out.writeObject(list.get(i));
            }
        }
        catch (NoSuchElementException e) {
            out.writeObject("podatki za stream, da preprecimo End Of File Exception ker prekratek stream podatkov");
        }
    }

    @Override
    public void restore(InputStream inputStream) throws IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(inputStream);
        int count = in.readInt();
        this.reset();

        for (int i = 0; i < count; i++) {
            Tip element = (Tip) in.readObject();
            this.add(element);
        }
    }
}
