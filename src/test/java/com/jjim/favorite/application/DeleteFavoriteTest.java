package com.jjim.favorite.application;

import com.jjim.common.BaseUnitTest;
import com.jjim.common.exception.favorite.FavoriteException;
import com.jjim.common.exception.favorite.FavoriteExceptionCode;
import com.jjim.favorite.domain.entity.Favorite;
import com.jjim.favorite.fixture.FavoriteFixture;
import com.jjim.favorite.infra.repository.FavoriteRepository;
import com.jjim.user.fixture.UserFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DeleteFavoriteTest extends BaseUnitTest {

    @Mock
    private FavoriteRepository favoriteRepository;

    @InjectMocks
    private FavoriteService favoriteService;

    @Test
    @DisplayName("찜 삭제 성공")
    void delete_favorite_success() {
        // Given
        Favorite favorite = FavoriteFixture.createMockFavorite();
        when(favoriteRepository.findById(FavoriteFixture.FAVORITE_ID)).thenReturn(Optional.of(favorite));

        // When
        favoriteService.deleteFavorite(UserFixture.USER_ID, FavoriteFixture.FAVORITE_ID);

        // Then
        verify(favoriteRepository).delete(favorite);
    }

    @Test
    @DisplayName("찜 삭제 실패 - 존재하지 않는 찜")
    void delete_favorite_fail_not_found() {
        // Given
        when(favoriteRepository.findById(FavoriteFixture.FAVORITE_ID)).thenReturn(Optional.empty());

        // When Then
        FavoriteException exception = assertThrows(FavoriteException.class,
            () -> favoriteService.deleteFavorite(UserFixture.USER_ID, FavoriteFixture.FAVORITE_ID));
        assertEquals(FavoriteExceptionCode.FAVORITE_NOT_FOUNT, exception.getCode());
    }

    @Test
    @DisplayName("찜 삭제 실패 - 유저 불일치")
    void delete_favorite_fail_not_match_user() {
        // Given
        Favorite favorite = FavoriteFixture.createMockFavorite();
        when(favoriteRepository.findById(FavoriteFixture.FAVORITE_ID)).thenReturn(Optional.of(favorite));

        // When Then
        FavoriteException exception = assertThrows(FavoriteException.class,
            () -> favoriteService.deleteFavorite(UUID.randomUUID(), FavoriteFixture.FAVORITE_ID));
        assertEquals(FavoriteExceptionCode.FAVORITE_NOT_MATCH_USER, exception.getCode());
    }


}
