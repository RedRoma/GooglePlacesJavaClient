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
import tech.sirwellington.alchemy.test.junit.runners.DontRepeat;
import tech.sirwellington.alchemy.test.junit.runners.Repeat;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 *
 * @author SirWellington
 */
@Repeat(25)
@RunWith(AlchemyTestRunner.class)
public class GetPhotoRequestTest 
{
    private GetPhotoRequest instance;
    private GetPhotoRequest other;
    

    @Before
    public void setUp() throws Exception
    {
        
        setupData();
        setupMocks();
    }


    private void setupData() throws Exception
    {
        instance = Generators.createGetPhotoRequest();
        other = Generators.createGetPhotoRequest();
    }

    private void setupMocks() throws Exception
    {
        
    }

    @Test
    public void testHasPhotoReference()
    {
        assertTrue(instance.hasPhotoReference());
    }

    @Test
    public void testHasMaxHeight()
    {
        instance.hasMaxHeight();
    }

    @Test
    public void testHasMaxWidth()
    {
        instance.hasMaxWidth();
    }

    @Test
    public void testHashCode()
    {
        assertThat(instance.hashCode(), is(instance.hashCode()));
        assertThat(instance.hashCode(), not(other.hashCode()));
        
        GetPhotoRequest copy = copy(instance);
        assertThat(copy.hashCode(), is(instance.hashCode()));
    }

    @Test
    public void testEquals()
    {
        assertThat(instance, is(instance));
        
        GetPhotoRequest copy = copy(instance);
        assertThat(copy, is(instance));
        
        assertThat(instance, not(other));
    }

    @Test
    public void testToString()
    {
    }

    @DontRepeat
    @Test
    public void testNewBuilder()
    {
        GetPhotoRequest.Builder builder = GetPhotoRequest.newBuilder();
        assertThat(builder, notNullValue());
    }

    private GetPhotoRequest copy(GetPhotoRequest instance)
    {
        GetPhotoRequest.Builder builder = GetPhotoRequest.newBuilder()
            .withPhotoReference(instance.photoReference);
        
        if (instance.hasMaxHeight())
        {
            builder = builder.withMaxHeight(instance.maxHeight);
        }
        
        if (instance.hasMaxWidth())
        {
            builder = builder.withMaxWidth(instance.maxWidth);
        }
        
        return builder.build();
    }

}