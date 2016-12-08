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


import tech.sirwellington.alchemy.generator.AlchemyGenerator;

import static tech.sirwellington.alchemy.generator.AlchemyGenerator.one;
import static tech.sirwellington.alchemy.generator.GeolocationGenerators.latitudes;
import static tech.sirwellington.alchemy.generator.GeolocationGenerators.longitudes;

/**
 *
 * @author SirWellington
 */
public class Generators 
{

    public static AlchemyGenerator<Location> locations()
    {
        AlchemyGenerator<Double> lats = latitudes();
        AlchemyGenerator<Double> lons = longitudes();
        
        return () ->
        {
            return Location.of(one(lats), one(lons));
        };
    }
}
