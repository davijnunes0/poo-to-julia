
import javax.swing.SwingUtilities;

import controller.EmployeeController;
import service.PersistEmployee;
import service.SerializationService;
import view.EmployeeView;
public class App {
    public static void main(String[] args) throws Exception {
     
        
        SwingUtilities.invokeLater(new Runnable() {
            
            @Override
            public void run(){
                SerializationService serializationService = new PersistEmployee();
                EmployeeController employeeController = new EmployeeController(serializationService);
                EmployeeView employeeView = new EmployeeView(employeeController);

            }
        });
    }
}
