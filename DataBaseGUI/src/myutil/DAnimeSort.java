package myutil;

import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.Timer;

public class DAnimeSort implements Runnable {

  public static int speed = 10;
  public int targetX;
  public int targetY;
  boolean setx = true;
  boolean sety = true;
  boolean seth = true;
  Timer timer;
  Element animeEle;
  JPanel st;

  public void setElement(Element ele) {
    animeEle = ele;
  }

  public void setPanel(JPanel jp) {
    st = jp;
  }

  public void setTargetX(int i) {
    targetX = i;
  }

  public void setTargetY(int i) {
    targetY = i;
  }

  public synchronized void run() {
    while (true) {
      if (speed > 0) {
        try {
          Thread.sleep(speed);
        } catch (Exception e) {}
      }

      if (setx) {
        if (animeEle.getX() != targetX) {
          if (targetX < animeEle.getX()) {
            animeEle.setLocation(animeEle.getX() - 2, animeEle.getY());
          } else if (targetX > animeEle.getX()) {
            animeEle.setLocation(animeEle.getX() + 2, animeEle.getY());
          }
        } else setx = false;
      }
      if (sety) {
        if (targetY != animeEle.getY()) {
          if (targetY < animeEle.getY()) {
            animeEle.setLocation(animeEle.getX(), animeEle.getY() - 2);
          } else if (targetY > animeEle.getY()) {
            animeEle.setLocation(animeEle.getX(), animeEle.getY() + 2);
          }
        } else sety = false;
      }
      if (setx == false && sety == false) {
        sety = true;
        setx = true;
        animeEle.setBackground(new Color(205, 196, 230));
        end();
        return;
      }
      processBetween();
      st.revalidate();
      st.repaint();
    }
  }

  public void processBetween() {}

  public void end() {}
}
