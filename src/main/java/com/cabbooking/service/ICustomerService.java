package com.cabbooking.service;

import java.util.List;

import com.cabbooking.dto.CustomerDTO;
import com.cabbooking.entity.Customer;
import com.cabbooking.exception.CabBookingException;
import com.cabbooking.exception.CustomerBookingException;

public interface ICustomerService {

	public Customer registerCustomer(Customer customer);

	public Customer updateCustomer(Customer customer,int customerId) throws CustomerBookingException;

	public List<Customer> viewCustomers()throws CustomerBookingException;

	public Customer viewCustomerById(Integer customerId)throws CustomerBookingException;
}
