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

import com.google.common.io.Resources;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import sir.wellington.alchemy.collections.lists.Lists;
import tech.redroma.google.places.data.Photo;
import tech.redroma.google.places.data.Place;
import tech.redroma.google.places.data.PlaceDetails;
import tech.redroma.google.places.exceptions.GooglePlacesBadArgumentException;
import tech.redroma.google.places.exceptions.GooglePlacesException;
import tech.redroma.google.places.exceptions.GooglePlacesOperationFailedException;
import tech.redroma.google.places.requests.GetPhotoRequest;
import tech.redroma.google.places.requests.GetPlaceDetailsRequest;
import tech.redroma.google.places.requests.NearbySearchRequest;
import tech.redroma.google.places.responses.GetPlaceDetailsResponse;
import tech.redroma.google.places.responses.NearbySearchResponse;
import tech.sirwellington.alchemy.annotations.arguments.NonEmpty;
import tech.sirwellington.alchemy.annotations.arguments.Required;
import tech.sirwellington.alchemy.http.AlchemyHttp;

import static tech.sirwellington.alchemy.arguments.Arguments.checkThat;
import static tech.sirwellington.alchemy.arguments.assertions.Assertions.notNull;
import static tech.sirwellington.alchemy.arguments.assertions.StringAssertions.nonEmptyString;

/**
 * Interact with the GooglePlaces API using this interface.
 * <p>
 * See <a href="https://developers.google.com/places/web-service">https://developers.google.com/places/web-service</a>
 *
 * @see #create(java.lang.String) 
 * @see  <a href="https://developers.google.com/places/web-service">https://developers.google.com/places/web-service</a>
 * @author SirWellington
 */
public interface GooglePlacesAPI
{

    /**
     * Search for nearby places using the
     * <a href="https://developers.google.com/places/web-service/search">Nearby Search Request API</a>
     *
     * @param request
     * @return
     * @throws GooglePlacesException
     * @see NearbySearchRequest#newBuilder()
     * @see #simpleSearchNearbyPlaces(tech.redroma.google.places.requests.NearbySearchRequest)
     * @see <a href="https://developers.google.com/places/web-service/search">Nearby Search Request API</a>
     */
    NearbySearchResponse searchNearbyPlaces(@Required NearbySearchRequest request) throws GooglePlacesException;

    /**
     * This is a convenient version of {@link #searchNearbyPlaces(tech.redroma.google.places.requests.NearbySearchRequest) }
     * that returns a {@code List<Place>}.
     *
     * @param request
     * @return
     * @throws GooglePlacesException
     */
    default List<Place> simpleSearchNearbyPlaces(@Required NearbySearchRequest request) throws GooglePlacesException
    {
        NearbySearchResponse result = searchNearbyPlaces(request);

        return Lists.nullToEmpty(result.getResults());
    }

    /**
     * Get more information about a Place using the
     * <a href="https://developers.google.com/places/web-service/details">Place Details API</a>
     *
     * @param request
     * @return
     * @throws GooglePlacesException
     * @see #simpleGetPlaceDetails(tech.redroma.google.places.data.Place)
     * @see GetPlaceDetailsRequest#newBuilder()
     * @see <a href="https://developers.google.com/places/web-service/details">Place Details API</a>
     */
    GetPlaceDetailsResponse getPlaceDetails(@Required GetPlaceDetailsRequest request) throws GooglePlacesException;

    /**
     * This is a convenient version of {@link #getPlaceDetails(tech.redroma.google.places.requests.GetPlaceDetailsRequest) }
     * that returns a {@link PlaceDetails}.
     *
     * @param place
     * @return
     * @throws GooglePlacesException
     */
    default PlaceDetails simpleGetPlaceDetails(@Required Place place) throws GooglePlacesException
    {
        checkThat(place)
            .throwing(GooglePlacesBadArgumentException.class)
            .is(notNull());

        GetPlaceDetailsRequest request = GetPlaceDetailsRequest.newBuilder()
            .withPlaceID(place.placeId)
            .build();

        GetPlaceDetailsResponse result = this.getPlaceDetails(request);

        return result.getResult();
    }

    /**
     * Get a URL to a {@link Photo} using the
     * <a href="https://developers.google.com/places/web-service/photos">Places Photo API</a>
     * @param request
     * @return
     * @throws GooglePlacesException 
     * @see Photo
     * @see Photo#photoReference
     * @see <a href="https://developers.google.com/places/web-service/photos">Places Photo API</a>
     */
    URL getPhoto(@Required GetPhotoRequest request) throws GooglePlacesException;

    /**
     * This is a convenience method for {@link #getPhoto(tech.redroma.google.places.requests.GetPhotoRequest) }
     * which returns a fully-downloaded image.
     * <p>
     * NOTE: This operation downloads the full-sized image. 
     * Use {@link #getPhoto(tech.redroma.google.places.requests.GetPhotoRequest) } if you want more
     * customized sizing.
     * 
     * @param photo The image to download.
     * @return
     * @throws GooglePlacesException 
     * @see Photo
     * @see Photo#photoReference
     * @see #getPhoto(tech.redroma.google.places.requests.GetPhotoRequest) 
     */
    default byte[] downloadPhoto(@Required Photo photo) throws GooglePlacesException
    {
        checkThat(photo)
            .throwing(GooglePlacesBadArgumentException.class)
            .is(notNull());
        
        GetPhotoRequest request = GetPhotoRequest.newBuilder()
            .withPhotoReference(photo.photoReference)
            .withMaxWidth(GetPhotoRequest.Builder.MAX_WIDTH)
            .build();

        URL url = getPhoto(request);

        try
        {
            return Resources.toByteArray(url);
        }
        catch (IOException ex)
        {
            throw new GooglePlacesOperationFailedException("Could not download Image at: " + url, ex);
        }
    }
    
    /**
     * Creates a production {@link GooglePlacesAPI} that can be used to make requests.
     * 
     * @param apiKey The API Key is required, and can be obtained from the Google Console.
     * @return
     * @throws IllegalArgumentException 
     */
    static GooglePlacesAPI create(@NonEmpty String apiKey) throws IllegalArgumentException
    {
        checkThat(apiKey).is(nonEmptyString());
       
        AlchemyHttp http = AlchemyHttp.newDefaultInstance();
        RequestEncoders.NearbySearchEncoder nearbySearchEncoder = new RequestEncoders.NearbySearchEncoder();
        RequestEncoders.GetPlaceDetailsEncoder placeDetailsEncoder = new RequestEncoders.GetPlaceDetailsEncoder();
        RequestEncoders.AutocompleteEncoder autocompleteEncoder = new RequestEncoders.AutocompleteEncoder();
        
        return new GooglePlacesAPIImpl(apiKey,
                                       http,
                                       ExceptionMapper.INSTANCE,
                                       nearbySearchEncoder,
                                       placeDetailsEncoder,
                                       autocompleteEncoder,
                                       URLProvider.PRODUCTION);
    }
}
