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

 
package tech.redroma.google.places;


import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tech.redroma.google.places.data.OpeningHours;
import tech.redroma.google.places.data.Types;
import tech.sirwellington.alchemy.annotations.access.Internal;

/**
 *
 * @author SirWellington
 */
@Internal
final class InternalResources 
{
    private final static Logger LOG = LoggerFactory.getLogger(InternalResources.class);

    static final Gson GSON = new GsonBuilder()
        .registerTypeAdapter(Types.ReturnedPlaceType.class, Types.ReturnedPlaceType.createJSONDeserializer())
        .registerTypeAdapter(OpeningHours.Period.class, OpeningHours.Period.createDeserializer())
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .create();
}
