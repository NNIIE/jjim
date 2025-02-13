package com.jjim.favorite.fixture;

import com.jjim.drawer.fixture.DrawerFixture;
import com.jjim.favorite.domain.entity.Favorite;
import com.jjim.favorite.presentation.request.CreateFavoriteRequest;
import com.jjim.favorite.presentation.response.FavoriteResponse;
import com.jjim.user.fixture.UserFixture;

import java.math.BigDecimal;
import java.util.List;

public class FavoriteFixture {

    public static final Long FAVORITE_ID = 1L;
    public static final Long PRODUCT_ID = 1L;

    public static Favorite createMockFavorite() {
        return Favorite.builder()
            .drawerId(DrawerFixture.DRAWER_ID)
            .userId(UserFixture.USER_ID)
            .productId(PRODUCT_ID)
            .build();
    }

    public static FavoriteResponse createFavoriteResponse() {
        return new FavoriteResponse(1L, 1L, "A", "A", BigDecimal.ONE);
    }

    public static List<FavoriteResponse> createMockFavoriteResponses() {
        return List.of(createFavoriteResponse(), createFavoriteResponse());
    }

    public static CreateFavoriteRequest createFavoriteRequest() {
        return new CreateFavoriteRequest(PRODUCT_ID, DrawerFixture.DRAWER_ID);
    }

}
