package yinder.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import yinder.demo.model.Restaurant;
import yinder.demo.service.RestaurantService;

import java.util.List;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService) { this.restaurantService = restaurantService; }

    @GetMapping
    public List<Restaurant> getAllRestaurants() { return restaurantService.getAllRestaurants(); }

    @PostMapping
    public Restaurant createRestaurant(@RequestBody Restaurant restaurant) {
        return restaurantService.createRestaurant(restaurant); }

    @PutMapping("/{restaurant_id}")
    public Restaurant updateRestaurant(@PathVariable("restaurant_id") long id, @RequestBody Restaurant restaurant) {
        return restaurantService.updateRestaurant(id, restaurant);
    }

    @DeleteMapping("/{restaurant_id}")
    public void deleteRestaurant(@PathVariable("restaurant_id") long id) {
        restaurantService.deleteRestaurant(id);
    }
}
