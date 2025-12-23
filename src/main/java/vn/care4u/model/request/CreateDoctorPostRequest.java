package vn.care4u.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateDoctorPostRequest {
    private String title;
    private String content;
    private String thumbnail;
}
