package com.jjim.favorite.application;

import com.jjim.common.BaseUnitTest;
import com.jjim.common.domain.PagedResponse;
import com.jjim.drawer.fixture.DrawerFixture;
import com.jjim.favorite.fixture.FavoriteFixture;
import com.jjim.favorite.infra.repository.FavoriteRepository;
import com.jjim.favorite.presentation.response.FavoriteResponse;
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

public class GetFavoritesTest extends BaseUnitTest {

    @Mock
    private FavoriteRepository favoriteRepository;

    @InjectMocks
    private FavoriteService favoriteService;

    @Test
    @DisplayName("찜 목록 조회 성공")
    void get_favorites_success() {
        // Given
        Pageable pageable = PageRequest.of(0, 10);
        List<FavoriteResponse> favoriteResponses = FavoriteFixture.createMockFavoriteResponses();
        Page<FavoriteResponse> page = new PageImpl<>(favoriteResponses, pageable, favoriteResponses.size());
        when(favoriteRepository.getFavorites(UserFixture.USER_ID, DrawerFixture.DRAWER_ID, pageable)).thenReturn(page);

        // When
        PagedResponse<FavoriteResponse> result = favoriteService.getFavorites(UserFixture.USER_ID, DrawerFixture.DRAWER_ID, pageable);

        // Then
        assertEquals(2, result.elements().size());
    }

}
