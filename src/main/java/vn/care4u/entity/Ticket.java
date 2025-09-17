package vn.care4u.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
@Table(name="ticket")
public class Ticket implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="ticketcode",columnDefinition = "nvarchar(10)",nullable = false,unique = true)
	private String ticketCode;
	
	@Column(name="waitingnumber",columnDefinition = "integer",nullable = false)
	private int waitingNumber;
	
	@Column(name="isexpired",columnDefinition = "boolean",nullable = false)
	private boolean isExpired;

}
