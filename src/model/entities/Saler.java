package model.entities;

public class Saler extends Employee {
    

    public Saler(){
        super();
    }

    public Saler(Integer code, String firstName, String lastName, Double salary){
        super(code, firstName, lastName, salary);
    }

    @Override
    public Double calculateSalary(){
        return this.getSalary() + (this.getSalary() * 0.25);
        
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return super.toString() + " calculate salary= " + String.format("%.2f", calculateSalary());
    }

    
}
