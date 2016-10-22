package Controladora;

public class InterfazException extends Exception{
    private String desc;
    
    public InterfazException(String desc) {
        super(desc);
        this.desc = desc;
    }
    
    @Override
    public String toString(){
        return desc;
    }
}
