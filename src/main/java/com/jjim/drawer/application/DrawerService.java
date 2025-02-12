package com.jjim.drawer.application;

import com.jjim.common.domain.PagedResponse;
import com.jjim.common.exception.drawer.DrawerException;
import com.jjim.common.exception.drawer.DrawerExceptionCode;
import com.jjim.drawer.domain.entity.Drawer;
import com.jjim.drawer.infra.repository.DrawerRepository;
import com.jjim.drawer.presentation.response.DrawerResponse;
import com.jjim.favorite.infra.repository.FavoriteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DrawerService {

    private final DrawerRepository drawerRepository;
    private final FavoriteRepository favoriteRepository;

    @Transactional(readOnly = true)
    public PagedResponse<DrawerResponse> getDrawers(final UUID userId, final Pageable pageable) {
        final Page<Drawer> pages = drawerRepository.findByUserId(userId, pageable);
        final List<DrawerResponse> drawerResponses = pages.getContent()
            .stream()
            .map(drawer -> new DrawerResponse(drawer.getId(), drawer.getName()))
            .toList();

        return new PagedResponse<>(
            drawerResponses,
            pages.getTotalPages(),
            pages.getTotalElements(),
            pages.getNumber(),
            pages.getSize()
        );
    }

    @Transactional
    public void createDrawer(final UUID userId, final String name) {
        existsByUserIdAndName(userId, name);

        final Drawer drawer = Drawer.builder()
            .userId(userId)
            .name(name)
            .build();

        drawerRepository.save(drawer);
    }

    @Transactional
    public void deleteDrawer(final UUID userId, final Long drawerId) {
        final Drawer drawer = drawerRepository.findById(drawerId)
            .orElseThrow(() -> new DrawerException(DrawerExceptionCode.DRAWER_NOT_FOUNT));

        if (!drawer.getUserId().equals(userId)) {
            throw new DrawerException(DrawerExceptionCode.DRAWER_NOT_MATCH_USER);
        }

        favoriteRepository.deleteFavoritesByDrawerId(drawerId);
        drawerRepository.delete(drawer);

    }

    private void existsByUserIdAndName(final UUID userId, final String name) {
        if (drawerRepository.existsByUserIdAndName(userId, name)) {
            throw new DrawerException(DrawerExceptionCode.EXISTS_DRAWER_NAME);
        }
    }

}
