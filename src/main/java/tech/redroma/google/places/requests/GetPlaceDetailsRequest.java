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

import com.google.gson.annotations.SerializedName;
import java.util.Objects;
import tech.sirwellington.alchemy.annotations.arguments.NonEmpty;
import tech.sirwellington.alchemy.annotations.concurrency.Immutable;
import tech.sirwellington.alchemy.annotations.concurrency.ThreadSafe;
import tech.sirwellington.alchemy.annotations.designs.patterns.BuilderPattern;
import tech.sirwellington.alchemy.annotations.objects.Pojo;

import static com.google.common.base.Strings.isNullOrEmpty;
import static tech.sirwellington.alchemy.annotations.designs.patterns.BuilderPattern.Role.BUILDER;
import static tech.sirwellington.alchemy.annotations.designs.patterns.BuilderPattern.Role.PRODUCT;
import static tech.sirwellington.alchemy.arguments.Arguments.checkThat;
import static tech.sirwellington.alchemy.arguments.assertions.StringAssertions.nonEmptyString;

/**
 * This request is used to get more detailed information about a {@linkplain Place Google Place}.
 *
 * @author SirWellington
 */
@ThreadSafe
@Immutable
@Pojo
@BuilderPattern(role = PRODUCT)
public final class GetPlaceDetailsRequest
{

    @SerializedName("place_id")
    private final String placeId;
    private final String extensions;
    private final String language;

    GetPlaceDetailsRequest(String placeId, String extensions, String language)
    {
        checkThat(placeId)
            .usingMessage("placeId is required")
            .is(nonEmptyString());

        this.placeId = placeId;
        this.extensions = extensions;
        this.language = language;
    }

    public boolean hasPlaceId()
    {
        return !isNullOrEmpty(placeId);
    }

    public boolean hasExtensions()
    {
        return !isNullOrEmpty(extensions);
    }

    public boolean hasLanguage()
    {
        return !isNullOrEmpty(language);
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.placeId);
        hash = 37 * hash + Objects.hashCode(this.extensions);
        hash = 37 * hash + Objects.hashCode(this.language);
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
        final GetPlaceDetailsRequest other = (GetPlaceDetailsRequest) obj;
        if (!Objects.equals(this.placeId, other.placeId))
        {
            return false;
        }
        if (!Objects.equals(this.extensions, other.extensions))
        {
            return false;
        }
        if (!Objects.equals(this.language, other.language))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "GetPlaceDetailsRequest{" + "placeId=" + placeId + ", extensions=" + extensions + ", language=" + language + '}';
    }

    public static Builder newBuilder()
    {
        return Builder.newInstance();
    }

    @BuilderPattern(role = BUILDER)
    public static class Builder
    {
        
        private String placeId;
        private String extensions;
        private String language;
        
        public static Builder newInstance()
        {
            return new Builder();
        }
        
        public Builder withPlaceID(@NonEmpty String placeId) throws IllegalArgumentException
        {
            checkThat(placeId).is(nonEmptyString());
            
            this.placeId = placeId;
            return this;
        }
        
        public Builder withExtensions(@NonEmpty String extensions) throws IllegalArgumentException
        {
            checkThat(extensions).is(nonEmptyString());
            
            this.extensions = extensions;
            return this;
        }
        
        public Builder withLanguage(@NonEmpty String language) throws IllegalArgumentException
        {
            checkThat(language).is(nonEmptyString());
            
            this.language = language;
            return this;
        }
        
        public GetPlaceDetailsRequest build() throws IllegalArgumentException
        {
            checkThat(placeId)
                .usingMessage("placeID is required")
                .is(nonEmptyString());
            
            return new GetPlaceDetailsRequest(placeId, extensions, language);
        }
            
    }

}
