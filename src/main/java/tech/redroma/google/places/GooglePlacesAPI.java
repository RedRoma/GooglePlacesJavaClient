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


package tech.redroma.google.places;

import java.util.List;
import sir.wellington.alchemy.collections.lists.Lists;
import tech.redroma.google.places.data.Place;
import tech.redroma.google.places.data.PlaceDetails;
import tech.redroma.google.places.exceptions.GooglePlacesException;
import tech.redroma.google.places.requests.GetPlaceDetailsRequest;
import tech.redroma.google.places.requests.NearbySearchRequest;
import tech.redroma.google.places.responses.GetPlaceDetailsResponse;
import tech.redroma.google.places.responses.NearbySearchResponse;
import tech.sirwellington.alchemy.annotations.arguments.Required;

import static tech.sirwellington.alchemy.arguments.Arguments.checkThat;
import static tech.sirwellington.alchemy.arguments.assertions.Assertions.notNull;


/**
 * Interact with the GooglePlaces API using this interface.
 * 
 * @author SirWellington
 */
public interface GooglePlacesAPI 
{
    NearbySearchResponse searchNearbyPlaces(@Required NearbySearchRequest request) throws GooglePlacesException;
    
    default List<Place> simpleSearchNearbyPlaces(@Required NearbySearchRequest request) throws GooglePlacesException
    {
        NearbySearchResponse result = searchNearbyPlaces(request);
        
        return Lists.nullToEmpty(result.getResults());
    }
    
    GetPlaceDetailsResponse getPlaceDetails(@Required GetPlaceDetailsRequest request) throws GooglePlacesException;
    
    default PlaceDetails simpleGetPlaceDetails(@Required Place place) throws GooglePlacesException, IllegalArgumentException
    {
        checkThat(place).is(notNull());
        
        GetPlaceDetailsRequest request = GetPlaceDetailsRequest.newBuilder()
            .withPlaceID(place.placeId)
            .build();
        
        GetPlaceDetailsResponse result = this.getPlaceDetails(request);
        
        return result.getResult();
    }
}
