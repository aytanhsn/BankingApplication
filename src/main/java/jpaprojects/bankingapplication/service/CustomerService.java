package jpaprojects.bankingapplication.service;
import jakarta.transaction.Transactional;
import jpaprojects.bankingapplication.entity.Customer;
import jpaprojects.bankingapplication.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {
    private CustomerRepository customerRepository;
    public CustomerService(CustomerRepository customerRepository){
        this.customerRepository=customerRepository;
    }
    public Customer addCustomer(Customer customer){
        return customerRepository.save(customer);
    }
    public Optional<Customer> getCustomerById(Integer id){
        return customerRepository.findById(id);
    }
    @Transactional
    public Customer topUpBalance(Integer id,Double amount ){
        Customer customer=customerRepository.findById(id).orElseThrow();
        customer.setBalance(customer.getBalance()+amount);
        return customerRepository.save(customer);
    }
    @Transactional
    public Customer pushBalance(Integer id,Double amount){
        Customer customer=customerRepository.findById(id).orElseThrow();
        if(customer.getBalance()>=amount){
            customer.setBalance(customer.getBalance()-amount);
        }
        return customerRepository.save(customer);
    }
    @Transactional
    public Customer refundBalance(Integer id,double amount){
        Customer customer=customerRepository.findById(id).orElseThrow();
        customer.setBalance(customer.getBalance()+amount);
        return customerRepository.save(customer);
    }

}
