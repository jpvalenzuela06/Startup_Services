package co.startupservice.bebold.business;

public class Ability {

    private float servicePrice;
    private Double distanceCoverage;
    private Category category;
    private Provider provider;


    public Ability(float servicePrice, Double distanceCoverage, Category category, Provider provider) {
        this.servicePrice = servicePrice;
        this.distanceCoverage = distanceCoverage;
        this.category = category;
        this.provider = provider;
    }

    public float getServicePrice() {
        return servicePrice;
    }

    public void setServicePrice(float servicePrice) {
        this.servicePrice = servicePrice;
    }

    public Double getDistanceCoverage() {
        return distanceCoverage;
    }

    public void setDistanceCoverage(Double distanceCoverage) {
        this.distanceCoverage = distanceCoverage;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }
}
