package model.entities;

public class TelephoneOperator extends Employee {
    
    public TelephoneOperator() {
        super();
    }


    public TelephoneOperator(Integer code, String firstName, String lastName, Double salary){
        super(code, firstName, lastName, salary);
    }

    @Override
    public Double calculateSalary(){
        return this.getSalary() + (this.getSalary() * 0.1);
    }


    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return super.toString() + " calculate salary = " + String.format("%.2f", calculateSalary());
    }

    
}
