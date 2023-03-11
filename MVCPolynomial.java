public class MVCPolynomial {
    public static void main(String[] args) {
        View theView = new View();
        Polynomial theModelFirst = new Polynomial();
        Polynomial theModelSecond = new Polynomial();
        Controller theController = new Controller(theView, theModelFirst,theModelSecond);
    }
}
