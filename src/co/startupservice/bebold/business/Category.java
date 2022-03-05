package co.startupservice.bebold.business;

import co.startupservice.bebold.Domain.Domain;

import java.util.ArrayList;

public class Category {

    private String task;
    private float basePrice;
    private ArrayList<Service> listServices;
    private Admin admin;
    private ArrayList<Ability> listAbilities;
    private Domain domain;


    /**
     * Category class constructor method
     * @param task
     * @param basePrice
     * @param admin
     */
    public Category(String task, float basePrice, Admin admin) {
        this.task = task;
        this.basePrice = basePrice;
        this.admin = admin;

        listAbilities = new ArrayList<>();
        listServices = new ArrayList<>();
    }

    @Override
    public boolean equals(Object category)
    {
        if(category instanceof Category)
        {
            Category tmpCategory = (Category) category;
            return (this.task.equals(tmpCategory.getTask())) ? true : false;
        }
        return false;
    }

    /**
     * Method for calculating the average cost of the task
     * @return average cost of the task
     */
    public float calculateCostBase()
    {
        float sum = 0;
        for(int i = 0; i < listAbilities.size(); i++)
        {
            sum += listAbilities.get(i).getServicePrice();
        }
        return sum / listAbilities.size();
    }

    @Override
    public String toString()
    {
        return this.task + " " + basePrice;
    }

    /**
     * --------------------- Start Methods getters and setters ---------------------
     */
    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public float getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(float basePrice) {
        this.basePrice = basePrice;
    }

    public ArrayList<Service> getListServices() {
        return listServices;
    }

    public void setListServices(ArrayList<Service> listServices) {
        this.listServices = listServices;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public ArrayList<Ability> getListAbilities() {
        return listAbilities;
    }

    public void setListAbilities(ArrayList<Ability> listAbilities) {
        this.listAbilities = listAbilities;
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
