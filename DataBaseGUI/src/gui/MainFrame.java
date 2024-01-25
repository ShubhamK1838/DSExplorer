package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import myutil.DButton;
import myutil.SetInfo;

public class MainFrame extends JFrame {

  private JPanel left, right;
  public static JPanel status = new JPanel();
  public static JPanel operations = new JPanel();
  private JPanel areapanel;
  private boolean leftvisible = true;
  private ActionListener actionlistener;
  public static JTextArea info;
  public static MainFrame currentcontainer;
  private boolean homeopen = true;

  JPanel nav;
  MainFrame This;

  Color color = new Color(158, 27, 84);
  Border border = BorderFactory.createLineBorder(Color.white, 2);
  String cur = "";
  ImageIcon ico;

  public MainFrame() {
    This = this;
    currentcontainer = this;
    setIcon(); 
    // this.setUndecorated(true);
    this.setTitle("DSExplorer");
    this.setVisible(true);
    this.setDefaultCloseOperation(3);
    this.setSize(new Dimension(1130, 720));
    this.setMinimumSize(new Dimension(1130, 720));
    this.setResizable(false);
    this.setLocationRelativeTo(null);
    this.setLayout(new BorderLayout());
    
    right = new JPanel();

    nav = new JPanel();
    nav.setPreferredSize(new Dimension(300, 46));
    nav.setBackground(color);
    nav.setBorder(border);
    initActionListener();
    initNav();
    initHome();
    initLeftPanel();

    this.add(left, BorderLayout.WEST);
    this.add(nav, BorderLayout.NORTH);
    this.revalidate();
  }

  public void initNav() {
    nav.setLayout(new FlowLayout(0, 60, 8));

    MouseAdapter mouselistener = new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        String cmd = ((JLabel) e.getComponent()).getText();

        if (cmd.equals("Dashboard")) {
          if (leftvisible == true) {
            This.remove(left);
            leftvisible = false;
          } else {
            leftvisible = true;
            This.add(left, BorderLayout.WEST);
          }
        } else if (cmd.equals("Home")) {
          This.remove(right);
          initHome();
        }

        This.revalidate();
        This.repaint();
      }
    };

    String labels[] = new String[] {
        "DS Explorer",
        "Home",
        "Dashboard",
        "About Us",
        "Contact",
    };
    for (String st : labels) {
      JLabel lbl = new JLabel(st);
      lbl.setFont(new Font("Arial", Font.BOLD, 17));
      lbl.setForeground(new Color(246, 237, 248));
      lbl.setCursor(new Cursor(Cursor.HAND_CURSOR));
      lbl.addMouseListener(mouselistener);
      nav.add(lbl);
    }
  }

  public void initLeftPanel() { // it initializes dashboard
    left = new JPanel();
    left.setBorder(border);
    left.setBackground(new Color(158, 27, 84));
    left.setPreferredSize(new Dimension(160, this.getHeight()));
    left.setLayout(new FlowLayout(1, 20, 20));

    String menueop[] = {
        "Array",
        "Stack",
        "Queue",
        "Linked L",
        "Sorting",
        "Searching",
    };
    DButton[] menu = new DButton[menueop.length];
    DButton ele;
    int i = 0;
    for (String st : menueop) {
      ele = new DButton(st, 1);
      ele.setCursor(new Cursor(Cursor.HAND_CURSOR));
      left.add(ele);
      ele.addActionListener(actionlistener);
      menu[i] = ele;
    }
    left.repaint();
    left.revalidate();
  }

  public void initActionListener() {
    actionlistener = new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        String str = e.getActionCommand();

        if (homeopen) {
          initRightPanel();
          homeopen = false;
        }

        if (str.equals("Linked L") && !cur.equals(str)) {
          cur = str;
          new LinkedList();
        } else if (str.equals("Stack") && !cur.equals(str)) {
          cur = str;
          new DStack();
        } else if (str.equals("Queue") && !cur.equals(str)) {
          cur = str;
          new DQueue();
        } else if (str.equals("Home") && !cur.equals(str)) {
          cur = str;
          initHome();
        } else if (str.equals("Sorting") && !cur.equals(str)) {
          cur = str;
          new DSorting();
        } else if (str.equals("Searching") && !cur.equals(str)) {
          cur = str;
          new DSearch();
        } else if (str.equals("Array") && !cur.equals(str)) {
          cur = str;
          new DArray();
        }

        This.revalidate();
      }
    };
  }

  public void initOp() {
    operations.removeAll();
    operations.revalidate();
    operations.repaint();
  }

  public void initRightPanel() {
    if (right != null) {
      right.removeAll();
      right.repaint();
      right.revalidate();
    }
    right.setLayout(new BorderLayout());
    right.setBorder(border);
    areapanel = new JPanel();
    areapanel.setBackground(Color.gray);
    areapanel.setPreferredSize(new Dimension(1130 - 160, 300));
    areapanel.setLayout(new BorderLayout());

    info = new JTextArea();
    info.setLineWrap(true);
    info.setEditable(false);
    info.setWrapStyleWord(rootPaneCheckingEnabled);
    info.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 16));
    info.setForeground(Color.black);
    info.setBackground(new Color(205, 196, 230));
    info.addMouseListener(
        new MouseAdapter() {
          public void mouseClicked(MouseEvent e) {
            SetInfo.speed = 0;
          }
        });

    JScrollPane jsp;
    areapanel.add(
        jsp = new JScrollPane(
            info,
            JScrollPane.VERTICAL_SCROLLBAR_NEVER,
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER));
    jsp.setBorder(new EmptyBorder(0, 0, 0, 0));
    right.add(areapanel, BorderLayout.SOUTH);
    operations = new JPanel();
    operations.setLayout(new FlowLayout(1, 20, 20));
    operations.setPreferredSize(new Dimension(right.getWidth(), 80));
    operations.setBackground(new Color(205, 196, 230));
    operations.setBorder(border);
    status.setPreferredSize(new Dimension(1130 - 140, 600));

    right.add(operations, BorderLayout.NORTH);
    right.add(status, BorderLayout.CENTER);

    This.add(right, BorderLayout.CENTER);
    This.revalidate();
  }
  public void setIcon()
  {
    ImageIcon icon=new ImageIcon("src\\icons\\icon.png"); 
    this.setIconImage(icon.getImage());
  }

  public void initHome() {
    cur = "";
    homeopen = true;
    right.removeAll();
    right.setLayout(new BorderLayout());
    right.setBackground(Color.red);
    ImageIcon icon = new ImageIcon("src\\icons\\DS2.gif");
    JLabel lb = new JLabel(icon);
    lb.setBounds(40, 40, 300, 300);
    right.revalidate();
    right.repaint();
    This.add(right);
  }
}
