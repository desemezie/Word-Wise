package ca.uwo.cs2212.group2.controller;

import ca.uwo.cs2212.group2.view.pages.LandingMenu;

public class LandingMenuController {
  private LandingMenu view;
  private ViewController viewController;

  public LandingMenuController(LandingMenu view, ViewController viewController) {
    this.view = view;
    this.viewController = viewController;
    attachListeners();
  }

  private void attachListeners() {

  }
}
