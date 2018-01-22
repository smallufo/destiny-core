/**
 * @author smallufo
 * 2002/8/25 下午 11:22:48
 */
package destiny.core.calendar.eightwords;

import destiny.core.chinese.Branch;
import destiny.core.chinese.Stem;
import destiny.core.chinese.StemBranch;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


/**
 * 八字資料結構 , 四柱任何一柱都不可以為 null
 * 2006/06/12 將此 class 繼承 EightWordsNullable
 */
public class EightWords extends EightWordsNullable {

  /** Constructor , 任何一柱都不可以為 null */
  public EightWords(@NotNull StemBranch year, @NotNull StemBranch month, @NotNull StemBranch day, @NotNull StemBranch hour) {
    super(year, month, day, hour);
  }

  /**
   * 以 "甲子","甲子","甲子","甲子" 方式 construct 此物件 , 任何一柱都不可以為 null
   */
  public EightWords(@NotNull String year , @NotNull String month , @NotNull String day , @NotNull String hour) {
    super(
      StemBranch.Companion.get(year.toCharArray()[0] , year.toCharArray()[1]) ,
      StemBranch.Companion.get(month.toCharArray()[0] , month.toCharArray()[1]),
      StemBranch.Companion.get(day.toCharArray()[0] , day.toCharArray()[1]) ,
      StemBranch.Companion.get(hour.toCharArray()[0] , hour.toCharArray()[1])
    );
  }

  /** 從 EightWordsNullable 建立 EightWords , 其中 EightWordsNullable 任何一柱都不可以為 null , 否則會出現 RuntimeException */
  public EightWords(EightWordsNullable nullable) {
    super(nullable.getYear() , nullable.getMonth() , nullable.getDay() , nullable.getHour());
  }

  /** 直接用八個干支建立八字 */
  public EightWords(Stem yearStem , Branch yearBranch , Stem monthStem , Branch monthBranch , Stem dayStem , Branch dayBranch , Stem hourStem , Branch hourBranch) {
    super(
      StemBranch.Companion.get(yearStem, yearBranch),
      StemBranch.Companion.get(monthStem , monthBranch) ,
      StemBranch.Companion.get(dayStem , dayBranch) ,
      StemBranch.Companion.get(hourStem , hourBranch)
    );
  }

  /**
   * null Constructor , 避免誤用而建構出有 null 的四柱
   */
  @SuppressWarnings("unused")
  private EightWords()
  {}

  @NotNull
  @Override public Stem getYearStem()  {
    assert (year.getStem() != null);
    return year.getStem();
  }

  @NotNull
  @Override public Stem getMonthStem() {
    assert (month.getStem() != null);
    return month.getStem();
  }

  @NotNull
  @Override public Stem getDayStem()   {
    assert (day.getStem() != null);
    return day.getStem();
  }

  @NotNull
  @Override public Stem getHourStem()  {
    assert (hour.getStem() != null);
    return hour.getStem();
  }

  @NotNull
  @Override public Branch getYearBranch()  {
    assert (year.getBranch() != null);
    return year.getBranch();
  }

  @NotNull
  @Override public Branch getMonthBranch() {
    assert (month.getBranch() != null);
    return month.getBranch();
  }

  @NotNull
  @Override public Branch getDayBranch()   {
    assert (day.getBranch() != null);
    return day.getBranch();
  }

  @NotNull
  @Override public Branch getHourBranch()  {
    assert (hour.getBranch() != null);
    return hour.getBranch();
  }

  @NotNull
  public StemBranch getYear() {
    return (StemBranch) year;
  }

  @NotNull
  public StemBranch getMonth() {
    return (StemBranch) month;
  }

  @NotNull
  public StemBranch getDay() {
    return (StemBranch) day;
  }

  @NotNull
  public StemBranch getHour() {
    return (StemBranch) hour;
  }

  @NotNull
  @Override
  public List<StemBranch> getStemBranches() {
    return new ArrayList<StemBranch>() {{
      add((StemBranch)year);
      add((StemBranch)month);
      add((StemBranch)day);
      add((StemBranch)hour);
    }};
  }

  @Override
  public boolean equals(Object o) {
    if ((o != null) && (o.getClass().equals(this.getClass()))) {
      EightWords ew = (EightWords) o;
      return (year.equals(ew.year) && month.equals(ew.month) && day.equals(ew.day) && hour.equals(ew.hour));
    }
    else
      return false;
  }

  @Override
  public int hashCode() {
    int hash = 13;
    hash = hash * 17 + year.hashCode();
    hash = hash * 17 + month.hashCode();
    hash = hash * 17 + day.hashCode();
    hash = hash * 17 + hour.hashCode();
    return hash;
  }

  @Override
  public String toString()
  {
    assert (hour.getStemOptional().isPresent());

    return "\n"+
      hour.getStem() + day.getStem() + month.getStem() + year.getStem()
      + "\n" +
      hour.getBranch() + day.getBranch() + month.getBranch()+year.getBranch();
  }
}
