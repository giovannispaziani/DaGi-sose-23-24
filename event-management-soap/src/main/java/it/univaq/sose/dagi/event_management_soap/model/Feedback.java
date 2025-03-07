package it.univaq.sose.dagi.event_management_soap.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "feedback")
public class Feedback {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private Long userId;
	@Column(nullable = false)
	private Long eventId;
	@Column(nullable = false)
	private int rating;
	@Column(nullable = true)
	private String body;
	
	public Feedback(Long id, Long userId, Long eventId, int rating, String body) {
		super();
		this.id = id;
		this.userId = userId;
		this.eventId = eventId;
		this.rating = rating;
		this.body = body;
	}
	
	public Feedback(Long userId, Long eventId, int rating, String body) {
		super();
		this.userId = userId;
		this.eventId = eventId;
		this.rating = rating;
		this.body = body;
	}
	
	public Feedback() {
		super();
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getEventId() {
		return eventId;
	}
	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	
	//The equals method compares two Feedback objects to determine whether they are equal.
	//If the object passed is null or is not a Feedback instance, returns false.
	//If the object is of the correct type, it compares the object IDs and returns true if they are equal, false otherwise.
	@Override
	public boolean equals(Object obj) {
		if(obj == null || !(obj instanceof Feedback)) return false;
		Feedback casted = (Feedback) obj;
		return this.getId().equals(casted.getId());
	}
}
