package com.cabbooking.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cabbooking.entity.Customer;
import com.cabbooking.exception.CabBookingException;
import com.cabbooking.exception.CustomerBookingException;
import com.cabbooking.repository.CustomerRepo;
import com.cabbooking.service.ICustomerService;
@Service
public class ICustomerServiceImpl implements ICustomerService {

	@Autowired
	CustomerRepo customerRepo;
	@Override
	public Customer registerCustomer(Customer customer) {
		if(customer.getRoles().equals("Customer")) {
			return customerRepo.save(customer);
		}
		return null;
	}

	@Override
	public Customer updateCustomer(Customer customer,int customerId)throws CustomerBookingException {
		Optional<Customer> cust = customerRepo.findById(customerId);
		if(cust.isPresent()) {
			Customer updatingCustomer = cust.get();
			updatingCustomer.setCustomerName(customer.getCustomerName());
			updatingCustomer.setUserName(customer.getUserName());
			updatingCustomer.setPassword(customer.getPassword());
			updatingCustomer.setAddress(customer.getAddress());
			updatingCustomer.setMobileNumber(customer.getMobileNumber());
			updatingCustomer.setEmail(customer.getEmail());
			updatingCustomer.setRoles(customer.getRoles());
			
			return customerRepo.save(updatingCustomer);
		}
		throw new  CustomerBookingException("Customer not found");
		
	}

	@Override
	public List<Customer> viewCustomers()throws CustomerBookingException {
		List<Customer> allCustomersList = customerRepo.findAll();
		if(!allCustomersList.isEmpty()) {
		return allCustomersList;
		}
		throw new CustomerBookingException("No Customers found");
	}

	@Override
	public Customer viewCustomerById(Integer customerId)throws CustomerBookingException {
		Optional<Customer> cust = customerRepo.findById(customerId);
		
		if(cust.isPresent()) {
			return cust.get();
		}
		throw new CustomerBookingException("Customer not found");
	}

}
