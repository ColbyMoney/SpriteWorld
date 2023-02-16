import javax.swing.*;

public class ConcreteMoveControl implements MoveBehavior {
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
                }
                break;
            case SOUTH:
                s.setY(s.getY() + 10);
                if (s.getY() + iconHeight > canvasHeight) {
                    s.setY((int)(canvasHeight - iconHeight));
                }
                break;
            case EAST:
                s.setX(s.getX() + 10);
                if (s.getX() + iconWidth > canvasWidth) {
                    s.setX((int)(canvasWidth - iconWidth));
                }
                break;
            case WEST:
                s.setX(s.getX() - 10);
                if (s.getX() < 0) {
                    s.setX(0);
                }
                break;
        }
    }
}
