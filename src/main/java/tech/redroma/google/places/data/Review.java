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
import java.time.Instant;
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
@Mutable
@ThreadUnsafe
@Pojo
public final class Review
{

    String authorName;

    @SerializedName("author_url")
    String authorURL;

    Language language;

    @SerializedName("profile_photo_url")
    String authorPhotoURL;

    Integer rating;

    String relativeTimeDescription;

    String text;

    Long time;

    List<AspectRating> aspects;

    public String getAuthorName()
    {
        return authorName;
    }

    public String getAuthorURL()
    {
        return authorURL;
    }

    public Language getLanguage()
    {
        return language;
    }

    public String getAuthorPhotoURL()
    {
        return authorPhotoURL;
    }

    public Integer getRating()
    {
        return rating;
    }

    public String getRelativeTimeDescription()
    {
        return relativeTimeDescription;
    }

    public String getText()
    {
        return text;
    }

    public Instant getTime()
    {
        if (time == null)
        {
            return null;
        }

        return Instant.ofEpochMilli(time);
    }

    public List<AspectRating> getAspects()
    {
        return Lists.copy(aspects);
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.authorName);
        hash = 89 * hash + Objects.hashCode(this.authorURL);
        hash = 89 * hash + Objects.hashCode(this.language);
        hash = 89 * hash + Objects.hashCode(this.authorPhotoURL);
        hash = 89 * hash + Objects.hashCode(this.rating);
        hash = 89 * hash + Objects.hashCode(this.relativeTimeDescription);
        hash = 89 * hash + Objects.hashCode(this.text);
        hash = 89 * hash + Objects.hashCode(this.time);
        hash = 89 * hash + Objects.hashCode(this.aspects);
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
        final Review other = (Review) obj;
        if (!Objects.equals(this.authorName, other.authorName))
        {
            return false;
        }
        if (!Objects.equals(this.authorURL, other.authorURL))
        {
            return false;
        }
        if (!Objects.equals(this.language, other.language))
        {
            return false;
        }
        if (!Objects.equals(this.authorPhotoURL, other.authorPhotoURL))
        {
            return false;
        }
        if (!Objects.equals(this.relativeTimeDescription, other.relativeTimeDescription))
        {
            return false;
        }
        if (!Objects.equals(this.text, other.text))
        {
            return false;
        }
        if (!Objects.equals(this.rating, other.rating))
        {
            return false;
        }
        if (!Objects.equals(this.time, other.time))
        {
            return false;
        }
        if (!Objects.equals(this.aspects, other.aspects))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "Review{" + "authorName=" + authorName + ", authorURL=" + authorURL + ", language=" + language + ", authorPhotoURL=" + authorPhotoURL + ", rating=" + rating + ", relativeTimeDescription=" + relativeTimeDescription + ", text=" + text + ", time=" + time + ", aspects=" + aspects + '}';
    }

    @Pojo
    @Mutable
    @ThreadUnsafe
    public static class AspectRating
    {

        public Integer rating;

        @SerializedName("type")
        public String aspect;

        @Override
        public int hashCode()
        {
            int hash = 3;
            hash = 59 * hash + Objects.hashCode(this.rating);
            hash = 59 * hash + Objects.hashCode(this.aspect);
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
            final AspectRating other = (AspectRating) obj;
            if (!Objects.equals(this.aspect, other.aspect))
            {
                return false;
            }
            if (!Objects.equals(this.rating, other.rating))
            {
                return false;
            }
            return true;
        }

        @Override
        public String toString()
        {
            return "AspectRating{" + "rating=" + rating + ", aspect=" + aspect + '}';
        }

    }

}
