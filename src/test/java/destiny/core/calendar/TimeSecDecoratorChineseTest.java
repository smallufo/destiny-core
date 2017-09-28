/**
 * @author smallufo
 * Created on 2007/3/20 at 上午 6:39:27
 */
package destiny.core.calendar;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.chrono.IsoEra;

import static org.junit.Assert.assertEquals;

public class TimeSecDecoratorChineseTest {

  private Logger logger = LoggerFactory.getLogger(getClass());


  @Test
  public void testGetOutputString() {
    TimeSecDecoratorChinese decorator = new TimeSecDecoratorChinese();

    LocalDateTime time;
    time = LocalDateTime.of(2000, 1, 1, 0, 0, 0);
    logger.info("{} : {}" , time , decorator.getOutputString(time));
    assertEquals("西元　2000年01月01日　00時00分 00.00秒" , decorator.getOutputString(time));

    time = LocalDateTime.of(LocalDate.of(2000 , 12 , 31).with(IsoEra.BCE) , LocalTime.of(23 , 59 , 59 , 999_000_000));
    logger.info("{} : {}" , time , decorator.getOutputString(time));
    assertEquals("西元前2000年12月31日　23時59分 59.99秒" , decorator.getOutputString(time));
  }

}
