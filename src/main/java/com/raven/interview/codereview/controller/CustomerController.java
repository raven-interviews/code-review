package com.raven.interview.codereview.controller;

import com.raven.interview.codereview.model.Customer;
import com.raven.interview.codereview.repository.CustomerRepository;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @PostMapping
    public void addCustomer(@RequestBody Collection<Customer> customers) {
        for (Customer customer : customers) {
            if (customer.getFirstName() == null) {
                return new ResponseEntity("First name is null", HttpStatus.BAD_REQUEST)
            }

            if (customer.getLastName() == null)
            {
                return new ResponseEntity("Last name is null", HttpStatus.BAD_REQUEST);
            }

            if (customer.getDateOfBirth() == null)
                return new ResponseEntity("Date of birth is null", HttpStatus.BAD_REQUEST);

            if (customer.getEmail() == null) {
                return new ResponseEntity("Email is null", HttpStatus.BAD_REQUEST);
            }

            customerRepository.save(customer);
        }
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable long id){
        customerRepository.deleteById(id);
    }
}
