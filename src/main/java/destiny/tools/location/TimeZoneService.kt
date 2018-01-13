/**
 * @author smallufo
 * Created on 2011/4/12 at 上午10:41:20
 */
package destiny.tools.location

import java.util.*

interface TimeZoneService {


  /** 嘗試從經緯度，尋找 TimeZone  */
  fun getTimeZoneId(lng: Double, lat: Double): String?

  fun getTimeZone(lng: Double, lat: Double): TimeZone? {
    return getTimeZoneId(lng , lat)?.let { TimeZone.getTimeZone(it) }
  }

  fun getTimeZoneOrGMT(lng: Double, lat: Double): TimeZone {
    return getTimeZoneId(lng , lat)?.let { TimeZone.getTimeZone(it) }?:TimeZone.getTimeZone("GMT")
  }
}
