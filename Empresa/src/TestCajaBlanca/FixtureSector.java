package TestCajaBlanca;

import empresa.Sector;

public class FixtureSector
{
  private Sector sectorTest = new Sector();

  public FixtureSector()
  {
    super();
  }

  public Sector getSectorTest()
  {
    return sectorTest;
  }

  public void setUp()
  {

  }

  public void tearDown()
  {
    sectorTest = new Sector();
  }
}
