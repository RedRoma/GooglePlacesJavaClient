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

import com.google.gson.JsonDeserializer;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sir.wellington.alchemy.collections.maps.Maps;
import tech.sirwellington.alchemy.annotations.arguments.NonEmpty;

import static tech.sirwellington.alchemy.arguments.Arguments.checkThat;
import static tech.sirwellington.alchemy.arguments.assertions.Assertions.equalTo;
import static tech.sirwellington.alchemy.arguments.assertions.CollectionAssertions.keyInMap;

/**
 * Supported languages by the Google Places API.
 * 
 * @author SirWellington
 */
public enum Language
{
    Arabic("ar"),
    Basque("eu"),
    Bengali("bn"),
    Bulgarian("bg"),
    Catalan("ca"),
    Chinese_Simplified("zh-CN"),
    Chinese_Traditional("zh-TW"),
    Croatian("hr"),
    Czech("cs"),
    Danish("da"),
    Dutch("nl"),
    English("en"),
    English_Australian("en-AU"),
    English_Great_Britain("en-GB"),
    Farsi("fa"),
    Finnish("fi"),
    French("fr"),
    Galician("gl"),
    German("de"),
    Greek("el"),
    Gujarati("gu"),
    Hebrew("iw"),
    Hindi("hi"),
    Hungarian("hu"),
    Indonesian("id"),
    Italian("it"),
    Japanese("ja"),
    Kannada("kn"),
    Korean("ko"),
    Latvian("lv"),
    Lithuanian("lt"),
    Malayalam("ml"),
    Marathi("mr"),
    Norwegian("no"),
    Polish("pl"),
    Portugese_Brazil("pt-BR"),
    Portugese_Portugal("pt-PT"),
    Portuguese("pt"),
    Romanian("ro"),
    Russian("ru"),
    Serbian("sr"),
    Slovak("sk"),
    Slovenian("sl"),
    Spanish("es"),
    Swedish("sv"),
    Tagalog("tl"),
    Tamil("ta"),
    Telugu("te"),
    Thai("th"),
    Turkish("tr"),
    Ukrainian("uk"),
    Vietnamese("vi"),
    Filipino("fil")
    ;

    public final String code;

    private Language(String code)
    {
        this.code = code;
    }

    /**
     * Finds the corresponding language given the language code.
     * 
     * @param code The language code.
     * @return
     * @throws IllegalArgumentException 
     */
    public static Language fromCode(@NonEmpty String code) throws IllegalArgumentException
    {
        checkThat(code)
            .usingMessage("Unknown language code: " + code)
            .is(keyInMap(CODE_TO_LANGUAGE));
        
        return CODE_TO_LANGUAGE.get(code);
    } 
    
    private static final Map<String, Language> CODE_TO_LANGUAGE = createLanguageToCodeMap();

    private static Map<String, Language> createLanguageToCodeMap()
    {
        Map<String, Language> map = Maps.create();

        for (Language language : Language.values())
        {
            map.put(language.code, language);
        }

        return map;
    }
    
    public static JsonDeserializer<Language> createDeserializer()
    {
        final Logger LOG = LoggerFactory.getLogger(Language.class);
        
        return (json, type, context) ->
        {
            checkThat(type)
                .is(equalTo(Language.class));
            
            if (json == null || json.isJsonNull())
            {
                return null;
            }
            
            if (!json.isJsonPrimitive())
            {
                return null;
            }
            
            String code = json.getAsString();
            
            try 
            {
                return Language.fromCode(code);
            }
            catch (IllegalArgumentException ex)
            {
                LOG.warn("Failed to deserialize from JSON: {}", code, ex);
                return null;
            }
        };
    }
}
