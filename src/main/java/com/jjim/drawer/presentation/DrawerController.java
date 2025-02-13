package com.jjim.drawer.presentation;

import com.jjim.common.annotation.CurrentUser;
import com.jjim.common.domain.PagedResponse;
import com.jjim.common.domain.SessionUser;
import com.jjim.drawer.application.DrawerService;
import com.jjim.drawer.presentation.request.CreateDrawerRequest;
import com.jjim.drawer.presentation.response.DrawerResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "Drawer")
@RequestMapping("/drawer")
public class DrawerController {

    private final DrawerService drawerService;

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "찜서랍 목록 조회")
    public PagedResponse<DrawerResponse> getDrawers(
            @Parameter(hidden = true) @CurrentUser final SessionUser user,
            @PageableDefault final Pageable pageable
    ) {
        return drawerService.getDrawers(user.id(), pageable);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "찜서랍 생성")
    public void createDrawer(
            @Parameter(hidden = true) @CurrentUser final SessionUser user,
            @RequestBody @Valid final CreateDrawerRequest request
    ) {
        drawerService.createDrawer(user.id(), request.getName());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "찜서랍 삭제")
    public void deleteDrawer(
            @Parameter(hidden = true) @CurrentUser final SessionUser user,
            @PathVariable final Long id
    ) {
        drawerService.deleteDrawer(user.id(), id);
    }

}
