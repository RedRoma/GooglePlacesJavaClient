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

import com.google.gson.JsonDeserializer;
import java.util.Objects;
import tech.sirwellington.alchemy.annotations.arguments.NonEmpty;

import static tech.sirwellington.alchemy.arguments.Arguments.checkThat;
import static tech.sirwellington.alchemy.arguments.assertions.Assertions.equalTo;
import static tech.sirwellington.alchemy.arguments.assertions.Assertions.notNull;
import static tech.sirwellington.alchemy.arguments.assertions.BooleanAssertions.trueStatement;
import static tech.sirwellington.alchemy.arguments.assertions.StringAssertions.nonEmptyString;

/**
 * This page lists the supported values for the types property in the Google PlaceType API. You can use {@link PlaceType} in place
 * searches and place additions.
 * <p>
 * {@link ReturnedPlaceType} types that may be returned in the place search results. Table 3 lists the types you can use in place
 * auto-complete requests.
 * <p>
 * {@link AutocompleteType} types can be used when making queries to the Auto-Complete API.
 *
 * @see ReturnedPlaceType
 * @author SirWellington
 */
public class Types
{

    /**
     * This enum lists the supported values for the types property in the Google PlaceType API. You can use {@link PlaceType} in
     * place searches and place additions.
     * <p>
     * {@link ReturnedPlaceType} types that may be returned in the place search results. Table 3 lists the types you can use in
     * place auto-complete requests.
     *
     * @see ReturnedPlaceType
     * @author SirWellington
     */
    public static enum PlaceType
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

        public String asText()
        {
            return this.toString().toLowerCase();
        }

        public static PlaceType from(@NonEmpty String string) throws IllegalArgumentException
        {
            checkThat(string)
                .usingMessage("text cannot be empty")
                .is(nonEmptyString());

            return PlaceType.valueOf(string.toUpperCase());
        }

    }

    /**
     * {@link ReturnedPlaceType} represent types returned by the PlaceType service
     *
     * The following types may be returned in the results of a place search, in addition to the types in table 1 above. For more
     * details on these types, refer to Address Types in Geocoding Responses
     */
    public static enum ReturnedPlaceType
    {
        //Base Types
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
        ZOO,
        //Additional types
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
        SUBPREMISE,;

        public String asText()
        {
            return this.toString().toLowerCase();
        }

        public static ReturnedPlaceType from(@NonEmpty String text) throws IllegalArgumentException
        {
            checkThat(text)
                .usingMessage("text cannot be empty")
                .is(nonEmptyString());

            return ReturnedPlaceType.valueOf(text.toUpperCase());
        }
        
        public static JsonDeserializer<ReturnedPlaceType> createJSONDeserializer()
        {
            return (json, type, context) ->
            {
                checkThat(type)
                    .is(equalTo(ReturnedPlaceType.class))
                    .is(notNull());

                if (Objects.isNull(json))
                {
                    return null;
                }

                checkThat(json.isJsonPrimitive())
                    .usingMessage("expecting json primitive")
                    .is(trueStatement());

                String string = json.getAsString();
                try
                {
                    return ReturnedPlaceType.from(string);
                }
                catch (IllegalArgumentException ex)
                {
                    return null;
                }
            };
        }
    }

    /**
     * You may restrict results from a Place Auto-complete request to be a of a certain type by passing a types parameter.
     * <p>
     * The parameter specifies a type or a type collection, as listed in the supported types below. if nothing is specified, all
     * types are returned. in general only a single type is allowed. The exception is that you can safely mix the {@link #GEOCODE}
     * and {@link #ESTABLISHMENT} types, but not that this will have the same effect as specifying no types.
     */
    public static enum AutocompleteType
    {
        GEOCODE,
        ADDRESS,
        ESTABLISHMENT,
        REGIONS,
        CITIES;

        public String asText()
        {
            switch (this)
            {
                case REGIONS: return "(regions)";
                case CITIES : return "(cities)";
            }
            
            return this.toString().toLowerCase();
        }

        public static AutocompleteType from(@NonEmpty String string) throws IllegalArgumentException
        {
            checkThat(string)
                .usingMessage("text cannot be empty")
                .is(nonEmptyString());

            switch (string)
            {
                case "(regions)" : return REGIONS;
                case "(cities)" : return CITIES;
            }
            
            return AutocompleteType.valueOf(string.toUpperCase());
        }
    }

}
