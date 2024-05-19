package org.ericwubbo.onetomanytoonedemo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BasketItemRepository extends JpaRepository<BasketItem, UUID> {
}
