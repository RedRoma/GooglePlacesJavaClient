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
import tech.redroma.google.places.data.Types.AutocompleteType;
import tech.redroma.google.places.data.Types.PlaceType;
import tech.redroma.google.places.data.Types.ReturnedPlaceType;
import tech.sirwellington.alchemy.test.junit.runners.AlchemyTestRunner;
import tech.sirwellington.alchemy.test.junit.runners.GenerateEnum;
import tech.sirwellington.alchemy.test.junit.runners.Repeat;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 *
 * @author SirWellington
 */
@Repeat(10)
@RunWith(AlchemyTestRunner.class)
public class TypesTest
{

    @GenerateEnum
    private PlaceType placeType;
    
    @GenerateEnum
    private ReturnedPlaceType returnedPlaceType;

    @GenerateEnum
    private AutocompleteType autocompleteType;

    @Before
    public void setUp() throws Exception
    {
        setupData();
        setupMocks();
    }

    private void setupData() throws Exception
    {

    }

    private void setupMocks() throws Exception
    {

    }

    @Test
    public void testPlaceType() throws Exception
    {
        assertThat(placeType, notNullValue());
        
        String text = placeType.asText();
        assertThat(text, not(isEmptyOrNullString()));
    }
    
    @Test
    public void testPlaceTypeFrom() throws Exception
    {
        String text = placeType.asText();
        PlaceType result = PlaceType.from(text);
        assertThat(result, is(placeType));
    }
    
    @Test
    public void testReturnedPlaceType() throws Exception
    {
        assertThat(returnedPlaceType, notNullValue());
    }

    @Test
    public void testReturnedPlaceTypeFrom() throws Exception
    {
        String text = returnedPlaceType.asText();
        ReturnedPlaceType result = ReturnedPlaceType.from(text);
        assertThat(result, is(returnedPlaceType));
    }    

    @Test
    public void testAutocompleteType() throws Exception
    {
        assertThat(autocompleteType, notNullValue());
    }
    
    @Test
    public void testAutocompleteTypeFrom() throws Exception
    {
        String text = autocompleteType.asText();
        AutocompleteType result = AutocompleteType.from(text);
        assertThat(result, is(autocompleteType));
    }
}
