package myutil;

import java.awt.*;
import javax.swing.*;

public class DElement extends JPanel {

  String ele;
  String ind;
  public Element label;
  JLabel index;

  public DElement(String ele, String ind) {
    this.setLayout(null);
    this.ele = ele;
    this.ind = ind;
    this.setSize(80, 60);
    label = new Element();
    label.setText(ele);
    label.setBounds(0, 0, 80, 35);
    index = new JLabel(ind);
    index.setBounds(36, 41, 20, 15);
    index.setFont(new Font("Arial", Font.BOLD, 15));
    this.add(index);
    this.add(label);
    this.revalidate();
  }

  public DElement(ImageIcon ico) {
    this.setLayout(null);
    this.setSize(80, 60);
    label = new Element(ico);
    this.add(label);
    this.revalidate();
  }
}
