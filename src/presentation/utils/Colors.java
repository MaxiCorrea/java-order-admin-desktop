package presentation.utils;

import java.awt.Color;

public enum Colors {

  BACKGROUND(Color.WHITE), PANEL_COLOR(new Color(34, 34, 34)), BUTTON_OPERATIONS(Color.BLACK);

  private final Color color;

  private Colors(Color color) {
    this.color = color;
  }

  public Color color() {
    return color;
  }
}
