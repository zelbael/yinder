package yinder.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import yinder.demo.model.Businesses;
import yinder.demo.service.BusinessesService;

import java.util.List;

@RestController
@RequestMapping("/businesses")
public class BusinessesController {

    private final BusinessesService businessesService;

    @Autowired
    public BusinessesController(BusinessesService businessesService) {
        this.businessesService = businessesService;
    }
    @GetMapping("/search")
    public List<Businesses> searchBusinesses(
            @RequestParam(required = false) String location,
            @RequestParam(required = false) Double latitude,
            @RequestParam(required = false) Double longitude,
            @RequestParam(required = false) String term,
            @RequestParam(required = false) Integer radius,
            @RequestParam(required = false) String[] categories,
            @RequestParam(required = false) String locale,
            @RequestParam(required = false) Integer[] price,
            @RequestParam(required = false) Boolean open_now,
            @RequestParam(required = false) Integer open_at
    ) {
        // You can use these parameters to construct your Yelp API request
        // Call the corresponding service method to handle the logic
        return businessesService.searchBusinesses(location, latitude, longitude, term, radius, categories, locale, price, open_now, open_at);
    }

    @PostMapping
    public Businesses createBusiness(@RequestBody Businesses businesses) {
        return businessesService.createBusiness(businesses);
    }

    @PutMapping("/{business_id}")
    public Businesses updateBusiness(@PathVariable("business_id") long id, @RequestBody Businesses businesses) {
        return businessesService.updateBusiness(id, businesses);
    }

    @DeleteMapping("/{business_id}")
    public void deleteBusiness(@PathVariable("business_id") long id) {
        businessesService.deleteBusiness(id);
    }
}
