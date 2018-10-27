package presentation.utils;

import javax.swing.ImageIcon;

public class Icon {
  private Icon() {
    throw new AssertionError();
  }

  public static javax.swing.Icon load(String path) {
    return new ImageIcon(Icon.class.getClass().getResource(path));
  }
}
