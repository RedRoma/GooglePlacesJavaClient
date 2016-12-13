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

package tech.redroma.google.places.requests;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import tech.redroma.google.places.data.Generators;
import tech.sirwellington.alchemy.test.junit.runners.AlchemyTestRunner;
import tech.sirwellington.alchemy.test.junit.runners.Repeat;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 *
 * @author SirWellington
 */
@Repeat(25)
@RunWith(AlchemyTestRunner.class)
public class AutocompletePlaceRequestTest
{

    private AutocompletePlaceRequest instance;
    private AutocompletePlaceRequest other;

    @Before
    public void setUp() throws Exception
    {

        setupData();
    }

    private void setupData() throws Exception
    {
        instance = Generators.createAutocompleteRequest();
        other = Generators.createAutocompleteRequest();
    }

    @Test
    public void testHasInput()
    {
        assertTrue(instance.hasInput());
    }

    @Test
    public void testHasOffset()
    {
        instance.hasOffset();
    }

    @Test
    public void testHasLocation()
    {
        assertTrue(instance.hasLocation());
    }

    @Test
    public void testHasRadius()
    {
        assertTrue(instance.hasLocation());
    }

    @Test
    public void testHasLanguage()
    {
        assertTrue(instance.hasLanguage());
    }

    @Test
    public void testHasTypes()
    {
        instance.hasTypes();
    }

    @Test
    public void testHasStrictBounds()
    {
        instance.hasStrictBounds();
    }

    @Test
    public void testHashCode()
    {
        assertThat(instance.hashCode(), is(instance.hashCode()));
        assertThat(instance.hashCode(), not(other.hashCode()));
        
        AutocompletePlaceRequest copy = copy(instance);
        assertThat(copy.hashCode(), is(instance.hashCode()));
    }

    @Test
    public void testEquals()
    {
        assertThat(instance, is(instance));
        assertThat(instance, not(other));
        
        AutocompletePlaceRequest copy = copy(instance);
        assertThat(copy, is(instance));
    }

    @Test
    public void testToString()
    {
        String string = instance.toString();
        assertFalse(string.isEmpty());
    }

    @Test
    public void testNewBuilder()
    {
        AutocompletePlaceRequest.Builder builder = AutocompletePlaceRequest.newBuilder();
        assertThat(builder, notNullValue());
    }

    private AutocompletePlaceRequest copy(AutocompletePlaceRequest request)
    {
        return AutocompletePlaceRequest.Builder.from(request)
            .build();
    }

}
