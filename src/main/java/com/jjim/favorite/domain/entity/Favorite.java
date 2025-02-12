package com.jjim.favorite.domain.entity;

import com.jjim.common.domain.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Favorite extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "drawer_id", nullable = false)
    private Long drawerId;

    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(name = "user_id",columnDefinition = "VARCHAR(36)",  updatable = false, nullable = false)
    private UUID userId;

    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Builder
    public Favorite(
            final Long drawerId,
            final UUID userId,
            final Long productId
    ) {
        this.drawerId = drawerId;
        this.userId = userId;
        this.productId = productId;
    }

}
