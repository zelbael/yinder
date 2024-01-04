package yinder.demo.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "businesses")
public class Businesses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long businesses_id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "location", nullable = false)
    private String location;

    @Column(name = "latitude", nullable = true)
    private Double latitude;

    @Column(name = "longitude", nullable = true)
    private Double longitude;

    @Column(name = "radius", nullable = true)
    private Integer radius;

    @Column(name = "category", nullable = false)
    private String category;

    @ElementCollection
    @CollectionTable(name = "businesses_categories", joinColumns = @JoinColumn(name = "businesses_id"))
    @Column(name = "category")
    private List<String> categories;

    @Column(name = "locale", nullable = true)
    private String locale;

    @Column(name = "price", nullable = true)
    private Integer[] price;

    @Column(name = "open_now", nullable = true)
    private Boolean open_now;

    @Column(name = "open_at", nullable = true)
    private Integer open_at;
}
