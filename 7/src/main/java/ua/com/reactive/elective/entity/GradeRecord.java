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
@Table("grade_records")
public class GradeRecord {

    @Id
    private Long id;
    private Long enrollmentId;
    private Integer grade;
}
