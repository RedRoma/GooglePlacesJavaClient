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

import com.google.gson.annotations.SerializedName;
import java.util.Objects;
import tech.sirwellington.alchemy.annotations.concurrency.Mutable;
import tech.sirwellington.alchemy.annotations.concurrency.ThreadUnsafe;
import tech.sirwellington.alchemy.annotations.objects.Pojo;
import tech.sirwellington.alchemy.arguments.AlchemyAssertion;

import static tech.sirwellington.alchemy.arguments.Arguments.checkThat;
import static tech.sirwellington.alchemy.arguments.assertions.Assertions.notNull;
import static tech.sirwellington.alchemy.arguments.assertions.GeolocationAssertions.validLatitude;
import static tech.sirwellington.alchemy.arguments.assertions.GeolocationAssertions.validLongitude;

/**
 * Represents a Geo-Coordinate.
 *
 * @author SirWellington
 */
@Mutable
@ThreadUnsafe
@Pojo
public class Location
{

    /** Latitude field. */
    @SerializedName("lat")
    public Double latitude;
    
    /** Longitude field. */
    @SerializedName("lon")
    public Double longitude;

    public Location()
    {
    }

    public Location(double latitude, double longitude)
    {
        checkThat(latitude).is(validLatitude());
        checkThat(longitude).is(validLongitude());

        this.latitude = latitude;
        this.longitude = longitude;
    }

    public static Location of(double latitude, double longitude)
    {
        return new Location(latitude, longitude);
    }
    
    public static AlchemyAssertion<Location> validLocation()
    {
        return l ->
        {
            checkThat(l)
                .usingMessage("Location is null")
                .is(notNull());
            
            checkThat(l.latitude).is(validLatitude());
            checkThat(l.longitude).is(validLongitude());
        };
    }

    /**
     * Returns whether the 
     * @return 
     */
    public boolean isSet()
    {
        return latitude != null && longitude != null;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 43 * hash + Objects.hashCode(this.latitude);
        hash = 43 * hash + Objects.hashCode(this.longitude);
        return hash;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        final Location other = (Location) obj;
        if (!Objects.equals(this.latitude, other.latitude))
        {
            return false;
        }
        if (!Objects.equals(this.longitude, other.longitude))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "Location{" + "latitude=" + latitude + ", longitude=" + longitude + '}';
    }

}
