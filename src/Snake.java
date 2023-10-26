import java.awt.Image;
import javax.swing.ImageIcon;

public class Snake {
  private Image dot, head;

  public Snake() {
    loadImages();
  }

  public Image getHead() {
    return head;
  }

  public Image getDot() {
    return dot;
  }

  public void loadImages() {
    ImageIcon ii;
    // Load images from images folder
    ii = new ImageIcon("images/dot.png");
    dot = ii.getImage();
    ii = new ImageIcon("images/head.png");
    head = ii.getImage();
  }
}
