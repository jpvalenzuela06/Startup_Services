package co.startupservice.bebold.business;

import co.startupservice.bebold.Domain.Domain;

import java.util.ArrayList;

public class Customer extends User {

    private String contact;
    private ArrayList<Notification> listNotifications;
    private Service service;
    private Domain domain;

    /**
     * Customer class constructor method
     * @param username User name
     * @param password User password
     * @param id User identifier
     */
    public Customer(String username, String password, Long id) {
        super(username, password, id);
        listNotifications = new ArrayList<>();
    }

    /**
     * Method to request a service
     * @param service Service
     * @return true if the request was created successfully, false if the request could not be created
     */
    public boolean requestService(Service service)
    {
        return domain.requestService(service);
    }

    /**
     *
     * @param service Service
     * @param rating Rating
     * @return true if the service was successfully qualified, false otherwise
     */
    public boolean rateService(Service service, int rating)
    {
        if(service.getStatus().equals("REALIZADO"))
        {
            service.setServiceRating(rating);
            service.getProvider().receiveRating(rating);
            service.changeStatus("CALIFICADO");
            return true;
        }
        return false;

    }

    /**
     * --------------------- Start Methods getters and setters ---------------------
     */
    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Domain getDomain() {
        return domain;
    }

    public void setDomain(Domain domain) {
        this.domain = domain;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public ArrayList<Notification> getListNotifications() {
        return listNotifications;
    }

    public void setListNotifications(ArrayList<Notification> listNotifications) {
        this.listNotifications = listNotifications;
    }
    /**
     * --------------------- End Methods getters and setters ---------------------
     */
}
