package com.jjim.drawer.fixture;

import com.jjim.drawer.domain.entity.Drawer;
import com.jjim.user.fixture.UserFixture;

import java.util.List;

public class DrawerFixture {

    public static final Long DRAWER_ID = 1L;
    public static final String DRAWER_NAME = "drawer";

    public static Drawer createMockDrawer() {
        return Drawer.builder()
            .userId(UserFixture.USER_ID)
            .name(DRAWER_NAME)
            .build();
    }

    public static List<Drawer> createMockDrawers() {
        return List.of(createMockDrawer(), createMockDrawer());
    }

}
