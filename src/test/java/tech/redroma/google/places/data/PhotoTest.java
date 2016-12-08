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


/**
 *
 * @author SirWellington
 */
@Repeat(50)
@RunWith(AlchemyTestRunner.class)
public class PhotoTest 
{

    @GeneratePojo
    private Photo instance;
    
    @GeneratePojo
    private Photo first;

    @GeneratePojo
    private Photo second;
    
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
        assertThat(first, notNullValue());
        assertThat(second, notNullValue());
    }

    @Test
    public void testHasWidth()
    {
        assertTrue(instance.hasWidth());
        instance.width = null;
        assertFalse(instance.hasWidth());
    }

    @Test
    public void testHasHeight()
    {
        assertTrue(instance.hasHeight());
        instance.height = null;
        assertFalse(instance.hasHeight());
    }

    @Test
    public void testHasHTMLAttributions()
    {
        assertTrue(instance.hasHTMLAttributions());
        instance.htmlAttributions = Lists.emptyList();
        assertFalse(instance.hasHTMLAttributions());
        
        instance.htmlAttributions = null;
        assertFalse(instance.hasHTMLAttributions());
    }

    @Test
    public void testHasPhotoReference()
    {
        assertTrue(instance.hasPhotoReference());
        
        instance.photoReference = "";
        assertFalse(instance.hasPhotoReference());
        
        instance.photoReference = null;
        assertFalse(instance.hasPhotoReference());
    }

    @Test
    public void testHashCode()
    {
        assertThat(first.hashCode(), not(second.hashCode()));
        assertThat(second.hashCode(), not(first.hashCode()));
        assertThat(instance.hashCode(), is(instance.hashCode()));
    }

    @Test
    public void testEquals()
    {
        assertThat(first, not(second));
        
        Photo copy = copyPhoto(instance);
        assertThat(copy, is(instance));
    }

    @DontRepeat
    @Test
    public void testToString()
    {
        String string = instance.toString();
        assertThat(string, not(isEmptyOrNullString()));
    }

    private Photo copyPhoto(Photo photo)
    {
        Photo copy = new Photo();

        copy.height = photo.height;
        copy.width = photo.width;
        copy.htmlAttributions = Lists.copy(photo.htmlAttributions);
        copy.photoReference = photo.photoReference;

        return copy;
    }

}