package ca.uwo.cs2212.group2.constants;

import java.awt.*;

/** This class contains the colours of the application. */
public class ViewConstants {
  // Colours
  public static final Color PRIMARY_COLOUR = new Color(0x993399);
  public static final Color MENU_BACKGROUND_COLOUR = new Color(0xEBEBE0);
  public static final Color PRIMARY_BACKGROUND_COLOUR = Color.decode("#800080");

  // Dimensions
  public static final int APP_WIDTH = 1600;
  public static final int APP_HEIGHT = 900;
  public static final int POPUP_WIDTH = 800;
  public static final int POPUP_HEIGHT = 500;
  public static final int MENU_BAR_HEIGHT = 89;

  // Fonts
  public static final Font MENU_FONT = new Font("SansSerif", Font.PLAIN, 20);

  /** Private constructor to prevent instantiation */
  private ViewConstants() {
    throw new UnsupportedOperationException("This is a constants class and cannot be instantiated");
  }
}
