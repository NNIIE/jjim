package com.jjim.favorite.infra.repository;

import com.jjim.favorite.presentation.response.FavoriteResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface FavoriteRepositoryCustom {

    Page<FavoriteResponse> getFavorites(final UUID userId, final Long drawerId, final Pageable pageable);

    void deleteFavoritesByDrawerId(final Long drawerId);
}
