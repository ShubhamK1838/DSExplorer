package myutil;

import javax.swing.JOptionPane;

public class DList {

  public static DList list;
  String info;
  DList next;
  public DNode node;

  public DList getHead() {
    return list;
  }

  public DNode getNode() {
    return node;
  }

  public DList getNext() {
    return next;
  }

  public String getInfo() {
    return info;
  }
  public void deleteAfter(String  before)
  {
    DList head=list; 
    if(head==null || head.next==null || head.next.next==null)
    {
      return ; 
    }else
    {
      while(head.next!=null)
      {
        if(head.info.equals(before))
        {
          head.next=head.next.next; 
          return ; 
        }
        head=head.getNext(); 
      }
    }
  }


  public void deleteEnd() {
    DList head = list;
    if (head == null) {
      return;
    } else if (head.next == null) {
      deleteFirst();
    } else {
      while (head.next.next != null) {
        head = head.next;
      }
      head.next = null;
    }
  }

  public void deleteFirst() {
    DList head = list;
    if (list == null) {
      return;
    } else if (head.next == null) {
      list = null;
    } else {
      list = head.getNext();
    }
  }

  public void insAfter(String before, String ele) {
    if (list == null || list.next == null) {
      JOptionPane.showMessageDialog(null, "Remove Between Is Not Possible");
    } else {
      DList tmp = list;

      while (tmp.next != null) {
        if (tmp.info.equals(before)) {
          System.out.print("Got it...");
          DList p = new DList();
          p.info = ele;
          p.next = tmp.next;
          p.node = null;
          tmp.next = p;
          return;
        }
        System.out.println(before + " this data is received");
        tmp = tmp.next;
      }
    }
  }

  public void insEnd(String ele) {
    if (list == null) {
      insFirst(ele);
    } else {
      DList tmp = list;
      while (tmp.next != null) {
        tmp = tmp.next;
      }

      DList p = new DList();
      p.next = null;
      p.info = ele;
      p.node = null;

      tmp.next = p;
    }
  }

  public void insFirst(String info) {
    if (list == null) {
      list = new DList();
      list.info = info;
      list.next = null;
      list.node = new DNode().initNode(list);
    } else {
      DList tmp = new DList();
      tmp.info = info;
      tmp.next = list;
      tmp.node = new DNode().initNode(tmp);
      list = tmp;
    }
  }

  public void status() {
    DList tmp = list;

    while (tmp != null) {
      System.out.println(tmp.info);
      tmp = tmp.next;
    }
  }
}
