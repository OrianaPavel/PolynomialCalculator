
import javax.swing.*;
import java.awt.event.*;

public class Controller {
    private final View theView;
    private final Polynomial firstPoly;
    private final Polynomial secondPoly;
    private int focusedField;

    public Controller(View view, Polynomial polynomial1, Polynomial polynomial2) { // in constructor am adaugat actionlistener fiecarui buton
        theView = view;
        firstPoly = polynomial1;
        secondPoly = polynomial2;
        AddToField field = new AddToField();
        theView.b0.addActionListener(field);
        theView.b1.addActionListener(field);
        theView.b2.addActionListener(field);
        theView.b3.addActionListener(field);
        theView.b4.addActionListener(field);
        theView.b5.addActionListener(field);
        theView.b6.addActionListener(field);
        theView.b7.addActionListener(field);
        theView.b8.addActionListener(field);
        theView.b9.addActionListener(field);
        theView.bAdd.addActionListener(field);
        theView.bSubstract.addActionListener(field);
        theView.bDivide.addActionListener(field);
        theView.bMultiplication.addActionListener(field);
        theView.bPower.addActionListener(field);
        theView.bPoint.addActionListener(field);
        theView.bX.addActionListener(field);

        theView.bDel.addActionListener(new DeleteAction());
        theView.firstField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                focusedField = 1;
            }
        });
        theView.secondField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                focusedField = 2;
            }
        });

        theView.add.addActionListener((new AddAction()));
        theView.substract.addActionListener(new SubstractAction());
        theView.exit.addActionListener(new ExitAction());
        theView.multiplication.addActionListener(new MultiplyAction());
        theView.divide.addActionListener(new DivideAction());
        theView.modulo.addActionListener(new ModuloAction());
        theView.integral.addActionListener(new IntegralAction());
        theView.derivative.addActionListener(new DerivativeAction());
    }
    private void showResult(boolean ok,Polynomial rez,int type){
        if(type == 2) // type = 2 => foloseste ambele campuri
            ok = ok && !theView.getTextField(1).isEmpty() && !theView.getTextField(2).isEmpty();
        else if( type == 1)
            ok = ok && !theView.getTextField(1).isEmpty();
        if(ok)
            JOptionPane.showMessageDialog(null, rez); // daca datele au fost valide afiseaza polinomul rezultat
        else
            JOptionPane.showMessageDialog(null, "Date invalide");
    }
    class AddToField implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            String input = ((JButton)(e.getSource())).getText();
            theView.setTextField(theView.getTextField(focusedField) + input,focusedField);
        }
    }
    class DeleteAction implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            int length;
            String s = theView.getTextField(focusedField);
            length = s.length();
            if( length > 0) {
                theView.setTextField(s.substring(0,length-1),focusedField);
            }
        }
    }
    private boolean getPolynoms(){ // transforma string-urile din campuri in obiecte de tipul polinom
        try {
            if(!theView.getTextField(1).isEmpty())
                firstPoly.transformFromString(theView.getTextField(1));
            if(!theView.getTextField(2).isEmpty())
                secondPoly.transformFromString(theView.getTextField(2));
        } catch (Exception exception) {
            return false;
        }
        return true;
    }
    class AddAction implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) { // apeleaza functia de adunare
            boolean ok = getPolynoms();
            showResult(ok,firstPoly.add(firstPoly,secondPoly),2);
        }
    }
    class SubstractAction implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) { // apeleaza functia de scadere
            boolean ok = getPolynoms();
            showResult(ok,firstPoly.sub(firstPoly,secondPoly),2);
        }
    }
    class MultiplyAction implements ActionListener{ //// apeleaza functia de inmultire
        @Override
        public void actionPerformed(ActionEvent e) {
            boolean ok = getPolynoms();
            showResult(ok,firstPoly.multiply(firstPoly,secondPoly),2);
        }
    }

    class DivideAction implements ActionListener{ // apeleaza functia de impartire
        @Override
        public void actionPerformed(ActionEvent e) {
            boolean ok = getPolynoms();
            showResult(ok,firstPoly.divide(firstPoly,secondPoly),2);
        }
    }
    class ModuloAction implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) { // apeleaza functia de aflarea restului
            boolean ok = getPolynoms();
            showResult(ok,firstPoly.modulo(firstPoly,secondPoly),2);
        }
    }
    class IntegralAction implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            boolean ok = getPolynoms();
            showResult(ok,firstPoly.integral(firstPoly),1);
        }
    }
    class DerivativeAction implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            boolean ok = getPolynoms();
            showResult(ok,firstPoly.derivative(firstPoly),1);
        }
    }
    class ExitAction implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }
}
