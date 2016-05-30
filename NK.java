//
// NK
// Author: Martin Maiers
// Date:   May 9, 1997
// Desc:   This applet displays graphs for given values of N and K
// Revision History:
// 

import java.awt.*;
import java.util.Random;

//
// NK
//
class NK extends Frame{
  NKCanvas nk = new NKCanvas();
  Choice n_choice = new Choice();
  Choice k_choice = new Choice();
  Label  n_label = new Label();
  Label  k_label = new Label();

  static public void main(String[] args) {
    new NK();
  }
  NK() {
    super("NK Drawing Applet");
    Panel opt_p = new Panel();
    opt_p.setLayout(new FlowLayout(FlowLayout.RIGHT));

    for (int i=1; i<8; i+= 1) {  		// by 1s
      n_choice.addItem(""+i);
      k_choice.addItem(""+i);
    }
    for (int i=8; i<48; i+= 2) {		// by 2s
      n_choice.addItem(""+i);
      k_choice.addItem(""+i);
    }
    for (int i=48; i<100; i+= 8) {		// by 8s
      n_choice.addItem(""+i);
      k_choice.addItem(""+i);
    }
    n_choice.select(1);
    k_choice.select(1);
    nk.setN(2);
    nk.setK(2);
    nk.resize(200, 200);
    nk.show();

    add("Center", nk);

    n_label.setText("N:");
    opt_p.add(n_label);
    opt_p.add(n_choice);
    k_label.setText(" K:");
    opt_p.add(k_label);
    opt_p.add(k_choice);
    add("South", opt_p);
    resize(200, 200);
    pack();
    show();
  }
  public boolean action(Event evt, Object what) {
    if (evt.target ==  n_choice) {
      nk.setN(Integer.parseInt((String)what));
      return true;
    }
    if (evt.target ==  k_choice) {
      nk.setK(Integer.parseInt((String)what));
      return true;
    }
    return false;
  }
}
 
//
// Node
//
class Node {
  int x, y; //location

  public Node(int x, int y) {
    this.x = x;
    this.y = y;
  }
}

//
// NKCanvas
//
class NKCanvas extends Canvas {
  int n, k, r=5;

  public void setN(int n) {
    this.n = n;
    repaint();
  }
  public void setK(int k) {
    this.k = k;
    repaint();
  }
  public void paint(Graphics g) {
    int edges=0;
    int offset;
    int w = size().width, h = size().height;
    Node N[];


    if(h<w)
      offset = h/2;
    else
      offset = w/2;
 
    offset -=5; // some goofy offset

    N = new Node[n];

    for(int i=0; i<n; i++) {
      Double theta = new Double(2*Math.PI *(double)i /(double)n);
      Double x = new Double(Math.cos(theta.doubleValue()) * offset +r);
      Double y = new Double(Math.sin(theta.doubleValue()) * offset +r);
      N[i] = new Node(x.intValue() + offset, y.intValue() + offset);
           
      g.fillOval(N[i].x-r/2, N[i].y-r/2, r, r);
    }
    Random randGen = new Random();
    for(int i=0; i<n; i++) {
      for(int j=0; j<i; j++) {
        if(randGen.nextDouble() < (double)k/(double)n) {
          g.drawLine(N[i].x, N[i].y, N[j].x, N[j].y);
          edges++;
        }
      }
    }
    System.out.println(edges);
  }
}
