package vn.care4u.entity;

import jakarta.persistence.Column;
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
@JsonIgnoreProperties({"appointments", "medicalRecords", "prescriptions"})
public class Patient extends User {
    
    private static final long serialVersionUID = 1L;
    
    // Email để liên hệ
    @Column(name = "email", columnDefinition = "nvarchar(255)")
    private String email;
    
    // Bảo hiểm y tế
    @Column(name = "insurance", columnDefinition = "nvarchar(255)")
    private String insurance;
    
    // Số điện thoại người thân
    @Column(name = "relativePhone", columnDefinition = "nvarchar(255)")
    private String relativePhone;
    
    // Địa chỉ chi tiết - Tỉnh/Thành phố
    @Column(name = "province", columnDefinition = "nvarchar(255)")
    private String province;
    
    // Quận/Huyện
    @Column(name = "district", columnDefinition = "nvarchar(255)")
    private String district;
    
    // Phường/Xã
    @Column(name = "ward", columnDefinition = "nvarchar(255)")
    private String ward;
    
    // Dân tộc
    @Column(name = "ethnic", columnDefinition = "nvarchar(255)")
    private String ethnic;
    
    // Mã giới thiệu
    @Column(name = "referralCode", columnDefinition = "nvarchar(255)")
    private String referralCode;
    
    // Quan hệ với Account - THÊM @JsonIgnore
    @OneToOne
    @JoinColumn(name = "account_email", nullable = true)
    @JsonIgnore  // ⭐ QUAN TRỌNG: Ngăn circular reference
    private Account account;
    
    // Quan hệ với Prediction - THÊM @JsonIgnore
    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore  // ⭐ QUAN TRỌNG: Ngăn circular reference
    private List<Prediction> predictions = new ArrayList<>();

}
