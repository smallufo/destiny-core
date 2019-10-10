/**
 * @author smallufo
 * Created on 2008/6/19 at 上午 1:55:27
 */
package destiny.astrology

import java.io.Serializable
import java.util.*

/**
 * 現代占星術，計算一張星盤中，星體交角列表的實作
 * */

class HoroscopeAspectsCalculatorModern : IHoroscopeAspectsCalculator, Serializable {

  private val modern: AspectEffectiveModern = AspectEffectiveModern()

  override fun getPointAspectAndScore(point: Point,
                                      positionMap: Map<Point, IPos>,
                                      points: Collection<Point>,
                                      aspects: Collection<Aspect>): Set<Triple<Point, Aspect, Double>> {
    return positionMap[point]?.lng?.let { starDeg ->
      points
        .filter { it !== point }
        .filter { positionMap.containsKey(it) }
        .filter { eachPoint -> !(point is Axis && eachPoint is Axis) }  // 過濾四角點互相形成的交角
        .filter { eachPoint -> !(point is LunarNode && eachPoint is LunarNode) } // 過濾南北交點對沖
        .flatMap { eachPoint ->
          val eachDeg = positionMap.getValue(eachPoint).lng
          aspects.map { eachAspect ->
            eachAspect to modern.isEffectiveAndScore(point, starDeg, eachPoint, eachDeg, eachAspect)
          }.filter { (_, pair: Pair<Boolean, Double>) ->
            pair.first
          }.map { (aspect, pair) ->
            Triple(eachPoint , aspect , pair.second)
          }
        }.toSet()
    }?: emptySet()
  }

  override fun getTitle(locale: Locale): String {
    return "現代占星術"
  }

  override fun getDescription(locale: Locale): String {
    return "現代占星術實作"
  }


}
