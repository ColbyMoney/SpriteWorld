import java.util.LinkedList;
import java.util.List;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class EastWestSprite extends Sprite {

  public EastWestSprite(int x, int y) {
    super(x, y);
    images = new LinkedList<Icon>();
    images.add(new ImageIcon("images/east.png"));
    images.add(new ImageIcon("images/west.png"));
    super.setDirection(Sprite.Direction.EAST);
  }

  public void move(Canvas c) {
//    switch (getMoveType()) {
//      case AUTO:
//        ConcreteMoveAuto.move(c, this);
//        break;
//      case CONTROL:
//        ConcreteMoveControl.move(c, this);
//        break;
//    }
  }

  public void animate(Canvas c) {
    switch (getDirection()) {
      case EAST:
        current = 0;
        break;
      case WEST:
        current = 1;
        break;
    }
  }

}
