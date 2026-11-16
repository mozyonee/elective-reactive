package ua.com.reactive.elective.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table("enrollments")
public class Enrollment {

    @Id
    private Long id;
    private Long studentId;
    private Long courseId;
}
