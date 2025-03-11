package jpaprojects.bankingapplication.controller;

import jpaprojects.bankingapplication.entity.Customer;
import jpaprojects.bankingapplication.service.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    private CustomerService customerService;
    public CustomerController(CustomerService customerService) {
        this.customerService=customerService;
    }
    @PostMapping("/create")
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerService.addCustomer(customer);
    }
    @GetMapping("/{id}")
    public Optional<Customer> findCustomer(Integer id) {
        return customerService.getCustomerById(id);
    }
    @PostMapping("/{id}/top-up-balance")
    public Customer topUpBalance(@PathVariable Integer id,@RequestParam Double amount){
        return customerService.topUpBalance(id,amount);
    }
    @PostMapping("/{id}/push-balance")
    public Customer pushBalance(@PathVariable Integer id,@RequestParam Double amount){
        return customerService.pushBalance(id,amount);
    }
    @PostMapping("/{id}/refund-balance")
    public Customer refundCustomer(@PathVariable Integer id, @RequestParam Double amount){
        return customerService.refundBalance(id,amount);
    }
}
