package it.univaq.sose.dagi.event_management_soap.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ticket_info")
public class TicketInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private Long eventId;
	@Column(nullable = false)
	private LocalDateTime referenceDate;
	@Column(nullable = false)
	private int availableTickets;
	
	public TicketInfo(Long id, Long eventId, LocalDateTime referenceDate, int availableTickets) {
		super();
		this.id = id;
		this.eventId = eventId;
		this.referenceDate = referenceDate;
		this.availableTickets = availableTickets;
	}
	
	public TicketInfo(Long eventId, LocalDateTime referenceDate, int availableTickets) {
		super();
		this.eventId = eventId;
		this.referenceDate = referenceDate;
		this.availableTickets = availableTickets;
	}

	public TicketInfo() {
		super();
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getEventId() {
		return eventId;
	}
	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}
	public LocalDateTime getReferenceDate() {
		return referenceDate;
	}
	public void setReferenceDate(LocalDateTime referenceDate) {
		this.referenceDate = referenceDate;
	}
	public int getAvailableTickets() {
		return availableTickets;
	}
	public void setAvailableTickets(int availableTickets) {
		this.availableTickets = availableTickets;
	}
	
	//This method compares whether two TicketInfo objects are equal. 
	//First checks whether the passed object is null or not of type TicketInfo; in that case, it returns false.
	//If the object is of the correct type, compare the IDs of the two objects.
	//If the IDs are equal, it returns true, otherwise false.
	@Override
	public boolean equals(Object obj) {
		if(obj == null || !(obj instanceof TicketInfo)) return false;
		TicketInfo casted = (TicketInfo) obj;
		return this.getId().equals(casted.getId());
	}
}
