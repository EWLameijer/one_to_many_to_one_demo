package org.ericwubbo.onetomanytoonedemo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
public class BasketItem {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JsonBackReference
    private Basket basket;

    @ManyToOne
    private Item item;

    private int quantity;

    public BasketItem(Item item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }
}
