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

    public Location getLocation()
    {
        return location;
    }

    public void setLocation(Location location)
    {
        this.location = location;
    }

    public Viewport getViewport()
    {
        return viewport;
    }

    public void setViewport(Viewport viewport)
    {
        this.viewport = viewport;
    }

    @Override
    public String toString()
    {
        return "Geometry{" + "location=" + location + ", viewport=" + viewport + '}';
    }

}
