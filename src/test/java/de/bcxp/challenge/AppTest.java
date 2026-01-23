package de.bcxp.challenge;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/** Example JUnit 5 test case. */
class AppTest {

  private String successLabel = "not successful";

  @BeforeEach
  void setUp() {
    successLabel = "successful";
  }

  @Test
  void aPointlessTest() {
    assertEquals("successful", successLabel, "My expectations were not met");
  }
}
