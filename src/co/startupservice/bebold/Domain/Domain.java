package co.startupservice.bebold.Domain;

import co.startupservice.bebold.business.*;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

public class Domain {
    private ArrayList<Service> listServices;
    private ArrayList<Customer> listCustomers;
    private ArrayList<Admin> listAdmins;
    private ArrayList<Provider> listProviders;
    private ArrayList<Category> listCategories;

    /**
     * Domain class constructor method
     */
    public Domain ()
    {
        this.listServices = new ArrayList<>();
        this.listCustomers = new ArrayList<>();
        this.listAdmins = new ArrayList<>();
        this.listProviders = new ArrayList<>();
        this.listCategories = new ArrayList<>();
    }

    /**
     * Method to initialize some test data
     */
    public void initialize()
    {
        //Usuarios
        Admin admin1 = new Admin("admin", "1234", 1L);
        Provider provider1 = new Provider("Jhon Doe", "1234", 2L);
        Customer customer1 = new Customer("Alex James", "1234", 3L);
        Provider provider2 = new Provider("Jose Smith", "1234", 4L);
        Customer customer2 = new Customer("Pedro Perez", "1234", 5L);
        provider1.setStatus("Libre");
        provider2.setStatus("Libre");

        listCustomers.add(customer1);
        listCustomers.add(customer2);

        listProviders.add(provider1);
        listProviders.add(provider2);

        listAdmins.add(admin1);

        //Categories
        Category category1 = new Category("Limpieza de alfombras", 0,admin1);
        Category category2 = new Category("Pasear perros", 0,admin1);
        Category category3 = new Category("Reparación eléctrica", 0,admin1);
        Category category4 = new Category("Catering y entrega de alimentos", 0,admin1);
        Category category5 = new Category("Reparación de electrodomésticos", 0,admin1);
        Category category6 = new Category("Cortar el césped", 0,admin1);
        Category category7 = new Category("Reparación del hogar (general)", 0,admin1);

        admin1.getListCategories().add(category1);
        admin1.getListCategories().add(category2);
        admin1.getListCategories().add(category3);
        admin1.getListCategories().add(category4);
        admin1.getListCategories().add(category5);
        admin1.getListCategories().add(category6);
        admin1.getListCategories().add(category7);

        listCategories.add(category1);
        listCategories.add(category2);
        listCategories.add(category3);
        listCategories.add(category4);
        listCategories.add(category5);
        listCategories.add(category6);
        listCategories.add(category7);
    }

    /**
     * Method to request a service
     * @param service Service
     * @return true if the service could be registered with the system
     */
    public boolean requestService(Service service)
    {
        ArrayList<Provider> listProvidersService = findProviders(service);
        System.out.println(listProvidersService.toString());

        if(listProvidersService.size()> 0)
        {
            //Notificar proveedores
            service.changeStatus("EN_ESPERA");
            service.setCreationDate(LocalDateTime.now());
            notifyProviders(listProvidersService, service);
            return true;
        }
        else
        {
            //Notificar clientes no existen prooverdores
            String msg = "Ningun hacedor cuenta con las condiciones necesarias para cumplir con el servicio";
            service.notifyCustomer(service.getCustomer(), msg);
            service.changeStatus("CANCELADO");
            return false;
        }
    }

    /**
     *
     Method to find providers that meet the conditions of a service
     * @param service Service
     * @return list providers
     */
    public ArrayList<Provider> findProviders (Service service)
    {
        ArrayList<Provider> listPro = new ArrayList<>();

        for ( int i = 0;  i < listProviders.size(); i++)
        {
            if(listProviders.get(i).isAppropriate(service))
            {
                listPro.add(listProviders.get(i));
            }
        }
        return listPro;
    }

    /**
     * Method for notifying providers
     * @param listProviders List providers
     * @param service Service
     */
    public void notifyProviders(ArrayList<Provider> listProviders, Service service)
    {
        Notification notification = new Notification();
        notification.setService(service);
        notification.setListProviders(listProviders);
        for (int i = 0; i < listProviders.size(); i++)
        {
            listProviders.get(i).reportNotification(notification);
        }
    }

    /**
     * --------------------- Start Methods getters and setters ---------------------
     */
    public ArrayList<Service> getListServices() {
        return listServices;
    }

    public ArrayList<Customer> getListCustomers() {
        return listCustomers;
    }

    public ArrayList<Admin> getListAdmins() {
        return listAdmins;
    }

    public ArrayList<Provider> getListProviders() {
        return listProviders;
    }

    public ArrayList<Category> getListCategories() {
        return listCategories;
    }
    /**
     * --------------------- End Methods getters and setters ---------------------
     */
}
