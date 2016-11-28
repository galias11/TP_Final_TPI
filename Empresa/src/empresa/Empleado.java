package empresa;

/**
 * Clase Empleado.
 * Representa a los empleados de la empresa.
 * inv:
 * legajo debe ser un valor entero entre: 1..999999
 * apeYNomb debe ser una cadena no nula ni vacia y de no mas
 * de 100 caracteres.
 * sector no debe ser nulo.
 */
public class Empleado {
    private int legajo;
    private String apeYNomb;
    private Sector sector;
    
    /**
     * Constructor vacio para serializacion.
     * No utilizar.
     */
    public Empleado(){
        
    }
    
    /**
     * Contructor con parametros.
     * PreCondicion:
     * legajo debe ser un valor entero entre: 1..999999
     * apeYNomb debe ser una cadena no nula ni vacia y de no mas
     * de 100 caracteres.
     * sector no debe ser nulo.
     * @param legajo
     * int: numero de legajo
     * @param apeYNomb
     * String: apellido y nombre.
     * @param sector
     * Sector: sector asignado al empleado.
     */
    public Empleado(int legajo, String apeYNomb, Sector sector) {
        assert(legajo > 0 && legajo < 1000000) :
            ("Legajo fuera de rango.");
        assert(apeYNomb != null) : ("apeYNomb nulo");
        assert(!apeYNomb.isEmpty()) : ("apeYNomb vacio");
        assert(apeYNomb.length() <= 100) : ("apeYNomb demasiado largo");
        assert(sector != null) : ("Sector nulo");
        this.legajo = legajo;
        this.apeYNomb = apeYNomb;
        this.sector = sector;
        verificarInvariante();
    }
    
    /*
     * ***********************************************
     * Getters y setters agregados para serializacion.
     * ***********************************************
     */


    public void setLegajo(int legajo) {
        this.legajo = legajo;
    }

    public void setAyn(String ayn) {
        this.apeYNomb = ayn;
    }

    public void setSector(Sector sector) {
        this.sector = sector;
    }


    /*
     * ***********************************************
     */

    public int getLegajo() {
        return legajo;
    }

    public String getAyn() {
        return apeYNomb;
    }

    public Sector getSector() {
        return sector;
    }
    
    /**
     * metodo autorizaOperacion
     * Metodo que retorna si el empleado se encuentra habilitado
     * para realizar una determinada operacion.
     * @param codOpe
     * int: codigo de la operacion a consultar.
     * @return
     * boolena: true si esta autorizado, false en caso contrario.
     */
    public boolean autorizaOperacion(int codOpe){
        return sector.permiteOperar(codOpe);
    }
    
    private void verificarInvariante(){
        assert(legajo > 0 && legajo < 1000000) :
            ("Legajo fuera de rango.");
        assert(apeYNomb != null) : ("apeYNomb nulo");
        assert(!apeYNomb.isEmpty()) : ("apeYNomb vacio");
        assert(apeYNomb.length() <= 100) : ("apeYNomb demasiado largo");
        assert(sector != null) : ("Sector nulo");
        
    }

  /*
   * ***********************************************
   * Equals utilizado para el test de persistencia.
   * ***********************************************
   */

  @Override
  public boolean equals(Object object)
  {
    if (this == object)
    {
      return true;
    }
    if (!(object instanceof Empleado))
    {
      return false;
    }
    final Empleado other = (Empleado) object;
    if (legajo != other.legajo)
    {
      return false;
    }
    return true;
  }
}

