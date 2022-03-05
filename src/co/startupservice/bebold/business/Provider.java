package co.startupservice.bebold.business;

import co.startupservice.bebold.Domain.Domain;


import java.util.ArrayList;

public class Provider extends User{

    private int averageRating = 0;
    private String status;
    private String contact;
    private ArrayList<Ability> listAbilities;
    private ArrayList<Service> listAssociatedServices;
    private ArrayList<Notification> listNotications;
    private Domain domain;

    /**
     * Provider class constructor method
     * @param username User name
     * @param password User password
     * @param id User identifier
     */
    public Provider(String username, String password, Long id) {
        super(username, password, id);
        listNotications = new ArrayList<>();
        listAssociatedServices = new ArrayList<>();
        listAbilities = new ArrayList<>();
    }

    /**
     * Method to check if the provider complies with the service conditions
     * @param service Service
     * @return true if the provider meets the conditions or false if it does not.
     */
    public boolean isAppropriate(Service service) {
        if(this.status.equals("busy"))
        {
            return false;
        }
        else
        {
            int pos = validateCategory(service.getCategory());
            if( pos != -1)
            {
                if(validateDistance(service, pos))
                {
                    return validatePrice(service, pos);
                }
            }
            return false;
        }
    }

    /**
     * Method to validate if the price of the service is within the limits set by the provider
     * @param service Service
     * @param pos Ability index
     * @return true if the price is within the ranges or false if it is not.
     */
    private boolean validatePrice(Service service, int pos) {
        Ability ability = listAbilities.get(pos);
        float range = ability.getServicePrice() * 0.2f;
        return ( service.getExpectedPrice() <= ability.getServicePrice() +
                range && service.getExpectedPrice() >= ability.getServicePrice() - range);
    }

    /**
     * Method to validate if the service is within the provider's coverage distance.
     * @param service Service
     * @param pos Ability index
     * @return true if the service coverage distance is within the ranges or false if it is not
     */
    private boolean validateDistance(Service service , int pos) {
        Ability ability = listAbilities.get(pos);
        Double distance = service.getLocation();
        return (distance <= ability.getDistanceCoverage());
    }

    /**
     * Method to validate if the category of the service is within the provider's ability range
     * @param category Category
     * @return -1 if it does not match any of the provider's abilities,
     *          if the category matches it returns the index of the corresponding ability.
     */
    public int validateCategory( Category category)
    {
        for (int i = 0; i < listAbilities.size(); i++)
        {
            if (listAbilities.get(i).getCategory().equals(category))
            {
                return i;
            }
        }
        return -1;
    }

    /**
     * Method to add a notification to the provider's list of notifications
     * @param notification Notification
     */
    public void reportNotification(Notification notification)
    {
        this.listNotications.add(notification);
    }

    /**
     * Method to accept the performance of a service by the provider
     * @param notification Notification that is related to the service to be accepted
     * @return true if the service was assigned to the provider or false if it was not assigned
     */
    public boolean acceptService(Notification notification)
    {
        Service service = notification.getService();
        if(service.getStatus().equals("EN_ESPERA"))
        {
            if(service.validateLifeTime())
            {
                this.listAssociatedServices.add(notification.getService());
                this.status = "busy";
                service.setProvider(this);
                notification.getService().changeStatus("ACEPTADO");
                //Eliminar las demas notificaciones
                return true;
            }
        }
        return false;
    }

    /**
     * Method to calculate the average rating of the service performance
     * @param rating Rating of service
     */
    public void receiveRating(int rating)
    {
        this.averageRating = (averageRating+ rating) / 2;
    }

    /**
     * Method to take a service for granted
     * @param service Service
     */
    public void performService(Service service)
    {
        service.changeStatus("REALIZADO");
    }

    /**
     * --------------------- Start Methods getters and setters ---------------------
     */
    public int getAverageRating() {
        return averageRating;
    }

    public String getStatus() {
        return status;
    }

    public String getContact() {
        return contact;
    }

    public ArrayList<Ability> getListAbilities() {
        return listAbilities;
    }

    public ArrayList<Service> getListAssociatedServices() {
        return listAssociatedServices;
    }

    public ArrayList<Notification> getListNotications() {
        return listNotications;
    }

    public Domain getDomain() {
        return domain;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDomain(Domain domain) {
        this.domain = domain;
    }
    /**
     * --------------------- End Methods getters and setters ---------------------
     */
}
