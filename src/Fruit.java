import java.awt.Image;
import javax.swing.ImageIcon;

public class Fruit {
  private Image image;
  private String imageName;

  public Fruit(String imageName) {
    this.imageName = imageName;
    loadImages();
  }

  public Image getImage() {
    return image;
  }

  private void loadImages() {
    ImageIcon ii;
    // Load images from images folder
    ii = new ImageIcon("images/"+ imageName +".png");
    image = ii.getImage();
  }
}
