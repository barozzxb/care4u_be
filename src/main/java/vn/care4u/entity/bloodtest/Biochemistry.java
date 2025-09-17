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
@Table(name = "biochemistry")
public class Biochemistry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double ast;          // SGOT
    private double alt;          // SGPT
    private double ggt;          // Gamma GT
    private double alp;          // Alkaline Phosphatase
    private double bilirubin;    // Total
    private double urea;
    private double creatinine;
    private double egfr;
    private double glucose;
    private double hba1c;
    private double cholesterol;
    private double triglycerides;
    private double hdl;
    private double ldl;
}