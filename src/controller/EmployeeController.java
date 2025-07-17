package controller;
import service.SerializationService;
import model.entities.Employee;
import java.util.List;
import factory.EmployeeFactory;
import java.util.ArrayList;
import java.util.Collections;

public class EmployeeController {

    private List<Employee> employees;
    private SerializationService serializationService;
    private final String aplicationFile = "employees.ser";

    /**
     * Usando injeção de dependência para reduzir o acomplamento entre classes.
     * @param serializationService
     */
    public EmployeeController(SerializationService serializationService){
        this.serializationService = serializationService;
        loadEmployees();

    }

    /**
     * Tenta carregar a lista de funcionário do arquivo. Se não conseguir, inicializa uma lista vazia.
     */
    private void loadEmployees(){
        try{
            this.employees = (List<Employee>) serializationService.read(aplicationFile);

            if(this.employees == null){
                this.employees = new ArrayList<Employee>();
            }
        }catch(Exception e){
            System.err.println("Nâo foi possivel carregar  o arquivo de funcionário.  Inicializando com uma lista vazia");
            this.employees = new ArrayList<>();
        }
    }

    /**
     * Registra um novo funcionário e retorna true em caso de sucesso
     * @param code
     * @param firstName
     * @param lastName
     * @param salary
     * @param typeEmployee
     * @return boolean indicando se a operação foi bem-sucedida.
     */
    public boolean registerEmployee(String code, String firstName, String lastName,String salary,String typeEmployee){
        try{
                Employee employee = parseFields(code, firstName, lastName, salary, typeEmployee);

                if(employee == null){
                    return false;
                }

                employees.add(employee);
                serializationService.save(aplicationFile, employees);
                return true;
                
        }catch(Exception e){
            System.err.println("ERROR CRÍTICO: Falha ao salvar o funcionário no arquivo.");
            e.printStackTrace();
            return false;
        }

    }


    /**
     * Retrona uma visão não modificável da lista de funcionários.
     * Isso impede que outras partes do código alterem a lista diretamente.
     */
    public List<Employee> getEmployees(){
        return Collections.unmodifiableList(employees);
    }

    private Employee parseFields(String code, String firstName,String lastName, String salary,String typeEmployee){
        
        if(code.isBlank() || firstName.isBlank() || lastName.isBlank() || salary.isBlank() || typeEmployee.isBlank()){
            System.err.println("Erro: Todos os campos devem ser preenchidos.");
            return null;
        }
        try{
            Integer integerCode = Integer.parseInt(code.trim());
            Double doubleSalary = Double.parseDouble(salary.trim());

            if(doubleSalary < 0){
                System.err.println("Erro: O salário não pode ser negativo.");
                return null;
            }

            return buildEmployee(integerCode, firstName, lastName, doubleSalary, typeEmployee);
        }catch(NumberFormatException NumberFormatException){
            System.err.println(NumberFormatException.getMessage());
            NumberFormatException.printStackTrace();
            return null;
        }

    }
    
    private Employee buildEmployee(Integer code, String firstName,String lastName,Double salary,String typeEmployee){
        return EmployeeFactory.getEmployee(code, firstName, lastName,salary, typeEmployee);

    }


    public List<Employee> selectEmployees(){
        return (List<Employee>) serializationService.read(aplicationFile);
    }


    public boolean deleteEmployee(Integer code){

        for(Employee employee : employees){
            if(employee.getCode().equals(code)){
                // Remove o usuário!
                employees.remove(employee);
                // Grava a lista com o usuário removido!
                serializationService.save(aplicationFile, employees);
                return true;
            }
        }

        return false;
    }

}
