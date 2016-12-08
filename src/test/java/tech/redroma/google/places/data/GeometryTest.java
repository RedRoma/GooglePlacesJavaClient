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


/**
 *
 * @author SirWellington
 */
@Repeat(50)
@RunWith(AlchemyTestRunner.class)
public class GeometryTest 
{
    
    @GeneratePojo
    private Geometry instance;
    
    @GeneratePojo
    private Geometry first;
    
    @GeneratePojo
    private Geometry second;
    

    @Before
    public void setUp() throws Exception
    {
        testInstances();
        setupData();
    }


    private void setupData() throws Exception
    {
        instance = Generators.createGeometry();
        first = Generators.createGeometry();
        second = Generators.createGeometry();
    }

    private void testInstances()
    {
        assertThat(instance, notNullValue());
        assertThat(first, notNullValue());
        assertThat(second, notNullValue());
    }

    @Test
    public void testHasLocation()
    {
        assertTrue(instance.hasLocation());
        instance.location = null;
        assertFalse(instance.hasLocation());
    }

    @Test
    public void testHasViewport()
    {
        assertTrue(instance.hasViewport());
        instance.viewport = null;
        assertFalse(instance.hasViewport());
    }

    @Test
    public void testHashCode()
    {
        assertThat(first.hashCode(), not(second.hashCode()));
        assertThat(instance.hashCode(), is(instance.hashCode()));
    }

    @Test
    public void testEquals()
    {
        assertThat(instance, is(instance));
        
        Geometry copy = copy(instance);
        assertThat(copy, is(instance));
        
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

    private Geometry copy(Geometry instance)
    {
        Geometry copy = new Geometry(instance.location, instance.viewport);
        return copy;
        
    }

    @Test
    public void testCreate()
    {
        Geometry result = Geometry.create(instance.location, instance.viewport);
        assertThat(result, is(instance));
    }
 

}