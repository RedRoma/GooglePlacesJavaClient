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

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static tech.redroma.google.places.data.TestResources.GSON;

/**
 *
 * @author SirWellington
 */
@Repeat(10)
@RunWith(AlchemyTestRunner.class)
public class ReviewTest
{

    @GeneratePojo
    private Review instance;

    @GeneratePojo
    private Review other;

    @Before
    public void setUp() throws Exception
    {
    }

    @Test
    public void testInstance() throws Exception
    {
        checkResult(instance);
        checkResult(other);
    }

    @Test
    public void testHashCode()
    {
        assertThat(instance.hashCode(), is(instance.hashCode()));
        assertThat(instance.hashCode(), not(other.hashCode()));

        Review copy = copy(instance);
        assertThat(copy.hashCode(), is(instance.hashCode()));
    }

    @Test
    public void testEquals()
    {
        assertThat(instance, is(instance));
        assertThat(instance, not(other));

        Review copy = copy(instance);
        assertThat(copy, is(instance));
    }

    @Test
    public void testToString()
    {
        String string = instance.toString();
        assertThat(string, not(isEmptyOrNullString()));
    }

    @DontRepeat
    @Test
    public void testDeserializeFromJSON() throws Exception
    {
        String file = TestResources.loadFile("review.json");
        Review result = GSON.fromJson(file, Review.class);
        checkResult(result);
    }

    private void checkResult(Review result)
    {
        assertThat(result, notNullValue());
        assertThat(result.authorName, not(isEmptyOrNullString()));
        assertThat(result.authorPhotoURL, not(isEmptyOrNullString()));
        assertThat(result.authorURL, not(isEmptyOrNullString()));
        assertThat(result.relativeTimeDescription, not(isEmptyOrNullString()));
        assertThat(result.text, not(isEmptyOrNullString()));

        assertThat(result.aspects, not(empty()));
        assertThat(result.language, notNullValue());
        assertThat(result.rating, notNullValue());
        assertThat(result.time, notNullValue());
        assertThat(result.getTime(), notNullValue());
    }

    private Review copy(Review instance)
    {
        Review copy = new Review();

        copy.aspects = Lists.copy(instance.aspects);
        copy.authorName = instance.authorName;
        copy.authorPhotoURL = instance.authorPhotoURL;
        copy.authorURL = instance.authorURL;
        copy.language = instance.language;
        copy.rating = instance.rating;
        copy.relativeTimeDescription = instance.relativeTimeDescription;
        copy.text = instance.text;
        copy.time = instance.time;

        return copy;
    }

}
