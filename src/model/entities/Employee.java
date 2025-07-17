package model.entities;

public class Employee extends Person {
    
    protected Double salary;
    
    public Employee(){
        super();
    }

    public Employee(Integer code, String firstName, String lastName, Double salary){

        // Inicializa os atributos herdados da classe Person, usando o construtor da classe Person.
        super(code,firstName,lastName);
        this.salary = salary;
    }


    public Double getSalary() {return this.salary;}

    public Double calculateSalary(){
        return this.salary;
    }

    @Override
    public String toString() {
        return "Employee [getSalary()=" + getSalary() + ", getCode()=" + getCode() + ", getFirstName()="
                + getFirstName() + ", getLastName()=" + getLastName() + "]";
    }

    


}
