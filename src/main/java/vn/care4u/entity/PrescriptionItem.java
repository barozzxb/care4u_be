package vn.care4u.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class PrescriptionItem {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Prescription prescription;

    @ManyToOne(optional = true)
    private Drug drug;

    private String name;

    private String dose;

    private Integer quantity;

    private String note;
}
