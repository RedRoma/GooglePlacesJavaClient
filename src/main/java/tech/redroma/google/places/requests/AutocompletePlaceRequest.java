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

import java.util.List;
import java.util.Objects;
import sir.wellington.alchemy.collections.lists.Lists;
import tech.redroma.google.places.data.Language;
import tech.redroma.google.places.data.Location;
import tech.redroma.google.places.data.Types;
import tech.sirwellington.alchemy.annotations.arguments.NonEmpty;
import tech.sirwellington.alchemy.annotations.arguments.Optional;
import tech.sirwellington.alchemy.annotations.arguments.Positive;
import tech.sirwellington.alchemy.annotations.arguments.Required;
import tech.sirwellington.alchemy.annotations.concurrency.Immutable;
import tech.sirwellington.alchemy.annotations.concurrency.ThreadSafe;
import tech.sirwellington.alchemy.annotations.designs.patterns.BuilderPattern;
import tech.sirwellington.alchemy.annotations.objects.Pojo;

import static com.google.common.base.Strings.isNullOrEmpty;
import static java.util.stream.Collectors.toList;
import static tech.redroma.google.places.data.Location.validLocation;
import static tech.sirwellington.alchemy.annotations.designs.patterns.BuilderPattern.Role.BUILDER;
import static tech.sirwellington.alchemy.annotations.designs.patterns.BuilderPattern.Role.PRODUCT;
import static tech.sirwellington.alchemy.arguments.Arguments.checkThat;
import static tech.sirwellington.alchemy.arguments.assertions.Assertions.notNull;
import static tech.sirwellington.alchemy.arguments.assertions.CollectionAssertions.nonEmptyList;
import static tech.sirwellington.alchemy.arguments.assertions.NumberAssertions.positiveInteger;
import static tech.sirwellington.alchemy.arguments.assertions.StringAssertions.nonEmptyString;
import static tech.sirwellington.alchemy.arguments.assertions.StringAssertions.stringWithLengthGreaterThanOrEqualTo;

/**
 * Used to make requests to the
 * <a href="https://developers.google.com/places/web-service/autocomplete">Google Places Autocomplete API</a>.
 *
 * @author SirWellington
 * @see Builder
 * @see #newBuilder() 
 * @see
 * <a href="https://developers.google.com/places/web-service/autocomplete">https://developers.google.com/places/web-service/autocomplete</a>
 */
@ThreadSafe
@Immutable
@Pojo
@BuilderPattern(role = PRODUCT)
public final class AutocompletePlaceRequest
{

    private final String input;
    private final Integer offset;
    private final Location location;
    private final Integer radiusInMeters;
    private final Language language;
    private final List<Types.AutocompleteType> types;
    private final boolean strictBounds;

    AutocompletePlaceRequest(String input,
                             Integer offset, Location location,
                             Integer radiusInMeters,
                             Language language,
                             List<Types.AutocompleteType> types,
                             boolean strictBounds)
    {
        this.input = input;
        this.offset = offset;
        this.location = location;
        this.radiusInMeters = radiusInMeters;
        this.language = language;
        this.types = types;
        this.strictBounds = strictBounds;
    }

    public boolean hasInput()
    {
        return !isNullOrEmpty(input);
    }

    public boolean hasOffset()
    {
        return Objects.nonNull(offset);
    }

    public boolean hasLocation()
    {
        return Objects.nonNull(location);
    }

    public boolean hasRadius()
    {
        return Objects.nonNull(radiusInMeters);
    }

    public boolean hasLanguage()
    {
        return Objects.nonNull(language);
    }

    public boolean hasTypes()
    {
        return Lists.notEmpty(types);
    }

    public boolean hasStrictBounds()
    {
        return strictBounds;
    }

    @Override
    public int hashCode()
    {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.input);
        hash = 53 * hash + Objects.hashCode(this.offset);
        hash = 53 * hash + Objects.hashCode(this.location);
        hash = 53 * hash + Objects.hashCode(this.radiusInMeters);
        hash = 53 * hash + Objects.hashCode(this.language);
        hash = 53 * hash + Objects.hashCode(this.types);
        hash = 53 * hash + (this.strictBounds ? 1 : 0);
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
        final AutocompletePlaceRequest other = (AutocompletePlaceRequest) obj;
        if (this.strictBounds != other.strictBounds)
        {
            return false;
        }
        if (!Objects.equals(this.input, other.input))
        {
            return false;
        }
        if (!Objects.equals(this.offset, other.offset))
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
        if (this.language != other.language)
        {
            return false;
        }
        if (!Objects.equals(this.types, other.types))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "AutocompletePlaceRequest{" + "input=" + input + ", offset=" + offset + ", location=" + location + ", radiusInMeters=" + radiusInMeters + ", language=" + language + ", types=" + types + ", strictBounds=" + strictBounds + '}';
    }
    
    public static Builder newBuilder()
    {
        return Builder.newInstance();
    }

    /**
     * Facilitates creation of {@link AutocompletePlaceRequest} requests.
     */
    @BuilderPattern(role = BUILDER)
    public static class Builder
    {

        private final static int MINIMUM_INPUT_LENGTH = 2;

        private String input;
        private Integer offset;
        private Location location;
        private Integer radiusInMeters;
        private Language language;
        private List<Types.AutocompleteType> types = Lists.emptyList();
        private boolean strictBounds = false;

        Builder()
        {
        }
        
        public static Builder newInstance()
        {
            return new Builder();
        }
        
        public Builder withInput(@NonEmpty String input) throws IllegalArgumentException
        {
            checkThat(input)
                .is(nonEmptyString())
                .is(stringWithLengthGreaterThanOrEqualTo(MINIMUM_INPUT_LENGTH));
            
            this.input = input;
            return this;
        }
        
        
        public Builder withOffset(@Positive int offset) throws IllegalArgumentException
        {
            checkThat(offset)
                .is(positiveInteger());
            
            this.offset = offset;
            return this;
        }
        
        public Builder withLocation(@Required Location location) throws IllegalArgumentException
        {
            checkThat(location).is(validLocation());
            
            this.location = Location.copyOf(location); 
            return this;
        }
        
        public Builder withRadiusInMeters(@Positive int radius) throws IllegalArgumentException
        {
            checkThat(radius)
                .is(positiveInteger());
            
            this.radiusInMeters = radius;
            return this;
        }
        
        public Builder withLanguage(@Required Language language) throws IllegalArgumentException
        {
            checkThat(language).is(notNull());
            
            this.language = language;
            return this;
        }
        
        public Builder withTypes(@Required Types.AutocompleteType first, @Optional Types.AutocompleteType... others) throws IllegalArgumentException
        {
            checkThat(first)
                .usingMessage("first parameter is required")
                .is(notNull());
                
            return this.withTypes(Lists.createFrom(first, others));
        }
        
        public Builder withTypes(@NonEmpty List<Types.AutocompleteType> types) throws IllegalArgumentException
        {
            checkThat(types)
                .usingMessage("types cannot be empty")
                .is(nonEmptyList());
            
            this.types = types.stream()
                .distinct()
                .collect(toList());
            
            return this;
        }
        
        public Builder withStrictBounds()
        {
            this.strictBounds = true;
            return this;
        }
        
        public AutocompletePlaceRequest build() throws IllegalArgumentException
        {
            checkParameters();
            
            return new AutocompletePlaceRequest(input, offset, location, radiusInMeters, language, types, strictBounds);
        }

        private void checkParameters()
        {
            checkThat(input)
                .usingMessage("input is missing")
                .is(nonEmptyString())
                .usingMessage("input must have at least " + MINIMUM_INPUT_LENGTH + " characters")
                .is(stringWithLengthGreaterThanOrEqualTo(MINIMUM_INPUT_LENGTH));
            
        }
    }
}
