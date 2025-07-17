package model.entities;

/**
 * 
 *  Essa classe será abstrata, porque ela é generica demais para ser uma classe concreta da aplicação.
 *  Uma classe não concreta,ou seja, abstrata, a Java Virtual Machine não permite que ela seja instânciada somente herdada.
 */
public abstract class Person {

    // Os atributos serão protected porque queremos que sejam visiveis somente para subclasses.
    protected Integer code;
    protected String firstName;
    protected String lastName;


    // Construtor sem argumentos para framerworks
    public Person(){}

    public Person(Integer code, String firstName,String lastName){
        this.setCode(code);
        this.setFirstName(firstName);
        this.setLastName(lastName);

    }


    public void setCode(Integer code){

        if(code == null){
            throw new IllegalArgumentException("The code cannot be null");
        }

        if(code < 0){
            throw new IllegalStateException("The code must be greater than zero");
        }

        this.code = code;
    }

    /**
     * 
     * @return integer code
     */
    public Integer getCode() {return this.code;}


    public void setFirstName(String firstName){

        if(firstName == null){
            throw new IllegalArgumentException("The first name cannot be null");
        }

        this.firstName = firstName;
    }


    public String getFirstName() {return this.firstName;}


    public void setLastName(String lastName){
        
        if(lastName == null){
            throw new IllegalArgumentException("The last name cannot be null");
        }

        this.lastName = lastName;
    }


    public String getLastName() {return this.lastName;}

    @Override
    public String toString() {
        return "Person [getCode()=" + getCode() + ", getFirstName()=" + getFirstName() + ", getLastName()="
                + getLastName() + "]";
    }

    

    
}
