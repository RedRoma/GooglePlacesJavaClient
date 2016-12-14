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

/**
 *
 * @author SirWellington
 */
@ThreadUnsafe
@Mutable
@Pojo
public final class PlaceDetails
{
    
    List<AddressComponent> addressComponents;
    
    String formattedAddress;
    
    String formattedPhoneNumber;
    
    String internationalPhoneNumber;
    
    Geometry geometry;
    
    @SerializedName("icon")
    String iconURL;
    
    String id;
    
    String name;
    
    List<OpeningHours> openingHours;
    
    List<Photo> photos;
    
    @SerializedName("place_id")
    String placeId;
    
    Integer rating;
    
    String reference;
    
    List<Review> reviews;
    
    List<Types.ReturnedPlaceType> types;
    
    String url;
    
    Integer utcOffset;
    
    String vicinity;
    
    String website;
    
    public List<AddressComponent> getAddressComponents()
    {
        if (Lists.isEmpty(addressComponents))
        {
            return Lists.emptyList();
        }
        else        
        {
            return Lists.immutableCopyOf(addressComponents);
        }
    }
    
    public String getFormattedAddress()
    {
        return formattedAddress;
    }
    
    public String getFormattedPhoneNumber()
    {
        return formattedPhoneNumber;
    }
    
    public String getInternationalPhoneNumber()
    {
        return internationalPhoneNumber;
    }
    
    public Geometry getGeometry()
    {
        return geometry;
    }
    
    public String getIconURL()
    {
        return iconURL;
    }
    
    public String getId()
    {
        return id;
    }
    
    public String getName()
    {
        return name;
    }
    
    public List<OpeningHours> getOpeningHours()
    {
        if (Lists.isEmpty(openingHours))
        {
            return Lists.emptyList();
        }
        else        
        {
            return Lists.immutableCopyOf(openingHours);
        }
    }
    
    public List<Photo> getPhotos()
    {
        if (Lists.isEmpty(photos))
        {
            return Lists.emptyList();
        }
        else        
        {
            return Lists.immutableCopyOf(photos);
        }
    }
    
    public String getPlaceId()
    {
        return placeId;
    }
    
    public Integer getRating()
    {
        return rating;
    }
    
    public String getReference()
    {
        return reference;
    }
    
    public List<Review> getReviews()
    {
        if (Lists.isEmpty(reviews))
        {
            return Lists.emptyList();
        }
        else        
        {
            return Lists.immutableCopyOf(reviews);
        }
    }
    
    public List<Types.ReturnedPlaceType> getTypes()
    {
        if (Lists.isEmpty(types))
        {
            return Lists.emptyList();
        }
        else        
        {
            return Lists.immutableCopyOf(types);
        }
    }
    
    public String getUrl()
    {
        return url;
    }
    
    public Integer getUtcOffset()
    {
        return utcOffset;
    }
    
    public String getVicinity()
    {
        return vicinity;
    }
    
    public String getWebsite()
    {
        return website;
    }
    
    @Override
    public int hashCode()
    {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.addressComponents);
        hash = 97 * hash + Objects.hashCode(this.formattedAddress);
        hash = 97 * hash + Objects.hashCode(this.formattedPhoneNumber);
        hash = 97 * hash + Objects.hashCode(this.internationalPhoneNumber);
        hash = 97 * hash + Objects.hashCode(this.geometry);
        hash = 97 * hash + Objects.hashCode(this.iconURL);
        hash = 97 * hash + Objects.hashCode(this.id);
        hash = 97 * hash + Objects.hashCode(this.name);
        hash = 97 * hash + Objects.hashCode(this.openingHours);
        hash = 97 * hash + Objects.hashCode(this.photos);
        hash = 97 * hash + Objects.hashCode(this.placeId);
        hash = 97 * hash + Objects.hashCode(this.rating);
        hash = 97 * hash + Objects.hashCode(this.reference);
        hash = 97 * hash + Objects.hashCode(this.reviews);
        hash = 97 * hash + Objects.hashCode(this.types);
        hash = 97 * hash + Objects.hashCode(this.url);
        hash = 97 * hash + Objects.hashCode(this.utcOffset);
        hash = 97 * hash + Objects.hashCode(this.vicinity);
        hash = 97 * hash + Objects.hashCode(this.website);
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
        final PlaceDetails other = (PlaceDetails) obj;
        if (!Objects.equals(this.formattedAddress, other.formattedAddress))
        {
            return false;
        }
        if (!Objects.equals(this.formattedPhoneNumber, other.formattedPhoneNumber))
        {
            return false;
        }
        if (!Objects.equals(this.internationalPhoneNumber, other.internationalPhoneNumber))
        {
            return false;
        }
        if (!Objects.equals(this.iconURL, other.iconURL))
        {
            return false;
        }
        if (!Objects.equals(this.id, other.id))
        {
            return false;
        }
        if (!Objects.equals(this.name, other.name))
        {
            return false;
        }
        if (!Objects.equals(this.placeId, other.placeId))
        {
            return false;
        }
        if (!Objects.equals(this.reference, other.reference))
        {
            return false;
        }
        if (!Objects.equals(this.url, other.url))
        {
            return false;
        }
        if (!Objects.equals(this.vicinity, other.vicinity))
        {
            return false;
        }
        if (!Objects.equals(this.website, other.website))
        {
            return false;
        }
        if (!Objects.equals(this.addressComponents, other.addressComponents))
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
        if (!Objects.equals(this.reviews, other.reviews))
        {
            return false;
        }
        if (!Objects.equals(this.types, other.types))
        {
            return false;
        }
        if (!Objects.equals(this.utcOffset, other.utcOffset))
        {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString()
    {
        return "PlaceDetails{" + "addressComponents=" + addressComponents + ", formattedAddress=" + formattedAddress + ", formattedPhoneNumber=" + formattedPhoneNumber + ", internationalPhoneNumber=" + internationalPhoneNumber + ", geometry=" + geometry + ", iconURL=" + iconURL + ", id=" + id + ", name=" + name + ", openingHours=" + openingHours + ", photos=" + photos + ", placeId=" + placeId + ", rating=" + rating + ", reference=" + reference + ", reviews=" + reviews + ", types=" + types + ", url=" + url + ", utcOffset=" + utcOffset + ", vicinity=" + vicinity + ", website=" + website + '}';
    }

    /**
     * This represents a piece of an Address.
     */
    @Pojo
    @Mutable
    @ThreadUnsafe
    public final static class AddressComponent
    {

        /**
         * The full text description or name of the address component.
         */
        String longName;
        /**
         * The abbreviated textual name for the address component, if available. For example, an address component for the state
         * of Alaska may have a 'long_name' or Alaska, but a 'short_name' of 'AK', using the 2-letter postal abbreviation.
         */
        String shortName;

        /**
         * An array indicating the type of the address component.
         */
        List<Types.ReturnedPlaceType> types;
        
        public AddressComponent()
        {
        }
        
        public String getLongName()
        {
            return longName;
        }
        
        public String getShortName()
        {
            return shortName;
        }
        
        public List<Types.ReturnedPlaceType> getTypes()
        {
            return Lists.immutableCopyOf(types);
        }
        
        @Override
        public int hashCode()
        {
            int hash = 7;
            hash = 53 * hash + Objects.hashCode(this.longName);
            hash = 53 * hash + Objects.hashCode(this.shortName);
            hash = 53 * hash + Objects.hashCode(this.types);
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
            final AddressComponent other = (AddressComponent) obj;
            if (!Objects.equals(this.longName, other.longName))
            {
                return false;
            }
            if (!Objects.equals(this.shortName, other.shortName))
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
            return "AddressComponent{" + "longName=" + longName + ", shortName=" + shortName + ", types=" + types + '}';
        }
        
    }
    
}
