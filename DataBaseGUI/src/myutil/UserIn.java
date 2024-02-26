package myutil;

import javax.swing.JOptionPane;


public class UserIn {

  public static String getStr(String msg) {
    String str = JOptionPane.showInputDialog(null, msg);
    if (str == null)
      return null;
    if (!(str.isBlank() || str.isEmpty() || str.length() <= 0)) {
      return str;
    } else
      return null;
  }

  public static int getInt(String msg) {
    String str = getStr(msg);

    if ((str != null)) {
      char ch[] = str.toCharArray();
      for (char c : ch) {
        if (!Character.isDigit(c)) {
          return 0;
        }
      }
      return Integer.parseInt(str);
    } else {
      return -1;
    }
  }

}
