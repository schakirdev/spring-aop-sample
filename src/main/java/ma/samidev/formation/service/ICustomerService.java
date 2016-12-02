package ma.samidev.formation.service;

public interface ICustomerService {

	void addCustomer();

	String addCustomerReturnValue();

	void addCustomerThrowException() throws Exception;

	void addCustomerAround(String name);

}