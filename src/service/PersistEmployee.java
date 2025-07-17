package service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class PersistEmployee implements SerializationService {


    FileOutputStream filePointerOutput;
    ObjectOutputStream output;

    FileInputStream filePointerInput;
    ObjectInputStream input;


    @Override
    /**
     * Metodo responsável por serializar objeto, ou seja, escrevero o objeto na memória secundaria.
     * @param path
     * @param object
     */
    public void save(String path,Object object) {


        try{
            // Referencia do arquivo que iremos gravar os objetos
            filePointerOutput = new FileOutputStream(path);
            // Passando o endereço para a classe que irá escrever os dados na memória secundaria
            output = new ObjectOutputStream(filePointerOutput);
            // Gravando os objetos na memória secundaria
            output.writeObject(object);
        }catch(IOException exception){
            System.err.println(exception.getMessage());
            exception.printStackTrace();
        }finally{
            if(output != null){
                try{
                    output.close();
                }catch(IOException exception2){
                    System.err.println(exception2.getMessage());
                    exception2.printStackTrace();
                }
            }
            if(filePointerOutput != null){
                try{
                    filePointerOutput.close();
                }catch(IOException exception3){
                    System.err.println(exception3.getMessage());
                    exception3.printStackTrace();
                }
            }
        }
    }

    @Override
    /**
     * Metodo responsável por pegar a sequência de bytes ler é trazer como objeto novamente.
     * @parm path
     */
    public Object read(String path) {
        try{
            filePointerInput = new FileInputStream(path);
            input = new ObjectInputStream(filePointerInput);
            Object object = input.readObject();
            return object;
        }catch(IOException exception){
            System.err.println(exception.getMessage());
            exception.printStackTrace();
        }
        catch(ClassNotFoundException classNotFoundException){
            System.err.println(classNotFoundException.getMessage());
            classNotFoundException.printStackTrace();
        }
        finally{

            if(filePointerInput != null){
                try{
                    filePointerInput.close();
                }catch(IOException exception2){
                    System.err.println(exception2.getMessage());
                    exception2.printStackTrace();
                }
            }


            if(input != null){
                try{
                    input.close();
                }catch(IOException exception3){
                    System.err.println(exception3.getMessage());
                    exception3.printStackTrace();
                }
            }
        }

        return null;
    }
    
}
