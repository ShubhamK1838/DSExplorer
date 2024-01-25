package gui;

import java.awt.event.ActionListener;
import javax.swing.JPanel;
import myutil.DButton;
import myutil.SetInfo;

public class DS {

  protected ActionListener actions;
  protected JPanel operations_panel;
  protected DButton buttons[];
  protected String[] operations;
  protected JPanel status;

  public DS() { // static content initialization
    status = MainFrame.status;
    status.removeAll();
    operations_panel = MainFrame.operations;
    operations_panel.removeAll(); 
  }

  public void setOperations(String operations[]) {
    this.operations = operations;
  }


  
  public void writeInfo(String name) {
    SetInfo setinfo = new SetInfo();
    setinfo.setNameF(name);
    setinfo.start();
  }

  public void loadOperations() {
  }

  public void initAction() {
  }

  public void updateFromDS() {
    MainFrame.operations.revalidate();
    MainFrame.operations.repaint();
  }
}
