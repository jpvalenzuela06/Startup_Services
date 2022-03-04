package co.startupservice.bebold.app;

import co.startupservice.bebold.Domain.Domain;
import co.startupservice.bebold.business.Ability;
import co.startupservice.bebold.business.Customer;
import co.startupservice.bebold.business.Notification;
import co.startupservice.bebold.business.Service;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class Main {

    public static void main(String[] args) {
       /** LocalDateTime ayer = LocalDateTime.of(2022, Month.MARCH,3, 10, 36, 0);
        LocalDateTime actual = LocalDateTime.now();

        //if((ayer.getHours() + 24) <= actual)
        System.out.println(ChronoUnit.DAYS.between(ayer, actual));
        System.out.println(actual.getClass());
        **/
        Domain domain = new Domain();
        domain.initialize();

        //System.out.println(domain.getListAdmins().toString());
        //System.out.println(domain.getListCustomers().toString());
        //System.out.println(domain.getListProviders().toString());
        //System.out.println(domain.getListCategories().toString());

        Ability ability1 = new Ability(40000f, 10.0, domain.getListCategories().get(0),domain.getListProviders().get(0));
        domain.getListProviders().get(0).getListAbilities().add(ability1);
        Ability ability2 = new Ability(40000f, 10.0, domain.getListCategories().get(1),domain.getListProviders().get(1));
        domain.getListProviders().get(1).getListAbilities().add(ability2);
        Ability ability3 = new Ability(50000f, 20.0, domain.getListCategories().get(0),domain.getListProviders().get(1));
        domain.getListProviders().get(1).getListAbilities().add(ability3);

        Customer customer = domain.getListCustomers().get(0);
        customer.setDomain(domain);
        Service service = new Service(40000f,10.0,1L,LocalDateTime.now(),customer,domain.getListCategories().get(0));

        System.out.println(customer.requestService(service));
        System.out.println(service.getStatus());
        System.out.println(customer.getListNotifications().size());
        System.out.println(domain.getListProviders().get(1).getListNotications().size());
        System.out.println(domain.getListProviders().get(0).getListNotications().size());
        Notification not = domain.getListProviders().get(0).getListNotications().get(0);
        boolean res = domain.getListProviders().get(0).acceptService(not);
        System.out.println(res);

        res = domain.getListProviders().get(1).acceptService(domain.getListProviders().get(1).getListNotications().get(0));
        System.out.println(res);
        domain.getListProviders().get(0).performService(not.getService());
        System.out.println(not.getService().getStatus());
        System.out.println(customer.rateService(not.getService(), 5));
        System.out.println(not.getService().getStatus());
    }
}
