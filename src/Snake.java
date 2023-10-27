import java.awt.Image;
import javax.swing.ImageIcon;

/**
 * @brief Snake class
 * This class is used to create the snake component and move it
 * @file Snake.java
 * @version 1.0
 */
public class Snake {
  private Image dot, head;
  private int dots;
  private final int x[] = new int[Commons.ALL_DOTS];
  private final int y[] = new int[Commons.ALL_DOTS];

  public Snake() {
    this.dots = 3;
    loadImages();
  }

  public Image getHead() {
    return head;
  }

  public Image getBody() {
    return dot;
  }

  public int getDots() {
    return dots;
  }

  public int[] getXArray() {
    return x;
  }

  public int[] getYArray() {
    return y;
  }

  public void setDots(int n) {
    this.dots = n;
  }

  public void loadImages() {
    ImageIcon ii;
    // Load images from images folder
    ii = new ImageIcon("images/dot.png");
    dot = ii.getImage();
    ii = new ImageIcon("images/head.png");
    head = ii.getImage();
  }

  /**
   * @brief Move snake
   * @return void
   */
  public void move(boolean leftDirection, boolean rightDirection, boolean upDirection, boolean downDirection) {
    for (int i = dots; i > 0; i--) {
      x[i] = x[i - 1];
      y[i] = y[i - 1];
    }

    if (leftDirection)
      x[0] -= Commons.DOT_SIZE;

    if (rightDirection)
      x[0] += Commons.DOT_SIZE;

    if (upDirection)
      y[0] -= Commons.DOT_SIZE;

    if (downDirection)
      y[0] += Commons.DOT_SIZE;
  }
}
