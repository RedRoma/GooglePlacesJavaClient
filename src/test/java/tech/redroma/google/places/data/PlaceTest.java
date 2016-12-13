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

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import sir.wellington.alchemy.collections.lists.Lists;
import tech.sirwellington.alchemy.test.junit.runners.AlchemyTestRunner;
import tech.sirwellington.alchemy.test.junit.runners.DontRepeat;
import tech.sirwellington.alchemy.test.junit.runners.GeneratePojo;
import tech.sirwellington.alchemy.test.junit.runners.Repeat;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static tech.redroma.google.places.data.TestResources.GSON;

/**
 *
 * @author SirWellington
 */
@Repeat(50)
@RunWith(AlchemyTestRunner.class)
public class PlaceTest
{

    @GeneratePojo
    private Place instance;

    @GeneratePojo
    private Place other;

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
    }

    @Test
    public void testHasIcon()
    {
        assertTrue(instance.hasIcon());
        instance.iconURL = null;
        assertFalse(instance.hasIcon());
    }

    @Test
    public void testHasGeometry()
    {
        assertTrue(instance.hasGeometry());
        instance.geometry = null;
        assertFalse(instance.hasGeometry());
    }

    @Test
    public void testHasName()
    {
        assertTrue(instance.hasName());
        instance.name = "";
        assertFalse(instance.hasName());
    }

    @Test
    public void testHasOpeningHours()
    {
        assertTrue(instance.hasOpeningHours());
        instance.openingHours = null;
        assertFalse(instance.hasOpeningHours());
    }

    @Test
    public void testHasPhotos()
    {
        assertTrue(instance.hasPhotos());
        
        instance.photos = Lists.emptyList();
        assertFalse(instance.hasPhotos());
        
        instance.photos = null;
        assertFalse(instance.hasPhotos());
    }

    @Test
    public void testHasRating()
    {
        assertTrue(instance.hasRating());
        instance.rating = null;
        assertFalse(instance.hasRating());
    }

    @Test
    public void testHasPriceLevel()
    {
        assertTrue(instance.hasPriceLevel());
        instance.priceLevel = null;
        assertFalse(instance.hasPriceLevel());
    }

    @Test
    public void testHasTypes()
    {
        assertTrue(instance.hasTypes());
        
        instance.types = null;
        assertFalse(instance.hasTypes());
        
        instance.types = Lists.emptyList();
        assertFalse(instance.hasTypes());
    }

    @Test
    public void testHasVicinity()
    {
        assertTrue(instance.hasVicinity());
        instance.vicinity = "";
        assertFalse(instance.hasVicinity());
    }

    @Test
    public void testHasFormattedAddress()
    {
        assertTrue(instance.hasFormattedAddress());
        instance.formattedAddress = "";
        assertFalse(instance.hasFormattedAddress());
    }

    @Test
    public void testIsPermanentlyClosed()
    {
        instance.permanentlyClosed = true;
        assertTrue(instance.permanentlyClosed);
        
        instance.permanentlyClosed = false;
        assertFalse(instance.isPermanentlyClosed());
        
        instance.permanentlyClosed = null;
        assertFalse(instance.isPermanentlyClosed());
    }

    @Test
    public void testHashCode()
    {
        assertThat(instance.hashCode(), is(instance.hashCode()));
        assertThat(instance.hasFormattedAddress(), not(other.hashCode()));
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
        assertThat(string, not(isEmptyOrNullString()));
    }

    @DontRepeat
    @Test
    public void testJSONDeserialization() throws Exception
    {
        String json = TestResources.loadFile("place.json");
        Place place = GSON.fromJson(json, Place.class);
        assertThat(place, notNullValue());
    }

}
