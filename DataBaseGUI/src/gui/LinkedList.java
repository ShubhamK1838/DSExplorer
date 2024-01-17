package gui;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;
import myutil.*;

public class LinkedList extends DS {

  JPanel operations = MainFrame.operations;
  JPanel status = MainFrame.status;
  JPanel mn;
  DList head;
  DList nodestatuspanelad;
  DComboBox box;

  private ActionListener actionlistener;
  public DButton operationsbuttons[];

  JButton btn;
  Random rnd;

  public LinkedList() {
    cleanUp();
    rnd = new Random();
    initActionListener();
    // initOperations();
    loadOperations();
    head = new DList();

    hed = head.getHead();
    nodestatuspanelad = head.getHead();
    initStatus();

    this.writeInfo("linkedlist");
  }

  JFrame jp;

  public void initActionListener() {
    actionlistener =
      new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          String cmd = e.getActionCommand();

          if (cmd.equals("Run")) {
              runCommand();
          } else if (cmd.equals("Status")) {

          } 

          MainFrame.currentcontainer.revalidate();
        }
      };
  }

  public void runCommand()
  {
    String command = box.getSelectedItem().toString();

      if(command.contains("Insert"))
      {
        insertNode(command);
      }else 
      {
        deleteNode(command); 
      }

    initNodes();

  }


  public void insertNode(String command) {

    if (command.contains("Insert")) {
      String data = UserIn.getStr("Enter The Data");
      if (data == null) return;
      if (command.equals("Insert First")) {
        head.insFirst(data);
      } else if (command.equals("Insert End")) {
        head.insEnd(data);
      } else if (command.equals("Insert After")) {
        String after = UserIn.getStr("Enter the data  which after you insert");
        if (after == null) return;
        head.insAfter(after, data);
      }
    }

  }
  public void deleteNode(String command)
  {
      if(command.equals("Delete First"))
      {
;        head.deleteFirst();
      }else if(command.equals("Delete End"))
      {
        head.deleteEnd(); 
      }else if(command.equals("Delete After"))
      {
        String after = UserIn.getStr("Enter the data  which after you Delete");
        if(after==null) return; 
        head. deleteAfter(after); 
      
      }
  }

  public void initNodes() {
    DNode n;
    DList l = head.getHead();
    mn.removeAll();
    mn.repaint(); 
    while (l != null) {
      n = new DNode().initNode(l);
      l.node = n;
      mn.add(n);
      l = l.getNext();
    }
  }

  int x = 20;

  public void refresh() {
    status.repaint();
    operations.repaint();
    status.revalidate();
    operations.revalidate();
  }

  public void cleanUp() {
    operations.removeAll();
    DList.list = null;
    status.removeAll();
  }

  @Override
  public void loadOperations() {
    box =
      new DComboBox(
        new String[] {
          
          "Delete First",
          "Delete After",
          "Delete End",
          "Insert First",
          "Insert After",
          "Insert End",
        }
      );
    // box.setSize(140, 36);
    String oper[] = new String[] { "Run", "Status", "Length", "Mid" };

    operationsbuttons = new DButton[oper.length + 1];
    for (int i = 0; i < oper.length; i++) {
      operationsbuttons[i] = new DButton(oper[i]);
      operationsbuttons[i].addActionListener(actionlistener);
      operations.add(operationsbuttons[i]);
    }
    operations.add(box);
    operations.repaint();
  }

  public void say() {
    DList l = head.getHead();

    while (l != null) {
      System.out.println(l.getInfo() + "\t" + l.node);
      l = l.getNext();
    }
  }

  DList hed;

  public int getRandom() {
    return rnd.nextInt(0, 254);
  }

  public void initStatus() {
    status.removeAll();
    status.setLayout(new BorderLayout());

    mn =
      new JPanel() {
        public void paint(Graphics g) {
          super.paint(g);

          hed = head.getHead();
          DNode a;
          DNode b;

          while (hed != null && hed.getNext() != null) {
            a = hed.node;
            b = hed.getNext().node;

            g.setColor(new Color(getRandom(), getRandom(), getRandom()));
            g.drawLine(
              a.getX() + a.getWidth(),
              a.getY(),
              b.getX(),
              b.getY() + 40
            );

            hed = hed.getNext();
          }
        }
      };
    // mn.setBackground(Color.red);
    mn.setLayout(new FlowLayout(1, 60, 70));

    jsp =
      new JScrollPane(
        mn,
        JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
        JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
      );
    mn.setPreferredSize(
      new Dimension(status.getWidth() - 10, status.getHeight() + 1000)
    );

    status.add(jsp, BorderLayout.CENTER);
  }

  JScrollPane jsp;
}
