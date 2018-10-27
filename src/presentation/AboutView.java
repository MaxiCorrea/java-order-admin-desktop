package presentation;

import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.EAST;
import static java.awt.BorderLayout.NORTH;
import static java.awt.BorderLayout.SOUTH;
import static java.awt.BorderLayout.WEST;
import static java.awt.Font.BOLD;
import static javax.swing.BorderFactory.createTitledBorder;
import static javax.swing.JOptionPane.showMessageDialog;
import static javax.swing.border.TitledBorder.TOP;
import static presentation.extras.DeckeFont.INSTANCE;
import static presentation.utils.Colors.BACKGROUND;
import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import presentation.utils.Colors;
import presentation.utils.Icon;

public class AboutView extends JPanel {

  private static final String LUK_LINKEDIN_URL =
      "https://www.linkedin.com/in/lucas-fr%C3%ADas-516268130/";
  private static final String LUK_GITHUB_URL = "https://github.com/LucasFrias94";
  private static final String MAX_LINKEDIN_URL =
      "https://www.linkedin.com/in/maximiliano-correa-3398b012a/";
  private static final String MAX_GITHUB_URL = "https://github.com/MaxiCorrea";
  private static final String TITLE = "Sistema de Administración de Pedidos";
  private static final long serialVersionUID = 1L;

  public AboutView() {
    setLayout(new BorderLayout());
    setBackground(Colors.BACKGROUND.color());
    add(createNorthPane(), NORTH);
    add(createCenterPane(), CENTER);
    add(createSouthPane(), SOUTH);
  }

  private JPanel createNorthPane() {
    JPanel panel = new JPanel();
    JLabel labelTitle = new JLabel(TITLE);
    labelTitle.setFont(INSTANCE.getFont().deriveFont(40f).deriveFont(BOLD));
    panel.setBackground(BACKGROUND.color());
    panel.add(labelTitle);
    return panel;
  }

  private JPanel createCenterPane() {
    JPanel panel = new JPanel(new BorderLayout());
    panel.setBackground(BACKGROUND.color());
    JLabel labelInfo = new JLabel("TP nº3 de Taller de Programacion 2017 UNPAZ", JLabel.CENTER);
    labelInfo.setFont(INSTANCE.getFont().deriveFont(20f).deriveFont(BOLD));
    JLabel labelUnpaz = new JLabel(Icon.load("/unpaz.png"));
    panel.add(labelInfo, NORTH);
    panel.add(labelUnpaz, CENTER);
    return panel;
  }

  private JPanel createSouthPane() {
    JPanel panel = new JPanel(new BorderLayout());
    panel.setBorder(createTitledBorder(null, "Desarrolladores", TitledBorder.CENTER, TOP));
    panel.setFont(INSTANCE.getFont());
    panel.setBackground(BACKGROUND.color());
    panel.add(createEast(), EAST);
    panel.add(createWest(), WEST);
    return panel;
  }

  private JPanel createWest() {
    JPanel panel = new JPanel(new FlowLayout());
    panel.setPreferredSize(new Dimension(350, 75));
    panel.setBorder(createTitledBorder(null ,"El frontend fue programado por ",
        TitledBorder.CENTER , TOP));
    JLabel labelMaxi = new JLabel("Maxi Correa");
    JLabel maxiGithub = new JLabel(Icon.load("/github.png"));
    maxiGithub.setToolTipText(MAX_GITHUB_URL);
    JLabel maxiLinkedin = new JLabel(Icon.load("/linkedin.png"));
    maxiLinkedin.setToolTipText(MAX_LINKEDIN_URL);
    panel.add(labelMaxi);
    panel.add(maxiGithub);
    maxiGithub.addMouseListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent e) {
        openURI(maxiGithub.getToolTipText());
      }
    });
    panel.add(maxiLinkedin);
    maxiLinkedin.addMouseListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent e) {
        openURI(maxiLinkedin.getToolTipText());
      }
    });
    panel.setBackground(Colors.BACKGROUND.color());
    return panel;
  }

  private JPanel createEast() {
    JPanel panel = new JPanel(new FlowLayout());
    panel.setPreferredSize(new Dimension(350, 75));
    panel.setBorder(createTitledBorder( null ,"El backend fue programado por ",
        TitledBorder.CENTER , TOP));
    JLabel labelLucas = new JLabel("Lucas Frias");
    JLabel lucasGithub = new JLabel(Icon.load("/github.png"));
    lucasGithub.setToolTipText(LUK_GITHUB_URL);
    JLabel lucasLinkedin = new JLabel(Icon.load("/linkedin.png"));
    lucasLinkedin.setToolTipText(LUK_LINKEDIN_URL);
    panel.add(labelLucas);
    panel.add(lucasGithub);
    lucasGithub.addMouseListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent e) {
        openURI(lucasGithub.getToolTipText());
      }
    });
    panel.add(lucasLinkedin);
    lucasLinkedin.addMouseListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent e) {
        openURI(lucasLinkedin.getToolTipText());
      }
    });
    panel.setBackground(Colors.BACKGROUND.color());
    return panel;
  }

  private void openURI(String uri) {
    Desktop desktop = Desktop.getDesktop();
    try {
      desktop.browse(new URI(uri));
    } catch (Exception e1) {
      showMessageDialog(null, "Navegador no soportado");
    }
  }

}
