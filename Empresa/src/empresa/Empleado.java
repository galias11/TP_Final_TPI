package empresa;

public class Empleado {
    private int legajo;
    private String ayn;
    private Sector sector;
    
    public Empleado(int legajo, String ayn, Sector sector) {
        this.legajo = legajo;
        this.ayn = ayn;
        this.sector = sector;
    }

    public int getLegajo() {
        return legajo;
    }

    public String getAyn() {
        return ayn;
    }

    public Sector getSector() {
        return sector;
    }
    
    public boolean autorizaOperacion(int codOpe){
        return sector.permiteOperar(codOpe);
    }
}

