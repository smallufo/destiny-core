/**
 * @author smallufo
 * Created on 2013/5/12 at 上午12:37:45
 */
package destiny.iching.graph;

import destiny.core.chart.Constants.WIDTH_HEIGHT;
import destiny.iching.Hexagram;
import destiny.iching.graph.ProcessorSideSymbol.Side;
import destiny.utils.image.Processor;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

/**
 * 兩個卦象直接並排，不考慮 黃金比例
 */
public class PairMergeBufferedImage extends AbstractPairBufferImage
{
  public PairMergeBufferedImage(@NotNull Hexagram src, String srcName, @NotNull Hexagram dst, String dstName, WIDTH_HEIGHT which, int value, Color bg, Color fore)
  {
    super(src, srcName, dst, dstName, Type.MERGED, which, value, bg , fore);
  }

  @Override
  protected double getOxX()
  {
    return getWidth() / 2.0 - getPaddingX()/2.0;
  }

  @Override
  protected double getArrowX(double radius)
  {
    return w /2 + getPaddingX() - radius*2;
  }
  
  /** 繪製側邊文字 , 可能是八卦類像文字（天澤火雷...） */
  @Override
  protected void drawSide()
  {
    Processor pSymbol;
    pSymbol = new ProcessorSideSymbol(src, Side.LEFT, getSrcChart());
    pSymbol.process(getSrcChart());
    pSymbol = new ProcessorSideSymbol(dst, Side.RIGHT, getSrcChart());
    pSymbol.process(getDstChart());
  }

  /** 單一卦象的 paddingX */
  private double getPaddingX()
  {
    return getSrcChart().paddingX;
  }
}
