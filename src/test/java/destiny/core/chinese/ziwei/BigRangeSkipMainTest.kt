/**
 * Created by smallufo on 2017-04-29.
 */
package destiny.core.chinese.ziwei

import org.junit.Assert.assertNotNull
import org.junit.Test
import org.slf4j.LoggerFactory
import java.util.*

class BigRangeSkipMainTest {

  private val logger = LoggerFactory.getLogger(javaClass)

  internal var impl: IBigRange = BigRangeSkipMain()

  @Test
  fun testString() {
    assertNotNull(impl.getTitle(Locale.TAIWAN))
    assertNotNull(impl.getTitle(Locale.SIMPLIFIED_CHINESE))
    logger.info("title tw = {} , cn = {}", impl.getTitle(Locale.TAIWAN), impl.getTitle(Locale.SIMPLIFIED_CHINESE))
  }

}