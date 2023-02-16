import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Graphics;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.Timer;

public class Canvas extends JComponent implements ActionListener, KeyListener {

  private static Canvas canvas;
  private ConcreteMoveAuto autoMove = new ConcreteMoveAuto();
  private ConcreteMoveControl controlMove = new ConcreteMoveControl();
  private ConcreteMoveRedSprite redMove = new ConcreteMoveRedSprite();

  public static Canvas getCanvas() {
    if (canvas == null) {
      canvas = new Canvas();
    }
    return canvas;
  }

  private List<Sprite>  sprites;
  private Timer         timer;
  private JFrame        frame;
  private int           highlighted = 0;

  public int getHighlighted() {return highlighted;}

  private Canvas() {
    super();

    frame = new JFrame("Monitor");
    frame.setSize(800, 800);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.add( this );

    sprites = new LinkedList<Sprite>();

    timer = new Timer(25, this);
    timer.start();

    setFocusTraversalKeysEnabled(false);
    addKeyListener(this);

    frame.setVisible(true);
  }

  public synchronized void actionPerformed(ActionEvent evt) {
    for (Sprite s : sprites) {
      switch (s.getMoveType()) {
        case AUTO:
          autoMove.move(this, s);
          break;
        case CONTROL:
          controlMove.move(this, s);
          break;
        case RED :
          redMove.move(this, s);
          break;
      }
      //s.move(this);
      s.animate(this);
    }
    repaint();
  }

  public synchronized void addSprite(Sprite sprite) {
    sprites.add(sprite);
  }

  public synchronized void paint(Graphics g) {
    for (Sprite s : sprites) {
      s.draw(this, g);
    }
    if (sprites.size() > highlighted) {
      Sprite s = sprites.get(highlighted);
      s.highlight(this, g);
    }
  }

  public void keyTyped(KeyEvent e) {
  }

  public void keyPressed(KeyEvent e) {
    if (highlighted != 3) {
      if (e.getKeyCode() == e.VK_UP) {
        sprites.get(highlighted).setDirection(Sprite.Direction.NORTH);
      }
      if (e.getKeyCode() == e.VK_DOWN) {
        sprites.get(highlighted).setDirection(Sprite.Direction.SOUTH);
      }
      if (e.getKeyCode() == e.VK_RIGHT) {
        sprites.get(highlighted).setDirection(Sprite.Direction.EAST);
      }
      if (e.getKeyCode() == e.VK_LEFT) {
        sprites.get(highlighted).setDirection(Sprite.Direction.WEST);
      }
    }
  }

  public void keyReleased(KeyEvent e) {
    if (e.getKeyCode() == e.VK_TAB) {
      if (highlighted == 0 || highlighted == 1) {
        sprites.get(highlighted).setMoveType(Sprite.MoveType.AUTO);
        highlighted++;
        sprites.get(highlighted).setMoveType(Sprite.MoveType.CONTROL);
        sprites.get(highlighted).setDirection(Sprite.Direction.NONE);
      }
      else if (highlighted == 2) {
        sprites.get(highlighted).setMoveType(Sprite.MoveType.AUTO);
        highlighted++;
      }
      else if (highlighted == 3) {
        highlighted = 0;
        sprites.get(highlighted).setMoveType(Sprite.MoveType.CONTROL);
        sprites.get(highlighted).setDirection(Sprite.Direction.NONE);
      }
    }
    //  code that doesnt work right
//      System.out.print(highlighted + " ");
//      switch (highlighted) {
//        case 0:
//          sprites.get(highlighted).setMoveType(Sprite.MoveType.AUTO);
//          highlighted = 1;
//          sprites.get(highlighted).setMoveType(Sprite.MoveType.CONTROL);
//        case 1:
//          sprites.get(highlighted).setMoveType(Sprite.MoveType.AUTO);
//          highlighted = 2;
//          sprites.get(highlighted).setMoveType(Sprite.MoveType.CONTROL);
//        case 2:
//          sprites.get(highlighted).setMoveType(Sprite.MoveType.AUTO);
//          highlighted = 3;
//          //sprites.get(highlighted).setMoveType(Sprite.MoveType.CONTROL);
//        case 3:
//          //sprites.get(highlighted).setMoveType(Sprite.MoveType.AUTO);
//          highlighted = 0;
////          if (highlighted == sprites.size()) {
////            highlighted = 0;
////          }
//          sprites.get(highlighted).setMoveType(Sprite.MoveType.CONTROL);
//
//      }
////    if (highlighted != 3) {
////      sprites.get(highlighted).setMoveType(Sprite.MoveType.AUTO);
////      highlighted = highlighted + 1;
////      if (highlighted == sprites.size()) {
////        highlighted = 0;
////      }
////      sprites.get(highlighted).setMoveType(Sprite.MoveType.CONTROL);
////    } else {
////      //sprites.get(highlighted).setMoveType(Sprite.MoveType.AUTO);
////      highlighted = highlighted + 1;
////      if (highlighted == sprites.size()) {
////        highlighted = 0;
////      }
////      sprites.get(highlighted).setMoveType(Sprite.MoveType.CONTROL);
////    }
//    }
    if (e.getKeyCode() != e.VK_TAB && highlighted != 3) {
      sprites.get(highlighted).setDirection(Sprite.Direction.NONE);
    }
  }

}
