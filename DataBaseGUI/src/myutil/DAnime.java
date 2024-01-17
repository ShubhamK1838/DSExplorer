package myutil;

import javax.swing.JPanel;
import javax.swing.Timer;

public class DAnime implements Runnable {

  public int targetX;
  public int targetY;
  boolean setx = true;
  boolean sety = true;
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
      try {
        Thread.sleep(10);
      } catch (Exception e) {}

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
        end();
        return;
      }
      st.revalidate();
      st.repaint();
    }
  }

  public void end() {}
}
