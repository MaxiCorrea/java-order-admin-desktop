package presentation.extras;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JFrame;
import javax.swing.JLabel;
import presentation.utils.Colors;
import presentation.utils.Icon;

public class MoveLabel extends JLabel {

  private static final long serialVersionUID = 1L;
  private JFrame owner;
  private JLabel minimize;
  private JLabel close;
  private Point p1, p2;

  public MoveLabel(JFrame owner) {
    super("  AdminPedidos", JLabel.LEFT);
    this.owner = owner;
    setForeground(Color.WHITE);
    setBackground(Colors.PANEL_COLOR.color());
    setFont(DeckeFont.INSTANCE.getFont().deriveFont(20f).deriveFont(Font.BOLD));
    setLayout(new FlowLayout(FlowLayout.RIGHT));
    add(obtenerEtiquetaDeMinimizar());
    add(obtenerEtiquetaDeCierre());
    setPreferredSize(new Dimension(850, 41));
    agregaOyenteDeMouse();
    agregaOyenteDeMovimientoDeMouse();
  }

  private void agregaOyenteDeMouse() {
    addMouseListener(new MouseAdapter() {
      public void mousePressed(MouseEvent e) {
        p1 = new Point(e.getX(), e.getY());
      }
    });
  }

  private void agregaOyenteDeMovimientoDeMouse() {
    addMouseMotionListener(new MouseMotionAdapter() {
      public void mouseDragged(MouseEvent e) {
        p2 = MouseInfo.getPointerInfo().getLocation();
        owner.setLocation(p2.x - p1.x, p2.y - p1.y);
      }
    });
  }

  private JLabel obtenerEtiquetaDeCierre() {
    close = new JLabel();
    close.setIcon(Icon.load("/close.png"));
    close.addMouseListener(new MouseAdapter() {
      public void mousePressed(MouseEvent e) {
        owner.setVisible(false);
        owner.dispose();
        System.exit(0);
      }
    });
    return close;
  }

  private JLabel obtenerEtiquetaDeMinimizar() {
    minimize = new JLabel();
    minimize.setIcon(Icon.load("/mini.png"));
    minimize.addMouseListener(new MouseAdapter() {
      public void mousePressed(MouseEvent e) {
        owner.setState(JFrame.ICONIFIED);
      }
    });
    return minimize;
  }

}
