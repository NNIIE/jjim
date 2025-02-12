package com.jjim.drawer.domain.entity;

import com.jjim.common.domain.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Drawer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(name = "user_id",columnDefinition = "VARCHAR(36)",  updatable = false, nullable = false)
    private UUID userId;

    @Column(name = "name", nullable = false)
    private String name;

    @Builder
    public Drawer(
            final UUID userId,
            final String name
    ) {
        this.userId = userId;
        this.name = name;
    }

}
