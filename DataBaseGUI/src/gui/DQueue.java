package gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import myutil.*;

public class DQueue extends DS implements QueueOperations {

  private int size;
  private int left;
  private int right;
  private JPanel operation;
  private JPanel status;
  private ArrayList<Object> list;
  private DButton[] buttons;
  private Element tmpele;
  private int coY;
  private int coX;
  private int max = 27;
  private JPanel leftp;
  private JPanel rightp;
  private Element empty;

  public DQueue() {
    
    initMainFrame();
    initAction();
    loadOperations(); 
    initLeft();
    initRight();
    
    writeInfo("queue"); 
  }

  
  @Override
  public void initAction() {
    actions =
      new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          String sc = e.getActionCommand();
          if (sc.equals("Create")) {
            create();
          } else if (sc.equals("Is Full")) {
            isFull();
          } else if (sc.equals("IsEmpty")) {
            isEmpty();
          } else if (sc.equals("Insert")) {
            Insert();
          } else if (sc.equals("Clear")) {
            clear();
          } else if (sc.equals("Remove")) {
            remove();
          } else if (sc.equals("Peek")) {
            peek();
          } else if (sc.equals("Size")) {
            size();
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

  @Override
  public void loadOperations() {
    initMainFrame();
    String[] op = {
      "Create",
      "Insert",
      "Remove",
      "Peek",
      "Is Full",
      "IsEmpty",
      "Size",
      "Clear",
    };
    super.setOperations(op);
    buttons = new DButton[op.length];
    int ind = 0;
    for (String st : super.operations) {
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
      empty = new Element();
      empty.setSize(90, 30);
      setRight();
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

  public void Insert() {
    if (right != size - 1) {
      String ele = UserIn.getStr("Enter The Element:");
      if (ele == null || ele.length() <= 0) return;
      disableAll();
      tmpele = new Element(ele);
      list.add(tmpele);
      right++;
      if (left == -1) left = 0;
      tmpele.setBounds(460, 200, 90, 30);
      status.add(tmpele);
      DAnimeSort anime = new DAnimeSort() {
        public void end() {
          setLeft();
          setRight();
          enabledAll();
        }
      };
      anime.setElement(tmpele);
      anime.setPanel(status);
      anime.setTargetX(empty.getX());
      anime.setTargetY(empty.getY());
      new Thread(anime).start();
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

  public void peek() {
    if (left < list.size()) ((Element) list.get(left)).setBackground(
        Color.green
      );
  }

  public void isFull() {
    if (size - 1 == right) {
      JOptionPane.showMessageDialog(null, "Queue is Full...");
    } else {
      JOptionPane.showMessageDialog(null, "Queue is Not Full...");
    }
  }

  public void isEmpty() {
    if (right == left) {
      JOptionPane.showMessageDialog(null, "Queue Is Empty...");
    } else {
      JOptionPane.showMessageDialog(null, "Queue Is Not Empty...");
    }
  }

  public void clear() {
    status.removeAll();
    right = 0;
    left = 0;
    list.removeAll(list);
    coX = 10;
    coY = 20;
    setRight();
    refresh();
  }

  public void remove() {
    if (left == right - 1 && left != -1) {
      Element ele = empty;
      leftp.setLocation(ele.getX(), ele.getY() + 40);
      ((Element) list.get(left)).setBackground(Color.red);
      status.add(leftp);
      left++;
      refresh();
    } else if (left < list.size() && left != -1) {
      ((Element) list.get(left)).setBackground(Color.red);
      left++;
      setLeft();
    } else {
      JOptionPane.showMessageDialog(null, "Queue Is Empty...");
    }
  }

  public void size() {
    JOptionPane.showMessageDialog(
      null,
      "Size Is " + size,
      "Size",
      JOptionPane.INFORMATION_MESSAGE
    );
  }

  public void setRight() {
    empty.setLocation(coX, coY);
    updateCo();
    Element ele = empty;
    rightp.setLocation(ele.getX() + 44, ele.getY() + 40);
    status.add(rightp);
    refresh();
  }

  public void setLeft() {
    if (left < list.size()) {
      Element ele = (Element) list.get(left);
      leftp.setLocation(ele.getX(), ele.getY() + 40);
      status.add(leftp);
      refresh();
    }
  }

  public void initLeft() {
    leftp = new JPanel();
    leftp.setBackground(new Color(0, 0, 0, 0));
    leftp.setLayout(new FlowLayout(1, 1, 1));
    ImageIcon ico = new ImageIcon("src\\icons\\forQueue.png");
    JLabel left = new JLabel("LEFT");
    JLabel lbl = new JLabel(ico);
    leftp.add(lbl);
    leftp.add(left);
    leftp.setSize(34, 49);
  }

  public void initRight() {
    rightp = new JPanel();
    rightp.setBackground(new Color(0, 0, 0, 0));
    rightp.setLayout(new FlowLayout(1, 1, 1));
    ImageIcon ico = new ImageIcon("src\\icons\\forQueue.png");
    JLabel left = new JLabel("RIGHT");
    JLabel lbl = new JLabel(ico);
    rightp.add(lbl);
    rightp.add(left);
    rightp.setSize(34, 49);
  }

  public void enabledAll() {
    for (DButton e : buttons) {
      e.setEnabled(true);
    }
    refresh();
  }

  public void disableAll() {
    for (DButton e : buttons) {
      e.setEnabled(false);
    }
    refresh();
  }
}

interface QueueOperations {
  // "Create", "Insert", "Remove", "Peek", "Is Full", "IsEmpty", "Clear"
  public void create();

  public void Insert();

  public void remove();

  public void peek();

  public void isFull();

  public void isEmpty();

  public void clear();

  public void size();
}
