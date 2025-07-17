import java.util.Locale;
import java.util.Scanner;

import javax.swing.SwingUtilities;
import model.entities.Employee;
import view.EmployeeView;
import factory.EmployeeFactory;

public class App {
    public static void main(String[] args) throws Exception {
     
        
        SwingUtilities.invokeLater(new Runnable() {
            
            @Override
            public void run(){

                EmployeeView employeeView = new EmployeeView();

            }
        });
    }
}
