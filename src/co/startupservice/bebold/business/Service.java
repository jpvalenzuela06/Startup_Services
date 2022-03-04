package co.startupservice.bebold.business;

import co.startupservice.bebold.Domain.Domain;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;

public class Service {

    private float servicePrice;
    private float expectedPrice;
    private int serviceRating;
    private Double location;
    private String status;
    private String description;
    private long id;
    private LocalDateTime creationDate;
    private Provider provider;
    private Customer customer;
    private Notification notification;
    private Category category;
    private Domain domain;

    public Service(float expectedPrice, Double location, long id, LocalDateTime creationDate, Customer customer, Category category) {
        this.expectedPrice = expectedPrice;
        this.location = location;
        this.id = id;
        this.creationDate = creationDate;
        this.customer = customer;
        this.category = category;
    }

    public void changeStatus(String status) {
        this.status = status;
    }

    public boolean validateLifeTime()
    {
        LocalDateTime now = LocalDateTime.now();
        if(ChronoUnit.DAYS.between(creationDate, now) <= 1)
        {
            return true;
        }
        else
        {
            changeStatus("CANCELADO");
            float priceBase = this.category.calculateCostBase();
            String msg = "Ningun hacedor acepto prestar el servicio, el precio promedio del servicio es" + priceBase;
            notifyCustomer(this.customer, msg);
            return false;
        }

    }

    public void notifyCustomer(Customer customer, String message)
    {
        if(this.notification == null)
        {
            this.notification = new Notification();
        }
        this.notification.setMessage(message);
        this.notification.setCustomer(customer);
        customer.getListNotifications().add(notification);
    }

    public float getServicePrice() {
        return servicePrice;
    }

    public void setServicePrice(float servicePrice) {
        this.servicePrice = servicePrice;
    }

    public float getExpectedPrice() {
        return expectedPrice;
    }

    public void setExpectedPrice(float expectedPrice) {
        this.expectedPrice = expectedPrice;
    }

    public int getServiceRating() {
        return serviceRating;
    }

    public void setServiceRating(int serviceRating) {
        this.serviceRating = serviceRating;
    }

    public Double getLocation() {
        return location;
    }

    public void setLocation(Double location) {
        this.location = location;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Notification getNotification() {
        return notification;
    }

    public void setNotification(Notification notification) {
        this.notification = notification;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Domain getDomain() {
        return domain;
    }

    public void setDomain(Domain domain) {
        this.domain = domain;
    }
}
