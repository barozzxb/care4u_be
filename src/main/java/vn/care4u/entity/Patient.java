package vn.care4u.entity;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "patients")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Patient extends User {
    
    private static final long serialVersionUID = 1L;
    
    @Column(name = "email", columnDefinition = "nvarchar(255)")
    private String email;
    
    @Column(name = "insurance", columnDefinition = "nvarchar(255)")
    private String insurance;
    
    @Column(name = "relativePhone", columnDefinition = "nvarchar(255)")
    private String relativePhone;
    
    @Column(name = "province", columnDefinition = "nvarchar(255)")
    private String province;
    
    @Column(name = "district", columnDefinition = "nvarchar(255)")
    private String district;
    
    @Column(name = "ward", columnDefinition = "nvarchar(255)")
    private String ward;
    
    @Column(name = "ethnic", columnDefinition = "nvarchar(255)")
    private String ethnic;
    
    @Column(name = "referralCode", columnDefinition = "nvarchar(255)")
    private String referralCode;
    @OneToOne
    @JoinColumn(name = "account_email", nullable = true)
    @JsonIgnore 
    private Account account;
    
    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore  
    private List<Prediction> predictions = new ArrayList<>();
}