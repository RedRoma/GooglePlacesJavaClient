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
import tech.redroma.google.places.GooglePlacesAPI;
import tech.sirwellington.alchemy.annotations.concurrency.Mutable;
import tech.sirwellington.alchemy.annotations.concurrency.ThreadUnsafe;
import tech.sirwellington.alchemy.annotations.objects.Pojo;

import static com.google.common.base.Strings.isNullOrEmpty;

/**
 * Represents a Photo returned from the Google API.
 * Note that Google does not return publicly accessible URLs from their service,
 * so in order to download an image, the API key is needed.
 * 
 * @see GooglePlacesAPI
 * @author SirWellington
 */
@Mutable
@ThreadUnsafe
@Pojo
public class Photo
{

    public Integer width;

    public Integer height;

    @SerializedName("html_attributions")
    public List<String> htmlAttributions;

    public String photoReference;

    public Photo()
    {
    }

    public boolean hasWidth()
    {
        return width != null;
    }

    public boolean hasHeight()
    {
        return height != null;
    }

    public boolean hasHTMLAttributions()
    {
        return !Lists.isEmpty(htmlAttributions);
    }

    public boolean hasPhotoReference()
    {
        return !isNullOrEmpty(photoReference);
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.width);
        hash = 89 * hash + Objects.hashCode(this.height);
        hash = 89 * hash + Objects.hashCode(this.htmlAttributions);
        hash = 89 * hash + Objects.hashCode(this.photoReference);
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
        final Photo other = (Photo) obj;
        if (!Objects.equals(this.photoReference, other.photoReference))
        {
            return false;
        }
        if (!Objects.equals(this.width, other.width))
        {
            return false;
        }
        if (!Objects.equals(this.height, other.height))
        {
            return false;
        }
        if (!Objects.equals(this.htmlAttributions, other.htmlAttributions))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "Photo{" + "width=" + width + ", height=" + height + ", htmlAttributions=" + htmlAttributions + ", photoReference=" + photoReference + '}';
    }

}
