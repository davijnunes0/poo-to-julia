import java.util.Locale;
import java.util.Scanner;
import model.entities.Employee;

import factory.EmployeeFactory;

public class App {
    public static void main(String[] args) throws Exception {
        
        Locale.setDefault(Locale.US);
        // System.in, porque iremos inserir dado pelo teclado.
        Scanner sc = new Scanner(System.in);

        try{
            Integer code = sc.nextInt();
            sc.nextLine();
            String firstName = sc.nextLine();
            String lastName = sc.nextLine();
            Double salary = sc.nextDouble();
            sc.nextLine();
            String typeEmployee = sc.nextLine();

            Employee employee = EmployeeFactory.getEmployee(code, firstName, lastName, salary, typeEmployee);

            System.out.println(employee.toString() + " class= " + employee.getClass());
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        finally{
            if(sc != null){
                sc.close();
            }
        }
    }
}
