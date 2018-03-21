/**
 * Created by smallufo on 2017-07-08.
 */
package destiny.astrology

import destiny.core.calendar.ILocation
import java.time.chrono.ChronoLocalDateTime

open class PositionFixedStarImpl internal constructor(fixedStar: FixedStar) : AbstractPositionImpl<FixedStar>(fixedStar) {

  override fun getPosition(lmt: ChronoLocalDateTime<*>, loc: ILocation, centric: Centric, coordinate: Coordinate, starPositionImpl: IStarPosition<*>): IPos {
    return starPositionImpl.getPosition(point, lmt, loc, centric, coordinate)
  }
}
