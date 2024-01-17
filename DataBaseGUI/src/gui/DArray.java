package gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import myutil.DButton;
import myutil.DElement;
import myutil.UserIn;

public class DArray extends DS {

  private ArrayList<Object> list;
  private JPanel st = MainFrame.status;
  private int size;
  public static Timer tt;

  int max = 33;

  public DArray() {
    super(); 
    st.setLayout(new FlowLayout(0, 3, 20));
    st.setAlignmentX(10);
    initAction();
    setOperations(
      new String[] {
        "Create",
        "Insert",
        "Remove",
        "Update",
        "Reverse",
        "Is Full",
        "Empty",
        "Clear",
        "Fill",
      }
    );
    loadOperations();
    st.revalidate();
    writeInfo("array");
  }

  @Override
  public void initAction() {
    actions =
      new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          String sc = e.getActionCommand();
          if (sc.equals("Insert")) insert(); else if (
            sc.equals("Remove")
          ) remove(); else if (sc.equals("Reverse")) reverse(); else if (
            sc.equals("Create")
          ) create(); else if (sc.equals("Clear")) list.clear(); else if (
            sc.equals("Empty")
          ) isEmpty(); else if (sc.equals("Update")) update(); else if (
            sc.equals("Is Full")
          ) isFull(); else if (sc.equals("Fill")) demo();

          if (!(size <= 0)) status();
        }
      };
  }

  @Override
  public void loadOperations() {
    MainFrame.operations.removeAll();
    buttons = new DButton[operations.length];

    int ind = 0;
    for (String st : operations) {
      buttons[ind] = new DButton(st);
      buttons[ind].addActionListener(actions);
      operations_panel.add(buttons[ind]);
      if (buttons[ind].getText().equals("Create")) {
        ind++;
        continue;
      }
      buttons[ind++].setEnabled(false);
    }
  }

  void update() {
    int ind = UserIn.getInt("Enter The Index of Element ");
    if (ind < list.size()) {
      String ele = UserIn.getStr("Enter New Value");
      list.set(ind, ele);
    } else {
      JOptionPane.showMessageDialog(st, "Invalid Index Number");
    }
  }

  public void remove() {
    String ele = UserIn.getStr("Enter The Index");
    if (ele != null && Integer.parseInt(ele) < list.size()) list.remove(
      Integer.parseInt(ele)
    );
  }

  public void insert() {
    String ele = JOptionPane.showInputDialog("Enter The Ele");
    if (ele != null && this.size > list.size()) list.add(
      ele
    ); else JOptionPane.showMessageDialog(
      st,
      "Array Is Full...",
      "Message",
      JOptionPane.WARNING_MESSAGE
    );
  }

  public void create() {
    int size = UserIn.getInt("Enter The Size");

    if (size <= max) {
      this.size = size;
      list = new ArrayList<Object>(size);
      for (DButton bt : buttons) {
        bt.setEnabled(true);
      }
    } else {
      if (size > max) {
        JOptionPane.showMessageDialog(st, "Max Size Is : " + max);
      }
    }
  }

  // public void operations() {
  //   MainFrame.operations.removeAll();

  //   String[] op = {
  //     "Create",
  //     "Insert",
  //     "Remove",
  //     "Update",
  //     "Reverse",
  //     "Is Full",
  //     "Empty",
  //     "Clear",
  //     "Demo",
  //   };
  //   buttons = new DButton[op.length];
  //   int ind = 0;
  //   for (String st : op) {
  //     buttons[ind] = new DButton(st);
  //     buttons[ind].addActionListener(actions);
  //     operation.add(buttons[ind]);
  //     if (buttons[ind].getText().equals("Create")) {
  //       ind++;
  //       continue;
  //     }
  //     buttons[ind++].setEnabled(false);
  //   }
  //   MainFrame.operations.revalidate();
  //   MainFrame.operations.repaint();
  // }

  public void status() {
    if (list.size() < 0) return;
    DElement elm;
    st.removeAll();
    for (int i = 0; i < size; i++) {
      if (i < list.size()) {
        elm = new DElement((String) list.get(i), Integer.toString(i));
        elm.setPreferredSize(new Dimension(80, 60));
        st.add(elm);
      } else {
        elm = new DElement("", Integer.toString(i));
        elm.setPreferredSize(new Dimension(80, 60));
        st.add(elm);
      }
    }
    st.revalidate();
    st.repaint();
  }

  public void reverse() {
    Object tmp;
    int left = 0;
    int right = list.size() - 1;
    while (left < right) {
      tmp = list.get(left);
      list.set(left, list.get(right));
      list.set(right, tmp);
      right--;
      left++;
    }
  }

  public void demo() {
    Random rn = new Random();
    for (int i = list.size(); i < size; i++) {
      list.add(Integer.toString(rn.nextInt(99)));
    }
  }

  public void isFull() {
    if (list.size() == size) JOptionPane.showMessageDialog(
      st,
      "Array Is Full...",
      "Is Full",
      JOptionPane.INFORMATION_MESSAGE
    ); else JOptionPane.showMessageDialog(
      st,
      "Array Is Not Full...",
      "Is Full",
      JOptionPane.INFORMATION_MESSAGE
    );
  }

  public void isEmpty() {
    if (list.size() == 0) JOptionPane.showMessageDialog(
      st,
      "Array Is Empty...",
      "Is Empty",
      JOptionPane.INFORMATION_MESSAGE
    ); else JOptionPane.showMessageDialog(
      st,
      "Array Is Not Empty...",
      "Is Empty",
      JOptionPane.INFORMATION_MESSAGE
    );
  }
}
