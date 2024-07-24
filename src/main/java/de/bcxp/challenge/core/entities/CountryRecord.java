package de.bcxp.challenge.core.entities;

public class CountryRecord {
    private String name;
    private Long population;
    private Double area;

    public CountryRecord() {
    }

    public CountryRecord(String name, Long population, Double area) {
        this.name = name;
        this.population = population;
        this.area = area;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPopulation() {
        return population;
    }

    public void setPopulation(Long population) {
        this.population = population;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public Double getDensity() {
        return (double) population / area;
    }
}
