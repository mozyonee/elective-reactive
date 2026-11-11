package ua.com.reactive.elective.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@NoArgsConstructor
@Table("message")
public class Message {

    @Id
    private Long id;
    private String data;

    public Message(String data) {
        this.data = data;
    }
}
