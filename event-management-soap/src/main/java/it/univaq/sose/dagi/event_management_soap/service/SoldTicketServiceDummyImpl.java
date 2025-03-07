package it.univaq.sose.dagi.event_management_soap.service;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import it.univaq.sose.dagi.event_management_soap.model.SoldTicket;

@Service
public class SoldTicketServiceDummyImpl implements SoldTicketService {

	private static long nextId = 0L;
	private static List<SoldTicket> soldTickets = new ArrayList<>();
	
	//The constructor initializes a collection of SoldTicket instances, each with a unique ID and specific attributes such as userId, eventId, and referenceDate.
	//This setup provides a basic dataset for testing or development purposes.
	public SoldTicketServiceDummyImpl() {
		SoldTicket t0 = new SoldTicket(nextId++, 0L, 0L, null);
		t0.setReferenceDate(LocalDateTime.of(2024, Month.JULY, 29, 18, 0));
		SoldTicket t1 = new SoldTicket(nextId++, 2L, 0L, null);
		t1.setReferenceDate(LocalDateTime.of(2024, Month.JULY, 29, 18, 0));
		SoldTicket t2 = new SoldTicket(nextId++, 1L, 2L, null);
		t2.setReferenceDate(LocalDateTime.of(2024, Month.AUGUST, 12, 21, 0));
		SoldTicket t3 = new SoldTicket(nextId++, 4L, 2L, null);
		t3.setReferenceDate(LocalDateTime.of(2024, Month.AUGUST, 12, 21, 0));
		SoldTicket t4 = new SoldTicket(nextId++, 0L, 1L, null);
		t4.setReferenceDate(LocalDateTime.of(2024, Month.JULY, 30, 23, 0));
		SoldTicket t5 = new SoldTicket(nextId++, 2L, 1L, null);
		t5.setReferenceDate(LocalDateTime.of(2024, Month.JULY, 30, 23, 0));
		SoldTicket t6 = new SoldTicket(nextId++, 3L, 2L, null);
		t6.setReferenceDate(LocalDateTime.of(2024, Month.AUGUST, 12, 21, 0));
		SoldTicket t7 = new SoldTicket(nextId++, 1L, 3L, null);
		t6.setReferenceDate(LocalDateTime.of(2025, Month.JUNE, 24, 17, 0));
		
		soldTickets.add(t0);
		soldTickets.add(t1);
		soldTickets.add(t2);
		soldTickets.add(t3);
		soldTickets.add(t4);
		soldTickets.add(t5);
		soldTickets.add(t6);
		soldTickets.add(t7);
		
	}
	
	//This method creates a new SoldTicket object. It assigns a unique ID to the newSoldTicket, adds it to the collection, and returns the newly created SoldTicket.
	@Override
	public SoldTicket create(SoldTicket newSoldTicket) {
		newSoldTicket.setId(nextId++);
		soldTickets.add(newSoldTicket);
		return newSoldTicket;
	}

	//This method updates an existing SoldTicket object. It first checks if the soldTicket has a non-null ID; otherwise, it throws an IllegalArgumentException.
	//It then searches for the SoldTicket in the collection and updates its attributes. If the SoldTicket is found, it returns the updated instance; otherwise, it throws a NoSuchElementException.
	@Override
	public SoldTicket update(SoldTicket soldTicket) throws IllegalArgumentException, NoSuchElementException {
		if(soldTicket.getId() == null) {
			throw new IllegalArgumentException("ID cannot be null.");
		}
		SoldTicket found = null;
		for(SoldTicket current : soldTickets) {
			if(current.equals(soldTicket)) {
				current.setUserId(soldTicket.getUserId());
				current.setEventId(soldTicket.getEventId());
				current.setReferenceDate(soldTicket.getReferenceDate());
				
				found = current;
				break;
			}
		}
		if(found == null) {
			throw new NoSuchElementException("Ticket sale info to update not found.");
		}
		return found;
	}

	//This method deletes a SoldTicket object from the collection. It requires the soldTicket to have a non-null ID; otherwise, it throws an IllegalArgumentException.
	//It iterates through the collection to find and remove the SoldTicket. If not found, it throws a NoSuchElementException.
	@Override
	public void delete(SoldTicket soldTicket) throws IllegalArgumentException, NoSuchElementException {
		if(soldTicket.getId() == null) {
			throw new IllegalArgumentException("ID cannot be null.");
		}
		for(Iterator<SoldTicket> itr = soldTickets.listIterator(); itr.hasNext();) {
			SoldTicket current = itr.next();
			if(current.equals(soldTicket)) {
				itr.remove();
				return;
			}
		}
		throw new NoSuchElementException("Ticket sale info to delete not found.");
	}

	//This method retrieves a SoldTicket object by its ID. It iterates through the collection to find a match.
	//If found, it returns the SoldTicket; otherwise, it throws a NoSuchElementException.
	@Override
	public SoldTicket findById(long id) throws NoSuchElementException {
		for(SoldTicket current : soldTickets) {
			if(current.getId().equals(id)) {
				return current;
			}
		}
		throw new NoSuchElementException("Ticket sale info not found.");
	}

	//This method returns a list of all SoldTicket objects in the collection.
	@Override
	public List<SoldTicket> getAll() {
		return List.copyOf(soldTickets);
	}

	//This method returns a list of SoldTicket objects associated with a specific event, identified by eventId.
	//It filters the soldTickets collection to include only those with the matching event ID.
	@Override
	public List<SoldTicket> findByEventId(long eventId) {
		List<SoldTicket> result = new ArrayList<>();
		for(SoldTicket current : soldTickets) {
			if(current.getEventId().equals(eventId)) {
				result.add(current);
			}
		}
		return result;
	}

	@Override
	public List<SoldTicket> findByCustomerId(long customerId) {
		List<SoldTicket> result = new ArrayList<>();
		for(SoldTicket current : soldTickets) {
			if(current.getUserId().equals(customerId)) {
				result.add(current);
			}
		}
		return result;
	}
	
	
	
}
