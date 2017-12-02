/**
 * Created by smallufo on 2017-06-16.
 */
package destiny.core.chinese.ziwei

import destiny.core.chinese.Branch.*
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertSame
import org.junit.Test
import org.slf4j.LoggerFactory
import java.util.*

class StarGeneralFrontTest {

  private val logger = LoggerFactory.getLogger(javaClass)

  @Test
  fun testToString() {
    for (star in StarGeneralFront.values) {
      assertNotNull(star.toString())
      assertNotNull(star.toString(Locale.TAIWAN))
      assertNotNull(star.toString(Locale.CHINA))
      logger.info("tw = {}({}) , cn = {}({})",
        star.toString(Locale.TAIWAN), star.getAbbreviation(Locale.TAIWAN),
        star.toString(Locale.CHINA), star.getAbbreviation(Locale.CHINA))
    }
  }

  @Test
  fun testFun() {
    // 酉年，將星[1] 在酉 , 攀鞍[2] 在戌 , 亡神[12] 在 申
    assertSame(酉, StarGeneralFront.fun將星.apply(酉))
    assertSame(戌, StarGeneralFront.fun攀鞍.apply(酉))
    assertSame(申, StarGeneralFront.fun亡神.apply(酉))
  }
}