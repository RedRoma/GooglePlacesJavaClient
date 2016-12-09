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

import static tech.redroma.google.places.data.Location.validLocation;
import static tech.sirwellington.alchemy.arguments.Arguments.checkThat;

/**
 *
 * @author SirWellington
 */
@Mutable
@ThreadUnsafe
@Pojo
public class Viewport
{

    @SerializedName(value = "northeast")
    public Location northEast;

    @SerializedName(value = "southwest")
    public Location southWest;

    public Viewport()
    {
    }

    Viewport(Location northEast, Location southWest)
    {
        checkThat(northEast, southWest).are(validLocation());
        
        this.northEast = northEast;
        this.southWest = southWest;
    }

    public boolean hasNorthEast()
    {
        return Objects.nonNull(northEast);
    }
    
    public boolean hasSouthWest()
    {
        return Objects.nonNull(southWest);
    }
    
    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.northEast);
        hash = 97 * hash + Objects.hashCode(this.southWest);
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
        final Viewport other = (Viewport) obj;
        if (!Objects.equals(this.northEast, other.northEast))
        {
            return false;
        }
        if (!Objects.equals(this.southWest, other.southWest))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "Viewport{" + "northEast=" + northEast + ", southWest=" + southWest + '}';
    }

}
