/**
 * @author smallufo 
 * Created on 2007/3/14 at 上午 5:20:43
 */ 
package destiny.core.calendar;

import destiny.core.calendar.Location.EastWest;
import destiny.core.calendar.Location.NorthSouth;
import destiny.tools.location.TimeZoneUtils;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LocationTest {

  private Logger logger = LoggerFactory.getLogger(getClass());


  @Test
  public void testNorthSouth()
  {
    Assert.assertSame(NorthSouth.NORTH, NorthSouth.getNorthSouth('N'));
    Assert.assertSame(NorthSouth.NORTH, NorthSouth.getNorthSouth('n'));
    Assert.assertSame(NorthSouth.SOUTH, NorthSouth.getNorthSouth('S'));
    Assert.assertSame(NorthSouth.SOUTH, NorthSouth.getNorthSouth('s'));
  }
  
  @Test
  public void testEastWest()
  {
    Assert.assertSame(EastWest.EAST, EastWest.getEastWest('E'));
    Assert.assertSame(EastWest.EAST, EastWest.getEastWest('e'));
    Assert.assertSame(EastWest.WEST, EastWest.getEastWest('W'));
    Assert.assertSame(EastWest.WEST, EastWest.getEastWest('w'));
  }
  
  @Test
  public void testLocation()
  {
    Location actual;
    Location expected;
    
    actual = new Location(-12,23,45.0 , -23,34,56.0 , TimeZoneUtils.getTimeZone(120));
    expected = new Location(EastWest.WEST , 12 , 23 , 45.0 , NorthSouth.SOUTH , 23 , 34 , 56.0 , TimeZoneUtils.getTimeZone(120).getID());
    Assert.assertEquals(expected, actual);
    
    actual = new Location(12,23,45.0 , -23,34,56.0 , TimeZoneUtils.getTimeZone(120));
    expected = new Location(EastWest.EAST, 12 , 23 , 45.0 , NorthSouth.SOUTH , 23 , 34 , 56.0 , TimeZoneUtils.getTimeZone(120).getID());
    Assert.assertEquals(expected, actual);
    
    
    actual = new Location(-12,23,45.0 , 23,34,56.0 , TimeZoneUtils.getTimeZone(120));
    expected = new Location(EastWest.WEST , 12 , 23 , 45.0 , NorthSouth.NORTH, 23 , 34 , 56.0 , TimeZoneUtils.getTimeZone(120).getID());
    Assert.assertEquals(expected, actual);
    
    actual = new Location(12,23,45.0 , 23,34,56.0 , TimeZoneUtils.getTimeZone(120));
    expected = new Location(EastWest.EAST , 12 , 23 , 45.0 , NorthSouth.NORTH , 23 , 34 , 56.0 , TimeZoneUtils.getTimeZone(120).getID());
    Assert.assertEquals(expected, actual);
  }
  
  @Test
  public void testLocationDebugString()
  {
    Location location ;
    String expected ;
    location = new Location(EastWest.EAST , 121 , 30 , 12.34 , NorthSouth.NORTH , 25 , 3, 12.34 , 12.3456 , TimeZoneUtils.getTimeZone(480).getID());
    expected = "+1213012.34+25 312.34 12.3456 CTT";
    Assert.assertEquals(expected, location.getDebugString());
    
    location = new Location(EastWest.EAST , 121 , 30 , 12.34 , NorthSouth.NORTH , 25 , 3, 12.34 , 0 , TimeZoneUtils.getTimeZone(-60).getID());
    expected = "+1213012.34+25 312.34 0.0 Etc/GMT+1";
    Assert.assertEquals(expected, location.getDebugString());
    
    location = new Location(EastWest.EAST , 121 , 30 , 12.34 , NorthSouth.NORTH , 25 , 3, 12.34 , -1000 , TimeZoneUtils.getTimeZone(-60).getID());
    expected = "+1213012.34+25 312.34 -1000.0 Etc/GMT+1";
    Assert.assertEquals(expected, location.getDebugString());

  }
  
  /**
   * 2012/03 之後的格式 , 尾方為 altitude TimeZone [minuteOffset]
   */
  @Test
  public void testLocationFromDebugString_format2012()
  {
    Location location , expected;
    
    location = new Location("+1213012.34+25 312.34 12.3456 Asia/Taipei");
    expected = new Location(EastWest.EAST , 121 , 30 , 12.34 , NorthSouth.NORTH , 25 , 3, 12.34 , 12.3456 , "Asia/Taipei");
    Assert.assertEquals(expected, location);
    
    //強制設定 minuteOffset = 0
    location = new Location(location.getDebugString()+ " 0");
    expected = new Location(EastWest.EAST , 121 , 30 , 12.34 , NorthSouth.NORTH , 25 , 3, 12.34 , 12.3456 , "Asia/Taipei" , 0);
    Assert.assertEquals(expected, location);
    
    location = new Location("+1213012.34+25 312.34 12.3456 Asia/Taipei 60");
    expected = new Location(EastWest.EAST , 121 , 30 , 12.34 , NorthSouth.NORTH , 25 , 3, 12.34 , 12.3456 , "Asia/Taipei" , 60);
    Assert.assertEquals(expected, location);
    
    location = new Location("+1213012.34+25 312.34 12.3456 Asia/Taipei -480");
    expected = new Location(EastWest.EAST , 121 , 30 , 12.34 , NorthSouth.NORTH , 25 , 3, 12.34 , 12.3456 , "Asia/Taipei" , -480);
    Assert.assertEquals(expected, location);
  }
  
  @Test
  public void testLocationEastWestDoubleNorthSouthDoubleInt()
  {
    Location location ;
    location = new Location(EastWest.EAST , 121.51 ,NorthSouth.NORTH , 25.33 , 0, "Asia/Taipei", null) ;
    Assert.assertEquals(121, location.getLngDeg());
    Assert.assertEquals(30, location.getLngMin());
    Assert.assertEquals(36.0, location.getLngSec() , 0.0);
    
    Assert.assertEquals(25, location.getLatDeg());
    Assert.assertEquals(19, location.getLatMin());
    Assert.assertEquals(48.0, location.getLatSec() , 0.0);
  }


}
