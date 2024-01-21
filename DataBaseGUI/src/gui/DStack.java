package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import myutil.DButton;
import myutil.Element;
import myutil.UserIn;

public class DStack extends DS implements StackOperations {

  private static int checkob = 0;
  private JPanel operation = MainFrame.operations;
  private DButton buttons[];
  // private ActionListener actions;
  private JPanel st = MainFrame.status;
  private ArrayList<Object> list;
  private JPanel jp = new JPanel();
  private int max = 30;
  private int size = 0;
  private int x = 70;
  private int y = 200;
  private int stepx = 90;
  private int stepy = 70;
  private boolean sety = true;
  private boolean setx = true;
  private Element peeks = null;
  private int weightTime;

  public DStack() {
    MainFrame.info.removeAll();
    st.setLayout(null);
    initTop();
    initAction();
    operations();
    initTop();
    st.revalidate();
    st.repaint();
    writeInfo("stack"); 
  }

  public void initAction() {
    actions =
      new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          String sc = e.getActionCommand();
          if (sc.equals("Push")) {
            push();
            if (peeks != null) {
              peeks.setBackground(new Element().getBackground());
            }
          } else if (sc.equals("Pop")) pop(); else if (
            sc.equals("Peek")
          ) peek(); else if (sc.equals("Create")) create(); else if (
            sc.equals("Clear")
          ) clear(); else if (sc.equals("Empty")) isEmpty(); else if (
            sc.equals("Update")
          ) update(); else if (sc.equals("Is Full")) isFull(); else if (
            sc.equals("Demo")
          ) demo();
        }
      };
  }

  public void operations() {
    MainFrame.operations.removeAll();
    MainFrame.status.removeAll();
    String[] op = {
      "Create",
      "Push",
      "Pop",
      "Peek",
      "Is Full",
      "Empty",
      "Clear",
    };
    buttons = new DButton[op.length];
    int ind = 0;
    for (String st : op) {
      buttons[ind] = new DButton(st);
      buttons[ind].addActionListener(actions);
      operation.add(buttons[ind]);
      if (buttons[ind].getText().equals("Create")) {
        ind++;
        continue;
      }
      buttons[ind++].setEnabled(false);
    }
    MainFrame.operations.revalidate();
    MainFrame.operations.repaint();
  }

  public void initTop() {
    jp.setLayout(null);
    jp.setSize(40, 60);
    ImageIcon ico = new ImageIcon("src\\icons\\rightTop.png");
    JLabel top = new JLabel("Top");
    JLabel lbl = new JLabel(ico);
    top.setBounds(2, 7, 25, 16);
    lbl.setBounds(25, 2, 30, 30);
    top.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
    jp.add(lbl);
    jp.add(top);
    jp.setSize(60, 30);
  }

  public void disableButtons() {
    for (DButton bt : buttons) if (bt.getText() != "Push") bt.setEnabled(false);
  }

  public void enaButtons() {
    for (DButton bt : buttons) if (bt.getText() != "Push") bt.setEnabled(true);
  }

  public void push() {
    if (list.size() < size) {
      String str = UserIn.getStr("Enter Value");
      if (str == null || str.length() <= 0) return;
      Element animeEle = new Element();
      animeEle.setText(str);
      animeEle.setBounds(190 + x, 10, 90, 30);
      weightTime = (str != null) ? str.length() : 3;
      st.add(animeEle);
      trd animeThread;
      animeThread = new trd();
      animeThread.targetX = x;
      animeThread.targetY = y;
      animeThread.animeEle = animeEle;
      new Thread(animeThread).start();
      y = y - 34;
      if (y < 30) {
        y = 200;
        x += 180;
      }

      list.add(str);
    } else {
      JOptionPane.showMessageDialog(
        null,
        "Stack Is full",
        "Message",
        JOptionPane.WARNING_MESSAGE
      );
    }
  }

  public void pop() {
    if (list.size() > 0) {
      Element e = (Element) st.getComponent(list.size() - 1);
      list.remove(list.size() - 1);
      st.remove(e);

      if (list.size() >= 1) {
        e = (Element) st.getComponent(list.size() - 1);
        jp.setLocation(e.getX() - 60, e.getY());
        y = e.getY();
        x = e.getX();
        y = y - 34;
        if (y < 30) {
          y = 200;
          x += 180;
        }
      } else {
        st.remove(jp);
        clear();
      }
    }
    st.revalidate();
    st.repaint();
  }

  public void peek() {
    if (list.size() >= 1) {
      try {
        peeks = (Element) st.getComponent(list.size() - 1);
      } catch (Exception ew) {
        peeks = (Element) st.getComponent(list.size() - 2);
      }
      peeks.setOpaque(true);
      peeks.setBackground(Color.red);
      st.revalidate();
      st.repaint();
    }
  }

  public void create() {
    int len = UserIn.getInt("Enter Size  , The Max Size is : " + max) - 1;
    if (len > 0 && len < max) {
      clear();
      for (DButton ele : buttons) {
        ele.setEnabled(true);
      }
      list = new ArrayList<Object>(len);
      size = len;
    } else {
      JOptionPane.showMessageDialog(
        null,
        "Wrong Input ",
        "Message",
        JOptionPane.ERROR_MESSAGE
      );
    }
  }

  public void isFull() {
    int size = list.size();
    if (size == this.size) JOptionPane.showMessageDialog(
      st,
      "Stack Is Full...",
      "Is Full",
      JOptionPane.INFORMATION_MESSAGE
    ); else JOptionPane.showMessageDialog(
      st,
      "Stack Is Not Full...",
      "Is Full",
      JOptionPane.INFORMATION_MESSAGE
    );
  }

  public void isEmpty() {
    int size = list.size();
    if (size == 0) JOptionPane.showMessageDialog(
      st,
      "Stack Is  Empty...",
      "Is Full",
      JOptionPane.INFORMATION_MESSAGE
    ); else JOptionPane.showMessageDialog(
      st,
      "Stack Is Not Empty...",
      "Is Full",
      JOptionPane.INFORMATION_MESSAGE
    );
  }

  public void clear() {
    if (list != null) list.clear();
    st.removeAll();
    x = 70;
    y = 200;
    st.repaint();
    st.revalidate();
  }

  public void demo() {
    Random rn = new Random();
    for (int i = list.size(); i < size; i++) {
      list.add(Integer.toString(rn.nextInt(99)));
    }
  }

  public void update() {}

  class trd implements Runnable {

    int targetX = 0;
    int targetY = 0;
    boolean setx = true;
    boolean sety = true;
    Timer timer;
    Element animeEle;

    public synchronized void run() {
      anime();
      timer.start();
    }

    public void anime() {
      timer =
        new Timer(
          12,
          new ActionListener() {
            public void actionPerformed(ActionEvent e) {
              disableButtons();
              if (setx) {
                if (animeEle.getX() != targetX) {
                  if (targetX < animeEle.getX()) {
                    animeEle.setLocation(animeEle.getX() - 2, animeEle.getY());
                  } else if (targetX > animeEle.getX()) {
                    animeEle.setLocation(animeEle.getX() + 2, animeEle.getY());
                  }
                } else setx = false;
              } else if (sety) {
                if (targetY != animeEle.getY()) {
                  if (targetY < animeEle.getY()) {
                    animeEle.setLocation(animeEle.getX(), animeEle.getY() - 2);
                  } else if (targetY > animeEle.getY()) {
                    animeEle.setLocation(animeEle.getX(), animeEle.getY() + 2);
                  }
                } else sety = false;
              }
              if (setx == false && sety == false) {
                jp.setLocation(targetX - 60, targetY);
                st.add(jp);
                sety = true;
                setx = true;
                enaButtons();
                timer.stop();
              }
              st.revalidate();
              st.repaint();
            }
          }
        );
    }
  }

  public void status() {}
}

interface StackOperations {
  void push();

  void pop();

  void peek();

  void create();

  void isFull();

  void isEmpty();

  void clear();

  void demo();

  void update();

  void status();
}
