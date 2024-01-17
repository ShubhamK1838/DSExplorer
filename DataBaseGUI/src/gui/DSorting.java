package gui;

import java.awt.Color;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import myutil.*;

public class DSorting extends DS {

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
  private DAnimeSort anime1;
  private Thread targetEle1;
  private Thread targetEle2;
  public DSorting This;
  public int i = 0;
  public int j = 1;
  private boolean sort = true;
  public static boolean say;
  public int gap;
  private DComboBox searches;

  public DSorting() {
    This = this;
    initMainFrame();
    initAction();
    initOperations();
    
    writeInfo("sortinfo");

  }

  public void initAction() {
    actions =
      new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          String sc = e.getActionCommand();
          if (sc.equals("Create")) {
            create();
          } else if (sc.equals("Sort")) {
            aplySearch();
          } else if (sc.equals("Insert")) {
            Insert("user");
          } else if (sc.equals("Clear")) {
            clear();
          } else if (sc.equals("Demo")) {
            Demo();
          } else if (sc.equals("S++")) {
            if (DAnimeSort.speed > -2) {
              DAnimeSort.speed--;
            }
          } else if (sc.equals("S--")) {
            DAnimeSort.speed++;
          }
        }
      };
  }

  public void aplySearch() {
    String srch = (String) searches.getSelectedItem();

    if (srch.equals("Exchange")) {
      i = 0;
      j = 1;
      exchange();
    } else if (srch.equals("Bubble")) {
      i = 0;
      j = 0;
      bubble();
    } else if (srch.equals("Insertion")) {
      insertionSort();
    }
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
    String[] op = { "Create", "Insert", "Sort", "Clear", "Demo", "S++", "S--" };
    searches =
      new DComboBox(new String[] { "Bubble", "Exchange", "Insertion" });
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
    operation.add(searches);
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
      empty = new Element();
      empty.setSize(90, 30);
      list = new ArrayList<Object>(size);
      enabledAll();
      sort = true;
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
      sort = true;
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
    sort = true;
    refresh();
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

  public void bubble() {
    if (j == list.size() || j + 1 == list.size() || sort == false) return;
    Element f, s;
    disableAll();
    f = (Element) list.get(j);
    s = (Element) list.get(j + 1);

    if (Integer.parseInt(f.getText()) > Integer.parseInt(s.getText())) {
      f.setOpaque(true);
      f.setBackground(Color.red);
      s.setOpaque(true);
      s.setBackground(Color.red);

      list.set(j, s);
      list.set(j + 1, f);
      anime = new DAnimeSort();
      anime.setElement(f);
      anime.setPanel(status);
      anime.setTargetX(s.getX());
      anime.setTargetY(s.getY());
      targetEle1 = new Thread(anime);
      targetEle1.start();

      anime1 =
        new DAnimeSort() {
          public void end() {
            while (targetEle1.isAlive()) {}
            updateBubbleSort();
          }
        };
      anime1.setElement(s);
      anime1.setPanel(status);
      anime1.setTargetX(f.getX());
      anime1.setTargetY(f.getY());
      targetEle2 = new Thread(anime1);
      targetEle2.start();
    } else {
      f.setOpaque(true);
      f.setBackground(Color.green);
      s.setOpaque(true);
      s.setBackground(Color.green);
      if (DAnimeSort.speed > 0) {
        try {
          Thread.sleep(100);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
      f.setBackground(new Color(205, 196, 230));
      s.setBackground(new Color(205, 196, 230));
      updateBubbleSort();
    }
  }

  public void updateBubbleSort() {
    if (i == list.size() - 1) {
      i = 0;
      j = 1;
      enabledAll();
      sort = false;
      return;
    } else if (j + 1 == list.size() - 1) {
      if (i < list.size() - 1) {
        i++;
        j = 0;
      }
    } else {
      if (j + 2 < list.size()) j++;
    }
    bubble();
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
      targetEle1 = new Thread(anime);
      targetEle1.start();

      anime1 =
        new DAnimeSort() {
          public void end() {
            while (targetEle1.isAlive()) {}
            updateExchangeSort();
          }
        };
      anime1.setElement(s);
      anime1.setPanel(status);
      anime1.setTargetX(f.getX());
      anime1.setTargetY(f.getY());
      targetEle2 = new Thread(anime1);
      targetEle2.start();
    } else {
      f.setOpaque(true);
      f.setBackground(Color.green);
      s.setOpaque(true);
      s.setBackground(Color.green);
      if (DAnimeSort.speed > 0) {
        try {
          Thread.sleep(100);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      f.setBackground(new Color(205, 196, 230));
      s.setBackground(new Color(205, 196, 230));
      updateExchangeSort();
    }
  }

  public void updateExchangeSort() {
    if (i == list.size() - 2) {
      i = 0;
      j = 1;
      sort = false;
      enabledAll();
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

  public void insertionSort() {
    if (i == list.size() - 1 || list.size() == 0 || sort == false) {
      i = 0;
      j = 1;
      enabledAll();
      sort = false;
      return;
    }

    Element current = (Element) list.get(j);
    int key = Integer.parseInt(current.getText());
    int prevIndex = j - 1;

    if (
      prevIndex >= 0 &&
      Integer.parseInt(((Element) list.get(prevIndex)).getText()) > key
    ) {
      disableAll();
      Element prevElement = (Element) list.get(prevIndex);

      current.setOpaque(true);
      current.setBackground(Color.red);
      prevElement.setOpaque(true);
      prevElement.setBackground(Color.red);

      list.set(j, prevElement);
      list.set(prevIndex, current);

      anime = new DAnimeSort();
      anime.setElement(prevElement);
      anime.setPanel(status);
      anime.setTargetX(current.getX());
      anime.setTargetY(current.getY());
      targetEle1 = new Thread(anime);
      targetEle1.start();

      anime1 =
        new DAnimeSort() {
          public void end() {
            while (targetEle1.isAlive()) {}
            updateInsertionSort();
          }
        };
      anime1.setElement(current);
      anime1.setPanel(status);
      anime1.setTargetX(prevElement.getX());
      anime1.setTargetY(prevElement.getY());
      targetEle2 = new Thread(anime1);
      targetEle2.start();
    } else {
      current.setOpaque(true);
      current.setBackground(Color.green);
      if (DAnimeSort.speed > 0) {
        try {
          Thread.sleep(100);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      current.setBackground(new Color(205, 196, 230));
      updateInsertionSort();
    }
  }

  public void updateInsertionSort() {
    if (j == 0) {
      if (i < list.size() - 1) {
        i++;
        j = i + 1;
      }
    } else {
      j--;
    }
    insertionSort();
  }
}
