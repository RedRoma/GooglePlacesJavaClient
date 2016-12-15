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


import java.net.URL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tech.redroma.google.places.exceptions.GooglePlacesBadArgumentException;
import tech.redroma.google.places.exceptions.GooglePlacesException;
import tech.redroma.google.places.requests.GetPhotoRequest;
import tech.redroma.google.places.requests.GetPlaceDetailsRequest;
import tech.redroma.google.places.requests.NearbySearchRequest;
import tech.redroma.google.places.responses.GetPlaceDetailsResponse;
import tech.redroma.google.places.responses.NearbySearchResponse;
import tech.sirwellington.alchemy.annotations.access.Internal;

import static tech.sirwellington.alchemy.arguments.Arguments.checkThat;
import static tech.sirwellington.alchemy.arguments.assertions.Assertions.notNull;

/**
 * Performs no operations on any API calls made.
 * 
 * @author SirWellington
 */
@Internal
final class NoOpGooglePlaces implements GooglePlacesAPI 
{
    private final static Logger LOG = LoggerFactory.getLogger(NoOpGooglePlaces.class);

    public NoOpGooglePlaces()
    {
    }

    @Override
    public NearbySearchResponse searchNearbyPlaces(NearbySearchRequest request) throws GooglePlacesException
    {
        checkRequest(request);
        
        return new NearbySearchResponse();
    }

    @Override
    public GetPlaceDetailsResponse getPlaceDetails(GetPlaceDetailsRequest request) throws GooglePlacesException
    {
        checkRequest(request);
        
        return new GetPlaceDetailsResponse();
    }

    @Override
    public URL getPhoto(GetPhotoRequest request) throws GooglePlacesException
    {
        checkRequest(request);
        
        return null;
    }

    private void checkRequest(Object request)
    {
        checkThat(request)
            .throwing(GooglePlacesBadArgumentException.class)
            .is(notNull());
    }

}
