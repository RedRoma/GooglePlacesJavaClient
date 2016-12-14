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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tech.redroma.google.places.data.Location;
import tech.redroma.google.places.exceptions.GooglePlacesBadArgumentException;
import tech.redroma.google.places.requests.AutocompletePlaceRequest;
import tech.redroma.google.places.requests.GetPlaceDetailsRequest;
import tech.redroma.google.places.requests.NearbySearchRequest;
import tech.sirwellington.alchemy.annotations.access.Internal;
import tech.sirwellington.alchemy.annotations.access.NonInstantiable;
import tech.sirwellington.alchemy.http.AlchemyRequest;

import static tech.sirwellington.alchemy.arguments.Arguments.checkThat;
import static tech.sirwellington.alchemy.arguments.assertions.Assertions.notNull;

/**
 *
 * @author SirWellington
 */
@Internal
@NonInstantiable
final class RequestEncoders
{

    private final static Logger LOG = LoggerFactory.getLogger(RequestEncoders.class);

    static class NearbySearchEncoder implements RequestEncoder<NearbySearchRequest>
    {

        @Override
        public AlchemyRequest.Step3 encodeRequest(AlchemyRequest.Step3 alchemyRequest, NearbySearchRequest request)
        {
            checkThat(alchemyRequest, request)
                .throwing(GooglePlacesBadArgumentException.class)
                .are(notNull());
                
            Location location = request.getLocation();
            
            String locationString = String.format("%s,%s", location.latitude, location.longitude);
            
            AlchemyRequest.Step3 result = alchemyRequest.usingQueryParam(Parameters.LOCATION, locationString);
            
            if (request.hasLanguage())
            {
                result = result.usingQueryParam(Parameters.LANGUAGE, request.getLanguage().code);
            }
            
            if (request.hasKeyword())
            {
                result = result.usingQueryParam(Parameters.KEYWORD, request.getKeyword());
            }
            
            if (request.hasMinAndMaxPrice())
            {
                result = result
                    .usingQueryParam(Parameters.MIN_PRICE, request.getMinPrice().value)
                    .usingQueryParam(Parameters.MAX_PRICE, request.getMaxPrice().value);
            }
            
            if (request.hasName())
            {
                result = result.usingQueryParam(Parameters.NAME, request.getName());
            }
            
            if (request.hasOnlyOpenNow())
            {
                result = result.usingQueryParam(Parameters.OPEN_NOW, request.isOnlyOpenNow());
            }
            
            if (request.hasPageToken())
            {
                result = result.usingQueryParam(Parameters.PAGE_TOKEN, request.getPageToken());
            }
            
            if (request.hasRadius())
            {
                result = result.usingQueryParam(Parameters.RADIUS, request.getRadiusInMeters());
            }
            
            if (request.hasRankBy())
            {
                result = result.usingQueryParam(Parameters.RANK_BY, request.getRankBy().toString());
            }
            
            if (request.hasType())
            {
                result = result.usingQueryParam(Parameters.TYPE, request.getType().asText());
            }
            
            return result;
        }

    }

    static class GetPlaceDetailsEncoder implements RequestEncoder<GetPlaceDetailsRequest>
    {

        @Override
        public AlchemyRequest.Step3 encodeRequest(AlchemyRequest.Step3 alchemyRequest, GetPlaceDetailsRequest request)
        {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

    }

    static class AutocompleteEncoder implements RequestEncoder<AutocompletePlaceRequest>
    {

        @Override
        public AlchemyRequest.Step3 encodeRequest(AlchemyRequest.Step3 alchemyRequest, AutocompletePlaceRequest request)
        {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

    }
    
    @Internal
    static class Parameters
    {

        static final String LOCATION = "location";
        static final String RADIUS = "radius";
        static final String KEYWORD = "keyword";
        static final String NAME = "name";
        static final String LANGUAGE = "language";
        static final String MIN_PRICE = "minprice";
        static final String MAX_PRICE = "maxprice";
        static final String OPEN_NOW = "opennow";
        static final String TYPE = "type";
        static final String PAGE_TOKEN = "pagetoken";
        static final String RANK_BY = "rankby";
    }

}
