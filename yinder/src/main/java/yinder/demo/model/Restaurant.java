package yinder.demo.model;

import jakarta.persistence.*;
import lombok.Data;
@Data
@Entity
@Table(name = "restaurant")
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long restaurant_id;

    @Column(name = "restaurant_name", nullable = false)
    private String restaurant_name;

    @Column(name = "restaurant_type", nullable = false)
    private String restaurant_type;

    @Column(name = "location", nullable = false)
    private String location;

    @Column(name = "description", nullable = true)
    private String description;
}