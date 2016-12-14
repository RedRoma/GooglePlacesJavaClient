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
import tech.redroma.google.places.exceptions.GooglePlacesException;
import tech.redroma.google.places.requests.GetPhotoRequest;
import tech.redroma.google.places.requests.GetPlaceDetailsRequest;
import tech.redroma.google.places.requests.NearbySearchRequest;
import tech.redroma.google.places.responses.GetPlaceDetailsResponse;
import tech.redroma.google.places.responses.NearbySearchResponse;
import tech.sirwellington.alchemy.test.junit.runners.AlchemyTestRunner;
import tech.sirwellington.alchemy.test.junit.runners.GenerateString;
import tech.sirwellington.alchemy.test.junit.runners.Repeat;

import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static tech.sirwellington.alchemy.test.junit.runners.GenerateString.Type.HEXADECIMAL;


/**
 *
 * @author SirWellington
 */
@Repeat(10)
@RunWith(AlchemyTestRunner.class)
public class GooglePlacesAPITest 
{

    @GenerateString(HEXADECIMAL)
    private String apiKey;
    
    private GooglePlacesAPI instance;
    
    private TestGooglePlacesAPIImpl testInstance;
    
    
    @Before
    public void setUp() throws Exception
    {
        
        setupData();
        setupMocks();
        instance = GooglePlacesAPI.create(apiKey);
        testInstance = new TestGooglePlacesAPIImpl();
    }


    private void setupData() throws Exception
    {
        
    }

    private void setupMocks() throws Exception
    {
        
    }
    
    @Test
    public void testInstance() throws Exception
    {
        assertThat(instance, notNullValue());
    }

    @Test
    public void testSearchNearbyPlaces()
    {
    }

    @Test
    public void testSimpleSearchNearbyPlaces()
    {
    }

    @Test
    public void testGetPlaceDetails()
    {
    }

    @Test
    public void testSimpleGetPlaceDetails()
    {
    }

    @Test
    public void testGetPhoto()
    {
    }

    @Test
    public void testDownloadPhoto()
    {
    }

    @Test
    public void testCreate()
    {
    }

    private class TestGooglePlacesAPIImpl implements GooglePlacesAPI
    {

        public NearbySearchResponse searchNearbyPlaces(NearbySearchRequest request) throws GooglePlacesException
        {
            return null;
        }

        public GetPlaceDetailsResponse getPlaceDetails(GetPlaceDetailsRequest request) throws GooglePlacesException
        {
            return null;
        }

        public URL getPhoto(GetPhotoRequest request) throws GooglePlacesException
        {
            return null;
        }
    }

}