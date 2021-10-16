package eu.opensource.ordermanagement.web;

import eu.opensource.ordermanagement.domain.Customer;
import eu.opensource.ordermanagement.service.CustomerService;
import eu.opensource.ordermanagement.service.impl.dto.CustomerDto;
import eu.opensource.ordermanagement.web.util.AddressForm;
import eu.opensource.ordermanagement.web.util.CustomerForm;
import eu.opensource.ordermanagement.web.util.PasswordForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/customers")
@RestController
public class CustomerController {

    private final CustomerService customerService;

    @PutMapping
    public ResponseEntity<CustomerDto> updateCustomer(@Valid @RequestBody CustomerForm customerForm) {

        // aggiorna informazioni utente
        Customer customer = customerService.getCustomerById(customerForm.getCustomerId());
        customer.setFirstname(customerForm.getFirstname());
        customer.setLastname(customerForm.getLastname());
        customer.setUsername(customerForm.getUsername());

        log.info("Customer: {}", customer);

        // aggiorna dati utente
        customerService.saveCustomer(customer);
        customerService.setCurrentCustomer(customer);

        CustomerDto customerDto = customerService.createCustomerDto(customer);

        return ResponseEntity.ok(customerDto);
    }

    @PutMapping("/changePassword")
    public ResponseEntity changePassword(@Valid @RequestBody PasswordForm passwordForm, Authentication authentication) {

        // verifica che le password inserite siano uguali
        String password = passwordForm.getPassword();
        String password2 = passwordForm.getPassword2();
        if (!password.equals(password2)) {

        }

        // rende persistente la modifica della password
        Customer customer = (Customer) authentication.getPrincipal();
        customer.setPassword(passwordForm.getPassword());

        log.info("Customer: {}", customer);

        customerService.saveCustomer(customer);
        customerService.setCurrentCustomer(customer);

        return ResponseEntity.status(200).build();
    }

    @GetMapping("/changeAddressHolder")
    public ResponseEntity<AddressForm> changeAddressesHolder(Authentication authentication) {

        Customer customer = (Customer) authentication.getPrincipal();
        AddressForm addressForm = new AddressForm();
        addressForm.setId(customer.getId());
        addressForm.setFirstname(customer.getHeader_addres()
                                         .getFirstname());
        addressForm.setLastname(customer.getHeader_addres()
                                        .getLastname());
        addressForm.setAddressId(customer.getHeader_addres()
                                         .getId());
        addressForm.setHouseNumber(customer.getHeader_addres()
                                           .getHouseNumber());
        addressForm.setStreet(customer.getHeader_addres()
                                      .getStreet());
        addressForm.setCity(customer.getHeader_addres()
                                    .getCity());
        addressForm.setCountry(customer.getHeader_addres()
                                       .getCountry());
        addressForm.setState(customer.getHeader_addres()
                                     .getState());

        return ResponseEntity.ok(addressForm);
    }

    @PostMapping("/changeAddressesHolder")
    public void changeAddressesHolder(@Valid @RequestBody AddressForm addressForm) {

        // aggiorna indirizzo
        Customer customer = customerService.getCustomerById(addressForm.getId());

        customer.getHeader_addres()
                .setFirstname(addressForm.getFirstname());
        customer.getHeader_addres()
                .setLastname(addressForm.getLastname());
        customer.getHeader_addres()
                .setHouseNumber(addressForm.getHouseNumber());
        customer.getHeader_addres()
                .setStreet(addressForm.getStreet());
        customer.getHeader_addres()
                .setCity(addressForm.getCity());
        customer.getHeader_addres()
                .setCountry(addressForm.getCountry());
        customer.getHeader_addres()
                .setState(addressForm.getState());

        customerService.saveCustomer(customer);
        customerService.setCurrentCustomer(customer);
    }

    @GetMapping("/changeAddressDelivery")
    public ResponseEntity<AddressForm> changeAddressesDelivery(Authentication authentication) {

        Customer customer = (Customer) authentication.getPrincipal();
        AddressForm addressForm = new AddressForm();
        addressForm.setId(customer.getId());
        addressForm.setFirstname(customer.getDelivery_addres()
                                         .getFirstname());
        addressForm.setLastname(customer.getDelivery_addres()
                                        .getLastname());
        addressForm.setAddressId(customer.getDelivery_addres()
                                         .getId());
        addressForm.setHouseNumber(customer.getDelivery_addres()
                                           .getHouseNumber());
        addressForm.setStreet(customer.getDelivery_addres()
                                      .getStreet());
        addressForm.setCity(customer.getDelivery_addres()
                                    .getCity());
        addressForm.setCountry(customer.getDelivery_addres()
                                       .getCountry());
        addressForm.setState(customer.getDelivery_addres()
                                     .getState());

        return ResponseEntity.ok(addressForm);
    }

    @PostMapping("/changeAddressesDelivery")
    public void changeAddressesDelivery(@Valid @RequestBody AddressForm addressForm) {

        // aggiorna indirizzo
        Customer customer = customerService.getCustomerById(addressForm.getId());
        customer.getDelivery_addres()
                .setFirstname(addressForm.getFirstname());
        customer.getDelivery_addres()
                .setLastname(addressForm.getLastname());
        customer.getDelivery_addres()
                .setHouseNumber(addressForm.getHouseNumber());
        customer.getDelivery_addres()
                .setStreet(addressForm.getStreet());
        customer.getDelivery_addres()
                .setCity(addressForm.getCity());
        customer.getDelivery_addres()
                .setCountry(addressForm.getCountry());
        customer.getDelivery_addres()
                .setState(addressForm.getState());

        customerService.saveCustomer(customer);
        customerService.setCurrentCustomer(customer);
    }
}
