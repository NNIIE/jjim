package com.jjim.favorite.application;

import com.jjim.common.domain.PagedResponse;
import com.jjim.common.exception.drawer.DrawerException;
import com.jjim.common.exception.drawer.DrawerExceptionCode;
import com.jjim.common.exception.favorite.FavoriteException;
import com.jjim.common.exception.favorite.FavoriteExceptionCode;
import com.jjim.drawer.infra.repository.DrawerRepository;
import com.jjim.favorite.domain.entity.Favorite;
import com.jjim.favorite.infra.repository.FavoriteRepository;
import com.jjim.favorite.presentation.response.FavoriteResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FavoriteService {

    private final FavoriteRepository favoriteRepository;
    private final DrawerRepository drawerRepository;

    @Transactional
    public void createFavorite(
            final UUID userId,
            final Long drawerId,
            final Long productId
    ) {
        existsByFavorite(userId, productId);
        existsByDrawer(userId, drawerId);

        final Favorite favorite = Favorite.builder()
            .userId(userId)
            .drawerId(drawerId)
            .productId(productId)
            .build();

        favoriteRepository.save(favorite);
    }

    @Transactional
    public void deleteFavorite(final UUID userId, final Long favoriteId) {
        final Favorite favorite = favoriteRepository.findById(favoriteId)
            .orElseThrow(() -> new FavoriteException(FavoriteExceptionCode.FAVORITE_NOT_FOUNT));

        if (!favorite.getUserId().equals(userId)) {
            throw new FavoriteException(FavoriteExceptionCode.FAVORITE_NOT_MATCH_USER);
        }

        favoriteRepository.delete(favorite);
    }

    @Transactional(readOnly = true)
    public PagedResponse<FavoriteResponse> getFavorites(
            final UUID userId,
            final Long drawerId,
            final Pageable pageable
    ) {
        final Page<FavoriteResponse> pages = favoriteRepository.getFavorites(userId, drawerId, pageable);

        return new PagedResponse<>(
            pages.getContent(),
            pages.getTotalPages(),
            pages.getTotalElements(),
            pages.getNumber(),
            pages.getSize()
        );
    }

    private void existsByFavorite(final UUID id, final Long productId) {
        if (favoriteRepository.existsByUserIdAndProductId(id, productId)) {
            throw new FavoriteException(FavoriteExceptionCode.EXISTS_FAVORITE);
        }
    }

    private void existsByDrawer(final UUID userId, final Long drawerId) {
        if (!drawerRepository.existsByIdAndUserId(drawerId, userId)) {
            throw new DrawerException(DrawerExceptionCode.DRAWER_NOT_FOUNT);
        }
    }

}
