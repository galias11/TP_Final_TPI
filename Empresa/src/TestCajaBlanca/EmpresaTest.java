package TestCajaBlanca;

import empresa.EmpresaException;
import empresa.Operacion;

import org.junit.After;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;

public class EmpresaTest
{
  private FixtureEmpresa fixture = new FixtureEmpresa();

  public EmpresaTest()
  {
    super();
  }

  @Before
  public void setUp()
  {
    fixture.setUp();
  }

  @After
  public void tearDown()
  {
    fixture.tearDown();
  }
  
    @Test
    public void testEMP01A(){
      
    }

    
    @Test
    public void testEMP01B(){
        
    }
    
    @Test
    public void testEMP01C(){
        
    }
    
    @Test
    public void testEMP01D(){
        
    }
    
    @Test
    public void testEMP01E(){
        
    }
    
    @Test
    public void testEMP01F(){
        
    }
  
  
  
  
  
  
  
  
  
    @Test
    public void testEMP03A(){
      fixture.setUp03A();
        boolean assertError=false;
        try {
            fixture.getEmpresaTest().reservarMateriales(10);
        }
        catch(AssertionError e){
            assertError=true;
        } catch (EmpresaException e) {
            assertError=true;
        }
        if (!assertError)
                fail("Se esperaba un error de asercion");
    }
  
    @Test
    public void testEMP03B(){
        
    }
    
    @Test
    public void testEMP03C(){
        
    }
  
  

}
