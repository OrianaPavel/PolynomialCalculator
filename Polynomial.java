
import java.util.*;

public class Polynomial {
    List<Monomial> monomialList;
    public Polynomial(){
        monomialList = new ArrayList<>();
    } // alocarea spatiliului in memorie pt list
    public void addMonomial(Monomial m){
        monomialList.add(m);
    }
    protected void clear(){
        monomialList.clear();
    } // stergerea tuturor obiectelor din lista
    protected Polynomial add(Polynomial a, Polynomial b){ // metoda de adunare
        Polynomial rez = new Polynomial();
        for (Monomial p1: a.monomialList) { // parcurgerea tuturor monoamelor din p1
            int grad = p1.getGrad();
            double coef = p1.getCoef(); // salvarea grad si coef in variabile locale
            for (Monomial p2: b.monomialList) { // cautarea monomului cu acelasi grad in b
                if( p1.getGrad() == p2.getGrad()){
                    coef += p2.getCoef(); // daca exista se aduna coefs
                    b.monomialList.remove(p2); // se sterge monomul din b pentru a fi usor sa adaug uleterior monoamele de grad diferit care nu se regasesc in a
                    break;
                }
            }
            if(!(Math.abs(coef) < 0.000001))  // inainte de adauga monomul la rezultate verific sa fie coef != 0
                rez.monomialList.add(new Monomial(grad,coef));
        }
        for(Monomial p1: b.monomialList) // adaug la rezultat toate monoms de grad diferit
            rez.monomialList.add(p1);
        Collections.sort(rez.monomialList); //sortez monoms din rez in ordine desc in fct de grad
        return rez;
    }
    protected Polynomial sub(Polynomial a, Polynomial b){ // similar cu
        Polynomial rez = new Polynomial();
        for (Monomial p1: a.monomialList) { // parcurgerea tuturor monoamelor din p1
            int grad = p1.getGrad();
            double coef = p1.getCoef(); // salvarea grad si coef in variabile locale
            for (Monomial p2: b.monomialList) {
                if( p1.getGrad() == p2.getGrad()){ // cautarea monomului cu acelasi grad in b
                    coef -= p2.getCoef(); // daca exista se scad coefs
                    b.monomialList.remove(p2); // se sterge monomul din b pentru a fi usor sa adaug uleterior monoamele de grad diferit care nu se regasesc in a
                    break;
                }
            }
            if(!(Math.abs(coef) < 0.000001))
                rez.monomialList.add(new Monomial(grad, coef));
        }
        for(Monomial p1: b.monomialList) // adaug la rezultat toate monoms de grad diferit
            rez.monomialList.add(new Monomial(p1.getGrad(),-p1.getCoef()));
        Collections.sort(rez.monomialList); //sortez monoms din rez in ordine desc in fct de grad
        if(rez.monomialList.isEmpty())
            rez.monomialList.add(new Monomial(0,0));
        return rez;
    }
    protected Polynomial multiply( Polynomial a, Polynomial b){
        Polynomial rez = new Polynomial();
        for(Monomial p1: a.monomialList){ // parcurg fiecare lista
            for(Monomial p2 : b.monomialList){ // pt fiecare monom din a parcurg toti monoms din b
                int flag = 1;
                double coef = p1.getCoef() * p2.getCoef(); // inmultesc coeficientii intre ei, iar gradele le adun
                int grad = p1.getGrad() + p2.getGrad();
                for(Monomial p3 : rez.monomialList) // caut in rez un monom de aclesi grad
                    if(p3.getGrad() == grad){
                        flag = 0;
                        p3.setCoef(p3.getCoef() + coef) ; // in caz ca exista adauga coef obtinut
                        break;
                    }
                if( flag == 1) // in caz ca nu exista in rez il adaug
                    rez.addMonomial(new Monomial(grad,coef));
            }
        }
        return rez;
    }
    protected Polynomial divide(Polynomial a, Polynomial b){
        Polynomial rez = new Polynomial();
        Collections.sort(a.monomialList);
        Collections.sort(b.monomialList);
        int grad;
        double coef;
        while((!a.monomialList.isEmpty() && !b.monomialList.isEmpty()) && a.monomialList.get(0).getGrad() >= b.monomialList.get(0).getGrad()){ // cat timp gradul celui mai mare monom din a >
            grad = a.monomialList.get(0).getGrad() - b.monomialList.get(0).getGrad(); // gradul celui mai mare monom din b
            coef = a.monomialList.get(0).getCoef() / b.monomialList.get(0).getCoef(); // scad grad si impart coef
            Polynomial c = new Polynomial();
            rez.addMonomial(new Monomial(grad,coef)); // adaug la cat
            c.addMonomial(new Monomial(grad,coef)); // adaug in c monomul obtinut
            c = c.multiply(b,c); // inmultesc c cu polinomul b si salvez rezultatul in c
            a = a.sub(a,c); // si scad din a polinomul obtinut

            Collections.sort(a.monomialList); // sortez a in ordine desc
        }
        return rez;
    }
    protected Polynomial modulo(Polynomial a, Polynomial b){ // similar cu metoda de impapartire
        Collections.sort(a.monomialList);
        Collections.sort(b.monomialList);
        int grad;
        double coef;
        while((!a.monomialList.isEmpty() && !b.monomialList.isEmpty()) && a.monomialList.get(0).getGrad() >= b.monomialList.get(0).getGrad()){
            grad = a.monomialList.get(0).getGrad() - b.monomialList.get(0).getGrad();
            coef = a.monomialList.get(0).getCoef() / b.monomialList.get(0).getCoef();
            Polynomial c = new Polynomial();
            c.addMonomial(new Monomial(grad,coef));
            c = c.multiply(b,c);
            a = a.sub(a,c);
            Collections.sort(a.monomialList);
        }
        if(a.monomialList.isEmpty())
            a.addMonomial(new Monomial(0,0));
        return a;
    }
    protected Polynomial derivative(Polynomial a){
        Polynomial rez = new Polynomial();
        for(Monomial monomial: a.monomialList){ // parcurg monoms din a
            int grad = monomial.getGrad();
            double coef = monomial.getCoef(); // op matematice specifice
            monomial.setGrad(grad-1);
            monomial.setCoef(coef * grad);
            if(monomial.getGrad() >= 0) // daca gradul este >=0 il adaug la rez
                rez.addMonomial(monomial);
        }
        return rez;
    }
    protected Polynomial integral(Polynomial a){
        Polynomial rez = new Polynomial();
        for(Monomial monomial: a.monomialList) {
            int grad = monomial.getGrad();
            double coef = monomial.getCoef();
            monomial.setGrad(grad + 1);
            coef /= grad+1;
            monomial.setCoef(coef);
            rez.addMonomial(monomial);
        }
        return rez;
    }
    private Monomial getMonomial(int grad, double value, List<String> coefs) throws Exception{
        double second;
        String lastOp = null;
        for(String coef : coefs){ // parcurg fiecare string din lista
            if(coef.equals("*")) {
                if(lastOp != null) // verific sa nu am doua semne ** sau */ etc => date invalide
                    throw new Exception();
                lastOp = "*"; // retin semnul intalnit
                continue;
            }
            if(coef.equals("/")){ // similar ^^
                if(lastOp != null)
                    throw new Exception();
                lastOp = "/";
                continue;
            }
            second = Double.parseDouble(coef); // extrag valoarea
            if(lastOp.equals("*")) { //  inmultesc valoarea deja obtinuta cu cea curenta
                value *= second;
                lastOp = null;
            }
            else if(lastOp.equals("/")) { // impart val obt cu cea curenta
                value /= second;
                lastOp = null;
            }
        }
        if(lastOp != null) // daca la final am avut un semn => date de intrare ex : 2**x^2 => invalide
            throw new Exception();
        return new Monomial(grad,value); // returnez obiectul
    }
    protected void transformFromString(String text) throws Exception{
        this.clear();
        Float first ,second = 0f;
        int grad = 0,sign = 1;
        String[] monomyals = text.split("((?<=[+-])|(?=[+-]))"); // impart text in subsiruri in fct de aparitia + - // + - sunt stringuri separate
        for(String each: monomyals){ // partcurg fiecare string obitnut anterior
            if(each.equals("+")) // retin semnul pt monom
                sign = 1;
            else if(each.equals("-"))
                sign = -1;
            else {
                if(each.matches("[0-9]+")){ // daca are numai cifre ca gradul e 0 raman doar cu coef
                    this.addMonomial(new Monomial(0,sign * Double.parseDouble(each)));
                    continue;
                }
                if(each.matches("^[^x]*x[^x]*$")) { // contine x o singura data
                    grad = 1;
                    if(each.matches("^[^\\^]*\\^[^\\^]*$")) {  // contine ^ specific pt grad

                        grad = Integer.parseInt(each.substring(each.indexOf('^') + 1)); // extrag gradul
                        each = each.substring(0,each.indexOf('^'));
                    }
                    if(each.equals("x")) { // daca stringul ramas contine doar x => coef = 1
                        this.addMonomial(new Monomial(grad, 1 * sign)); // inmultesc coef cu sign
                        continue;
                    }
                    if(!each.endsWith("*x")) // daca nu se termina cu *x => date invalide
                        throw new Exception();
                    each = each.substring(0,each.length()-2); // elimin *x din string
                }
                else if(!each.contains("x")) // daca nu contine x => grad = 0
                    grad = 0;
                else
                    throw new Exception();
                List<String> coefs = new ArrayList<String>(Arrays.asList(each.split("((?<=[*/])|(?=[*/]))"))); // imaprt stringul ramas in subsiruri delimitate de / *
                // / * ramas in stringuri separate
                first = sign * Float.parseFloat(coefs.get(0)); // extrag prima valoare din coef
                coefs.remove(0); // elimin
                this.addMonomial(getMonomial(grad,first,coefs)); // trimitd rezultatul pt a obtine calcula valoarea coef
            }
        }
    }
    @Override
    public String toString() {
       String result = "";
       for(Monomial a: monomialList)
            result+=a;
       return result;
    }

    @Override
    public boolean equals(Object obj) {
        Iterator<Monomial> a = this.monomialList.iterator();
        Iterator<Monomial> b = ((Polynomial)obj).monomialList.iterator();
        while (a.hasNext() && b.hasNext())
            if(!a.next().equals(b.next()))
                return false;
        if( a.hasNext() || b.hasNext())
            return false;
        return true;
    }
}
