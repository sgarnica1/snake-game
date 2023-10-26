import java.awt.Image;
import javax.swing.ImageIcon;

public class Apple {
  private Image image;

  public Apple() {
    loadImages();
  }

  public Image getImage() {
    return image;
  }

  private void loadImages() {
    ImageIcon ii;
    // Load images from images folder
    ii = new ImageIcon("images/apple.png");
    image = ii.getImage();
  }
}
