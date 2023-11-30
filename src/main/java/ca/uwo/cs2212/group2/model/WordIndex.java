package ca.uwo.cs2212.group2.model;

import java.util.Objects;

public class WordIndex {
  private final String content;
  private final int position;

  public WordIndex(String content, int position) {
    this.content = content;
    this.position = position;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    WordIndex that = (WordIndex) o;
    return position == that.position && Objects.equals(content, that.content);
  }

  @Override
  public int hashCode() {
    return Objects.hash(content, position);
  }
}
