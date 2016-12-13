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

package tech.redroma.google.places.requests;

import java.util.Objects;
import tech.redroma.google.places.data.Location;
import tech.redroma.google.places.data.PriceLevel;
import tech.redroma.google.places.data.Types;
import tech.sirwellington.alchemy.annotations.concurrency.Immutable;
import tech.sirwellington.alchemy.annotations.concurrency.ThreadSafe;
import tech.sirwellington.alchemy.annotations.designs.patterns.BuilderPattern;

import static com.google.common.base.Strings.isNullOrEmpty;
import static tech.sirwellington.alchemy.annotations.designs.patterns.BuilderPattern.Role.PRODUCT;

/**
 *
 * @author SirWellington
 */
@Immutable
@ThreadSafe
@BuilderPattern(role = PRODUCT)
public final class NearbySearchRequest
{

    private Location location;
    private double radiusInMeters;
    private String keyword;
    private String language;
    private PriceLevel minPrice;
    private PriceLevel maxPrice;
    private boolean onlyOpenNow;
    private Ranking rankBy;
    private Types.PlaceType type;

    private NearbySearchRequest()
    {
    }

    NearbySearchRequest(Location location,
                        double radiusInMeters,
                        String keyword,
                        String language,
                        PriceLevel minPrice,
                        PriceLevel maxPrice,
                        boolean onlyOpenNow,
                        Ranking rankBy,
                        Types.PlaceType type)
    {
        this.location = location;
        this.radiusInMeters = radiusInMeters;
        this.keyword = keyword;
        this.language = language;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.onlyOpenNow = onlyOpenNow;
        this.rankBy = rankBy;
        this.type = type;
    }

    public boolean hasLocation()
    {
        return Objects.nonNull(location);
    }

    public boolean hasRadius()
    {
        return radiusInMeters > 0.0;
    }

    public boolean hasKeyword()
    {
        return !isNullOrEmpty(keyword);
    }

    public boolean hasLanguage()
    {
        return !isNullOrEmpty(language);
    }

    public boolean hasMinAndMaxPrice()
    {
        return Objects.nonNull(minPrice) && Objects.nonNull(maxPrice);
    }

    public boolean hasOnlyOpenNow()
    {
        return onlyOpenNow;
    }

    public boolean hasRankBy()
    {
        return Objects.nonNull(rankBy);
    }

    public boolean hasType()
    {
        return Objects.nonNull(type);
    }

    @Override
    public int hashCode()
    {
        int hash = 3;
        hash = 71 * hash + Objects.hashCode(this.location);
        hash = 71 * hash + (int) (Double.doubleToLongBits(this.radiusInMeters) ^ (Double.doubleToLongBits(this.radiusInMeters) >>> 32));
        hash = 71 * hash + Objects.hashCode(this.keyword);
        hash = 71 * hash + Objects.hashCode(this.language);
        hash = 71 * hash + Objects.hashCode(this.minPrice);
        hash = 71 * hash + Objects.hashCode(this.maxPrice);
        hash = 71 * hash + (this.onlyOpenNow ? 1 : 0);
        hash = 71 * hash + Objects.hashCode(this.rankBy);
        hash = 71 * hash + Objects.hashCode(this.type);
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
        final NearbySearchRequest other = (NearbySearchRequest) obj;
        if (Double.doubleToLongBits(this.radiusInMeters) != Double.doubleToLongBits(other.radiusInMeters))
        {
            return false;
        }
        if (this.onlyOpenNow != other.onlyOpenNow)
        {
            return false;
        }
        if (!Objects.equals(this.keyword, other.keyword))
        {
            return false;
        }
        if (!Objects.equals(this.language, other.language))
        {
            return false;
        }
        if (!Objects.equals(this.location, other.location))
        {
            return false;
        }
        if (this.minPrice != other.minPrice)
        {
            return false;
        }
        if (this.maxPrice != other.maxPrice)
        {
            return false;
        }
        if (this.rankBy != other.rankBy)
        {
            return false;
        }
        if (this.type != other.type)
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "NearbySearchRequest{" + "location=" + location + ", radiusInMeters=" + radiusInMeters + ", keyword=" + keyword + ", language=" + language + ", minPrice=" + minPrice + ", maxPrice=" + maxPrice + ", onlyOpenNow=" + onlyOpenNow + ", rankBy=" + rankBy + ", type=" + type + '}';
    }

    public static enum Ranking
    {
        PROMINENCE,
        DISTANCE
    }

}
