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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tech.redroma.google.places.data.Location;
import tech.redroma.google.places.data.Types;
import tech.redroma.google.places.exceptions.GooglePlacesBadArgumentException;
import tech.redroma.google.places.requests.AutocompletePlaceRequest;
import tech.redroma.google.places.requests.GetPlaceDetailsRequest;
import tech.redroma.google.places.requests.NearbySearchRequest;
import tech.sirwellington.alchemy.annotations.access.Internal;
import tech.sirwellington.alchemy.annotations.access.NonInstantiable;
import tech.sirwellington.alchemy.http.AlchemyRequest;

import static java.util.stream.Collectors.toList;
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
            checkThat(alchemyRequest, request)
                .throwing(GooglePlacesBadArgumentException.class)
                .are(notNull());
                
            AlchemyRequest.Step3 result = alchemyRequest.usingQueryParam(Parameters.PLACE_ID, request.placeId);
            
            if (request.hasLanguage())
            {
                result = result.usingQueryParam(Parameters.LANGUAGE, request.language.code);
            }
            
            if (request.hasExtensions())
            {
                result = result.usingQueryParam(Parameters.EXTENSIONS, request.extensions.asText());
            }
            
            return result;
        }

    }

    static class AutocompleteEncoder implements RequestEncoder<AutocompletePlaceRequest>
    {

        @Override
        public AlchemyRequest.Step3 encodeRequest(AlchemyRequest.Step3 alchemyRequest, AutocompletePlaceRequest request)
        {
            checkThat(alchemyRequest, request)
                .throwing(GooglePlacesBadArgumentException.class)
                .are(notNull());
            
            AlchemyRequest.Step3 result = alchemyRequest.usingQueryParam(Parameters.INPUT, request.input);
            
            if (request.hasLanguage())
            {
                result = result.usingQueryParam(Parameters.LANGUAGE, request.language.code);
            }
            
            if (request.hasLocation())
            {
                String location = String.format("%s,%s", request.location.latitude, request.location.longitude);
                result = result.usingQueryParam(Parameters.LOCATION, location);
            }
            
            if (request.hasOffset())
            {
                result = result.usingQueryParam(Parameters.OFFSET, request.offset);
            }
            
            if (request.hasRadius())
            {
                result = result.usingQueryParam(Parameters.RADIUS, request.radiusInMeters);
            }
            
            if (request.hasStrictBounds())
            {
                result = result.usingQueryParam(Parameters.STRICT_BOUNDS, request.strictBounds);
            }
            
            if (request.hasTypes())
            {
                List<String> types = request.types.stream()
                    .map(Types.AutocompleteType::asText)
                    .collect(toList());

                result = result.usingQueryParam(Parameters.TYPES, String.join("|", types));
            }
            
            return result;
        }

    }
    
    @Internal
    static class Parameters
    {

        static final String COMPONENTS = "components";
        static final String EXTENSIONS = "extensions";
        static final String KEYWORD = "keyword";
        static final String INPUT = "input";
        static final String LANGUAGE = "language";
        static final String LOCATION = "location";
        static final String MAX_PRICE = "maxprice";
        static final String MIN_PRICE = "minprice";
        static final String NAME = "name";
        static final String OFFSET = "offset";
        static final String OPEN_NOW = "opennow";
        static final String PLACE_ID = "placeid";
        static final String PAGE_TOKEN = "pagetoken";
        static final String RADIUS = "radius";
        static final String RANK_BY = "rankby";
        static final String STRICT_BOUNDS = "strictbounds";
        static final String TYPE = "type";
        static final String TYPES = "types";
    }

}
