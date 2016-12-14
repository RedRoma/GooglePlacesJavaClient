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
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sir.wellington.alchemy.collections.lists.Lists;
import tech.redroma.google.places.data.Location;
import tech.redroma.google.places.data.Photo;
import tech.redroma.google.places.data.Place;
import tech.redroma.google.places.data.PlaceDetails;
import tech.redroma.google.places.requests.GetPhotoRequest;
import tech.redroma.google.places.requests.GetPlaceDetailsRequest;
import tech.redroma.google.places.requests.NearbySearchRequest;
import tech.redroma.google.places.responses.GetPlaceDetailsResponse;
import tech.redroma.google.places.responses.NearbySearchResponse;
import tech.sirwellington.alchemy.test.junit.runners.AlchemyTestRunner;

import static com.google.common.base.Strings.isNullOrEmpty;

/**
 *
 * @author SirWellington
 */
@RunWith(AlchemyTestRunner.class)
public class GooglePlacesAPIIT
{

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    private final String apiKey = "";
    private Location location;
    private Place place;
    private Photo photo;

    private GooglePlacesAPI instance;

    @Before
    public void setUp() throws Exception
    {
        if (!isNullOrEmpty(apiKey))
        {
            instance = GooglePlacesAPI.create(apiKey);
        }

        location = Location.of(40.814697, -73.908013);
    }

    @Test
    public void testSearchNearbyPlaces()
    {
        if (instance == null)
        {
            return;
        }

        NearbySearchRequest request = NearbySearchRequest.newBuilder()
            .withKeyword("grocery")
            .withRadiusInMeters(5_000)
            .withLocation(location)
            .build();

        NearbySearchResponse result = instance.searchNearbyPlaces(request);
        LOG.info("Found [{}] results for request: [{}]", result, request);

        place = Lists.oneOf(result.getResults());
        photo = loadAPhotoFrom(result.getResults());
    }

    @Test
    public void testSimpleSearchNearbyPlaces()
    {
        if (instance == null)
        {
            return;
        }

        NearbySearchRequest request = NearbySearchRequest.newBuilder()
            .withKeyword("library")
            .withRadiusInMeters(5_000)
            .withLocation(location)
            .build();

        List<Place> results = instance.simpleSearchNearbyPlaces(request);
        LOG.info("Found {} results for request: [{}]", results.size(), request);

        place = Lists.oneOf(results);
        photo = loadAPhotoFrom(results);
    }

    @Test
    public void testGetPlaceDetails()
    {
        if (instance == null || place == null)
        {
            return;
        }

        GetPlaceDetailsRequest request = GetPlaceDetailsRequest.newBuilder()
            .withPlaceID(place.placeId)
            .build();

        GetPlaceDetailsResponse result = instance.getPlaceDetails(request);
        LOG.info("Got place details: [{}]", result);
    }

    @Test
    public void testSimpleGetPlaceDetails()
    {
        if (instance == null || place == null)
        {
            return;
        }

        PlaceDetails details = instance.simpleGetPlaceDetails(place);
        LOG.info("Retrieved details for place: [{}]", details);
    }

    @Test
    public void testGetPhoto()
    {
        if (instance == null)
        {
            return;
        }

        NearbySearchRequest searchRequest = NearbySearchRequest.newBuilder()
            .withKeyword("library")
            .withRadiusInMeters(5_000)
            .withLocation(location)
            .build();

        List<Place> results = instance.simpleSearchNearbyPlaces(searchRequest);
        photo = loadAPhotoFrom(results);

        if (Objects.isNull(photo))
        {
            return;
        }

        GetPhotoRequest request = GetPhotoRequest.newBuilder()
            .withPhotoReference(photo.photoReference)
            .withMaxHeight(GetPhotoRequest.Builder.MAX_HEIGHT)
            .build();

        URL result = instance.getPhoto(request);
        LOG.info("Got photo URL [{}] for request: [{}]", result, request);
    }

    @Test
    public void testDownloadPhoto()
    {
        if (instance == null)
        {
            return;
        }

        NearbySearchRequest searchRequest = NearbySearchRequest.newBuilder()
            .withKeyword("library")
            .withRadiusInMeters(5_000)
            .withLocation(location)
            .build();

        List<Place> results = instance.simpleSearchNearbyPlaces(searchRequest);
        photo = loadAPhotoFrom(results);

        if (Objects.isNull(photo))
        {
            return;
        }

        byte[] binary = instance.downloadPhoto(photo);
        LOG.info("Downloaded photo [{}] with {} bytes", photo, binary.length);
    }

    private Photo loadAPhotoFrom(List<Place> results)
    {
        Optional<Photo> optionalPhoto = results.stream()
            .filter(Place::hasPhotos)
            .limit(1)
            .flatMap(place -> place.photos.stream())
            .filter(Objects::nonNull)
            .findAny();

        return optionalPhoto.orElse(null);
    }

}
