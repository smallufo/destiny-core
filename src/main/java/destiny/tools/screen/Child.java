package destiny.tools.screen;

import org.jetbrains.annotations.NotNull;

/**
 * @author smallufo
 * @date 2002/8/29
 * @time 上午 02:23:42
 */
class Child {

  private final Canvas c;

  private final int x;

  private final int y;

  public Child(Canvas c, int x, int y) {
    this.c = c;
    this.x = x;
    this.y = y;
  }

  public void setParent(Canvas c) {
    this.c.setParent(c);
  }

  @NotNull
  public Canvas getCanvas() {
    return this.c;
  }

  public int getX() { return this.x; }

  public int getY() { return this.y; }
}
