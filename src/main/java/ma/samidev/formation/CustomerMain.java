package ma.samidev.formation;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ma.samidev.formation.service.CustomerService;

public class CustomerMain {

	public static void main(String[] args) {
		System.out.println("--- Début du programme ---");
		AbstractApplicationContext applicationContext = new ClassPathXmlApplicationContext("config/root-config.xml");
		CustomerService customerService = (CustomerService) applicationContext.getBean(CustomerService.class, "customerService");
		customerService.addCustomer();
		customerService.addCustomerReturnValue();
		try {
			customerService.addCustomerThrowException();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		customerService.addCustomerAround("c1");
		applicationContext.close();
		System.out.println("--- Fin du programme ---");
	}

}
