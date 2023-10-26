import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * @brief Board class
 *        This class is used to create the board of the game
 * @file Board.java
 * @version 1.0
 */
public class Board extends JPanel implements ActionListener {
  private static final long serialVersionUID = -2104647880252821933L;
  private Snake snake;
  private int dots;
  private int x[] = null;
  private int y[] = null;
  private boolean leftDirection = false;
  private boolean rightDirection = true;
  private boolean upDirection = false;
  private boolean downDirection = false;
  private boolean inMenu = true;
  private boolean inGame = false;
  private boolean inPause = false;
  private int appleX, appleY;
  private int lives = 3;
  private Timer timer;
  private Image dot, apple, head;

  /**
   * @brief Constructor
   * @return void
   */
  public Board() {
    initUI();
  }

  /**
   * @brief Init user interface
   * @return void
   */
  private void initUI() {
    addKeyListener(new TAdapter());
    setBackground(Color.black);
    setFocusable(true);
    setPreferredSize(new Dimension(Commons.B_WIDTH, Commons.B_HEIGHT));
    initComponents();
    initMenu();
  }

  /**
   * @brief Restart game
   * @return void
   */
  private void restartGame() {
    initGame();
    inMenu = false;
    inGame = true;
    rightDirection = true;
    leftDirection = false;
    upDirection = false;
    downDirection = false;
  }

  /**
   * @brief Pause game
   * @return void
   */
  private void pauseGame() {
    drawPausedGame(getGraphics());
    inPause = true;
    timer.stop();
  }

  /**
   * @brief Resume game
   * @return void
   */
  private void resumeGame() {
    inPause = false;
    timer.start();
  }

  /**
   * @brief Init UI Components
   * @return
   */
  private void initComponents() {
    initSnake();
    initApple();
  }

  /**
   * @brief Load snake
   * @return void
   */
  private void initSnake() {
    snake = new Snake();
    head = snake.getHead();
    dot = snake.getBody();
    x = snake.getXArray();
    y = snake.getYArray();
    dots = snake.getDots();
  }

  /**
   * @brief Load apple
   * @return void
   */
  private void initApple() {
    Apple _apple = new Apple();
    apple = _apple.getImage();
  }

  /**
   * @brief Init menu game
   * @return void
   */
  private void initMenu() {
    inMenu = true;
    inGame = false;
    inPause = false;
  }

  /**
   * @brief Init game
   * @return void
   */
  private void initGame() {
    lives = 3;
    locateSnake();
    locateApple();
    // Set timer
    timer = new Timer(Commons.DELAY, this);
    timer.start();
  }

  /**
   * @brief Locate apple in board
   */
  private void locateApple() {
    int aux;
    aux = (int) (Math.random() * Commons.RAND_POS);
    appleX = aux * Commons.DOT_SIZE; // x position of apple
    aux = (int) (Math.random() * Commons.RAND_POS);
    appleY = aux * Commons.DOT_SIZE; // y position of apple
  }

  /**
   * @brief locates snakes in the board
   * @return void
   */
  private void locateSnake() {
    for (int i = 0; i < dots; i++) {
      x[i] = 50 - (i * Commons.DOT_SIZE);
      y[i] = 50;
    }
    rightDirection = true;
    leftDirection = false;
    upDirection = false;
    downDirection = false;
  }

  private void collision() {
    lives--;
    locateSnake();
  }

  /**
   * @brief Paint component
   * @param g Graphics
   * @return void
   */
  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    doDrawing(g);
  }

  /**
   * @brief Draw components
   * @param g Graphics
   * @return void
   */
  private void doDrawing(Graphics g) {
    if (inMenu) {
      drawMenu(g); // Draw menu
    }
    if (inGame) {
      g.drawImage(apple, appleX, appleY, null); // Draw apple
      for (int i = 0; i < dots; i++) {
        if (i == 0) {
          g.drawImage(head, x[i], y[i], null); // Draw snake head
        } else {
          g.drawImage(dot, x[i], y[i], null); // Draw snake body
        }
      }
      drawNavigation(g);
      Toolkit.getDefaultToolkit().sync();
    }

    if (!inGame && !inMenu && !inPause) {
      drawGameOver(g); // Draw game over
    }
  }

  /**
   * @brief Draw game over
   * @param g
   * @return void
   */
  private void drawGameOver(Graphics g) {
    String msg = "Game Over";
    String msg2 = "Press SPACE to restart";
    Font small = new Font("Helvetica", Font.BOLD, 14);
    FontMetrics fm = getFontMetrics(small);
    g.setColor(Color.white);
    g.setFont(small);
    g.drawString(msg, ((Commons.B_WIDTH - fm.stringWidth(msg)) / 2), (Commons.B_HEIGHT / 2));
    // Draw message to restart game
    small = new Font("Helvetica", Font.BOLD, 12);
    fm = getFontMetrics(small);
    g.setFont(small);
    g.drawString(msg2, ((Commons.B_WIDTH - fm.stringWidth(msg2)) / 2), (Commons.B_HEIGHT / 2) + 20);
  }

  /**
   * @brief Draw inPause game
   * @param g
   * @return void
   */
  private void drawPausedGame(Graphics g) {
    String msg = "Game Paused";
    String msg2 = "Press R to resume";
    Font small = new Font("Helvetica", Font.BOLD, 14);
    FontMetrics fm = getFontMetrics(small);
    g.setColor(Color.white);
    g.setFont(small);
    g.drawString(msg, ((Commons.B_WIDTH - fm.stringWidth(msg)) / 2), (Commons.B_HEIGHT / 2));
    // Draw message to restart game
    small = new Font("Helvetica", Font.BOLD, 12);
    fm = getFontMetrics(small);
    g.setFont(small);
    g.drawString(msg2, ((Commons.B_WIDTH - fm.stringWidth(msg2)) / 2), (Commons.B_HEIGHT / 2) + 20);
  }

  /**
   * @brief Draw menu
   * @param g
   * @return void
   */
  private void drawMenu(Graphics g) {
    String msg = "Snake Game";
    String msg2 = "Press SPACE to start";
    Font small = new Font("Helvetica", Font.BOLD, 14);
    FontMetrics fm = getFontMetrics(small);
    g.setColor(Color.white);
    g.setFont(small);
    g.drawString(msg, ((Commons.B_WIDTH - fm.stringWidth(msg)) / 2), (Commons.B_HEIGHT / 2));
    // Draw message to restart game
    small = new Font("Helvetica", Font.BOLD, 12);
    fm = getFontMetrics(small);
    g.setFont(small);
    g.drawString(msg2, ((Commons.B_WIDTH - fm.stringWidth(msg2)) / 2), (Commons.B_HEIGHT / 2) + 20);
  }

  /**
   * @brief Draw navigation
   * @param g
   * @return void
   */
  private void drawNavigation(Graphics g) {
    g.setColor(Color.gray);
    g.fillRect(0, Commons.B_HEIGHT - Commons.NAVIGATION_HEIGHT, Commons.B_WIDTH, Commons.NAVIGATION_HEIGHT);

    String msg = "P = Pause";
    Font small = new Font("Helvetica", Font.BOLD, 12);
    FontMetrics fm = getFontMetrics(small);
    g.setColor(Color.black);
    g.setFont(small);
    g.drawString(msg, ((Commons.B_WIDTH - fm.stringWidth(msg)) / 2), Commons.B_HEIGHT -
        (Commons.NAVIGATION_HEIGHT / 2) + 5);
  }

  /**
   * @brief Action performed
   * @param e ActionEvent
   * @return void
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    if (inGame) {
      checkApple();
      checkCollision();
      snake.move(leftDirection, rightDirection, upDirection, downDirection);
    }
    repaint();
  }

  /**
   * @brief Check apple
   *        This method check if the snake eats the apple, increase the size of
   *        the snake and locate a new apple
   * @return void
   */
  private void checkApple() {
    if (x[0] == appleX && y[0] == appleY) {
      snake.setDots(dots++);
      locateApple(); // Locate new apple
    }
  }

  /**
   * @brief Check if the snake collides with itself or with the board
   * @return void
   */
  private void checkCollision() {
    // Check if the snake collides with itself
    for (int i = dots; i > 0; i--) {
      if ((dots > 4) && (x[0] == x[i]) && (y[0] == y[i])) {
        collision();
      }
    }
    // Check if the snake collides with the board
    if (x[0] >= Commons.B_WIDTH)
      collision();
    if (x[0] < 0)
      collision();
    if (y[0] >= Commons.B_HEIGHT - Commons.NAVIGATION_HEIGHT)
      collision();
    if (y[0] < 0)
      collision();
    if (lives == 0)
      inGame = false;
    // Stop timer if the game is over
    if (!inGame)
      timer.stop();
  }

  /**
   * @brief Key adapter
   *        This class is used to control the snake with the keyboard
   * @param e KeyEvent
   * @return void
   */
  private class TAdapter extends KeyAdapter {
    /**
     * @brief Key pressed
     * @param e KeyEvent
     * @return void
     */
    @Override
    public void keyPressed(KeyEvent e) {
      int key = e.getKeyCode();

      // Control the snake with the keyboard
      if ((key == KeyEvent.VK_LEFT) && !rightDirection) {
        leftDirection = true;
        upDirection = false;
        downDirection = false;
      }

      if ((key == KeyEvent.VK_RIGHT) && !leftDirection) {
        rightDirection = true;
        upDirection = false;
        downDirection = false;
      }

      if ((key == KeyEvent.VK_UP) && !downDirection) {
        upDirection = true;
        leftDirection = false;
        rightDirection = false;
      }

      if ((key == KeyEvent.VK_DOWN) && !upDirection) {
        downDirection = true;
        leftDirection = false;
        rightDirection = false;
      }

      if ((key == KeyEvent.VK_SPACE) && !inGame) {
        restartGame();
      }

      if ((key == KeyEvent.VK_P) && !inPause && inGame) {
        pauseGame();
      }

      if ((key == KeyEvent.VK_R) && inPause) {
        resumeGame();
      }

      if ((key == KeyEvent.VK_S) && inMenu) {
        initGame();
      }
    }
  }
}