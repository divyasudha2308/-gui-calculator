import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MinimalistCalculator extends JFrame implements ActionListener {
    private JTextField display;
    private String s0, s1, s2;

    public MinimalistCalculator() {
        s0 = s1 = s2 = "";
        setLayout(new BorderLayout());

        display = new JTextField("0");
        display.setEditable(false);
        display.setBackground(Color.BLACK);
        display.setForeground(Color.WHITE);
        display.setFont(new Font("Roboto", Font.PLAIN, 40));
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(display, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(5, 4, 5, 5));
        buttonPanel.setBackground(Color.BLACK);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        String[] buttonLabels = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+",
                "C"
        };

        for (String label : buttonLabels) {
            JButton button = createButton(label);
            buttonPanel.add(button);
        }

        add(buttonPanel, BorderLayout.CENTER);

        setTitle("Minimalist Calculator");
        setSize(350, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.BLACK);
        setVisible(true);
    }

    private JButton createButton(String label) {
        JButton button = new JButton(label);
        button.addActionListener(this);
        button.setFont(new Font("Roboto", Font.PLAIN, 24));
        button.setFocusPainted(false);
        button.setBorderPainted(false);

        if (label.matches("[0-9.]")) {
            button.setBackground(new Color(30, 30, 30));
            button.setForeground(Color.WHITE);
        } else if (label.equals("=")) {
            button.setBackground(new Color(0, 120, 215));
            button.setForeground(Color.WHITE);
        } else {
            button.setBackground(new Color(50, 50, 50));
            button.setForeground(Color.WHITE);
        }

        return button;
    }

    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();

        if (s.charAt(0) >= '0' && s.charAt(0) <= '9' || s.charAt(0) == '.') {
            if (!s1.equals(""))
                s2 = s2 + s;
            else
                s0 = s0 + s;
            display.setText(s0 + s1 + s2);
        } else if (s.charAt(0) == 'C') {
            s0 = s1 = s2 = "";
            display.setText("0");
        } else if (s.charAt(0) == '=') {
            double result;
            if (s1.equals("+"))
                result = Double.parseDouble(s0) + Double.parseDouble(s2);
            else if (s1.equals("-"))
                result = Double.parseDouble(s0) - Double.parseDouble(s2);
            else if (s1.equals("/"))
                result = Double.parseDouble(s0) / Double.parseDouble(s2);
            else
                result = Double.parseDouble(s0) * Double.parseDouble(s2);

            display.setText(String.valueOf(result));
            s0 = String.valueOf(result);
            s1 = s2 = "";
        } else {
            if (s1.equals("") || s2.equals(""))
                s1 = s;
            else {
                double result;
                if (s1.equals("+"))
                    result = Double.parseDouble(s0) + Double.parseDouble(s2);
                else if (s1.equals("-"))
                    result = Double.parseDouble(s0) - Double.parseDouble(s2);
                else if (s1.equals("/"))
                    result = Double.parseDouble(s0) / Double.parseDouble(s2);
                else
                    result = Double.parseDouble(s0) * Double.parseDouble(s2);

                s0 = String.valueOf(result);
                s1 = s;
                s2 = "";
            }
            display.setText(s0 + s1 + s2);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MinimalistCalculator());
    }
}
