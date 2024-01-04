package yinder.demo.service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import yinder.demo.model.Businesses;
import yinder.demo.repository.BusinessRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class BusinessesService {

    private final BusinessRepository businessesRepository;

    @Value("${yelp.api.base-url}")
    private String yelpApiBaseUrl;

    @Value("${yelp.api.key}")
    private String yelpApiKey;

    public BusinessesService(BusinessRepository businessesRepository) {
        this.businessesRepository = businessesRepository;
    }

    public List<Businesses> getAllBusinesses() {
        return businessesRepository.findAll();
    }

    public Businesses createBusiness(Businesses businesses) {
        return businessesRepository.save(businesses);
    }

    public Businesses updateBusiness(long business_id, Businesses updatedBusinesses) {
        return businessesRepository.findById(business_id)
                .map(existingBusiness -> {
                    existingBusiness.setName(updatedBusinesses.getName());
                    existingBusiness.setCategory(updatedBusinesses.getCategory());
                    return businessesRepository.save(existingBusiness);
                })
                .orElse(null);
    }

    public void deleteBusiness(long business_id) {
        businessesRepository.deleteById(business_id);
    }

    public List<Businesses> searchBusinesses(String location, Double latitude, Double longitude, String term, Integer radius, String[] categories, String locale, Integer[] price, Boolean open_now, Integer open_at) {
        String apiUrl = yelpApiBaseUrl + "/businesses/search";

        // Create a RestTemplate to make HTTP requests
        RestTemplate restTemplate = new RestTemplate();

        // Build the request parameters
        StringBuilder queryParams = new StringBuilder();
        if (location != null) queryParams.append("&location=").append(location);
        if (latitude != null) queryParams.append("&latitude=").append(latitude);
        if (longitude != null) queryParams.append("&longitude=").append(longitude);
        if (radius != null) queryParams.append("&radius=").append(radius);
        if (categories != null) queryParams.append("&categories=").append(String.join(",", categories));
        if (locale != null) queryParams.append("&locale=").append(locale);
        if (price != null) queryParams.append("&price=").append(Arrays.toString(price));
        if (open_now != null) queryParams.append("&open_now=").append(open_now);
        if (open_at != null) queryParams.append("&open_at=").append(open_at);

        // Add the API key to authenticate the request
        queryParams.append("&api_key=").append(yelpApiKey);

        // Create the full URL with parameters
        String fullUrl = apiUrl + "?" + queryParams.substring(1); // Remove the leading '&'

        // Make the API request
        Map<String, Object> response = restTemplate.getForObject(fullUrl, Map.class);

        // Process the response and extract the businesses
        List<Businesses> businessesList = extractBusinessesFromResponse(response);

        // Save the businesses to your local database if needed
        businessesRepository.saveAll(businessesList);

        return businessesList;
    }
    private List<Businesses> extractBusinessesFromResponse(Map<String, Object> response) {
        List<Businesses> businessesList = new ArrayList<>();

        // Check if the response contains the "businesses" key
        if (response.containsKey("businesses")) {
            List<Map<String, Object>> businesses = (List<Map<String, Object>>) response.get("businesses");

            for (Map<String, Object> business : businesses) {
                Businesses businessObject = new Businesses();

                //Extract relevate info from the business map and set it in the businessObject
                businessObject.setName((String) business.get("name"));
                businessObject.setLocation((String) business.get("location"));
                businessObject.setLatitude((Double) business.get("latitude"));
                businessObject.setLongitude((Double) business.get("longitude"));
                businessObject.setRadius((Integer) business.get("radius"));
                businessObject.setCategory((String) business.get("category"));
                businessObject.setLocale((String) business.get("locale"));
                businessObject.setPrice((Integer[]) business.get("price"));
                businessObject.setOpen_now((Boolean) business.get("open_now"));
                businessObject.setOpen_at((Integer) business.get("open_at"));

                businessesList.add(businessObject);
            }
        }
        return businessesList;
    }
}


