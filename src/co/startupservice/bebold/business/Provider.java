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
    public Provider(String username, String password, Long id) {
        super(username, password, id);
        listNotications = new ArrayList<>();
        listAssociatedServices = new ArrayList<>();
        listAbilities = new ArrayList<>();
    }

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

    private boolean validatePrice(Service service, int pos) {
        Ability ability = listAbilities.get(pos);
        float range = ability.getServicePrice() * 0.2f;
        return ( service.getExpectedPrice() <= ability.getServicePrice() + range && service.getExpectedPrice() >= ability.getServicePrice() - range);
    }

    private boolean validateDistance(Service service , int pos) {
        Ability ability = listAbilities.get(pos);
        Double distance = service.getLocation();
        return (distance <= ability.getDistanceCoverage());
    }

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

    public void reportNotification(Notification notification)
    {
        this.listNotications.add(notification);
    }

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
    public void receiveRating(int rating)
    {
        this.averageRating = (averageRating+ rating) / 2;
    }

    public void performService(Service service)
    {
        service.changeStatus("REALIZADO");
    }

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
}
