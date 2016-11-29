package TestRunner;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)

@SuiteClasses({TestPersistencia.TestSuite.class, TestGUI.TestSuite.class,
			   TestCajaBlanca.TestSuite.class, TestCajaNegra.TestSuite.class})

public class AllTest {
}
