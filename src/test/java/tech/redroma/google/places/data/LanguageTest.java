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
import tech.sirwellington.alchemy.test.junit.runners.GenerateEnum;
import tech.sirwellington.alchemy.test.junit.runners.Repeat;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static tech.sirwellington.alchemy.generator.AlchemyGenerator.Get.one;
import static tech.sirwellington.alchemy.generator.StringGenerators.hexadecimalString;
import static tech.sirwellington.alchemy.test.junit.ThrowableAssertion.assertThrows;

/**
 *
 * @author SirWellington
 */
@Repeat(10)
@RunWith(AlchemyTestRunner.class)
public class LanguageTest 
{
    
    @GenerateEnum
    private Language language;

    @Before
    public void setUp() throws Exception
    {
    }

    
    @Test
    public void testLanguage() throws Exception
    {
        assertThat(language, notNullValue());
        
        String code = language.code;
        assertThat(code, not(isEmptyOrNullString()));
        
        Language result = Language.fromCode(code);
        assertThat(result, notNullValue());
        assertThat(result, is(language));
    }
    
    @Test
    public void testFromWithUnknownCode() throws Exception
    {
        String code = one(hexadecimalString(10));
        assertThrows(() -> Language.fromCode(code))
            .isInstanceOf(IllegalArgumentException.class);
    }
}