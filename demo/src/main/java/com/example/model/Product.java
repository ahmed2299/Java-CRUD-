package com.example.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.FetchMode;
import org.hibernate.engine.profile.Fetch;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
@NamedEntityGraph(name = "loadbrands" , attributeNodes = @NamedAttributeNode("brands"))

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Builder
@Getter
@Entity
@Table(name = "product")
public class Product {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String category;
    private Double price;
    @Column(name = "created_at")
    private Date date;
    @OneToMany(mappedBy = "product",fetch = FetchType.LAZY , cascade = CascadeType.ALL)
    private Set<Brand> brands = new HashSet<>();
}
