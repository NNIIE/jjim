package com.jjim.drawer.application;

import com.jjim.common.BaseUnitTest;
import com.jjim.common.exception.drawer.DrawerException;
import com.jjim.common.exception.drawer.DrawerExceptionCode;
import com.jjim.drawer.domain.entity.Drawer;
import com.jjim.drawer.fixture.DrawerFixture;
import com.jjim.drawer.infra.repository.DrawerRepository;
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

public class DeleteDrawerTest extends BaseUnitTest {

    @Mock
    private DrawerRepository drawerRepository;

    @Mock
    private FavoriteRepository favoriteRepository;

    @InjectMocks
    private DrawerService drawerService;

    @Test
    @DisplayName("찜서랍 삭제 성공")
    void delete_drawer_success() {
        // Given
        Drawer drawer = DrawerFixture.createMockDrawer();
        when(drawerRepository.findById(DrawerFixture.DRAWER_ID)).thenReturn(Optional.of(drawer));

        // When
        drawerService.deleteDrawer(UserFixture.USER_ID, DrawerFixture.DRAWER_ID);

        // Then
        verify(favoriteRepository).deleteFavoritesByDrawerId(DrawerFixture.DRAWER_ID);
        verify(drawerRepository).delete(drawer);
    }

    @Test
    @DisplayName("찜서랍 삭제 실패- 존재하지 않는 Drawer")
    void delete_drawer_fail_not_found() {
        // Given
        when(drawerRepository.findById(DrawerFixture.DRAWER_ID)).thenReturn(Optional.empty());

        // When Then
        DrawerException exception = assertThrows(DrawerException.class,
            () -> drawerService.deleteDrawer(UserFixture.USER_ID, DrawerFixture.DRAWER_ID));
        assertEquals(DrawerExceptionCode.DRAWER_NOT_FOUNT, exception.getCode());
    }

    @Test
    @DisplayName("찜서랍 삭제 실패 - 유저 불일치")
    void delete_drawer_not_match() {
        // Given
        Drawer drawer = DrawerFixture.createMockDrawer();
        when(drawerRepository.findById(DrawerFixture.DRAWER_ID)).thenReturn(Optional.of(drawer));

        // When Then
        DrawerException exception = assertThrows(DrawerException.class,
            () -> drawerService.deleteDrawer(UUID.randomUUID(), DrawerFixture.DRAWER_ID));
        assertEquals(DrawerExceptionCode.DRAWER_NOT_MATCH_USER, exception.getCode());
    }

}
