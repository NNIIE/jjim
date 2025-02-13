package com.jjim.drawer.application;

import com.jjim.common.BaseUnitTest;
import com.jjim.common.exception.drawer.DrawerException;
import com.jjim.common.exception.drawer.DrawerExceptionCode;
import com.jjim.drawer.fixture.DrawerFixture;
import com.jjim.drawer.infra.repository.DrawerRepository;
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

public class CreateDrawerTest extends BaseUnitTest {

    @Mock
    private DrawerRepository drawerRepository;

    @InjectMocks
    private DrawerService drawerService;

    @Test
    @DisplayName("찜서랍 생성 성공")
    void create_drawer_success() {
        // Given
        when(drawerRepository.existsByUserIdAndName(UserFixture.USER_ID, DrawerFixture.DRAWER_NAME)).thenReturn(false);

        // When
        drawerService.createDrawer(UserFixture.USER_ID, DrawerFixture.DRAWER_NAME);

        // Then
        verify(drawerRepository).save(argThat(drawer ->
            drawer.getUserId().equals(UserFixture.USER_ID) && drawer.getName().equals(DrawerFixture.DRAWER_NAME)
        ));
    }

    @Test
    @DisplayName("찜서랍 생성 실패 - 이미 존재하는 이름")
    void create_drawer_fail_exists_name() {
        // Given
        when(drawerRepository.existsByUserIdAndName(UserFixture.USER_ID, DrawerFixture.DRAWER_NAME)).thenReturn(true);

        // When Then
        DrawerException exception = assertThrows(DrawerException.class,
            () -> drawerService.createDrawer(UserFixture.USER_ID, DrawerFixture.DRAWER_NAME));
        assertEquals(DrawerExceptionCode.EXISTS_DRAWER_NAME, exception.getCode());
    }

}
