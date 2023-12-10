package yinder.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yinder.demo.model.Restaurant;
import yinder.demo.repository.RestaurantRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }
    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    public Restaurant createRestaurant(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    public Restaurant updateRestaurant(long restaurant_id, Restaurant updatedRestaurant) {
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(restaurant_id);
        if (optionalRestaurant.isPresent()) {
            Restaurant existingRestaurant = optionalRestaurant.get();

            existingRestaurant.setRestaurant_name(updatedRestaurant.getRestaurant_name());
            existingRestaurant.setCategory(updatedRestaurant.getCategory());

            return restaurantRepository.save(existingRestaurant);
        } else {
            return null;
        }
    }

    public void deleteRestaurant(long restaurant_id) {
        restaurantRepository.deleteById(restaurant_id);
    }
}
