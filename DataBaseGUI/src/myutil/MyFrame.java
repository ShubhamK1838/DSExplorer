package myutil;


import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MyFrame extends JFrame {
    private MouseListener actionlistener;
    private MyFrame This;
    private JPanel title;
    private MouseAdapter mouseadapter;
    private MouseMotionListener mousemotion;
    private Point pressedpoint;
    private JLabel exit, minimiz, miximiz;
    private JPanel titlebuttons;
    private JPanel titlenamelogo;
    private boolean state = true;

    public static void main(String ar[])
    {



        MyFrame frame=new MyFrame(); 

        frame.setSize(400,400); 

        frame.setVisible(true); 
    }

    public MyFrame() {
        This = this;
        this.setSize(400, 400);
        this.setUndecorated(true);

        initTitle();

        this.add(title, BorderLayout.NORTH);

    }

    public void initTitle() {

        title = new JPanel();
        title.setPreferredSize(new Dimension(0, 26));
        title.setLayout(new BorderLayout());
;
        initMouseAdapter();
        initMouseMotionsListener();

        titlebuttons = new JPanel();
        titlebuttons.setLayout(new FlowLayout(0, 10, 4));
        titlebuttons.setPreferredSize(new Dimension(80, 26));
        titlebuttons.setBackground(title.getBackground());
        title.addMouseListener(mouseadapter);
        title.addMouseMotionListener(mousemotion);
        title.setBackground(Color.red);
        initTitleActionButtons();

        titlebuttons.add(minimiz);
        titlebuttons.add(miximiz);
        titlebuttons.add(exit);
        title.add(titlebuttons, BorderLayout.EAST);
        // title.add(exit);

        title.repaint();

        return;
    }

    public void initLogoTitle()
    {

    }

    public void initTitleActionButtons() {

        ImageIcon icoexit = new ImageIcon("src\\icons\\exit.png");
        ImageIcon icomini = new ImageIcon("src\\icons\\mmize.png");
        ImageIcon maxm = new ImageIcon("src\\icons\\maximize.png");

        exit = new JLabel(icoexit);
        minimiz = new JLabel(icomini);
        miximiz = new JLabel(maxm);

        initActionsOfTitleButton();

        exit.setPreferredSize(new Dimension((int) icoexit.getIconWidth(), icoexit.getIconHeight()));
        exit.addMouseListener(actionlistener);

        minimiz.setPreferredSize(new Dimension(icomini.getIconWidth(), icomini.getIconHeight()));
        minimiz.addMouseListener(actionlistener);

        miximiz.addMouseListener(actionlistener);
        miximiz.setPreferredSize(new Dimension(maxm.getIconWidth(), maxm.getIconHeight()));
    }

    public void initActionsOfTitleButton() {
        actionlistener = new MouseAdapter() {
            public void mousePressed(MouseEvent ae) {
                if (ae.getSource() == exit) {
                    System.exit(0);

                } else if (ae.getSource() == minimiz) {
                 
                } else {
                    setStates();
                }
            }

        };
    }

    public void setStates() {
        System.out.println(This.getState()+" "+JFrame.MAXIMIZED_BOTH); 
        if (state==true) {
            state=false; 
            This.setExtendedState(JFrame.MAXIMIZED_BOTH);
            This.setLocationRelativeTo(null);
            System.out.println("first"); 
        } else {
            state=true; 
            This.setSize(494, 460);
            This.setLocationRelativeTo(null);
        }
         
    }

    public void initMouseMotionsListener() {

        mousemotion = new MouseMotionListener() {

            public void mouseDragged(MouseEvent e) {
                Point curpoint = e.getLocationOnScreen();
                This.setLocation(curpoint.x - pressedpoint.x, curpoint.y - pressedpoint.y);

                return;
            }

            public void mouseMoved(MouseEvent e) {
                return;
            }

        };

        return;
    }

    public void initMouseAdapter() {
        mouseadapter = new MouseAdapter() {

            public void mousePressed(MouseEvent me) {

                pressedpoint = me.getPoint();
                if (This.getState() == JFrame.MAXIMIZED_BOTH) {
                    This.setState(JFrame.MAXIMIZED_BOTH);
                }

       
               
            }


        };
    }

   
    
    

}