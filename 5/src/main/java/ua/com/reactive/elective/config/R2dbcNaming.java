package ua.com.reactive.elective.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.relational.core.mapping.DefaultNamingStrategy;
import org.springframework.data.relational.core.mapping.NamingStrategy;
import org.springframework.data.relational.core.mapping.RelationalPersistentProperty;

@Configuration
public class R2dbcNaming {

    @Bean
    public NamingStrategy namingStrategy() {
        return new DefaultNamingStrategy() {
            @Override
            public String getColumnName(RelationalPersistentProperty property) {
                String name = property.getName();
                StringBuilder column = new StringBuilder();
                for (int i = 0; i < name.length(); i++) {
                    char c = name.charAt(i);
                    if (Character.isUpperCase(c) && i > 0) {
                        column.append('_');
                    }
                    column.append(Character.toLowerCase(c));
                }
                return column.toString();
            }
        };
    }
}
