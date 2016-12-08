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
import tech.sirwellington.alchemy.arguments.AlchemyAssertion;
import tech.sirwellington.alchemy.generator.AlchemyGenerator;
import tech.sirwellington.alchemy.test.junit.runners.AlchemyTestRunner;
import tech.sirwellington.alchemy.test.junit.runners.DontRepeat;
import tech.sirwellington.alchemy.test.junit.runners.GeneratePojo;
import tech.sirwellington.alchemy.test.junit.runners.Repeat;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static tech.redroma.google.places.data.Generators.locations;
import static tech.sirwellington.alchemy.generator.AlchemyGenerator.one;
import static tech.sirwellington.alchemy.generator.NumberGenerators.doubles;
import static tech.sirwellington.alchemy.test.junit.ThrowableAssertion.assertThrows;


/**
 *
 * @author SirWellington
 */
@Repeat(50)
@RunWith(AlchemyTestRunner.class)
public class LocationTest 
{
    @GeneratePojo
    private Location instance;
    
    @GeneratePojo
    private Location first;
    
    @GeneratePojo
    private Location second;
    

    @Before
    public void setUp() throws Exception
    {
        
        setupData();
        setupMocks();
    }


    private void setupData() throws Exception
    {
        instance = one(locations());
        first = one(locations());
        second = one(locations());
    }

    private void setupMocks() throws Exception
    {
        
    }

    @Test
    public void testOf()
    {
        Location result = Location.of(instance.latitude, instance.longitude);
        assertThat(result, is(instance));
    }

    @Test
    public void testOfWithInvalid()
    {
        AlchemyGenerator<Double> badNumbers = doubles(200, 2_000);
        
        assertThrows(() -> Location.of(badNumbers.get(), badNumbers.get()))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void testIsSet()
    {
    }

    @DontRepeat
    @Test
    public void testIsSetWhenNotSet()
    {
        instance.latitude = null;
        instance.longitude = null;
        assertFalse(instance.isSet());
        
        instance.latitude = first.latitude;
        assertFalse(instance.isSet());
        
        instance.longitude = first.longitude;
        instance.latitude = null;
        assertFalse(instance.isSet());
    }

    @Test
    public void testHashCode()
    {
        assertThat(instance.hashCode(), not(0));
        assertThat(first.hashCode(), not(second.hashCode()));
        
        Location copy = Location.of(instance.latitude, instance.longitude);
        assertThat(copy.hashCode(), is(instance.hashCode()));
    }

    @Test
    public void testEquals()
    {
        Location copy = Location.of(instance.latitude, instance.longitude);
        assertThat(copy, is(instance));
        assertThat(instance, is(copy));
    }
    
    @Test
    public void testEqualsWhenDifferent()
    {
        assertThat(first, not(second));
        assertThat(second, not(first));
    }

    @DontRepeat
    @Test
    public void testToString()
    {
        String string = instance.toString();
        assertThat(string, not(isEmptyOrNullString()));
    }

    @Test
    public void testValidLocation()
    {
        AlchemyAssertion<Location> assertion = Location.validLocation();
        
        assertion.check(first);
        assertion.check(second);
        assertion.check(instance);
    }

    @Test
    public void testValidLocationWithInvalid()
    {
        AlchemyAssertion<Location> assertion = Location.validLocation();
        
        assertThrows(() -> assertion.check(null)).isInstanceOf(IllegalArgumentException.class);
        
        AlchemyGenerator<Double> badNumbers = doubles(200, 2_000);
        instance.latitude = one(badNumbers);
        instance.longitude = one(badNumbers);
        
        assertThrows(() -> assertion.check(instance))
            .isInstanceOf(IllegalArgumentException.class);
    }

}