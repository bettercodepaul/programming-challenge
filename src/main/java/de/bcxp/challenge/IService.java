package de.bcxp.challenge;

import java.util.List;

public interface IService {

    void extractDataToDB(List<String[]> data);
    void closeConnection();
}
