import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TempConverter extends JFrame {
    JTextField tempInput, tempOutput;
    JComboBox<String> comboBoxFrom, comboBoxTo;
    JLabel inputLabel, outputLabel, typeLabel, convertLabel;
    JButton convertButton, clearButton;

    public TempConverter() {
        setTitle("Temperature Converter");
        setSize(400, 500);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setBackground(Color.WHITE);

        JPanel headerPanel = new JPanel(null);
        headerPanel.setBounds(0, 0, 400, 80);
        headerPanel.setBackground(Color.CYAN);
        JLabel titleLabel = new JLabel("TEMPERATURE CONVERTER", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Algerian", Font.BOLD, 20));
        titleLabel.setBounds(20, 10, 360, 60);
        headerPanel.add(titleLabel);
        add(headerPanel);

        inputLabel = new JLabel("Degrees");
        inputLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
        inputLabel.setBounds(30, 100, 100, 30);
        add(inputLabel);

        tempInput = new JTextField();
        tempInput.setFont(new Font("Book Antiqua", Font.BOLD, 15));
        tempInput.setBounds(30, 135, 130, 30);
        add(tempInput);

        typeLabel = new JLabel("From");
        typeLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
        typeLabel.setBounds(200, 100, 100, 30);
        add(typeLabel);

        comboBoxFrom = new JComboBox<>(new String[]{"Celsius", "Fahrenheit", "Kelvin"});
        comboBoxFrom.setFont(new Font("Book Antiqua", Font.BOLD, 15));
        comboBoxFrom.setBounds(200, 135, 150, 30);
        add(comboBoxFrom);

        convertLabel = new JLabel("To");
        convertLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
        convertLabel.setBounds(30, 180, 100, 30);
        add(convertLabel);

        comboBoxTo = new JComboBox<>(new String[]{"Celsius", "Fahrenheit", "Kelvin"});
        comboBoxTo.setFont(new Font("Book Antiqua", Font.BOLD, 15));
        comboBoxTo.setBounds(30, 215, 150, 30);
        add(comboBoxTo);

        outputLabel = new JLabel("Result");
        outputLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
        outputLabel.setBounds(200, 180, 100, 30);
        add(outputLabel);

        tempOutput = new JTextField();
        tempOutput.setFont(new Font("Book Antiqua", Font.BOLD, 15));
        tempOutput.setBounds(200, 215, 130, 30);
        tempOutput.setEditable(false);
        tempOutput.setBackground(Color.LIGHT_GRAY);
        add(tempOutput);

        convertButton = new JButton("Convert");
        convertButton.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
        convertButton.setBounds(30, 280, 140, 40);
        add(convertButton);

        clearButton = new JButton("Clear");
        clearButton.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
        clearButton.setBounds(190, 280, 140, 40);
        add(clearButton);

        convertButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                convertTemperature();
            }
        });

        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tempInput.setText("");
                tempOutput.setText("");
                comboBoxFrom.setSelectedIndex(0);
                comboBoxTo.setSelectedIndex(0);
            }
        });

        setVisible(true);
    }

    private void convertTemperature() {
        try {
            double inputTemp = Double.parseDouble(tempInput.getText());
            String from = (String) comboBoxFrom.getSelectedItem();
            String to = (String) comboBoxTo.getSelectedItem();
            double result = 0;

            if (from.equals(to)) {
                result = inputTemp;
            } else if (from.equals("Celsius")) {
                if (to.equals("Fahrenheit")) result = inputTemp * 9 / 5 + 32;
                else if (to.equals("Kelvin")) result = inputTemp + 273.15;
            } else if (from.equals("Fahrenheit")) {
                if (to.equals("Celsius")) result = (inputTemp - 32) * 5 / 9;
                else if (to.equals("Kelvin")) result = (inputTemp - 32) * 5 / 9 + 273.15;
            } else if (from.equals("Kelvin")) {
                if (to.equals("Celsius")) result = inputTemp - 273.15;
                else if (to.equals("Fahrenheit")) result = (inputTemp - 273.15) * 9 / 5 + 32;
            }

            tempOutput.setText(String.format("%.2f", result));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number!", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new TempConverter();
    }
}
