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
@Table(name = "electrolytes")
public class Electrolytes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double sodium;     // Na+
    private double potassium;  // K+
    private double chloride;   // Cl-
    private double calcium;    // Ca2+
    private double magnesium;  // Mg2+
}
