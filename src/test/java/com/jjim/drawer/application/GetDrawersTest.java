package com.jjim.drawer.application;

import com.jjim.common.BaseUnitTest;
import com.jjim.common.domain.PagedResponse;
import com.jjim.drawer.domain.entity.Drawer;
import com.jjim.drawer.fixture.DrawerFixture;
import com.jjim.drawer.infra.repository.DrawerRepository;
import com.jjim.drawer.presentation.response.DrawerResponse;
import com.jjim.user.fixture.UserFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class GetDrawersTest extends BaseUnitTest {

    @Mock
    private DrawerRepository drawerRepository;

    @InjectMocks
    private DrawerService drawerService;

    @Test
    @DisplayName("찜서랍 목록 조회 성공")
    void get_drawers_success() {
        // Given
        List<Drawer> drawers = DrawerFixture.createMockDrawers();
        Pageable pageable = PageRequest.of(0, 10);
        Page<Drawer> drawerPage = new PageImpl<>(drawers, pageable, drawers.size());
        when(drawerRepository.findByUserId(UserFixture.USER_ID, pageable)).thenReturn(drawerPage);

        // When
        PagedResponse<DrawerResponse> response = drawerService.getDrawers(UserFixture.USER_ID, pageable);

        // Then
        assertEquals(2, response.elements().size());
    }

}
