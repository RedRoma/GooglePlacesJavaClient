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
@Repeat(25)
@RunWith(AlchemyTestRunner.class)
public class OpeningHoursTest
{
    
    @GeneratePojo
    private OpeningHours instance;
    
    @GeneratePojo
    private OpeningHours first;
    
    @GeneratePojo
    private OpeningHours second;
    
    @Before
    public void setUp() throws Exception
    {
        
        setupData();
    }
    
    private void setupData() throws Exception
    {
        
    }
    
    @Test
    public void testInstance()
    {
        assertThat(instance, notNullValue());
        assertThat(first, notNullValue());
        assertThat(second, notNullValue());
        
    }

    @Test
    public void testCreate()
    {
        OpeningHours result = OpeningHours.create(instance.openNow, Lists.copy(instance.weekdayText));
        assertThat(result, is(instance));
    }
    
    @DontRepeat
    @Test
    public void testIsOpenNow()
    {
        instance.openNow = true;
        assertTrue(instance.isOpenNow());
        
        instance.openNow = false;
        assertFalse(instance.isOpenNow());
        
        instance.openNow = null;
        assertFalse(instance.isOpenNow());
    }
    
    @Test
    public void testHasWeekdayText()
    {
        assertTrue(instance.hasWeekdayText());
        
        instance.weekdayText = Lists.emptyList();
        assertFalse(instance.hasWeekdayText());
        
        instance.weekdayText = null;
        assertFalse(instance.hasWeekdayText());
    }
    
    @Test
    public void testHashCode()
    {
        assertThat(instance.hashCode(), is(instance.hashCode()));
        assertThat(first.hashCode(), not(second.hashCode()));
    }
    
    @Test
    public void testEquals()
    {
        OpeningHours copy = OpeningHours.create(instance.openNow, instance.weekdayText);
        assertThat(copy, is(instance));
        
        assertThat(instance, is(instance));
        assertThat(first, not(second));
    }
    
    @Test
    public void testToString()
    {
        String string = instance.toString();
        assertThat(string, not(isEmptyOrNullString()));
    }
    
}
