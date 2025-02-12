package com.jjim.favorite.infra.repository;

import com.jjim.favorite.domain.entity.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FavoriteRepository extends JpaRepository<Favorite, Long>, FavoriteRepositoryCustom {

    boolean existsByUserIdAndProductId(final UUID userId, final Long productId);

}
