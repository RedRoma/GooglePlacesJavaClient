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

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import tech.sirwellington.alchemy.test.junit.runners.AlchemyTestRunner;
import tech.sirwellington.alchemy.test.junit.runners.Repeat;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 *
 * @author SirWellington
 */
@Repeat(5)
@RunWith(AlchemyTestRunner.class)
public class URLProviderProductionTest
{

    private URLProviderProduction instance;

    @Before
    public void setUp() throws Exception
    {

        instance = new URLProviderProduction();
    }

    @Test
    public void testGetBase()
    {
        String result = instance.getBase();
        assertThat(result, is(URLProviderProduction.URLs.BASE));
    }

    @Test
    public void testGetPhotoAPI()
    {
        String result = instance.getPhotoAPI();
        assertThat(result, is(URLProviderProduction.URLs.PHOTO));

    }

    @Test
    public void testGetPlaceDetails()
    {
        String result = instance.getPlaceDetails();
        assertThat(result, is(URLProviderProduction.URLs.PLACE_DETAILS));
    }

    @Test
    public void testGetNearbySearch()
    {
        String result = instance.getNearbySearch();
        assertThat(result, is(URLProviderProduction.URLs.NEARBY_SEARCH));
    }

    @Test
    public void testGetAutocomplete()
    {
        String result = instance.getAutocomplete();
        assertThat(result, is(URLProviderProduction.URLs.AUTOCOMPLETE));
    }

}
