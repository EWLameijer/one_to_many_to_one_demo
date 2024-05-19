package org.ericwubbo.onetomanytoonedemo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
public class Basket {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private final LocalDateTime dateTime = LocalDateTime.now();

    @OneToMany(mappedBy = "basket")
    private final Set<BasketItem> items = new HashSet<>();

    public void addAll(Collection<BasketItem> items) {
        this.items.addAll(items);
    }
}
