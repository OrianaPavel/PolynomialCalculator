public class Monomial implements Comparable{
    private int grad;
    private double coef;

    protected Monomial(int grad, double coef) {
        this.grad = grad;
        this.coef = coef;
    }

    protected int getGrad() {
        return grad;
    }

    protected void setGrad(int grad) {
        this.grad = grad;
    }

    protected double getCoef() {
        return coef;
    }

    protected void setCoef(double coef) {
        this.coef = coef;
    }

    @Override
    public String toString() {
        String printGrad = "";
        if(grad != 0)
            printGrad = "*x^" + grad;
        if(java.lang.Math.abs(coef - 1) < 0.000001){
            if(coef > 0)
                return "+x^" + grad;
            return "-x^" + grad;
        }
        if(coef > 0)
            return "+"+coef + printGrad;
        return coef + printGrad;

    }

    @Override
    public int compareTo(Object b) {
        return ((Monomial)b).grad - this.grad;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null)
            return false;
        if(obj == this)
            return true;
        return grad == ((Monomial)obj).getGrad() && java.lang.Math.abs(coef - ((Monomial)obj).getCoef()) < 0.0000001 ;
    }
}
