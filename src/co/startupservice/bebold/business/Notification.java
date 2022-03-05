package co.startupservice.bebold.business;

import java.util.ArrayList;

public class Notification {

    private String message;
    private long id;
    private ArrayList<Provider> listProviders;
    private Customer customer;
    private Service service;


    /**
     * --------------------- Start Methods getters and setters ---------------------
     */
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ArrayList<Provider> getListProviders() {
        return listProviders;
    }

    public void setListProviders(ArrayList<Provider> listProviders) {
        this.listProviders = listProviders;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }
    /**
     * --------------------- End Methods getters and setters ---------------------
     */
}
