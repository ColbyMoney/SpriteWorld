import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class SpriteAdapter extends Sprite {
    private RedSprite redSprite;

    public SpriteAdapter(int x, int y) {
        super(x, y);
        redSprite = new RedSprite(x, y);
        images = new LinkedList<Icon>();
        images.add(new ImageIcon("images/RedNorth.png"));
        images.add(new ImageIcon("images/RedSouth.png"));
        images.add(new ImageIcon("images/RedEast.png"));
        images.add(new ImageIcon("images/RedWest.png"));
        setDirection(Sprite.Direction.NONE);
        setMoveType(Sprite.MoveType.RED);
    }

    public void move(Canvas c) {
        redSprite.moveMe(c);
    }

    public void animate(Canvas c) {
        redSprite.animateMe(c);
    }

    public void highlight(Component c, Graphics g) {
        redSprite.highlightMe(c, g);
    }

    public void draw(Component c, Graphics g) {
        redSprite.drawMe(c, g);
    }
}

