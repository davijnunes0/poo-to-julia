package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
// Importe o JCheckBox
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.EmployeeController;
import model.entities.Employee;
import java.util.List;
import java.util.ArrayList;

public class EmployeeView {

    private EmployeeController employeeController;

    // Componentes
    private JFrame mainFrame;
    private JPanel mainPanel,buttonPanel;
    private JLabel codeLabel, firstNameLabel, lastNameLabel, salaryLabel,typeEmployeeLabel;
    private JTextField codeField, firstNameField, lastNameField, salaryField,typeEmployeeField;;
    private JButton registerButton,deleteButton,clearButton,selectButton;

    Font font = new Font("Verdana", Font.PLAIN, 15);

    public EmployeeView(EmployeeController employeeController) {
        mainFrame = new JFrame();
        mainPanel = new JPanel();
        buttonPanel = new JPanel();
        this.employeeController = employeeController;
        initialization();
    }

    public void initialization() {
        setFrame();
        setMainPanel();
        setButtonPanel();

    }

    private void setFrame() {
        mainFrame.setTitle("Signup employee");
        mainFrame.setResizable(false);
        mainFrame.setSize(550,450);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        mainFrame.add(mainPanel, BorderLayout.CENTER);
        mainFrame.setVisible(true);
     
    }

    private void setMainPanel() {
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        codeLabel = createLabel("Code:");
        codeField = createJTextField(20, font);

        firstNameLabel = createLabel("First name:");
        firstNameField = createJTextField(20, font);

        lastNameLabel = createLabel("Last name:");
        lastNameField= createJTextField(20, font);

        salaryLabel = createLabel("Salary:");
        salaryField = createJTextField(20, font);
        typeEmployeeLabel = createLabel("Type employee:");
        typeEmployeeField = createJTextField(20, font);

        // Linhas de texto
        addFieldWithLabel(codeLabel, codeField, gbc, 0);
        addFieldWithLabel(firstNameLabel,firstNameField,gbc, 1);
        addFieldWithLabel(lastNameLabel,lastNameField, gbc, 2);
        addFieldWithLabel(salaryLabel,salaryField, gbc, 3);
        addFieldWithLabel(typeEmployeeLabel, typeEmployeeField, gbc, 4);

    
    }

    private void setButtonPanel(){
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER,5,5));
        setButton();
        mainFrame.add(buttonPanel,BorderLayout.SOUTH);
    }

    private void setButton(){
        registerButton = createButton("Register", "Botão para registrar usuários", font);
        deleteButton = createButton("Delete", "Botão para deletar usuário", font);
        selectButton = createButton("Select", "Botão para mostrar os funcionários", font);
        clearButton = createButton("Clear", "Botão para limpar os campos!", font);

        addButtonEvent(registerButton);
        addButtonEvent(deleteButton);
        addButtonEvent(selectButton);
        addButtonEvent(clearButton);

        buttonPanel.add(registerButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(selectButton);
        buttonPanel.add(clearButton);

    }

    private JLabel createLabel(String textLabel) {
        JLabel label = new JLabel(textLabel);
        label.setFont(new Font("Arial", Font.PLAIN, 14));
        return label;
    }

    private JTextField createJTextField(int columns, Font font) {
        JTextField textField = new JTextField(columns);
        textField.setFont(font);
        textField.setMaximumSize(new Dimension(250, 30));
        return textField;
    }

    private JButton createButton(String buttonText, String buttonMessage, Font font){
        JButton button = new JButton(buttonText);
        button.setFocusable(false);
        button.setToolTipText(buttonMessage);
        button.setFont(font);
        button.setMargin(new Insets(5,5,5,5));
        return button;
    }

    
        private void addButtonEvent(JButton button){

            button.addActionListener(new ActionListener() {
                
                @Override
                public void actionPerformed(ActionEvent e){

                    if(button.equals(registerButton)){
                        registerEmployee();
                    }
                    if(button.equals(deleteButton)){
                        deleteEmployee();
                    }

                    if(button.equals(selectButton)){
                        selectEmployee();
                    }

                    if(button.equals(clearButton)){
                        codeField.setText("");
                        firstNameField.setText("");
                        lastNameField.setText("");
                        salaryField.setText("");
                        typeEmployeeField.setText("");
                    }

                }
            });
        }

    private void addFieldWithLabel(JLabel label, JTextField textField, GridBagConstraints gbc, int row) {
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.weightx = 0.0;
        mainPanel.add(label, gbc);

        gbc.gridx = 1;
        gbc.gridy = row;
        gbc.weightx = 1.0;
        mainPanel.add(textField, gbc);
    }


    public void registerEmployee(){

       Boolean response = employeeController.registerEmployee(
            codeField.getText(),
            firstNameField.getText(),
            lastNameField.getText() ,
            salaryField.getText(),
            typeEmployeeField.getText());

        if(response){
            JOptionPane.showMessageDialog(mainFrame,"Employee cadastrado com sucesso","Sucesso",JOptionPane.INFORMATION_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(mainFrame,"Ocorreu um erro crítico ao salvar os dados.","Erro de arquivo",JOptionPane.ERROR_MESSAGE);
        }
    }


    public void selectEmployee(){
        StringBuilder sb =  new StringBuilder();

        List<Employee> employees = employeeController.selectEmployees();
        for(Employee employee : employees){
            sb.append(employee.toString() + "\n");
        }

        JOptionPane.showMessageDialog(mainFrame, sb.toString(), "Employees cadastrados", JOptionPane.INFORMATION_MESSAGE);
    }

    public void deleteEmployee(){

        Boolean response = employeeController.deleteEmployee(Integer.parseInt(codeField.getText()));

        if(response){
            JOptionPane.showMessageDialog(mainFrame,"Employee com id: " + codeField.getText() + " foi deletado com sucesso.", 
            "Employee removido",JOptionPane.INFORMATION_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(mainFrame, "Houve uma falha ao remover o employee", "Employee nãoa removido",JOptionPane.ERROR_MESSAGE);
        }
    }


}