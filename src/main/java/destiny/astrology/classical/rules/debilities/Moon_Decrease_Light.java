/**
 * @author smallufo
 * Created on 2007/12/31 at 上午 3:19:43
 */
package destiny.astrology.classical.rules.debilities;

import destiny.astrology.*;
import org.jetbrains.annotations.NotNull;
import org.jooq.lambda.tuple.Tuple;
import org.jooq.lambda.tuple.Tuple2;

import java.util.Optional;

import static java.util.Optional.empty;

/** Moon decreasing in light. */
public final class Moon_Decrease_Light extends Rule {

  public Moon_Decrease_Light() {
  }

  @Override
  protected Optional<Tuple2<String, Object[]>> getResult(Planet planet, @NotNull Horoscope h) {

    return h.getPositionOptional(planet)
      .filter(pos -> planet == Planet.MOON).map(Position::getLng).flatMap(planetDegree ->
        h.getPositionOptional(Planet.SUN).map(Position::getLng).flatMap(sunDegree -> {
          if (Horoscope.isOriental(planetDegree, sunDegree)) {
            logger.debug("{} 在太陽東邊（月減光/下弦月）");
            return Optional.of(Tuple.tuple("comment", new Object[]{planet}));
          }
          return empty();
        })
      );

//    if (planet == Planet.MOON) {
//      double planetDegree = h.getPosition(planet).getLng();
//      double sunDegree = h.getPosition(Planet.SUN).getLng();
//
//      if (Horoscope.isOriental(planetDegree, sunDegree)) {
//        logger.debug("{} 在太陽東邊（月減光/下弦月）");
//        return Optional.of(Tuple.tuple("comment", new Object[]{planet}));
//      }
//    }
//    return empty();
  }

}
