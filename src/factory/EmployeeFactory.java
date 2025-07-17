package factory;
import model.entities.Employee;
import model.entities.Manager;
import model.entities.Saler;
import model.entities.TelephoneOperator;

/**
 * Essa classe será responsável por ser uma fabrica de funcionarios, ou seja, ela será responsável por instânciar os funcionários
 */
public class EmployeeFactory {


    public static Employee getEmployee(Integer code, String firstName,String lastName,Double salary,String typeEmployee){

        if(typeEmployee.toLowerCase().equals("employee")){
            return new Employee(code, firstName, lastName, salary);
        }
        else if(typeEmployee.toLowerCase().equals("manager")){
            return new Manager(code, firstName, lastName, salary);
        }

        else if(typeEmployee.toLowerCase().equals("telephone operator")){
            return new TelephoneOperator(code, firstName, lastName, salary);
        }
        else{
            return new Saler(code, firstName, lastName, salary);
        }

    }
    
}
