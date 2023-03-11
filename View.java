import javax.swing.*;
import java.awt.*;

public class View extends JFrame {
    private final JPanel pTitle = new JPanel();
    private final JPanel pPolynoms = new JPanel();
    private final JPanel pOperations = new JPanel();
    private final JPanel pButtons = new JPanel();


    private final JLabel title = new JLabel("Polynomial Calculator");
    private final JLabel firstText = new JLabel("First Polynomial =");
    private final JLabel secondText = new JLabel("Second Polynomial =");

    protected JTextField firstField = new JTextField(16);
    protected JTextField secondField = new JTextField(16);

    protected JButton multiplication = new JButton("Multiplication");
    protected JButton substract = new JButton("Substract");
    protected JButton divide = new JButton("Divide");
    protected JButton add = new JButton("Add");
    protected JButton modulo = new JButton("Modulo");
    protected JButton integral = new JButton("Integral");
    protected JButton derivative = new JButton("Derivative");
    protected JButton exit = new JButton("Exit");

    protected JButton b1 = new JButton("1");
    protected JButton b2 = new JButton("2");
    protected JButton b3 = new JButton("3");
    protected JButton b4 = new JButton("4");
    protected JButton b5 = new JButton("5");
    protected JButton b6 = new JButton("6");
    protected JButton b7 = new JButton("7");
    protected JButton b8 = new JButton("8");
    protected JButton b9 = new JButton("9");
    protected JButton b0 = new JButton("0");

    protected JButton bAdd = new JButton("+");
    protected JButton bSubstract = new JButton("-");
    protected JButton bDivide = new JButton("/");
    protected JButton bMultiplication = new JButton("*");
    protected JButton bPower = new JButton("^");
    protected JButton bPoint = new JButton(".");
    protected JButton bX = new JButton("x");
    protected JButton bDel = new JButton("del");

    protected View(){
        this.setLayout(new GridLayout(4,1));
        this.setTitle("Polynom Calculator");
        this.setSize(400,450);

        pTitle.add(title);
        pPolynoms.setLayout(new GridBagLayout());
        pButtons.setLayout(new GridBagLayout());
        pOperations.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridy = 1;
        gbc.gridx = 1;
        pPolynoms.add(firstText,gbc);
        pOperations.add(multiplication,gbc);
        pButtons.add(b1,gbc);
        gbc.gridx = 4;
        pButtons.add(b0,gbc);
        gbc.gridx = 5;
        pButtons.add(bAdd,gbc);
        gbc.gridx = 6;
        pButtons.add(bSubstract,gbc);
        gbc.gridx = 2;
        pPolynoms.add(firstField,gbc);
        pOperations.add(substract,gbc);
        pButtons.add(b2,gbc);
        gbc.gridx = 3;
        pButtons.add(b3,gbc);
        gbc.gridy = 2;
        gbc.gridx = 4;
        pButtons.add(bDivide,gbc);
        gbc.gridx = 5;
        pButtons.add(bMultiplication,gbc);
        gbc.gridx = 6;
        pButtons.add(bPower,gbc);
        gbc.gridx = 1;
        pPolynoms.add(secondText,gbc);
        pOperations.add(divide,gbc);
        pButtons.add(b4,gbc);
        gbc.gridx = 2;
        pPolynoms.add(secondField,gbc);
        pOperations.add(modulo,gbc);
        pButtons.add(b5,gbc);
        gbc.gridx = 3;
        pButtons.add(b6,gbc);
        gbc.gridy = 3;
        gbc.gridx = 4;
        pButtons.add(bPoint,gbc);
        gbc.gridx = 5;
        pButtons.add(bX,gbc);
        gbc.gridx = 6;
        pButtons.add(bDel,gbc);
        gbc.gridx = 1;
        pOperations.add(add,gbc);
        pButtons.add(b7,gbc);
        gbc.gridx = 2;
        pOperations.add(derivative,gbc);
        pButtons.add(b8,gbc);
        gbc.gridx = 3;
        pButtons.add(b9,gbc);
        gbc.gridy = 4;
        gbc.gridx = 1;
        pOperations.add(integral,gbc);
        gbc.gridx = 2;
        pOperations.add(exit,gbc);
        gbc.gridy = 1;
        gbc.gridx = 1;
       this.add(title,gbc);
       gbc.gridy = 2;
        this.add(pPolynoms,gbc);
        gbc.gridy = 3;
        this.add(pOperations,gbc);
        gbc.gridy = 4;
        this.add(pButtons);
        this.setVisible(true);
    }
    protected void setTextField(String text,int fieldNr){
        if( fieldNr == 1)
            firstField.setText(text);
        else
            secondField.setText(text);
    }

    protected String getTextField(int fieldNr){
        if( fieldNr == 1)
            return firstField.getText();
        return secondField.getText();
    }


}
