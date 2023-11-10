package com.cabbooking.serviceimpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.cabbooking.entity.TripBooking;
import com.cabbooking.exception.CabBookingException;
import com.cabbooking.exception.CustomerBookingException;
import com.cabbooking.exception.TripBookingException;
import com.cabbooking.repository.TripBookingRepo;
import com.cabbooking.service.ITripBookingService;

@Service
public class ITripBookingServiceImpl implements ITripBookingService {
	
	@Autowired
	TripBookingRepo tripBookingRepo;

	@Override
	public TripBooking addTripBooking(TripBooking tripBooking) {
		// TODO Auto-generated method stub
		int id = tripBooking.getTripBookingId();
		Optional<TripBooking> check = tripBookingRepo.findById(id);
		if(check.isPresent()) {
			return tripBookingRepo.save(check.get());
		}
		else {
			return null;
		}
	}

	@Override
	public TripBooking updateTripBooking(TripBooking tripBooking) throws TripBookingException{
		int id = tripBooking.getTripBookingId();
		Optional<TripBooking> check = tripBookingRepo.findById(id);
		if(check.isPresent()) {
			TripBooking updatetripBook = check.get();
			updatetripBook.setDropoffLocation(tripBooking.getDropoffLocation());
			updatetripBook.setCabType(tripBooking.getCabType());
			updatetripBook.setStartDateTime(tripBooking.getStartDateTime());
			updatetripBook.setBookingStatus(tripBooking.getBookingStatus());
			updatetripBook.setDistanceInKm(tripBooking.getDistanceInKm());
			updatetripBook.setCustomer(tripBooking.getCustomer());
			updatetripBook.setPickupLocation(tripBooking.getPickupLocation());
			updatetripBook.setBill(tripBooking.getBill());
			
			return tripBookingRepo.save(updatetripBook);
			
		}
		throw new TripBookingException("Trip not found");
		
	}

	@Override
	public TripBooking cancleTripBooking(Integer tripBookingId) throws TripBookingException{
		// TODO Auto-generated method stub
		Optional<TripBooking> check = tripBookingRepo.findById(tripBookingId);
		if(check.isPresent()) {
			TripBooking deleteTripBooking = check.get();
			tripBookingRepo.delete(deleteTripBooking);
			return deleteTripBooking;
		}
		throw new TripBookingException("Trip not found");
		
	}

	@Override
	public List<TripBooking> viewAllBookings()throws TripBookingException {
		// TODO Auto-generated method stub
		List<TripBooking> check = tripBookingRepo.findAll();
		List<TripBooking> viewList = new ArrayList<>();
		for( TripBooking trip : check) {
			viewList.add(trip);
		}
		if(!viewList.isEmpty()) {
			return viewList;
		}
		throw new TripBookingException("Trips not found");

		
	}

	@Override
	public TripBooking viewBookingByBookingId(Integer tripBookingId) throws TripBookingException{
		
		Optional<TripBooking> check = tripBookingRepo.findById(tripBookingId);
		if(check.isPresent()) {
			return check.get();
		}
		throw new TripBookingException("Trips not found by bookingId");

		
	}

	@Override
	public List<TripBooking> viewBookingByCustomerId(Integer customerId) throws CustomerBookingException{
		
		List<TripBooking> tripsList = tripBookingRepo.findAll().stream().filter(e->e.getCustomer().getUserId()==customerId).collect(Collectors.toList());
		if(!tripsList.isEmpty()) {
			return tripsList;
		}
		throw new CustomerBookingException("Trips not found by customerId");

//		return  tripBookingRepo.findAll().stream().filter(e->e.getCustomer().getUserId()==customerId).collect(Collectors.toList());
	}

	@Override
	public List<TripBooking> viewBookingByBookingStatus(String status) throws TripBookingException{
		List<TripBooking> tripsListByStatus = tripBookingRepo.findAll().stream().filter(e->e.getBookingStatus().toString().equals(status)).collect(Collectors.toList());
		if(!tripsListByStatus.isEmpty()) {
			return tripsListByStatus;
		}
		throw new TripBookingException("Trips not found by bookingStatus");
//		return  tripBookingRepo.findAll().stream().filter(e->e.getBookingStatus().toString().equals(status)).collect(Collectors.toList());
	}

	@Override
	public List<TripBooking> viewBookingByCabType(String cabType)throws CabBookingException {
		List<TripBooking> tripsByCabType =tripBookingRepo.findAll().stream().filter(e->e.getCabType().toString().equals(cabType)).collect(Collectors.toList());
		if(!tripsByCabType.isEmpty()) {
			return tripsByCabType;
		}
		throw new CabBookingException("Trips not found by bookingStatus");
		
//		return tripBookingRepo.findAll().stream().filter(e->e.getCabType().toString().equals(cabType)).collect(Collectors.toList());
	}

	@Override
	public List<TripBooking> viewBookingsByDatewiseSortingOrder() throws TripBookingException{
		// TODO Auto-generated method stub
//		List<TripBooking> tripsByDatewise =
//		if(!tripsByDatewise.isEmpty()) {
//			return tripsByDatewise;
//		}
//		throw new TripBookingException("Trips not found by Datewise");
//		
		return null;
//				tripBookingRepo.findAll().stream().filter(e->e.getStartDateTime()!=null).sorted(Comparator.comparing(TripBooking::getStartDateTime)).collect(Collectors.toList());
	}

	@Override
	public List<TripBooking> viewBookingsByCustomerBasedOnDates(Integer customerId, LocalDate fromDate,
			LocalDate toDate) throws CustomerBookingException{
//		List<TripBooking> tripsByCustomer =
//		if(!tripsByCustomer.isEmpty()) {
//			return tripsByCustomer;
//		}
//		throw new TripBookingException("Trips not found by Customer");
		return null;
	}

	
}
