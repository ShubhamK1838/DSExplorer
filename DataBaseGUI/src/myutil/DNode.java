package myutil;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class DNode extends JPanel {

  public void DNode() {
    this.setBackground(new Color(205, 196, 230));
  }

  public void nodeColor(Color cl) {
    inf.setBackground(cl);
    nxt.setBackground(cl);
    self.setBackground(cl);
  }

  JPanel inf;
  JPanel nxt;
  JPanel self;

  public DNode initNode(DList tmp) {
    this.setLayout(new BorderLayout());
    this.setSize(100, 60);

    inf = new JPanel();
    inf.add(new JLabel(tmp.info));
    // inf.setPreferredSize(new Dimension(50, 35));

    nxt = new JPanel();
    nxt.add(new JLabel("" + (tmp.next == null ? null : tmp.next.hashCode())));
    // nxt.setPreferredSize(new Dimension(50, 35));

    self = new JPanel();
    self.add(new JLabel("" + (tmp.hashCode())));
    // self.setPreferredSize(new Dimension(47, 30));

    Border b = new LineBorder(Color.red);

    inf.setBorder(b);
    nxt.setBorder(b);
    self.setBorder(b);

    self.setBackground(new Color(205, 196, 230));
    nxt.setBackground(self.getBackground());
    inf.setBackground(nxt.getBackground());

    inf.setToolTipText("info : " + tmp.info);
    nxt.setToolTipText(
      "Next Node : " + (tmp.next == null ? null : tmp.next.hashCode())
    );
    self.setToolTipText("Address : " + this.hashCode());

    this.setBackground(nxt.getBackground());
    this.add(inf, BorderLayout.WEST);
    this.add(nxt, BorderLayout.EAST);
    this.add(self, BorderLayout.SOUTH);

    return this;
  }

  public static void main(String ar[]) {
    JFrame f = new JFrame();
    f.setSize(400, 400);
    f.setLocationRelativeTo(null);
    f.setDefaultCloseOperation(3);
    f.setLayout(null);

    DNode n = new DNode();

    f.setVisible(true);
  }
}
