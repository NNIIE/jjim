package com.jjim.favorite.presentation;

import com.jjim.common.annotation.CurrentUser;
import com.jjim.common.domain.PagedResponse;
import com.jjim.common.domain.SessionUser;
import com.jjim.favorite.application.FavoriteService;
import com.jjim.favorite.presentation.response.FavoriteResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "Favorite")
@RequestMapping("/favorite")
public class FavoriteController {

    private final FavoriteService favoriteService;

    @PostMapping("/drawer/{drawerId}/product/{productId}")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "찜 추가")
    public void createFavorite(
            @Parameter(hidden = true) @CurrentUser final SessionUser user,
            @PathVariable final Long drawerId,
            @PathVariable final Long productId
    ) {
        favoriteService.createFavorite(user.id(), drawerId, productId);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "찜 삭제")
    public void deleteFavorite(
            @Parameter(hidden = true) @CurrentUser final SessionUser user,
            @PathVariable final Long id
    ) {
        favoriteService.deleteFavorite(user.id(), id);
    }

    @GetMapping("/drawer/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "찜 목록 조회")
    public PagedResponse<FavoriteResponse> getFavoritesByDrawerId(
            @Parameter(hidden = true) @CurrentUser final SessionUser user,
            @PathVariable final Long id,
            @PageableDefault final Pageable pageable
    ) {
        return favoriteService.getFavorites(user.id(), id, pageable);
    }

}
