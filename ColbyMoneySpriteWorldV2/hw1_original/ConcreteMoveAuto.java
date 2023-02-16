import javax.swing.*;

public class ConcreteMoveAuto implements MoveBehavior {
    public void move(Canvas c, Sprite s) {
        Icon icon = s.getCurrentImage();

        int  iconHeight   = icon.getIconHeight();
        int  iconWidth    = icon.getIconWidth();
        int  canvasHeight = (int)c.getSize().getHeight();
        int  canvasWidth  = (int)c.getSize().getWidth();

        switch (s.getDirection()) {
            case NORTH:
                s.setY(s.getY() - 10);
                if (s.getY() < 0) {
                    s.setY(0);
                    s.setDirection(Sprite.Direction.SOUTH);
                }
                break;
            case SOUTH:
                s.setY(s.getY() + 10);
                if (s.getY() + icon.getIconHeight() > c.getSize().getHeight()) {
                    s.setY((int)(c.getSize().getHeight() - icon.getIconHeight()));
                    s.setDirection(Sprite.Direction.NORTH);
                }
                break;
            case EAST:
                s.setX(s.getX() + 10);
                if (s.getX() + icon.getIconWidth() > c.getSize().getWidth()) {
                    s.setX((int)(c.getSize().getWidth() - icon.getIconWidth()));
                    s.setDirection(Sprite.Direction.WEST);
                }
                break;
            case WEST:
                s.setX(s.getX() - 10);
                if (s.getX() < 0) {
                    s.setX(0);
                    s.setDirection(Sprite.Direction.EAST);
                }
                break;
            case NONE:
                if (s instanceof NorthSouthSprite)
                    s.setDirection(Sprite.Direction.NORTH);
                else {
                    s.setDirection(Sprite.Direction.EAST);
                }
                break;
        }
    }
}
