package jw.project.baemin.restaurant.restaurant.presentation.request;


import java.time.LocalTime;

public record UpdateRestaurantRequest(LocalTime openTime, LocalTime closeTime, String phoneNumber,
                                      String address, String description) {

}
