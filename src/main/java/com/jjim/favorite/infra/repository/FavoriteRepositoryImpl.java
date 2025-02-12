package com.jjim.favorite.infra.repository;

import com.jjim.favorite.presentation.response.FavoriteResponse;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

import static com.jjim.favorite.domain.entity.QFavorite.favorite;
import static com.jjim.product.domain.entity.QProduct.product;

@Repository
@RequiredArgsConstructor
public class FavoriteRepositoryImpl implements FavoriteRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;


    @Override
    public Page<FavoriteResponse> getFavorites(final UUID userId, final Long drawerId, final Pageable pageable) {
        final List<FavoriteResponse> favoriteResponses = jpaQueryFactory
            .select(Projections.constructor(FavoriteResponse.class,
                favorite.id,
                product.id,
                product.name,
                product.thumbnailUrl,
                product.price))
            .from(favorite)
            .join(product).on(favorite.productId.eq(product.id))
            .where(
                favorite.userId.eq(userId)
                    .and(favorite.drawerId.eq(drawerId))
            )
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();

        final JPAQuery<Long> countQuery = getCount(userId, drawerId);

        return PageableExecutionUtils.getPage(favoriteResponses, pageable, countQuery::fetchOne);
    }

    @Override
    public void deleteFavoritesByDrawerId(final Long drawerId) {
        jpaQueryFactory
            .delete(favorite)
            .where(favorite.drawerId.eq(drawerId))
            .execute();
    }

    private JPAQuery<Long> getCount(final UUID userId, final Long drawerId) {
        return jpaQueryFactory
            .select(favorite.count())
            .from(favorite)
            .where(
                favorite.userId.eq(userId)
                    .and(favorite.drawerId.eq(drawerId))
            );
    }

}
