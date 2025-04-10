import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Calc implements ActionListener{

    JFrame frame = new JFrame("Calculator");
    JPanel panel = new JPanel();
    JTextField textField;
    JButton[] numberButtons = new JButton[10];
    JButton[] funcButtons = new JButton[9];
    JButton addButton,subButton,mulButton,divButton;
    JButton decButton,eqButton,delButton,clrButton,negButton;

    ImageIcon image = new ImageIcon("calc.png");

    double num1=0,num2=0,result=0;
    char operator;

    Calc(){

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,600);
        frame.setLayout(null);

        textField = new JTextField();
        textField.setBounds(70,50,300,50);

        addButton = new JButton("+");
        subButton = new JButton("-");
        mulButton = new JButton("*");
        divButton = new JButton("/");
        decButton = new JButton(".");
        eqButton = new JButton("=");
        delButton = new JButton("<-");
        clrButton = new JButton("CLear");
        negButton = new JButton("+/-");

        funcButtons[0] = addButton;
        funcButtons[1] = subButton;
        funcButtons[2] = mulButton;
        funcButtons[3] = divButton;
        funcButtons[4] = decButton;
        funcButtons[5] = eqButton;
        funcButtons[6] = delButton;
        funcButtons[7] = clrButton;
        funcButtons[8] = negButton;

        for(int i=0;i<9;i++){
            funcButtons[i].addActionListener(this);
            funcButtons[i].setFocusable(false);
        }

        for(int i=0;i<10;i++){
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(this);
            numberButtons[i].setFocusable(false);
        }

        delButton.setBounds(120,450,100,50);
        clrButton.setBounds(220,450,100,50);
        negButton.setBounds(170,500,100,50);

        panel = new JPanel();
        panel.setBounds(70,110,300,300);
        panel.setBackground(new Color(66, 245, 245));
        panel.setLayout(new GridLayout(4,4,10,5));

        panel.add(numberButtons[1]);
        panel.add(numberButtons[2]);
        panel.add(numberButtons[3]);
        panel.add(addButton);
        panel.add(numberButtons[4]);
        panel.add(numberButtons[5]);
        panel.add(numberButtons[6]);
        panel.add(subButton);
        panel.add(numberButtons[7]);
        panel.add(numberButtons[8]);
        panel.add(numberButtons[9]);
        panel.add(mulButton);
        panel.add(decButton);
        panel.add(numberButtons[0]);
        panel.add(eqButton);
        panel.add(divButton);

        frame.add(delButton);
        frame.add(clrButton);
        frame.add(negButton);
        frame.add(textField);
        frame.add(panel);
        frame.setIconImage(image.getImage());
        frame.setVisible(true);

    }

    public static void main(String[] args) {
        new Calc();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for(int i=0;i<10;i++){
            if(e.getSource()==numberButtons[i]){
                textField.setText(textField.getText().concat(String.valueOf(i)));
            }
        }

        if(e.getSource()==decButton){
            textField.setText(textField.getText().concat("."));
        }

        if(e.getSource()==addButton){
            num1 = Double.parseDouble(textField.getText());
            operator = '+';
            textField.setText("");
        }
        if(e.getSource()==subButton){
            num1 = Double.parseDouble(textField.getText());
            operator = '-';
            textField.setText("");
        }

        if(e.getSource()==mulButton){
            num1 = Double.parseDouble(textField.getText());
            operator = '*';
            textField.setText("");
        }

        if(e.getSource()==divButton){
            num1 = Double.parseDouble(textField.getText());
            operator = '/';
            textField.setText("");
        }

        if(e.getSource()==eqButton){
            num2 = Double.parseDouble(textField.getText());

            switch (operator) {
                case '+' : result = num1 + num2;
                        break;
                
                case '-' : result = num1 - num2;
                        break;

                case '*' : result = num1 * num2;
                        break;
                         
                case '/' : result = num1 / num2;
                        break;
            }
            textField.setText(String.valueOf(result));
            num1 = result;

        }
        
        if(e.getSource()==clrButton){
            textField.setText("");
        }

        if(e.getSource()==delButton){
            String string=textField.getText();
            textField.setText("");
            for(int i=0;i<string.length()-1;i++){
                textField.setText(textField.getText()+string.charAt(i));
            }
         }

         if(e.getSource()==negButton){
            double temp=Double.parseDouble(textField.getText());
            temp*=-1;
            textField.setText(String.valueOf(temp));
         }
                     
    }
    
}
