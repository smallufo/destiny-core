/**
 * @author smallufo
 * Created on 2007/12/30 at 上午 12:04:45
 */
package destiny.astrology.classical.rules.accidentalDignities

import destiny.astrology.DayNight
import destiny.astrology.DayNightDifferentiator
import destiny.astrology.Horoscope
import destiny.astrology.Planet
import destiny.astrology.Planet.*
import org.jooq.lambda.tuple.Tuple
import org.jooq.lambda.tuple.Tuple2
import java.util.*

/**
 * 判斷得時 (Hayz) : 白天 , 晝星位於地平面上，落入陽性星座；或是晚上，夜星在地平面上，落入陰性星座
 * 晝星 : 日 , 木 , 土
 * 夜星 : 月 , 金 , 火
 */
class Hayz(
  /** 計算白天黑夜的實作  */
  var dayNightImpl: DayNightDifferentiator) : Rule() {

  public override fun getResult(planet: Planet, h: Horoscope): Optional<Tuple2<String, Array<Any>>> {
    return getResult2(planet , h)?.let { p -> Tuple.tuple(p.first , p.second) }.let { Optional.ofNullable(it) }
  }

  override fun getResult2(planet: Planet, h: Horoscope): Pair<String, Array<Any>>? {
    val dayNight = dayNightImpl.getDayNight(h.lmt, h.location)

    return h.getZodiacSign(planet)?.let { sign ->
      h.getHouse(planet)?.let { house ->
        return@let when (dayNight) {
          DayNight.DAY -> planet.takeIf { arrayOf(SUN, JUPITER, SATURN).contains(it) }
            ?.takeIf { house >= 7 && sign.booleanValue }
            ?.let {
              logger.debug("晝星 {} 於白天在地平面上，落入陽性星座 {} , 得時", planet, sign.toString(Locale.TAIWAN))
              "commonDay" to arrayOf(planet, sign)
            }
          DayNight.NIGHT -> planet.takeIf { arrayOf(MOON, VENUS, MARS).contains(it) }
            ?.takeIf { house >= 7 && !sign.booleanValue }
            ?.let {
              logger.debug("夜星 {} 於夜晚在地平面上，落入陰性星座 {} , 得時", planet, sign.toString(Locale.TAIWAN))
              "commentNight" to arrayOf(planet, sign)
            }
        }
      }
    }

  }
}
