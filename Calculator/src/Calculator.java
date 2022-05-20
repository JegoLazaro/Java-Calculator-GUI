import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Calculator implements ActionListener{

    JFrame frame;
    JTextField textField;
    JButton[] numberButtons = new JButton[10];
    ArrayList<JButton> functionButtons = new ArrayList<JButton>();
    JButton addBtn, subBtn, multBtn, divBtn, deciBtn, equalBtn, delBtn, clrBtn, modBtn, ngtBtn;
    JPanel panel;

    Font calcFont = new Font("DialogInput",Font.BOLD, 22);

    double num1 = 0, num2 = 0, result = 0;
    char operator;

    Calculator(){
        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420,550);
        frame.setLayout(null);

        textField = new JTextField();
        textField.setBounds(50,25,300,50);
        textField.setFont(calcFont);
        textField.setEditable(false);

        addBtn = new JButton("+");
        subBtn = new JButton("-");
        multBtn = new JButton("x");
        divBtn = new JButton("/");
        deciBtn = new JButton(".");
        equalBtn = new JButton("=");
        delBtn = new JButton("del");
        clrBtn = new JButton("clr");
        modBtn = new JButton("%");
        ngtBtn = new JButton("(-)");

        panel = new JPanel();
        panel.setBounds(50,100,300,300);
        panel.setLayout(new GridLayout(4,4,10,10));

        functionButtons.add(addBtn);//0
        functionButtons.add(subBtn);//1
        functionButtons.add(multBtn);//2
        functionButtons.add(divBtn);//3
        functionButtons.add(deciBtn);//4
        functionButtons.add(equalBtn);//5
        functionButtons.add(delBtn);
        functionButtons.add(clrBtn);
        functionButtons.add(modBtn);
        functionButtons.add(ngtBtn);

        for(int i = 0; i < functionButtons.size(); i++){
            functionButtons.get(i).addActionListener(this);
            functionButtons.get(i).setFont(calcFont);
            functionButtons.get(i).setFocusable(false);
        }

        for(int i = 0; i < numberButtons.length; i++){
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(this);
            numberButtons[i].setFont(calcFont);
            numberButtons[i].setFocusable(false);
        }

        modBtn.setBounds(50,430,75,50);
        delBtn.setBounds(125,430,75,50);
        clrBtn.setBounds(200,430,75,50);
        ngtBtn.setBounds(275,430,75,50);


        panel.add(numberButtons[7]);
        panel.add(numberButtons[8]);
        panel.add(numberButtons[9]);
        panel.add(functionButtons.get(0));

        panel.add(numberButtons[4]);
        panel.add(numberButtons[5]);
        panel.add(numberButtons[6]);
        panel.add(functionButtons.get(1));

        panel.add(numberButtons[1]);
        panel.add(numberButtons[2]);
        panel.add(numberButtons[3]);
        panel.add(functionButtons.get(2));

        panel.add(functionButtons.get(4));
        panel.add(numberButtons[0]);
        panel.add(functionButtons.get(3));
        panel.add(functionButtons.get(5));

        frame.add(panel);
        frame.add(modBtn);
        frame.add(delBtn);
        frame.add(clrBtn);
        frame.add(ngtBtn);
        frame.add(textField);

        frame.setVisible(true);
    }
    public static void main(String[] args){
        Calculator calculator = new Calculator();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for(int i = 0; i < numberButtons.length ; i++){
            if(e.getSource() == numberButtons[i])
                textField.setText(textField.getText().concat(String.valueOf(i)));
        }
        for(int i = 0; i < functionButtons.size(); i++){
            if(e.getSource() == functionButtons.get(i))
                functionButtons.get(i).setBackground(Color.GRAY);
        }

        if(e.getSource() == deciBtn)
            textField.setText(textField.getText().concat("."));

        if(e.getSource() == addBtn){
            num1 = Double.parseDouble(textField.getText());
            operator = '+';
            textField.setText("");
        }

        if(e.getSource() == subBtn){
            num1 = Double.parseDouble(textField.getText());
            operator = '-';
            textField.setText("");
        }

        if(e.getSource() == multBtn){
            num1 = Double.parseDouble(textField.getText());
            operator = 'x';
            textField.setText("");
        }

        if(e.getSource() == divBtn){
            num1 = Double.parseDouble(textField.getText());
            operator = '/';
            textField.setText("");
        }
        if(e.getSource() == modBtn){
            num1 = Double.parseDouble(textField.getText());
            operator = '%';
            textField.setText("");
        }

        if(e.getSource() == ngtBtn){
            Double temp = Double.parseDouble(textField.getText());
            temp *= -1;
            textField.setText(String.valueOf(temp));
        }

        if(e.getSource() == clrBtn){
            textField.setText("");
            for(int i = 0; i < 9; i++){
                functionButtons.get(i).setBackground(null);
            }
        }

        if(e.getSource() == delBtn){
            String str = textField.getText();
            textField.setText(" ");
            for(int i = 0; i < str.length() - 1; i++) {
                String str_new = str.substring(0, str.length() - 1);
                textField.setText(str_new);
            }
        }

        if(e.getSource() == equalBtn){
            num2 = Double.parseDouble(textField.getText());

            switch(operator){
                case '+':
                    result = num1 + num2; break;
                case '-':
                    result = num1 - num2; break;
                case 'x':
                    result = num1 * num2; break;
                case '/':
                    result = num1 / num2; break;
                case '%':
                    result = num1 % num2; break;
            }
            for(int i = 0; i < 9; i++){
                functionButtons.get(i).setBackground(null);
            }

            if((result % 1) == 0){
                textField.setText(String.valueOf((int)result));
            }
            else {
                textField.setText(String.valueOf(result));
            }
            num1 = result;
        }
    }
}
