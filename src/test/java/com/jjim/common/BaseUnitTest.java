package com.jjim.common;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.MockitoAnnotations;

public abstract class BaseUnitTest {

    @BeforeEach
    public void initMocks() {
        MockitoAnnotations.openMocks(this);
    }

}
