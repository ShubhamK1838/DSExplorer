package myutil;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList; 
import javax.swing.*; 



public class TakeList extends JFrame {

    private ActionListener listener; 
    ArrayList<String> list;
    private JPanel panel;
    private DButton submit;
    private JTextField fields[];
    private int size;
    private  TakeList This; 
  
    
    public TakeList(int size) {
      this.size = size;
      this.setSize(600, 600);
      this.setLocationRelativeTo(null);
      this.setLayout(new BorderLayout());
      this.setDefaultCloseOperation(3);
      This=this; 
      
      initPanel();
      initSubmit();
      initFields();
  
      list = new ArrayList<>();
  
      this.setVisible(true);
  
    };
  
    public void initFields() {
      fields = new JTextField[size];
      for (int i = 0; i < size; i++) {
        fields[i] = new JTextField();
        fields[i].setPreferredSize(new Dimension(46, 30));
        panel.add(fields[i]);
  
      }
  
    }
  
    public void initSubmit() {
      submit = new DButton("Submit");
      submit.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e)
        {
            
            System.out.println(getList()); 
            This.dispose();
            
        }
      });
      this.add(submit, BorderLayout.SOUTH);
  
    }
  
    public void initPanel() {
      panel = new JPanel();
      panel.setLayout(new FlowLayout(0, 20, 20));
      panel.setBackground(Color.CYAN);
      this.add(panel);
  
      return;
    }
  
    public ArrayList getList() {
  
      for(int i=0; i<size; i++)
      {
        list.add(fields[i].getText().toString()); 
      }
      return list;
    }
  
  }