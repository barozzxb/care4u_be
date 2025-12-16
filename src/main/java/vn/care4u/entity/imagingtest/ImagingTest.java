package vn.care4u.entity.imagingtest;

import jakarta.persistence.MappedSuperclass;
import vn.care4u.entity.MedicalTest;

@MappedSuperclass
public abstract class ImagingTest extends MedicalTest{

	protected String image;
}
