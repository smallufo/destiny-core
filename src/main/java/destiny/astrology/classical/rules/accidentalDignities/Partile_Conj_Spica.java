/**
 * @author smallufo 
 * Created on 2007/12/30 at 上午 12:02:30
 */ 
package destiny.astrology.classical.rules.accidentalDignities;

import destiny.astrology.*;
import destiny.utils.Tuple;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

/** Partile conjunct Spica at 23deg50' Libra in January 2000. */
public final class Partile_Conj_Spica extends Rule
{
  public Partile_Conj_Spica()
  {
  }

  @Override
  protected java.util.Optional<Tuple<String, Object[]>> getResult(Planet planet, @NotNull HoroscopeContext horoscopeContext)
  {
    double planetDegree = horoscopeContext.getPosition(planet).getLongitude();
    double spicaDeg = horoscopeContext.getPosition(FixedStar.SPICA).getLongitude();
    
    if (AspectEffectiveModern.isEffective(planetDegree , spicaDeg , Aspect.CONJUNCTION , 1))
    {
      // addComment(Locale.TAIWAN , planet + " 與 " + FixedStar.SPICA + " 形成 " + Aspect.CONJUNCTION);
      return Optional.of(Tuple.of("comment" , new Object[] {planet , FixedStar.SPICA , Aspect.CONJUNCTION}));
    }
    return Optional.empty();
  }

}
