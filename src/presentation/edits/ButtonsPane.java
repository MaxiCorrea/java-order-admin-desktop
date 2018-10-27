package presentation.edits;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import presentation.utils.Colors;

public class ButtonsPane extends JPanel implements ActionListener {

  private static final long serialVersionUID = 1L;

  private int widthButton;
  private int heightButton;

  private JButton newButton;
  private JButton editButton;
  private JButton deleteButton;
  private JButton toListButton;

  private JButton cancelButton;
  private JButton acceptButton;
  public String action = "";

  public ButtonsPane() {
    widthButton = 95;
    heightButton = 40;
    createButtonsPane();
  }

  public ButtonsPane(int w, int h) {
    widthButton = w;
    heightButton = h;
    createButtonsPane();
  }

  private void createButtonsPane() {
    setLayout(new FlowLayout(FlowLayout.CENTER));
    setBackground(Colors.BACKGROUND.color());
    add(newButton = createButton("Nuevo"));
    add(editButton = createButton("Editar"));
    add(deleteButton = createButton("Eliminar"));
    add(toListButton = createButton("Listar"));
    add(acceptButton = createButton(""));
    add(cancelButton = createButton(""));
    newButton.addActionListener(this);
    editButton.addActionListener(this);
    deleteButton.addActionListener(this);
    toListButton.addActionListener(this);
    hideCancelAndAcceptButton();
  }

  public JButton getNewButton() {
    return newButton;
  }

  public JButton getEditButton() {
    return editButton;
  }

  public JButton getDeleteButton() {
    return deleteButton;
  }

  public JButton getToListButton() {
    return toListButton;
  }

  public JButton getCancelButton() {
    return cancelButton;
  }

  public JButton getAcceptButton() {
    return acceptButton;
  }

  public String getAction() {
    return action;
  }

  private JButton createButton(String text) {
    JButton button = new JButton(text);
    button.setForeground(Colors.BACKGROUND.color());
    button.setBorderPainted(false);
    button.setFocusable(false);
    button.setBackground(Colors.PANEL_COLOR.color());
    button.setPreferredSize(new Dimension(widthButton, heightButton));
    return button;
  }

  public void showCancelAndAcceptButton(String s1, String s2) {
    acceptButton.setText(s1);
    cancelButton.setText(s2);
    cancelButton.setVisible(true);
    acceptButton.setVisible(true);
  }

  public void hideCancelAndAcceptButton() {
    cancelButton.setVisible(false);
    acceptButton.setVisible(false);
  }

  public void hideNewEditDeleteToListButtons() {
    newButton.setVisible(false);
    editButton.setVisible(false);
    deleteButton.setVisible(false);
    toListButton.setVisible(false);
  }

  public void showNewEditDeleteToListButtons() {
    newButton.setVisible(true);
    editButton.setVisible(true);
    deleteButton.setVisible(true);
    toListButton.setVisible(true);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    Object src = e.getSource();
    if (newButton == src) {
      action = "new";
    } else if (editButton == src) {
      action = "edit";
    } else if (deleteButton == src) {
      action = "delete";
    }
  }
}
