package presentation.extras;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.IOException;

public enum DeckeFont {

  INSTANCE;

  private Font font;

  private DeckeFont() {
    try {
      font =
          Font.createFont(Font.TRUETYPE_FONT, getClass().getResource("/Decker.ttf").openStream());
      GraphicsEnvironment genv = GraphicsEnvironment.getLocalGraphicsEnvironment();
      genv.registerFont(font);
      font = font.deriveFont(12f);
    } catch (FontFormatException | IOException e) {
      font = new Font(Font.MONOSPACED, Font.PLAIN, 17);
    }
  }

  public Font getFont() {
    return font;
  }


}
