/**
 * Created by smallufo on 2017-07-08.
 */
package destiny.core.astrology

import destiny.core.calendar.ILocation

open class PositionHamburgerImpl(val starPositionImpl: IStarPosition<*> , hamburger: Hamburger) :
  AbstractPositionImpl<Hamburger>(hamburger) {

  override fun getPosition(gmtJulDay: Double,
                           loc: ILocation,
                           centric: Centric,
                           coordinate: Coordinate,
                           temperature: Double,
                           pressure: Double): IPos {
    return starPositionImpl.getPosition(point, gmtJulDay, loc.lat, loc.lng, loc.altitudeMeter?:0.0, centric, coordinate , temperature, pressure)
  }
}
