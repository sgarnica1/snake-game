import java.awt.Image;

abstract class Sprite {
	protected int x, y, width, height;
	protected boolean visible;
	protected Image image;

	public Sprite(int x, int y) {
		this.x = x;
		this.y = y;
		this.visible = true;
		loadImage();
		this.width = image.getWidth(null);
		this.height = image.getHeight(null);
	}
	abstract void loadImage();
  /**
   * @brief Return x
   * @return int x
   */
	public int getX() {
		return x;
	}
  /**
   * @brief Return y
   * @return int y
   */
	public int getY() {
		return y;
	}

}
