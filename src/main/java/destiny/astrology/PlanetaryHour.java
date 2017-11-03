/**
 * Created by smallufo on 2017-11-03.
 */
package destiny.astrology;

import destiny.core.calendar.Location;

import java.io.Serializable;

/**
 * 描述一段期間，其 Planetary Hour 屬於哪顆行星 的資料結構
 */
public class PlanetaryHour implements Serializable {

  /** GMT julDay */
  private final double hourStart;

  /** GMT julDay */
  private final double hourEnd;

  private final Planet planet;

  private final Location location;

  public PlanetaryHour(double hourStart, double hourEnd, Planet planet, Location loc) {
    this.hourStart = hourStart;
    this.hourEnd = hourEnd;
    this.planet = planet;
    this.location = loc;
  }

  public double getHourStart() {
    return hourStart;
  }

  public double getHourEnd() {
    return hourEnd;
  }

  public Planet getPlanet() {
    return planet;
  }

  public Location getLocation() {
    return location;
  }
}
