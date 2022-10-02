import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator implements ActionListener {
    JFrame frame;
    JTextField display;
    JButton[] numberBtns = new JButton[10];
    JButton[] functionBtns = new JButton[10];
    JButton addBtn, subBtn, mulBtn, divBtn, decimalBtn, negateBtn, percentBtn, equateBtn, deleteBtn, clearBtn;
    JPanel panel;

    Font calFont = new Font("Courier", Font.BOLD, 20);
    Font displayFont = new Font("Courier", Font.BOLD, 30);
    double operand1 = 0, operand2 = 0;
    double result = 0;
    char operator;

    Calculator() {
        frame = new JFrame("Simple Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(450, 600);
        frame.setLayout(null);

        // display for showing output
        display = new JTextField();
        display.setBounds(25, 15, 400, 65);
        display.setFont(displayFont);
        display.setHorizontalAlignment(SwingConstants.RIGHT);
        display.setBackground(Color.LIGHT_GRAY);
        display.setEditable(false);

        // function and number buttons
        addBtn = new JButton("+");
        subBtn = new JButton("-");
        mulBtn = new JButton("x");
        divBtn = new JButton("/");
        decimalBtn = new JButton(".");
        equateBtn = new JButton("=");
        negateBtn = new JButton("+/-");
        percentBtn = new JButton("%");
        deleteBtn = new JButton("DEL");
        clearBtn = new JButton("CLEAR");
        functionBtns[0] = addBtn;
        functionBtns[1] = subBtn;
        functionBtns[2] = mulBtn;
        functionBtns[3] = divBtn;
        functionBtns[4] = decimalBtn;
        functionBtns[5] = equateBtn;
        functionBtns[6] = negateBtn;
        functionBtns[7] = percentBtn;
        functionBtns[8] = deleteBtn;
        functionBtns[9] = clearBtn;

        for (int i = 0; i < 10; i++) {
            functionBtns[i].addActionListener(this);
            functionBtns[i].setFont(calFont);
            functionBtns[i].setFocusable(false);

            numberBtns[i] = new JButton(Integer.toString(i));
            numberBtns[i].addActionListener(this);
            numberBtns[i].setFont(calFont);
            numberBtns[i].setFocusable(false);
        }

        panel = new JPanel();
        panel.setBounds(25, 85, 400, 450);
        panel.setLayout(new GridLayout(5, 4, 10, 10));

        for (int i = 6; i < 10; i++) {
            panel.add(functionBtns[i]);
        }

        for (int i = 1; i < 10; i++) {
            panel.add(numberBtns[i]);
            if (i >= 3 && i % 3 == 0) {
                panel.add(functionBtns[i / 3 - 1]);
            }
        }
        panel.add(decimalBtn);
        panel.add(numberBtns[0]);
        panel.add(equateBtn);
        panel.add(divBtn);

        frame.add(display);
        frame.add(panel);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        Calculator c = new Calculator();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // get input number
        for (int i = 0; i < 10; i++) {
            if (e.getSource() == numberBtns[i]) {
                display.setText(display.getText().concat(Integer.toString(i)));
            }
        }
        // input decimal point
        if (e.getSource() == decimalBtn) {
            display.setText(display.getText().concat("."));
        }
        // get operator
        for (int i = 0; i < 4; i++) {
            if (e.getSource() == functionBtns[i]) {
                operand1 = Double.parseDouble(display.getText());
                operator = functionBtns[i].getText().charAt(0);
                display.setText("");
            }
        }

        // equate button function
        if (e.getSource() == equateBtn) {
            operand2 = Double.parseDouble(display.getText());

            switch (operator) {
                case '+':
                    result = operand1 + operand2;
                    break;
                case '-':
                    result = operand1 - operand2;
                    break;
                case 'x':
                    result = operand1 * operand2;
                    break;
                case '/':
                    result = operand1 / operand2;
                    break;
            }
            display.setText(Double.toString(result));
            operand1 = result;
        }

        // negation button function
        if (e.getSource() == negateBtn) {
            double num = Double.parseDouble(display.getText());
            num *= -1;
            display.setText(Double.toString(num));
        }

        // percentage button function
        if (e.getSource() == percentBtn) {
            double num = Double.parseDouble(display.getText());
            num /= 100;
            display.setText(Double.toString(num));
        }

        // clear button function
        if (e.getSource() == clearBtn) {
            display.setText("");
        }

        // delete button function
        if (e.getSource() == deleteBtn) {
            String str = display.getText();
            display.setText(str.substring(0, str.length() - 1));
        }
    }
}