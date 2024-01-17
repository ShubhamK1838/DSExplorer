package myutil;

import gui.MainFrame;
import java.io.File;
import java.io.FileInputStream;

public class SetInfo extends Thread {

  public static boolean crtPos;
  public static SetInfo cur = null;
  String name;
  public static int speed = 20;
  int time = 0;

  public void setNameF(String name) {
    this.name = name;
  }

  public void run() {
    init();
  }

  public String getNameF() {
    return name;
  }

  public void init() {
    cur = this;
    name = getNameF();
    try {
      speed = 20;
      MainFrame.info.setText("");
      String path = "src\\storage\\"+ name;
      FileInputStream inp = new FileInputStream(path);

      int r;
      crtPos = true;

      while ((r = inp.read()) != -1) {
        if (cur != this) return;
        MainFrame.info.append("" + ((char) r));

        MainFrame.info.setCaretPosition(
          MainFrame.info.getDocument().getLength()
        );
        if (time % 500 == 0) {
          if (speed > 2) speed -= 2;
        }
        Thread.sleep(speed);
        time += speed;
      }
      MainFrame.info.setCaretPosition(0);
      inp.close();
    } catch (Exception e) {
      System.out.println("error in file handling\n" + e);
    }
  }
}
