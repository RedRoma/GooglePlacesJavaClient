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
import tech.redroma.google.places.data.Photo;
import tech.redroma.google.places.data.Place;
import tech.sirwellington.alchemy.annotations.arguments.NonEmpty;
import tech.sirwellington.alchemy.annotations.arguments.Positive;
import tech.sirwellington.alchemy.annotations.concurrency.Immutable;
import tech.sirwellington.alchemy.annotations.concurrency.ThreadSafe;
import tech.sirwellington.alchemy.annotations.designs.patterns.BuilderPattern;
import tech.sirwellington.alchemy.annotations.objects.Pojo;

import static com.google.common.base.Strings.isNullOrEmpty;
import static tech.sirwellington.alchemy.annotations.designs.patterns.BuilderPattern.Role.BUILDER;
import static tech.sirwellington.alchemy.annotations.designs.patterns.BuilderPattern.Role.PRODUCT;
import static tech.sirwellington.alchemy.arguments.Arguments.checkThat;
import static tech.sirwellington.alchemy.arguments.assertions.Assertions.nullObject;
import static tech.sirwellington.alchemy.arguments.assertions.NumberAssertions.greaterThanOrEqualTo;
import static tech.sirwellington.alchemy.arguments.assertions.NumberAssertions.lessThanOrEqualTo;
import static tech.sirwellington.alchemy.arguments.assertions.NumberAssertions.positiveInteger;
import static tech.sirwellington.alchemy.arguments.assertions.StringAssertions.nonEmptyString;

/**
 * Use to make requests for photos.
 * <p>
 * See {@link #newBuilder() } to make new requests.
 * 
 * @see #newBuilder() 
 * @see Builder
 * @author SirWellington
 */
@Immutable
@ThreadSafe
@Pojo
@BuilderPattern(role = PRODUCT)
public final class GetPhotoRequest
{

    public final String photoReference;
    public final Integer maxHeight;
    public final Integer maxWidth;

    GetPhotoRequest(@NonEmpty String photoReference, Integer maxHeight, Integer maxWidth)
    {
        checkThat(photoReference).is(nonEmptyString());

        this.photoReference = photoReference;
        this.maxHeight = maxHeight;
        this.maxWidth = maxWidth;
    }

    public boolean hasPhotoReference()
    {
        return !isNullOrEmpty(photoReference);
    }

    public boolean hasMaxHeight()
    {
        return Objects.nonNull(maxHeight);
    }

    public boolean hasMaxWidth()
    {
        return Objects.nonNull(maxWidth);
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.photoReference);
        hash = 17 * hash + Objects.hashCode(this.maxHeight);
        hash = 17 * hash + Objects.hashCode(this.maxWidth);
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
        final GetPhotoRequest other = (GetPhotoRequest) obj;
        if (!Objects.equals(this.photoReference, other.photoReference))
        {
            return false;
        }
        if (!Objects.equals(this.maxHeight, other.maxHeight))
        {
            return false;
        }
        if (!Objects.equals(this.maxWidth, other.maxWidth))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "GetPhotoRequest{" + "photoReference=" + photoReference + ", maxHeight=" + maxHeight + ", maxWidth=" + maxWidth + '}';
    }

    
    public static Builder newBuilder()
    {
        return Builder.newInstance();
    }
    
    /**
     * Facilitates creation of {@link GetPhotoRequest} objects.
     */
    @BuilderPattern(role = BUILDER)
    public static class Builder
    {

        private String photoReference;
        private Integer maxWidth;
        private Integer maxHeight;
        
        /** The minimum allowable width. */
        public final static int MIN_WIDTH = 1;
        /** The maximum allowable width. */
        public final static int MAX_WIDTH = 1600;
        
        /** The minimum allowable height. */
        public final static int MIN_HEIGHT = MIN_WIDTH;
        /** The maximum allowable height. */
        public final static int MAX_HEIGHT = MAX_WIDTH;
        
        
        Builder()
        {
        }
        
        public static Builder newInstance()
        {
            return new Builder();
        }
        
        /**
         * Sets the photo reference for the request. This uniquely identifies a photo.
         * These are usually obtained from a {@link Place} object after making a {@link NearbySearchRequest}.
         * 
         * @param photoReference The {@link Photo#photoReference} to use in the request.
         * @return
         * @throws IllegalArgumentException 
         */
        public Builder withPhotoReference(@NonEmpty String photoReference) throws IllegalArgumentException
        {
            checkThat(photoReference).is(nonEmptyString());
            
            this.photoReference = photoReference;
            return this;
        }
        
        /**
         * Sets the maxHeight for the request. This specifies the maximum desired height, in pixels, of the image returned by the
         * API. If the image is smaller than the specified value, the original image will be returned. If the image is larger, it
         * will be scaled down to match the smaller of the two dimensions, restricted to its original aspect-ratio.
         *
         * @param maxHeight Must be {@code >1 && < } {@link #MAX_HEIGHT}.
         * @return
         * @throws IllegalArgumentException
         */
        public Builder withMaxHeight(@Positive int maxHeight) throws IllegalArgumentException
        {
            checkThat(maxWidth)
                .usingMessage("Only one of maxWidth and maxHeight can be set at a time")
                .is(nullObject());
                
            checkThat(maxHeight)
                .is(positiveInteger())
                .is(greaterThanOrEqualTo(MIN_HEIGHT))
                .is(lessThanOrEqualTo(MAX_HEIGHT));
            
            this.maxHeight = maxHeight;
            return this;
        }

        /**
         * Sets the maxWidth for the request. This specifies the maximum desired width, in pixels, of the image returned by the
         * API. If the image is smaller than the specified value, the original image will be returned. If the image is larger, it
         * will be scaled down to match the smaller of the two dimensions, restricted to its original aspect-ratio.
         *
         * @param maxWidth Must be {@code >1 && < } {@link #MAX_WIDTH}.
         * @return
         * @throws IllegalArgumentException
         */
        public Builder withMaxWidth(@Positive int maxWidth) throws IllegalArgumentException
        {
            checkThat(maxHeight)
                .usingMessage("only one of maxWidth and maxHeight can be set at a time")
                .is(nullObject());
            
            checkThat(maxWidth)
                .is(positiveInteger());

            this.maxWidth = maxWidth;
            return this;
        }
        
        /**
         * Builds the {@link GetPhotoRequest} from the information specified so far.
         * 
         * @return
         * @throws IllegalArgumentException 
         */
        public GetPhotoRequest build() throws IllegalArgumentException
        {
            checkParameters();
            
            return new GetPhotoRequest(photoReference, maxHeight, maxWidth);
        }
        
        private void checkParameters()
        {
            checkThat(photoReference)
                .usingMessage("photoReference is required")
                .is(nonEmptyString());
            
            if (Objects.isNull(maxHeight) && Objects.isNull(maxWidth))
            {
                throw new IllegalArgumentException("maxWidth or maxHeight are required");
            }
            
            if (Objects.nonNull(maxHeight) && Objects.nonNull(maxWidth))
            {
                throw new IllegalArgumentException("Only one of maxWidth or maxHeight can be set.");
            }
        }
    }
}
