package TestCajaBlanca;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)

@SuiteClasses({ EmpresaTest.class, MaquinaTest.class, MaterialTest.class, ObservacionTest.class, PedidoTest.class,
                SectorTest.class })

public class TestSuite
{

}
