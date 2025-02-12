package com.jjim.common.domain;

import java.util.List;

public record PagedResponse<T>(
        List<T> elements,
        int totalPages,
        long totalElements,
        int pageNumber,
        int pageSize
) {}
