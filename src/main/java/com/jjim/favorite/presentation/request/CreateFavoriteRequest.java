package com.jjim.favorite.presentation.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class CreateFavoriteRequest {

    @NotNull(message = "필수 입력 입니다.")
    @Positive(message = "숫자만 가능합니다.")
    private Long productId;

    @NotNull(message = "필수 입력 입니다.")
    @Positive(message = "숫자만 가능합니다.")
    private Long drawerId;

}
