package co.startupservice.bebold.business;

import co.startupservice.bebold.Domain.Domain;

import java.util.ArrayList;

public class Customer extends User {

    private String contact;
    private ArrayList<Notification> listNotifications;
    private Service service;
    private Domain domain;

    public Customer(String username, String password, Long id) {
        super(username, password, id);
        listNotifications = new ArrayList<>();
    }

    public boolean requestService(Service service)
    {
        return domain.requestService(service);
    }

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
}
