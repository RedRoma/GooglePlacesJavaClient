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

import tech.redroma.google.places.requests.AutocompletePlaceRequest;
import tech.redroma.google.places.requests.GetPhotoRequest;
import tech.redroma.google.places.requests.GetPlaceDetailsRequest;
import tech.redroma.google.places.requests.NearbySearchRequest;
import tech.sirwellington.alchemy.generator.AlchemyGenerator;

import static tech.sirwellington.alchemy.generator.AlchemyGenerator.one;
import static tech.sirwellington.alchemy.generator.CollectionGenerators.listOf;
import static tech.sirwellington.alchemy.generator.EnumGenerators.enumValueOf;
import static tech.sirwellington.alchemy.generator.GeolocationGenerators.latitudes;
import static tech.sirwellington.alchemy.generator.GeolocationGenerators.longitudes;
import static tech.sirwellington.alchemy.generator.NumberGenerators.integers;
import static tech.sirwellington.alchemy.generator.StringGenerators.alphabeticString;
import static tech.sirwellington.alchemy.generator.StringGenerators.hexadecimalString;

/**
 *
 * @author SirWellington
 */
public class Generators
{

    public static Location createLocation()
    {
        return Location.of(one(latitudes()), one(longitudes()));
    }
    
    public static AlchemyGenerator<Location> locations()
    {
        return Generators::createLocation;
    }
    
    public static AlchemyGenerator<Viewport> viewPorts()
    {
        return Generators::createViewPort;
    }
    
    public static AlchemyGenerator<Geometry> geometries()
    {
        return Generators::createGeometry;
    }

    public static Viewport createViewPort()
    {
        Viewport viewport = new Viewport();
        viewport.northEast = one(locations());
        viewport.southWest = one(locations());

        return viewport;
    }

    public static Geometry createGeometry()
    {
        Geometry geometry = new Geometry();

        geometry.location = one(locations());
        geometry.viewport = createViewPort();

        return geometry;
    }
    
    public static NearbySearchRequest createNearbySearchRequest()
    {
        return NearbySearchRequest.newBuilder()
            .withLocation(one(locations()))
            .withKeyword(one(alphabeticString()))
            .onlyOpenNow()
            .withRadiusInMeters(createRadius())
            .withLanguage(oneLanguage())
            .build();
    }

    private static int createRadius()
    {
        return one(integers(1, NearbySearchRequest.Builder.MAX_RADIUS));
    }

    private static Language oneLanguage()
    {
        return enumValueOf(Language.class).get();
    }
    
    public static AutocompletePlaceRequest createAutocompleteRequest()
    {
        return AutocompletePlaceRequest.newBuilder()
            .withInput(one(alphabeticString()))
            .withLocation(one(locations()))
            .withRadiusInMeters(createRadius())
            .withTypes(listOf(enumValueOf(Types.AutocompleteType.class)))
            .build();
    }
    
    public static GetPlaceDetailsRequest createGetPlaceDetailsRequest()
    {
        return GetPlaceDetailsRequest.newBuilder()
            .withExtensions(enumValueOf(Extensions.class).get())
            .withLanguage(oneLanguage())
            .withPlaceID(one(hexadecimalString(12)))
            .build();
    }
    
    public static GetPhotoRequest createGetPhotoRequest()
    {
        AlchemyGenerator<Integer> widths = integers(GetPhotoRequest.Builder.MIN_WIDTH, GetPhotoRequest.Builder.MAX_WIDTH +1);
        
        return GetPhotoRequest.newBuilder()
            .withPhotoReference(one(hexadecimalString(20)))
            .withMaxHeight(one(widths))
            .build();
    }
}
