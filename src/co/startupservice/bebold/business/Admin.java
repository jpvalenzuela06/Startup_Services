package co.startupservice.bebold.business;

import co.startupservice.bebold.Domain.Domain;

import java.util.ArrayList;

public class Admin extends User{

    private ArrayList<Category> listCategories;
    private Domain domain;

    /**
     * Admin class constructor method
     * @param username User name
     * @param password User password
     * @param id User identifier
     */
    public Admin(String username, String password, Long id) {
        super(username, password, id);
        listCategories = new ArrayList<>();
    }

    /**
     * --------------------- Start Methods getters and setters ---------------------
     */
    public ArrayList<Category> getListCategories() {
        return listCategories;
    }

    public void setListCategories(ArrayList<Category> listCategories) {
        this.listCategories = listCategories;
    }

    public Domain getDomain() {
        return domain;
    }

    public void setDomain(Domain domain) {
        this.domain = domain;
    }
    /**
     * --------------------- End Methods getters and setters ---------------------
     */
}
