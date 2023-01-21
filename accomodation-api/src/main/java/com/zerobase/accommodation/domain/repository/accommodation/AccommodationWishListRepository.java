package com.zerobase.accommodation.domain.repository.accommodation;

import com.zerobase.accommodation.domain.entity.accommodation.AccommodationWishList;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccommodationWishListRepository extends JpaRepository<AccommodationWishList, Long> {

    void deleteByMemberIdAndAccommodationId(Long memberId, Long accommodationId);
    Optional<List<AccommodationWishList>> findAllByMemberId(Long memberId);
}