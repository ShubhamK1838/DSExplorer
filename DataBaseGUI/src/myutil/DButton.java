package myutil;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

public class DButton extends JButton {

  Color color = new Color(140, 68, 161);

  public DButton() {
    property();
  }

  public DButton(String str) {
    super(str);
    property();
  }

  public void property() {
    this.setPreferredSize(new Dimension(74, 35));
    this.setBackground(new Color(116, 108, 188));
    this.setForeground(Color.black);
    this.setBorder(new BevelBorder(10));
    this.setFont(new Font(Font.MONOSPACED, Font.BOLD, 16));
    this.setBorder(new EmptyBorder(0, 0, 0, 0));
  }

  DButton This;

  public DButton(String st, int i) {
    super(st);
    This = this;
    property();
    this.setPreferredSize(new Dimension(120, 30));
    //		this.setBackground(new Color(157,255,249));
    this.setBackground(new Color(116, 108, 188));
    this.setForeground(Color.black);
    this.setFont(new Font(Font.MONOSPACED, Font.BOLD, 16));
    this.setBorder(BorderFactory.createLineBorder(Color.white, 2));
    this.addMouseListener(
        new MouseAdapter() {
          public void mouseEntered(MouseEvent e) {
            This.setBackground(new Color(158, 27, 84));
            This.setForeground(Color.white);
          }

          public void mouseExited(MouseEvent e) {
            This.setBackground(new Color(116, 108, 188));
            This.setForeground(Color.black);
          }
        }
      );
  }
}
