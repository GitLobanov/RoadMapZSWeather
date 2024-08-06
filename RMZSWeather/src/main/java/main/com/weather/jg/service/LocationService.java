package main.com.weather.jg.service;

import main.com.weather.jg.model.Location;
import main.com.weather.jg.model.User;
import main.com.weather.jg.repository.LocationRepository;

import java.util.List;

public class LocationService {

    LocationRepository locationRepository;

    public LocationService() {
        this.locationRepository = new LocationRepository();
    }

    public void add(Location location) {
        locationRepository.save(location);
    }

    public boolean isHaveByCityAndUser(String city, User user) {
        return locationRepository.findByNameAndUser(city, user) != null;
    }

    public List<Location> findAllLocations(){
        return locationRepository.findAll();
    }

}
