package model.entities;

public class Manager extends Employee {
    

    public Manager() {
        super();
    }

    public Manager(Integer code,String firstName,String lastName, Double salary){
        super(code, firstName, lastName, salary);
    }

    @Override
    public Double calculateSalary(){
        return this.getSalary() + (this.getSalary() * 0.45);
    }

    @Override
    public String toString() {
        return super.toString() + " calculate salary= " + String.format("%.2f",calculateSalary());
    }

    

    
}
