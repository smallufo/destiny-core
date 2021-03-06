/**
 * Created by smallufo on 2020-10-22.
 */
package destiny.core.astrology.classical

import destiny.core.astrology.classical.AspectEffectiveClassicalBuilder.Companion.aspectEffectiveClassical
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.assertTrue


class AspectEffectiveClassicalBuilderTest {

  @Test
  fun equals_with_default() {
    val impl1 = aspectEffectiveClassical { }
    val impl2 = aspectEffectiveClassical {
      planetOrbsImpl = PointDiameterAlBiruniImpl()
      defaultThreshold = 0.6
    }
    assertEquals(impl1, impl2)
  }

  @Test
  fun notEquals_by_planetOrbsImpl() {
    val impl1 = aspectEffectiveClassical {
      planetOrbsImpl = PointDiameterAlBiruniImpl()
    }
    val impl2 = aspectEffectiveClassical {
      planetOrbsImpl = PointDiameterLillyImpl()
    }
    assertNotEquals(impl1, impl2)
    assertTrue(impl1.planetOrbsImpl is PointDiameterAlBiruniImpl)
    assertTrue(impl2.planetOrbsImpl is PointDiameterLillyImpl)
  }

  @Test
  fun notEquals_by_defaultThreshold() {
    val impl1 = aspectEffectiveClassical { }
    val impl2 = aspectEffectiveClassical {
      defaultThreshold = 0.7
    }
    assertNotEquals(impl1, impl2)
  }
}
