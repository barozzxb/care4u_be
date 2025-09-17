package vn.care4u.entity.bloodtest;

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
@Table(name = "cbc")
public class CBC {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double rbc;          // Red Blood Cell
    private double hb;           // Hemoglobin
    private double hct;          // Hematocrit
    private double mcv;          // Mean Corpuscular Volume
    private double mch;          // Mean Corpuscular Hemoglobin
    private double mchc;         // Mean Corpuscular Hb Concentration
    private double wbc;          // White Blood Cell
    private double neutrophils;  // %
    private double lymphocytes;  // %
    private double platelets;    // Tiểu cầu
}
