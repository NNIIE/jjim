package com.jjim.favorite.application;

import com.jjim.common.BaseUnitTest;
import com.jjim.common.exception.drawer.DrawerException;
import com.jjim.common.exception.drawer.DrawerExceptionCode;
import com.jjim.common.exception.favorite.FavoriteException;
import com.jjim.common.exception.favorite.FavoriteExceptionCode;
import com.jjim.drawer.infra.repository.DrawerRepository;
import com.jjim.favorite.fixture.FavoriteFixture;
import com.jjim.favorite.infra.repository.FavoriteRepository;
import com.jjim.favorite.presentation.request.CreateFavoriteRequest;
import com.jjim.user.fixture.UserFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CreateFavoriteTest extends BaseUnitTest {

    @Mock
    private FavoriteRepository favoriteRepository;

    @Mock
    private DrawerRepository drawerRepository;

    @InjectMocks
    private FavoriteService favoriteService;

    @Test
    @DisplayName("찜 생성 성공")
    void create_favorite_success() {
        // Given
        CreateFavoriteRequest request = FavoriteFixture.createFavoriteRequest();
        when(favoriteRepository.existsByUserIdAndProductId(UserFixture.USER_ID, request.getProductId())).thenReturn(false);
        when(drawerRepository.existsByIdAndUserId(request.getDrawerId(), UserFixture.USER_ID)).thenReturn(true);

        // When
        favoriteService.createFavorite(UserFixture.USER_ID, request);

        // Then
        verify(favoriteRepository).save(argThat(favorite ->
            favorite.getUserId().equals(UserFixture.USER_ID)
                && favorite.getProductId().equals(request.getProductId())
                && favorite.getDrawerId().equals(request.getDrawerId())
        ));
    }

    @Test
    @DisplayName("찜 생성 실패 - 이미 존재하는 찜")
    void create_favorite_exists() {
        // Given
        CreateFavoriteRequest request = FavoriteFixture.createFavoriteRequest();
        when(favoriteRepository.existsByUserIdAndProductId(UserFixture.USER_ID, request.getProductId())).thenReturn(true);

        // When Then
        FavoriteException exception = assertThrows(FavoriteException.class,
            () -> favoriteService.createFavorite(UserFixture.USER_ID, request));
        assertEquals(FavoriteExceptionCode.EXISTS_FAVORITE, exception.getCode());
    }

    @Test
    @DisplayName("찜 생성 실패 - Drawer가 존재하지 않음")
    void create_favorite_not_found_favorite() {
        // Given
        CreateFavoriteRequest request = FavoriteFixture.createFavoriteRequest();
        when(favoriteRepository.existsByUserIdAndProductId(UserFixture.USER_ID, request.getProductId())).thenReturn(false);
        when(drawerRepository.existsByIdAndUserId(request.getDrawerId(), UserFixture.USER_ID)).thenReturn(false);

        // When Then
        DrawerException exception = assertThrows(DrawerException.class,
            () -> favoriteService.createFavorite(UserFixture.USER_ID, request));
        assertEquals(DrawerExceptionCode.DRAWER_NOT_FOUNT, exception.getCode());
    }

}
