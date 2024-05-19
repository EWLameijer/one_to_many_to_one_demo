package org.ericwubbo.onetomanytoonedemo;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/baskets")
public class BasketController {
    private final ItemRepository itemRepository;

    private final BasketRepository basketRepository;

    @GetMapping("{id}")
    public ResponseEntity<Basket> getById(@PathVariable UUID id) {
        return basketRepository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Basket> create(@RequestBody List<BasketItemDto> basketItemDtos, UriComponentsBuilder ucb) {
        Basket basket = new Basket();
        List<BasketItem> basketItems = basketItemDtos.stream().
                map(basketItem -> basketItemOf(basketItem, basket)).toList();
        basket.addAll(basketItems);
        basketRepository.save(basket);
        URI location = ucb.path("api/v1/baskets/{basketId}").buildAndExpand(basket.getId()).toUri();
        return ResponseEntity.created(location).body(basket);
    }

    private BasketItem basketItemOf(BasketItemDto basketItemDto, Basket basket) {
        Long id = basketItemDto.id();
        Item item = itemRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Unknown item id " + id));
        return new BasketItem(basket, item, basketItemDto.quantity());
    }
}
