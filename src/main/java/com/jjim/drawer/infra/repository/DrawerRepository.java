package com.jjim.drawer.infra.repository;

import com.jjim.drawer.domain.entity.Drawer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DrawerRepository extends JpaRepository<Drawer, Long> {

    boolean existsByUserIdAndName(final UUID userId, final String name);

    boolean existsByIdAndUserId(final Long id, final UUID userId);

    Page<Drawer> findByUserId(final UUID userId, final Pageable pageable);

}
