package presentation;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import presentation.extras.DeckeFont;
import presentation.utils.Colors;

public class OperationsView extends JPanel {
  private static final long serialVersionUID = 1L;

  private final int SIZE_OPERATIONS = 5;
  private final String[] labels = {"Clientes", "Productos", "Pedidos", "Herramientas", "Ayuda"};

  private JPanel contentPane;
  private FlowLayout layout;
  private JButton[] operations;
  private JPanel viewsPane;

  public OperationsView() {
    setupPanel();
    createContentPane();
    createOperations();
    add(contentPane);
    operations[0].setBackground(Colors.BUTTON_OPERATIONS.color());
  }

  private void setupPanel() {
    setLayout(new BorderLayout(0, 0));
    setPreferredSize(new Dimension(150, 540));
    setBackground(new Color(34, 34, 34));
  }

  private void createContentPane() {
    contentPane = new JPanel();
    contentPane.setBackground(Colors.PANEL_COLOR.color());
    layout = new FlowLayout(FlowLayout.CENTER, 0, 0);
    contentPane.setLayout(layout);
  }

  private void createOperations() {
    final int size = SIZE_OPERATIONS;
    operations = new JButton[size];
    JLabel space = new JLabel("");
    space.setPreferredSize(new Dimension(150, 40));
    contentPane.add(space);
    for (int index = 0; index < size; ++index) {
      operations[index] = createButton(labels[index]);
      operations[index].setActionCommand(String.valueOf(index));
      addActionListener(operations[index]);
      contentPane.add(operations[index]);
    }
  }

  private JButton createButton(String text) {
    JButton b = new JButton(text);
    b.setHorizontalAlignment(SwingConstants.LEFT);
    b.setPreferredSize(new Dimension(160, 50));
    b.setFont(DeckeFont.INSTANCE.getFont().deriveFont(14f));
    b.setFocusable(false);
    b.setBorderPainted(false);
    b.setBackground(Colors.PANEL_COLOR.color());
    b.setForeground(Colors.BACKGROUND.color());
    return b;
  }

  private void addActionListener(JButton button) {
    button.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        String actionCommand = button.getActionCommand();
        updateViews(actionCommand);
        updateColorButtons(actionCommand);
      }
    });
  }

  private void updateColorButtons(String selectedCommand) {
    for (JButton button : operations) {
      String command = button.getActionCommand();
      if (!command.equals(selectedCommand)) {
        button.setBackground(Colors.PANEL_COLOR.color());
      } else {
        button.setBackground(Colors.BUTTON_OPERATIONS.color());
      }
    }
  }

  public void setViews(JPanel viewPane) {
    this.viewsPane = viewPane;
  }

  private void updateViews(String accion) {
    CardLayout cl = (CardLayout) (viewsPane.getLayout());
    cl.show(viewsPane, accion);
  }

}
