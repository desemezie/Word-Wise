package ca.uwo.cs2212.group2.constants;

import java.awt.Color;

/** This class contains the colours of the application. */
public class ColourConstants {
  public static final Color PRIMARY_COLOUR = new Color(0x993399);
  public static final Color BEIGE_COLOUR = new Color(0xEBEBE0);

  /** Private constructor to prevent instantiation */
  private ColourConstants() {
    throw new UnsupportedOperationException("This is a constants class and cannot be instantiated");
  }
}
