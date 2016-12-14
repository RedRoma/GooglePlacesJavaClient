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

import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import tech.sirwellington.alchemy.test.junit.runners.AlchemyTestRunner;
import tech.sirwellington.alchemy.test.junit.runners.GeneratePojo;
import tech.sirwellington.alchemy.test.junit.runners.Repeat;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

/**
 *
 * @author SirWellington
 */
@Repeat(10)
@RunWith(AlchemyTestRunner.class)
public class PlaceDetailsTest
{

    @GeneratePojo
    private PlaceDetails instance;

    @GeneratePojo
    private PlaceDetails other;

    @Before
    public void setUp() throws Exception
    {

        setupData();
    }

    private void setupData() throws Exception
    {

    }

    @Test
    public void testInstance() throws Exception
    {
        assertThat(instance, notNullValue());
        checkInstance(instance);
    }

    @Test
    public void testGetAddressComponents()
    {
        List<PlaceDetails.AddressComponent> result = instance.getAddressComponents();
        assertThat(result, notNullValue());
        assertThat(result, not(empty()));
    }

    @Test
    public void testGetFormattedAddress()
    {
        String result = instance.getFormattedAddress();
        assertThat(result, not(isEmptyString()));
    }

    @Test
    public void testGetFormattedPhoneNumber()
    {
        String result = instance.getFormattedPhoneNumber();
        assertThat(result, not(isEmptyString()));
    }

    @Test
    public void testGetInternationalPhoneNumber()
    {
        String result = instance.getInternationalPhoneNumber();
        assertThat(result, not(isEmptyString()));
    }

    @Test
    public void testGetGeometry()
    {
        Geometry result = instance.getGeometry();
        assertThat(result, notNullValue());
    }

    @Test
    public void testGetIconURL()
    {
        String result = instance.getIconURL();
        assertThat(result, not(isEmptyString()));
    }

    @Test
    public void testGetId()
    {
        String result = instance.getId();
        assertThat(result, not(isEmptyString()));
    }

    @Test
    public void testGetName()
    {
        String result = instance.getName();
        assertThat(result, not(isEmptyString()));
    }

    @Test
    public void testGetOpeningHours()
    {
        List<OpeningHours> result = instance.getOpeningHours();
        assertThat(result, not(empty()));
    }

    @Test
    public void testGetPhotos()
    {
        List<Photo> result = instance.getPhotos();
        assertThat(result, not(empty()));
    }

    @Test
    public void testGetPlaceId()
    {
        String result = instance.getPlaceId();
        assertThat(result, not(isEmptyString()));
    }

    @Test
    public void testGetRating()
    {
        String result = instance.getPlaceId();
        assertThat(result, not(isEmptyString()));
    }

    @Test
    public void testGetReference()
    {
        String result = instance.getReference();
        assertThat(result, not(isEmptyString()));
    }

    @Test
    public void testGetReviews()
    {
        List<Review> result = instance.getReviews();
        assertThat(result, not(empty()));
    }

    @Test
    public void testGetTypes()
    {
        List<Types.ReturnedPlaceType> result = instance.getTypes();
        assertThat(result, not(empty()));
    }

    @Test
    public void testGetUrl()
    {
        String result = instance.getUrl();
        assertThat(result, not(isEmptyString()));
    }

    @Test
    public void testGetUtcOffset()
    {
        Integer result = instance.getUtcOffset();
        assertThat(result, notNullValue());
    }

    @Test
    public void testGetVicinity()
    {
        String result = instance.getVicinity();
        assertThat(result, not(isEmptyString()));
    }

    @Test
    public void testGetWebsite()
    {
        String result = instance.getWebsite();
        assertThat(result, not(isEmptyString()));
    }

    @Test
    public void testHashCode()
    {
        assertThat(instance.hashCode(), is(instance.hashCode()));
        assertThat(instance.hashCode(), not(other.hashCode()));
    }

    @Test
    public void testEquals()
    {
        assertThat(instance, is(instance));
        assertThat(instance, not(other));
    }

    @Test
    public void testToString()
    {
        String string = instance.toString();
        assertThat(string, not(isEmptyString()));
    }

    private void checkInstance(PlaceDetails place)
    {
        assertThat(place, notNullValue());
        assertThat(place.placeId, not(isEmptyOrNullString()));
        assertThat(place.id, not(isEmptyString()));
        assertThat(place.name, not(isEmptyString()));
        assertThat(place.formattedAddress, not(isEmptyString()));
        assertThat(place.formattedPhoneNumber, not(isEmptyString()));
        assertThat(place.iconURL, not(isEmptyString()));
        assertThat(place.internationalPhoneNumber, not(isEmptyString()));
        assertThat(place.url, not(isEmptyString()));
        assertThat(place.vicinity, not(isEmptyString()));

        assertThat(place.addressComponents, notNullValue());
        assertThat(place.geometry, notNullValue());
        assertThat(place.openingHours, notNullValue());
        assertThat(place.photos, notNullValue());
        assertThat(place.rating, notNullValue());
        assertThat(place.reviews, notNullValue());
        assertThat(place.types, notNullValue());
    }

}
