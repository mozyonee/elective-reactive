package ua.com.reactive.elective.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GradeRecord {

    private Long id;
    private Long enrollmentId;
    private Integer grade;
}
