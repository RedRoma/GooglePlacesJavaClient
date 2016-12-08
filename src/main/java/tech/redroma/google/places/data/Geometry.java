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

import java.util.Objects;

import static tech.redroma.google.places.data.Location.validLocation;
import static tech.sirwellington.alchemy.arguments.Arguments.checkThat;
import static tech.sirwellington.alchemy.arguments.assertions.Assertions.notNull;

/**
 *
 * @author SirWellington
 */
public class Geometry
{

    public Location location;
    public Viewport viewport;

    public Geometry()
    {
    }

    public Geometry(Location location, Viewport viewport)
    {
        checkThat(location).is(validLocation());
        checkThat(viewport).is(notNull());

        this.location = location;
        this.viewport = viewport;
    }

    @Override
    public int hashCode()
    {
        int hash = 5;
        hash = 71 * hash + Objects.hashCode(this.location);
        hash = 71 * hash + Objects.hashCode(this.viewport);
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
        final Geometry other = (Geometry) obj;
        if (!Objects.equals(this.location, other.location))
        {
            return false;
        }
        if (!Objects.equals(this.viewport, other.viewport))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "Geometry{" + "location=" + location + ", viewport=" + viewport + '}';
    }

}
