package de.bcxp.challenge;

import java.util.List;

public interface IFileParser {

    List<String[]> parseFile(String filePath, char separator);

}
