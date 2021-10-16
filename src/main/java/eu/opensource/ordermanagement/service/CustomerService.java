package eu.opensource.ordermanagement.service;

import eu.opensource.ordermanagement.domain.Customer;
import eu.opensource.ordermanagement.service.impl.dto.CustomerDto;
import eu.opensource.ordermanagement.web.util.SignupForm;
import org.springframework.security.core.userdetails.UserDetails;

public interface CustomerService {

    Customer getCustomerById(Long id);

    Customer getCustomerByUsername(String email);

    Customer getCustomerByUsernameAndPassword(String email, String password);

    UserDetails loadUserByUsername(String email);

    Customer saveCustomer(final Customer customer);

    Customer registrationCustomer(SignupForm signupForm);

    Customer getCurrentCustomer();

    void setCurrentCustomer(Customer customer);

    CustomerDto createCustomerDto(Customer customer);
}