/*
 * Copyright 2016 RedRoma, Inc..
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package tech.redroma.google.places.data;

/**
 * This page lists the supported values for the types property in the Google Places API. You can use {@link PlaceType} in
 * place searches and place additions. 
 * <p>
 * {@link Additional} types that may be returned in the place search results. Table
 * 3 lists the types you can use in place auto-complete requests.
 *
 * @see Additional
 * @author SirWellington
 */
public enum PlaceType
{

    ACCOUNTING,
    AIRPORT,
    AMUSEMENT_PARK,
    AQUARIUM,
    ART_GALLERY,
    ATM,
    BAKERY,
    BANK,
    BAR,
    BEAUTY_SALON,
    BICYCLE_STORE,
    BOOK_STORE,
    BOWLING_ALLEY,
    BUS_STATION,
    CAFE,
    CAMPGROUND,
    CAR_DEALER,
    CAR_RENTAL,
    CAR_REPAIR,
    CAR_WASH,
    CASINO,
    CEMETERY,
    CHURCH,
    CITY_HALL,
    CLOTHING_STORE,
    CONVENIENCE_STORE,
    COURTHOUSE,
    DENTIST,
    DEPARTMENT_STORE,
    DOCTOR,
    ELECTRICIAN,
    ELECTRONICS_STORE,
    EMBASSY,
    FIRE_STATION,
    FLORIST,
    FUNERAL_HOME,
    FURNITURE_STORE,
    GAS_STATION,
    GYM,
    HAIR_CARE,
    HARDWARE_STORE,
    HINDU_TEMPLE,
    HOME_GOODS_STORE,
    HOSPITAL,
    INSURANCE_AGENCY,
    JEWELRY_STORE,
    LAUNDRY,
    LAWYER,
    LIBRARY,
    LIQUOR_STORE,
    LOCAL_GOVERNMENT_OFFICE,
    LOCKSMITH,
    LODGING,
    MEAL_DELIVERY,
    MEAL_TAKEAWAY,
    MOSQUE,
    MOVIE_RENTAL,
    MOVIE_THEATER,
    MOVING_COMPANY,
    MUSEUM,
    NIGHT_CLUB,
    PAINTER,
    PARK,
    PARKING,
    PET_STORE,
    PHARMACY,
    PHYSIOTHERAPIST,
    PLUMBER,
    POLICE,
    POST_OFFICE,
    REAL_ESTATE_AGENCY,
    RESTAURANT,
    ROOFING_CONTRACTOR,
    RV_PARK,
    SCHOOL,
    SHOE_STORE,
    SHOPPING_MALL,
    SPA,
    STADIUM,
    STORAGE,
    STORE,
    SUBWAY_STATION,
    SYNAGOGUE,
    TAXI_STAND,
    TRAIN_STATION,
    TRANSIT_STATION,
    TRAVEL_AGENCY,
    UNIVERSITY,
    VETERINARY_CARE,
    ZOO;

    public String text()
    {
        return this.toString().toLowerCase();
    }

    /**
     *
     * Place Types
     *
     *
     *
     * Table 1: Types supported in place search and addition
     *
     * You can use the following values in the types filter for place searches and when adding a place.
     *
     * Note: The following types are deprecated, and will continue to be supported until February 16, 2017: establishment,
     * finance, food, general_contractor, grocery_or_supermarket, health, place_of_worship. accounting airport amusement_park
     * aquarium art_gallery atm bakery bank bar beauty_salon bicycle_store book_store bowling_alley bus_station cafe campground
     * car_dealer car_rental car_repair car_wash casino cemetery church city_hall clothing_store convenience_store courthouse
     * dentist department_store doctor electrician electronics_store embassy establishment (deprecated) finance (deprecated)
     * fire_station florist food (deprecated) funeral_home furniture_store gas_station general_contractor (deprecated)
     * grocery_or_supermarket (deprecated) gym hair_care hardware_store health (deprecated) hindu_temple home_goods_store hospital
     * insurance_agency jewelry_store laundry lawyer library liquor_store local_government_office locksmith lodging meal_delivery
     * meal_takeaway mosque movie_rental movie_theater moving_company museum night_club painter park parking pet_store pharmacy
     * physiotherapist place_of_worship (deprecated) plumber police post_office real_estate_agency restaurant roofing_contractor
     * rv_park school shoe_store shopping_mall spa stadium storage store subway_station synagogue taxi_stand train_station
     * transit_station travel_agency university veterinary_care zoo
     *
     * Table 2: Additional types returned by the Places service
     *
     * The following types may be returned in the results of a place search, in addition to the types in table 1 above. For more
     * details on these types, refer to Address Types in Geocoding Responses
     */
    public static enum Additional
    {
        ADMINISTRATIVE_AREA_LEVEL_1,
        ADMINISTRATIVE_AREA_LEVEL_2,
        ADMINISTRATIVE_AREA_LEVEL_3,
        ADMINISTRATIVE_AREA_LEVEL_4,
        ADMINISTRATIVE_AREA_LEVEL_5,
        COLLOQUIAL_AREA,
        COUNTRY,
        ESTABLISHMENT,
        FINANCE,
        FLOOR,
        FOOD,
        GENERAL_CONTRACTOR,
        GEOCODE,
        HEALTH,
        INTERSECTION,
        LOCALITY,
        NATURAL_FEATURE,
        NEIGHBORHOOD,
        PLACE_OF_WORSHIP,
        POLITICAL,
        POINT_OF_INTEREST,
        POST_BOX,
        POSTAL_CODE,
        POSTAL_CODE_PREFIX,
        POSTAL_CODE_SUFFIX,
        POSTAL_TOWN,
        PREMISE,
        ROOM,
        ROUTE,
        STREET_ADDRESS,
        STREET_NUMBER,
        SUBLOCALITY,
        SUBLOCALITY_LEVEL_4,
        SUBLOCALITY_LEVEL_5,
        SUBLOCALITY_LEVEL_3,
        SUBLOCALITY_LEVEL_2,
        SUBLOCALITY_LEVEL_1,
        SUBPREMISE,
    }

}
