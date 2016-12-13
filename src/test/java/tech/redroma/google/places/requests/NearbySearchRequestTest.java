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
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 *
 * @author SirWellington
 */
@Repeat(10)
@RunWith(AlchemyTestRunner.class)
public class NearbySearchRequestTest
{

    private NearbySearchRequest instance;
    private NearbySearchRequest other;

    @Before
    public void setUp() throws Exception
    {

        setupData();
    }

    private void setupData() throws Exception
    {
        instance = Generators.createNearbySearchRequest();
        other = Generators.createNearbySearchRequest();
    }

    @Test
    public void testHasLocation()
    {
        assertTrue(instance.hasLocation());
    }

    @Test
    public void testHasRadius()
    {
        assertTrue(instance.hasRadius());
    }

    @Test
    public void testHasKeyword()
    {
        assertTrue(instance.hasKeyword());
    }

    @Test
    public void testHasName()
    {
        instance.hasName();
    }

    @Test
    public void testHasLanguage()
    {
        assertTrue(instance.hasLanguage());
    }

    @Test
    public void testHasMinAndMaxPrice()
    {
        instance.hasMinAndMaxPrice();
    }

    @Test
    public void testHasOnlyOpenNow()
    {
        assertTrue(instance.hasOnlyOpenNow());
    }

    @Test
    public void testHasRankBy()
    {
        instance.hasRankBy();
    }

    @Test
    public void testHasType()
    {
        instance.hasType();
    }

    @Test
    public void testHasPageToken()
    {
        instance.hasPageToken();
    }

    @Test
    public void testHashCode()
    {
        assertThat(instance.hashCode(), is(instance.hashCode()));
        assertThat(instance.hashCode(), not(other.hashCode()));

        NearbySearchRequest copy = copy(instance);
        assertThat(copy.hashCode(), is(instance.hashCode()));
    }

    @Test
    public void testEquals()
    {
        assertThat(instance, is(instance));
        assertThat(instance, not(other));

        NearbySearchRequest copy = copy(instance);
        assertThat(copy, is(instance));
    }

    @Test
    public void testToString()
    {
        String string = instance.toString();
        assertThat(string, not(isEmptyOrNullString()));
    }

    @Test
    public void testNewBuilder()
    {
        NearbySearchRequest.Builder builder = NearbySearchRequest.newBuilder();
        assertThat(builder, notNullValue());
    }

    private NearbySearchRequest copy(NearbySearchRequest request)
    {
        return NearbySearchRequest.Builder.from(request)
            .build();
    }

}
