package ca.uwo.cs2212.group2.service;

/**
 * @author Ryan Hecht Singleton service that keeps track of the number of different types of
 *     corrections accepted by the user from the dropdown for this active session.
 */
public class SessionCorrectionStatisticsService {
  private static SessionCorrectionStatisticsService instance;
  private int numberOfSuggestionCorrections;
  private int numberOfManualCorrections;
  private int numberOfDeletionCorrections;

  /** Constructor for the SessionCorrectionStatisticsService. */
  private SessionCorrectionStatisticsService() {
    numberOfSuggestionCorrections = 0;
    numberOfManualCorrections = 0;
    numberOfDeletionCorrections = 0;
  }

  /**
   * Lazily loads the singleton instance of the SessionCorrectionStatisticsService.
   *
   * @return The instance of the SessionCorrectionStatisticsService
   */
  public static SessionCorrectionStatisticsService getInstance() {
    if (instance == null) {
      instance = new SessionCorrectionStatisticsService();
    }
    return instance;
  }

  public int getNumberOfSuggestionCorrections() {
    return numberOfSuggestionCorrections;
  }

  public int getNumberOfManualCorrections() {
    return numberOfManualCorrections;
  }

  public int getNumberOfDeletionCorrections() {
    return numberOfDeletionCorrections;
  }

  public void incrementNumberOfSuggestionsCorrections() {
    numberOfSuggestionCorrections++;
  }

  public void incrementNumberOfManualCorrections() {
    numberOfManualCorrections++;
  }

  public void incrementNumberOfDeletionCorrections() {
    numberOfDeletionCorrections++;
  }
}
