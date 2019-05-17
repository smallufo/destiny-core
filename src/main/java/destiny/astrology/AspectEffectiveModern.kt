/**
 * @author smallufo
 * Created on 2007/11/24 at 下午 9:01:16
 */
package destiny.astrology

import java.io.Serializable
import kotlin.math.abs

/**
 * 現代占星的交角容許度
 * 參考資料
 * http://www.myastrologybook.com/aspects-and-orbs.htm
 */
class AspectEffectiveModern(
  /** 「不考慮行星」的交角容許度 */
  var aspectOrbsImpl: IAspectOrbs = AspectOrbsDefaultImpl(),
  /** 「考量行星的」交角容許度實作 */
  var aspectOrbsPlanetImpl: IAspectOrbsPlanet = AspectOrbsPlanetDefaultImpl()
) : IAspectEffective, Serializable {


  /** 直接比對度數是否形成交角，不考慮星體  */
  fun isEffective(deg1: Double, deg2: Double, aspect: Aspect): Boolean {
    val angle = IHoroscopeModel.getAngle(deg1, deg2)
    return abs(angle - aspect.degree) <= aspectOrbsImpl.getAspectOrb(aspect)
  }

  /** 兩星交角容許度是多少 */
  private fun getOrb(p1: Point, p2: Point, aspect: Aspect): Double {
    //從「考量行星」的交角容許度實作找起
    return aspectOrbsPlanetImpl.getPlanetAspectOrb(p1, p2, aspect)
      ?: aspectOrbsImpl.getAspectOrb(aspect) // 再從「不考慮行星」的交角容許度尋找
  }

  fun isEffectiveAndScore(p1: Point, deg1: Double, p2: Point, deg2: Double, aspect: Aspect): Pair<Boolean , Double> {
    val orb = getOrb(p1, p2, aspect)
    val angle = IHoroscopeModel.getAngle(deg1, deg2)
    val angleDiff = abs(angle - aspect.degree)

    return (angleDiff <= orb).let { value ->
      if (value) {
        true to (0.6 + 0.4 * (orb - angleDiff) / orb)
      } else {
        false to 0.0
      }
    }
  }

  /** 有些版本有考慮星體，例如：太陽月亮的交角，會有較高的容許度  */
  override fun isEffective(p1: Point, deg1: Double, p2: Point, deg2: Double, aspect: Aspect): Boolean {
    val orb = getOrb(p1, p2, aspect)
    val angle = IHoroscopeModel.getAngle(deg1, deg2)
    val angleDiff = abs(angle - aspect.degree)

    return (angleDiff <= orb)
  }

  companion object {

    fun isEffective(deg1: Double, deg2: Double, aspect: Aspect, orb: Double): Boolean {
      val angle = IHoroscopeModel.getAngle(deg1, deg2)
      return abs(angle - aspect.degree) <= orb
    }
  }


}
