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
import java.util.List;
import java.util.Objects;
import sir.wellington.alchemy.collections.lists.Lists;
import tech.sirwellington.alchemy.annotations.concurrency.Mutable;
import tech.sirwellington.alchemy.annotations.concurrency.ThreadUnsafe;
import tech.sirwellington.alchemy.annotations.objects.Pojo;

import static com.google.common.base.Strings.isNullOrEmpty;

/**
 *
 * <p>
 * For more information on the structure of this Object, see
 * <a href="https://developers.google.com/places/web-service/search#PlaceSearchResults">Google Documentation</a>.
 *
 * @see
 * <a href="https://developers.google.com/places/web-service/search#PlaceSearchResults">https://developers.google.com/places/web-service/search#PlaceSearchResults</a>
 * @author SirWellington
 */
@Mutable
@ThreadUnsafe
@Pojo
public final class Place
{

    /**
     * Contains the URL of a recommended icon which may be displayed to the user when indicating this result.
     */
    @SerializedName("icon")
    public String iconURL;

    /**
     * A Textual identifier that uniquely identifies a place. To retrieve information about the place, pass this identifier in the
     * PlaceSearchRequest.
     *
     * @see <a href="https://developers.google.com/places/web-service/place-id">Place ID Documentation</a>
     */
    @SerializedName("place_id")
    public String placeId;

    /**
     * Contains geometry information about the result, generally including the {@link Location} (Geocode) of the place and
     * (optionally) the {@link Viewport}, identifying its general area of coverage.
     */
    public Geometry geometry;

    /**
     * Contains the human-readable name for the returned result. For {@link Types.ReturnedPlaceType#ESTABLISHMENT}, this is
     * usually the business name.
     */
    public String name;

    /**
     * May contain the following information:
     * <p>
     * <li> {@link OpeningHours#openNow} - A boolean indicating whether the place is open at the time of request. </li>
     */
    public OpeningHours openingHours;

    /**
     * An Array of {@link Photo} objects, each containing a reference to an image. A Place Search will return at most one photo
     * object. Performing a Place Details request on the place may return up to 10 photos. More information about Place Photos and
     * how you can use the images in your application can be found in the
     * <a href="https://developers.google.com/places/web-service/photos">Place Photos Documentation</a>
     */
    public List<Photo> photos;

    /**
     * Contains the place's rating, from 1.0 to 5.0, based on aggregated user reviews.
     */
    public Double rating;

    /**
     * The price level of the place, on a scale of 0 to 4, with 0 being {@linkplain PriceLevel#FREE free} and 4 being
     * {@linkplain PriceLevel#VERY_EXPENSIVE "very expensive"}. The exact amount indicated by a specific level will vary form
     * region to region.
     *
     * @see PriceLevel
     */
    @SerializedName("price_level")
    public PriceLevel priceLevel;

    /**
     * Contains an array of {@linkplain Types.ReturnedPlaceType feature types} describing the given result.
     */
    public List<Types.ReturnedPlaceType> types;

    /**
     * Contains a feature name of a nearby location. Often this feature refers to a street or neighborhood within the given
     * results. The vicinity property is only returned for a NearbySearchRequest.
     */
    public String vicinity;

    /**
     * A string containing the human-readable address of this place. Often this address is equivalent to the postal address. This
     * property is only returned for a TextSearchRequest.
     */
    public String formattedAddress;

    /**
     * A flag indicating whether the place has permanently closed and shut down. If the place is not permanently closed, the flag
     * is absent.
     */
    @SerializedName("permanently_closed")
    public Boolean permanentlyClosed;

    /**
     * Contains a unique stable identifier denoting this place. This identifier may not be used to retrieve information about this
     * place, but is guaranteed to be valid across sessions. It can be used to consolidate data about this place, and to verify
     * the identity of a place across separate searches.
     * <p>
     * Note that this field is now deprecated in favor of {@link #placeId}.
     */
    @Deprecated
    private String id;

    /**
     * Contains a unique token that you can use to retrieve additional information about this place in a PlaceDetailsRequest.
     * Although this token uniquely identifies the place, the converse is not true. A place may be have many valid reference
     * tokens. It's not guaranteed that the same token will be returned for any given place across different searches.
     * <p>
     * Note that this field has been deprecated in favor of {@link #placeId}.
     */
    @Deprecated
    private String reference;

    Place()
    {
    }

    public boolean hasIcon()
    {
        return !isNullOrEmpty(iconURL);
    }

    public boolean hasGeometry()
    {
        return Objects.nonNull(geometry);
    }

    public boolean hasName()
    {
        return !isNullOrEmpty(name);
    }

    public boolean hasOpeningHours()
    {
        return Objects.nonNull(openingHours);
    }

    public boolean hasPhotos()
    {
        return !Lists.isEmpty(photos);
    }
    
    public boolean hasRating()
    {
        return Objects.nonNull(rating);
    }
    
    public boolean hasPriceLevel()
    {
        return Objects.nonNull(priceLevel);
    }
    
    public boolean hasTypes()
    {
        return !Lists.isEmpty(types);
    }
    
    public boolean hasVicinity()
    {
        return !isNullOrEmpty(vicinity);
    }
    
    public boolean hasFormattedAddress()
    {
        return !isNullOrEmpty(formattedAddress);
    }
    
    public boolean isPermanentlyClosed()
    {
        if (Objects.isNull(permanentlyClosed))
        {
            return false;
        }
        
        return permanentlyClosed;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.iconURL);
        hash = 23 * hash + Objects.hashCode(this.placeId);
        hash = 23 * hash + Objects.hashCode(this.geometry);
        hash = 23 * hash + Objects.hashCode(this.name);
        hash = 23 * hash + Objects.hashCode(this.openingHours);
        hash = 23 * hash + Objects.hashCode(this.photos);
        hash = 23 * hash + Objects.hashCode(this.rating);
        hash = 23 * hash + Objects.hashCode(this.priceLevel);
        hash = 23 * hash + Objects.hashCode(this.types);
        hash = 23 * hash + Objects.hashCode(this.vicinity);
        hash = 23 * hash + Objects.hashCode(this.formattedAddress);
        hash = 23 * hash + Objects.hashCode(this.permanentlyClosed);
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
        final Place other = (Place) obj;
        if (!Objects.equals(this.iconURL, other.iconURL))
        {
            return false;
        }
        if (!Objects.equals(this.placeId, other.placeId))
        {
            return false;
        }
        if (!Objects.equals(this.name, other.name))
        {
            return false;
        }
        if (!Objects.equals(this.vicinity, other.vicinity))
        {
            return false;
        }
        if (!Objects.equals(this.formattedAddress, other.formattedAddress))
        {
            return false;
        }
        if (!Objects.equals(this.geometry, other.geometry))
        {
            return false;
        }
        if (!Objects.equals(this.openingHours, other.openingHours))
        {
            return false;
        }
        if (!Objects.equals(this.photos, other.photos))
        {
            return false;
        }
        if (!Objects.equals(this.rating, other.rating))
        {
            return false;
        }
        if (this.priceLevel != other.priceLevel)
        {
            return false;
        }
        if (!Objects.equals(this.types, other.types))
        {
            return false;
        }
        if (!Objects.equals(this.permanentlyClosed, other.permanentlyClosed))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "Place{" + "iconURL=" + iconURL + ", placeId=" + placeId + ", geometry=" + geometry + ", name=" + name + ", openingHours=" + openingHours + ", photos=" + photos + ", rating=" + rating + ", priceLevel=" + priceLevel + ", types=" + types + ", vicinity=" + vicinity + ", formattedAddress=" + formattedAddress + ", permanentlyClosed=" + permanentlyClosed + '}';
    }

}
