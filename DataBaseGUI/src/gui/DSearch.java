package gui;

import java.awt.Color;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import myutil.*;


public class DSearch extends DS{

  private int size;
  private int left;
  private int right = 0;
  private JPanel operation;
  private JPanel status;
  private ArrayList<Object> list;
  private DButton[] buttons;
  private ActionListener actions;
  private Element tmpele;
  private int coY;
  private int coX;
  private int max = 27;
  private Element empty;
  private DAnimeSort anime;
  private boolean sort = true;

  DSearch This;
  int gap;

  public int i = 0;
  public int j = 1;

  public static boolean say;

  public DSearch() {
    This = this;
    initMainFrame();
    initAction();
    initOperations();
    writeInfo("search");

  }

  public void initAction() {
    actions =
      new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          String sc = e.getActionCommand();
          if (sc.equals("Create")) {
            create();
            new TakeList(size); 
          } else if (sc.equals("Insert")) {
            Insert("user");
          } else if (sc.equals("Clear")) {
            clear();
          } else if (sc.equals("Linear")) {
            String st = UserIn.getStr("Enter The Search Value");
            LinearSer ob = new LinearSer();
            ob.ind = 0;
            ob.key = st;
            ob.start();
          } else if (sc.equals("Binary")) {
            String st = UserIn.getStr("Enter The Search Value");
            BinarySer ob = new BinarySer();
            ob.key = st;
            ob.start();
          } else if (sc.equals("Demo")) {
            Demo();
          } else if (sc.equals("S++")) {
            if (DAnimeSort.speed > -2) {
              DAnimeSort.speed--;
            }
          } else if (sc.equals("S--")) {
            if (DAnimeSort.speed > -3) {
              DAnimeSort.speed++;
            }
          }
        }
      };
  }

  public void initMainFrame() {
    operation = MainFrame.operations;
    operation.removeAll();
    operation.repaint();
    status = MainFrame.status;
    status.removeAll();
    status.repaint();
    status.setLayout(null);
  }

  public void refresh() {
    status.revalidate();
    status.repaint();
    operation.revalidate();
    operation.repaint();
  }

  public void initOperations() {
    initMainFrame();
    String[] op = {
      "Create",
      "Insert",
      "Linear",
      "Binary",
      "Insertion",
      "Clear",
      "Demo",
      "S++",
      "S--",
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

    refresh();
  }

  public void create() {
    int tmp = UserIn.getInt("Enter The Size 1-" + max);
    if (tmp >= 1 && tmp <= max) {
      status.removeAll();
      status.repaint();
      coX = 10;
      coY = 20;
      size = tmp;
      left = -1;
      right = 0;
      sort = true;
      empty = new Element();
      empty.setSize(90, 30);
      list = new ArrayList<Object>(size);
      enabledAll();
    } else {
      JOptionPane.showMessageDialog(
        null,
        "Wrong Input",
        "Alert",
        JOptionPane.ERROR_MESSAGE
      );
    }
  }

  public void Demo() {
    for (int i = right; i < size; i++) {
      Insert("a");
    }
  }

  public void Insert(String s) {
    String ele = null;
    if (right != size) {
      if (s.equals("user")) {
        int tmp = UserIn.getInt("Enter The Element:");
        if (tmp == 0) return;
        ele = Integer.toString(tmp);
      } else {
        int rn = new Random().nextInt(1, 1000);
        ele = Integer.toString(rn);
      }
      if (ele == null || ele.length() <= 0) return;
      disableAll();
      tmpele = new Element(ele);
      list.add(tmpele);
      right++;
      if (left == -1) left = 0;
      tmpele.setBounds(460, 200, 90, 30);
      status.add(tmpele);
      anime =
        new DAnimeSort() {
          public void end() {
            enabledAll();
          }

          public void processBetween() {
            disableAll();
          }
        };
      anime.setElement(tmpele);
      anime.setPanel(status);
      anime.setTargetX(coX);
      anime.setTargetY(coY);
      new Thread(anime).start();
      updateCo();
    } else {
      JOptionPane.showMessageDialog(null, "Queue Is Full...");
    }
  }

  public void updateCo() {
    coX = coX + 100;
    if (coX >= 900) {
      coY = coY + 90;
      coX = 10;
    }
  }

  public void clear() {
    status.removeAll();
    right = 0;
    left = 0;
    list.removeAll(list);
    coX = 10;
    coY = 20;
    refresh();
    sort = true;
  }

  public void enabledAll() {
    for (DButton e : buttons) {
      e.setEnabled(true);
    }
    refresh();
  }

  public void disableAll() {
    for (DButton e : buttons) {
      if (
        !(
          e.getText().equals("S++") ||
          e.getText().equals("S--") ||
          e.getText().equals("Stop")
        )
      ) e.setEnabled(false);
    }
    refresh();
  }

  public void exchange() {
    if (i >= list.size() || j >= list.size() || sort == false) return;
    Element f, s;
    disableAll();
    f = (Element) list.get(i);
    s = (Element) list.get(j);

    if (Integer.parseInt(f.getText()) > Integer.parseInt(s.getText())) {
      f.setOpaque(true);
      f.setBackground(Color.red);
      s.setOpaque(true);
      s.setBackground(Color.red);

      list.set(i, s);
      list.set(j, f);
      anime = new DAnimeSort();
      anime.setElement(f);
      anime.setPanel(status);
      anime.setTargetX(s.getX());
      anime.setTargetY(s.getY());
      Thread targetEle1 = new Thread(anime);
      anime.speed = -1;
      targetEle1.start();

      DAnimeSort anime1 = new DAnimeSort() {
        public void end() {
          while (targetEle1.isAlive()) {}
          updateExchangeSort();
        }
      };
      anime1.setElement(s);
      anime1.setPanel(status);
      anime1.setTargetX(f.getX());
      anime1.setTargetY(f.getY());
      anime1.speed = -1;
      Thread targetEle2 = new Thread(anime1);
      targetEle2.start();
    } else {
      updateExchangeSort();
    }
  }

  public void updateExchangeSort() {
    if (i == list.size() - 2) {
      i = 0;
      j = 1;
      enabledAll();
      sort = false;
      return;
    } else if (j == list.size() - 1) {
      if (i + 1 < list.size() - 1) {
        i++;
        j = i + 1;
      }
    } else {
      if (j + 1 < list.size()) j++;
    }
    exchange();
  }

  class BinarySer extends Thread {

    String key;
    int left = 0;
    int right = list.size() - 1;
    int mid;
    boolean say = false;

    public void run() {
      exchange();
      while (sort) {
        try {
          Thread.sleep(10);
        } catch (Exception e) {
          System.out.println(e);
        }
      }
      Element cur = null;

      while (left <= right) {
        mid = (left + right) / 2;
        cur = (Element) list.get(mid);

        if (cur.getText().equals(key)) {
          cur.setOpaque(true);
          cur.setBackground(Color.green);
          refresh();
          try {
            Thread.sleep(2000);
          } catch (Exception e) {}
          cur.setBackground(new Color(205, 196, 230));
          say = true;
          break;
        } else {
          cur.setOpaque(true);
          cur.setBackground(Color.red);
          refresh();
          try {
            Thread.sleep(700);
          } catch (Exception e) {}
          cur.setBackground(new Color(205, 196, 230));
        }

        if (Integer.parseInt(key) > Integer.parseInt(cur.getText())) {
          left = mid + 1;
        } else {
          right = mid - 1;
        }
      }
      if (say) JOptionPane.showMessageDialog(
        null,
        "Key Is Present..."
      ); else JOptionPane.showMessageDialog(null, "Key Is Not Present...");
    }
  }

  class LinearSer extends Thread {

    String key;
    int ind;

    public void run() {
      Element cur = null;
      while (true) {
        if (ind < 0 || ind >= list.size()) return;

        cur = (Element) list.get(ind);

        if (cur.getText().equals(key)) {
          cur.setOpaque(true);
          cur.setBackground(Color.green);
          refresh();
          try {
            Thread.sleep(1400);
          } catch (Exception e) {}
          cur.setBackground(new Color(205, 196, 230));
          return;
        } else {
          cur.setOpaque(true);
          cur.setBackground(Color.red);
          refresh();
          try {
            Thread.sleep(700);
          } catch (Exception e) {}
          cur.setBackground(new Color(205, 196, 230));
          ind++;
        }
      }
    }
  }
}
