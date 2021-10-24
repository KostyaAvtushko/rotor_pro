package com.rotor.serwingwebcontent.entity;


import javax.persistence.*;

@Entity
@Table(name="items", schema = "public")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false, unique=true)
    private Long id;
    private String itemName;

    public Item() {}

    public Item(Long id, String itemName) {
        this.id = id;
        this.itemName = itemName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
}
