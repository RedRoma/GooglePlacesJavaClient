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
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import tech.redroma.google.places.data.Generators;
import tech.redroma.google.places.data.Place;
import tech.redroma.google.places.exceptions.GooglePlacesException;
import tech.redroma.google.places.requests.GetPhotoRequest;
import tech.redroma.google.places.requests.GetPlaceDetailsRequest;
import tech.redroma.google.places.requests.NearbySearchRequest;
import tech.redroma.google.places.responses.GetPlaceDetailsResponse;
import tech.redroma.google.places.responses.NearbySearchResponse;
import tech.sirwellington.alchemy.test.junit.runners.AlchemyTestRunner;
import tech.sirwellington.alchemy.test.junit.runners.GeneratePojo;
import tech.sirwellington.alchemy.test.junit.runners.Repeat;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import static tech.sirwellington.alchemy.test.junit.ThrowableAssertion.assertThrows;

/**
 *
 * @author SirWellington
 */
@Repeat(10)
@RunWith(AlchemyTestRunner.class)
public class NoOpGooglePlacesTest
{

    @GeneratePojo
    private Place place;

    private NoOpGooglePlaces instance;

    @Before
    public void setUp() throws Exception
    {

        setupData();
        setupMocks();
        instance = new NoOpGooglePlaces();
    }

    private void setupData() throws Exception
    {

    }

    private void setupMocks() throws Exception
    {

    }

    @Test
    public void testSearchNearbyPlaces()
    {
        NearbySearchRequest request = NearbySearchRequest.newBuilder()
            .withKeyword(place.name)
            .withLocation(Generators.createLocation())
            .build();

        NearbySearchResponse result = instance.searchNearbyPlaces(request);
        assertThat(result, notNullValue());
        assertThat(result.getResults(), is(empty()));
    }

    @Test
    public void testSearchNearbyPlacesWithBadArgs() throws Exception
    {
        assertThrows(() -> instance.searchNearbyPlaces(null))
            .isInstanceOf(GooglePlacesException.class);
    }

    @Test
    public void testGetPlaceDetails()
    {
        GetPlaceDetailsRequest request = GetPlaceDetailsRequest.newBuilder()
            .withPlaceID(place.placeId)
            .build();

        GetPlaceDetailsResponse result = instance.getPlaceDetails(request);
        assertThat(result, notNullValue());
        assertThat(result.getResult(), nullValue());
    }

    @Test
    public void testGetPlaceDetailsWithBadArgs() throws Exception
    {
        assertThrows(() -> instance.getPlaceDetails(null))
            .isInstanceOf(GooglePlacesException.class);

    }

    @Test
    public void testGetPhoto()
    {
        GetPhotoRequest request = GetPhotoRequest.newBuilder()
            .withPhotoReference(place.placeId)
            .withMaxHeight(GetPhotoRequest.Builder.MAX_HEIGHT)
            .build();

        URL result = instance.getPhoto(request);
        assertThat(result, nullValue());
    }

    @Test
    public void testGetPhotoWithBadArgs() throws Exception
    {
        assertThrows(() -> instance.getPhoto(null))
            .isInstanceOf(GooglePlacesException.class);

    }

}
