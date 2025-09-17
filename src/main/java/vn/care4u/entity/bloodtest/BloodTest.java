package vn.care4u.entity.bloodtest;

import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import vn.care4u.entity.MedicalTest;

public class BloodTest extends MedicalTest{
	
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "cbc_id", referencedColumnName = "id")
    private CBC cbc;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "biochemistry_id", referencedColumnName = "id")
    private Biochemistry biochemistry;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "electrolytes_id", referencedColumnName = "id")
    private Electrolytes electrolytes;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "thyroid_id", referencedColumnName = "id")
    private Thyroid thyroid;
}
