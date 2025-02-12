package com.jjim.favorite.presentation.response;

import java.math.BigDecimal;


public record FavoriteResponse(
    Long favoriteId,
    Long productId,
    String productName,
    String thumbnailUrl,
    BigDecimal price
) {}
