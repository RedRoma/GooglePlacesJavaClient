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
import static tech.redroma.google.places.data.Location.validLocation;
import static tech.sirwellington.alchemy.annotations.designs.patterns.BuilderPattern.Role.BUILDER;
import static tech.sirwellington.alchemy.annotations.designs.patterns.BuilderPattern.Role.PRODUCT;
import static tech.sirwellington.alchemy.arguments.Arguments.checkThat;
import static tech.sirwellington.alchemy.arguments.assertions.Assertions.nullObject;
import static tech.sirwellington.alchemy.arguments.assertions.NumberAssertions.lessThan;

/**
 *
 * @author SirWellington
 */
@Immutable
@ThreadSafe
@BuilderPattern(role = PRODUCT)
public final class NearbySearchRequest
{

    private final Location location;
    private final Double radiusInMeters;
    private final String keyword;
    private final String name;
    private final String language;
    private final PriceLevel minPrice;
    private final PriceLevel maxPrice;
    private final boolean onlyOpenNow;
    private final Ranking rankBy;
    private final Types.PlaceType type;
    private final String pageToken;

    NearbySearchRequest(Location location,
                        Double radiusInMeters,
                        String keyword,
                        String name,
                        String language,
                        PriceLevel minPrice,
                        PriceLevel maxPrice,
                        boolean onlyOpenNow,
                        Ranking rankBy,
                        Types.PlaceType type,
                        String pageToken)
    {
        this.location = location;
        this.radiusInMeters = radiusInMeters;
        this.keyword = keyword;
        this.name = name;
        this.language = language;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.onlyOpenNow = onlyOpenNow;
        this.rankBy = rankBy;
        this.type = type;
        this.pageToken = pageToken;
    }

    public boolean hasLocation()
    {
        return Objects.nonNull(location);
    }

    public boolean hasRadius()
    {
        if (Objects.isNull(radiusInMeters))
        {
            return false;
        }

        return radiusInMeters > 0.0;
    }

    public boolean hasKeyword()
    {
        return !isNullOrEmpty(keyword);
    }

    public boolean hasName()
    {
        return !isNullOrEmpty(name);
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
        hash = 59 * hash + Objects.hashCode(this.location);
        hash = 59 * hash + Objects.hashCode(this.radiusInMeters);
        hash = 59 * hash + Objects.hashCode(this.keyword);
        hash = 59 * hash + Objects.hashCode(this.name);
        hash = 59 * hash + Objects.hashCode(this.language);
        hash = 59 * hash + Objects.hashCode(this.minPrice);
        hash = 59 * hash + Objects.hashCode(this.maxPrice);
        hash = 59 * hash + (this.onlyOpenNow ? 1 : 0);
        hash = 59 * hash + Objects.hashCode(this.rankBy);
        hash = 59 * hash + Objects.hashCode(this.type);
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
        if (this.onlyOpenNow != other.onlyOpenNow)
        {
            return false;
        }
        if (!Objects.equals(this.keyword, other.keyword))
        {
            return false;
        }
        if (!Objects.equals(this.name, other.name))
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
        if (!Objects.equals(this.radiusInMeters, other.radiusInMeters))
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
        return "NearbySearchRequest{" + "location=" + location + ", radiusInMeters=" + radiusInMeters + ", keyword=" + keyword + ", name=" + name + ", language=" + language + ", minPrice=" + minPrice + ", maxPrice=" + maxPrice + ", onlyOpenNow=" + onlyOpenNow + ", rankBy=" + rankBy + ", type=" + type + '}';
    }

    public static enum Ranking
    {
        PROMINENCE,
        DISTANCE
    }

    public static Builder newBuilder()
    {
        return Builder.newInstance();
    }

    @BuilderPattern(role = BUILDER)
    public static class Builder
    {

        /**
         * The maximum allowable radius in meters.
         */
        public static final int MAX_RADIUS = 50_000;

        private Location location;
        private Double radiusInMeters;
        private String keyword;
        private String name;
        private String language;
        private PriceLevel minPrice;
        private PriceLevel maxPrice;
        private boolean onlyOpenNow;
        private Ranking rankBy;
        private Types.PlaceType type;
        private String pageToken;

        public Builder()
        {
        }

        static Builder newInstance()
        {
            return new Builder();
        }

        private void checkParameters()
        {
            checkThat(location)
                .usingMessage("a valid location is required")
                .is(validLocation());

            if (rankBy != null)
            {
                checkThat(radiusInMeters)
                    .usingMessage("Distance ranking cannot be used in conjunction with radius")
                    .is(nullObject());

                if (allAreEmpty(keyword, name, type))
                {
                    throw new IllegalArgumentException("when rankBy is set, at least one of name, keyword, or type are required");
                }
            }
            
            if (Objects.nonNull(minPrice) && Objects.nonNull(maxPrice))
            {
                checkThat(minPrice.value)
                    .usingMessage("minPrice must be < maxPrice")
                    .is(lessThan(maxPrice.value));
            }

        }

        private boolean allAreEmpty(Object... objects)
        {
            for (Object object : objects)
            {
                if (Objects.nonNull(object))
                {
                    return false;
                }
            }

            return true;
        }
    }

}
