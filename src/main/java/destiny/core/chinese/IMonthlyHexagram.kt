/**
 * Created by smallufo on 2019-04-03.
 */
package destiny.core.chinese

import destiny.core.Descriptive
import destiny.core.calendar.SolarTerms
import destiny.core.calendar.SolarTerms.*
import destiny.core.chinese.Branch.*
import destiny.core.iching.Hexagram
import destiny.core.iching.Hexagram.*
import destiny.tools.Domain
import destiny.tools.Impl
import destiny.tools.converters.Domains.Divine.KEY_MONTH_HEX_IMPL
import java.io.Serializable
import java.util.*

/**
 * 12消息卦，兩種設定
 */
interface IMonthlyHexagram : Descriptive {

  /** 此節氣，是什麼卦 , 以及此卦，始於何節氣 (incl.) 、終於何節氣 (excl.) */
  fun getHexagram(solarTerms: SolarTerms): Pair<Hexagram, Pair<SolarTerms, SolarTerms>>
}

/**
 * 冬至點起為 [Hexagram.復] 卦
 * 此為黃道「中氣」正切演算法，亦是 太陽星座劃分法
 */
@Impl([Domain(KEY_MONTH_HEX_IMPL, MonthlyHexagramSignImpl.VALUE, default = true)])
class MonthlyHexagramSignImpl : IMonthlyHexagram, Serializable {
  override fun toString(locale: Locale): String {
    return "黃道「中氣」切割法"
  }

  override fun getDescription(locale: Locale): String {
    return "「復」卦始於冬至點"
  }

  override fun getHexagram(solarTerms: SolarTerms): Pair<Hexagram, Pair<SolarTerms, SolarTerms>> {
    return if (solarTerms.major) {
      // 節
      list.first { (_, pair) -> pair.first == solarTerms.previous() }
    } else {
      // 中氣
      list.first { (_, pair) -> pair.first == solarTerms }
    }
  }

  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (javaClass != other?.javaClass) return false
    return true
  }

  override fun hashCode(): Int {
    return javaClass.hashCode()
  }


  companion object {

    const val VALUE = "sign"

    val instance = MonthlyHexagramSignImpl()

    private val list: List<Pair<Hexagram, Pair<SolarTerms, SolarTerms>>> = listOf(
      復 to (冬至 to 大寒), // 丑 , 摩羯
      臨 to (大寒 to 雨水), // 子 , 水瓶
      泰 to (雨水 to 春分),
      大壯 to (春分 to 穀雨),
      夬 to (穀雨 to 小滿),
      乾 to (小滿 to 夏至),

      姤 to (夏至 to 大暑), // 未 , 巨蟹
      遯 to (大暑 to 處暑), // 午 , 獅子
      否 to (處暑 to 秋分),
      觀 to (秋分 to 霜降),
      剝 to (霜降 to 小雪),
      坤 to (小雪 to 冬至)
    )
  }
}

/**
 * 子月 為 [Hexagram.復] 卦
 * 意味：以節氣的「節」，也是地支月份 來劃分
 */
@Impl([Domain(KEY_MONTH_HEX_IMPL, MonthlyHexagramBranchImpl.VALUE)])
class MonthlyHexagramBranchImpl : IMonthlyHexagram, Serializable {
  override fun toString(locale: Locale): String {
    return "黃道「節」切分法"
  }

  override fun getDescription(locale: Locale): String {
    return "「復」卦，始於子月，亦即：大雪節"
  }

  override fun getHexagram(solarTerms: SolarTerms): Pair<Hexagram, Pair<SolarTerms, SolarTerms>> {
    val hex: Hexagram = map.getValue(solarTerms.branch)
    val between = if (solarTerms.major) {
      solarTerms to (solarTerms.next().next())
    } else {
      solarTerms.previous() to solarTerms.next()
    }
    return hex to between
  }

  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (javaClass != other?.javaClass) return false
    return true
  }

  override fun hashCode(): Int {
    return javaClass.hashCode()
  }


  companion object {

    const val VALUE = "branch"

    val instance = MonthlyHexagramBranchImpl()

    private val map: Map<Branch, Hexagram> = mapOf(
      子 to 復,
      丑 to 臨,
      寅 to 泰,
      卯 to 大壯,
      辰 to 夬,
      巳 to 乾,
      午 to 姤,
      未 to 遯,
      申 to 否,
      酉 to 觀,
      戌 to 剝,
      亥 to 坤
    )
  }

}
