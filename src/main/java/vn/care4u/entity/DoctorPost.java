package vn.care4u.entity;

import jakarta.persistence.*;
import lombok.*;
import vn.care4u.enumeration.PostStatus;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "doctor_posts")
@Getter 
@Setter
@NoArgsConstructor 
@AllArgsConstructor
public class DoctorPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    private String thumbnail;

    @Enumerated(EnumType.STRING)
    private PostStatus status;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor; // 🔥 gắn bác sĩ

    private LocalDateTime createdAt;
}
