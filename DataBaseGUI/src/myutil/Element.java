package myutil;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Element extends JLabel {

  public Element() {
    this.setBackground(new Color(205, 196, 230));
    this.setForeground(Color.black);
    this.setOpaque(true);
    this.setPreferredSize(new Dimension(70, 30));
    this.setFont(new Font("Arial", Font.PLAIN, 14));
    this.setHorizontalAlignment(CENTER);
  }

  public Element(String st) {
    this();
    this.setText(st);
  }

  public Element(ImageIcon e) {
    super(e);
    this.setBackground(new Color(205, 196, 230));
    this.setForeground(Color.black);
    this.setOpaque(true);
    this.setPreferredSize(new Dimension(70, 30));
    this.setFont(new Font("Arial", Font.PLAIN, 14));
    this.setHorizontalAlignment(CENTER);
  }
}
