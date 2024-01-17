package myutil;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

public class DComboBox extends JComboBox {

  public DComboBox() {
    super();
    this.setBackground(new Color(205, 196, 230));
  }

  public DComboBox(Object[] ob) {
    super();
    for (Object ele : ob) {
      this.insertItemAt(ele, 0);
    }
    this.setSelectedIndex(0);
    this.setPreferredSize(new Dimension(140, 35));
    this.setBackground(new Color(116, 108, 188));
    this.setForeground(Color.black);
    this.setBorder(new BevelBorder(10));
    this.setFont(new Font(Font.MONOSPACED, Font.BOLD, 16));
    this.setBorder(new EmptyBorder(0, 0, 0, 0));
    this.setCursor(new Cursor(Cursor.HAND_CURSOR));
  }
}
