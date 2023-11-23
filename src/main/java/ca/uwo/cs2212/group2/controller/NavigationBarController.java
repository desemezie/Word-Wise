package ca.uwo.cs2212.group2.controller;

public interface NavigationBarController {
  void handleFileAction(String actionCommand);

  void handleSettingAction(String actionCommand);

  void handleSpellCheckAction();

  void handleMetricsAction(String actionCommand);

  void handleSaveAction();

  void handleHelpAction(String actionCommand);
}
