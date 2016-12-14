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

package tech.redroma.google.places.responses;

import java.util.List;
import java.util.Objects;
import sir.wellington.alchemy.collections.lists.Lists;
import tech.redroma.google.places.data.Place;
import tech.sirwellington.alchemy.annotations.objects.Pojo;

import static com.google.common.base.Strings.isNullOrEmpty;

/**
 *
 * @author SirWellington
 */
@Pojo
public final class NearbySearchResponse
{

    private String nextPageToken;
    private List<String> htmlAttributions;
    private List<Place> results;
    private String status;

    public boolean hasNextToken()
    {
        return !isNullOrEmpty(nextPageToken);
    }

    public boolean hasHtmlAttributions()
    {
        return Lists.notEmpty(htmlAttributions);
    }

    public boolean hasResults()
    {
        return Lists.notEmpty(results);
    }

    public boolean hasStatus()
    {
        return !isNullOrEmpty(status);
    }

    public String getNextPageToken()
    {
        return nextPageToken;
    }

    public List<String> getHtmlAttributions()
    {
        return Lists.immutableCopyOf(htmlAttributions);
    }

    public List<Place> getResults()
    {
        return Lists.immutableCopyOf(results);
    }

    public String getStatus()
    {
        return status;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.nextPageToken);
        hash = 59 * hash + Objects.hashCode(this.htmlAttributions);
        hash = 59 * hash + Objects.hashCode(this.results);
        hash = 59 * hash + Objects.hashCode(this.status);
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
        final NearbySearchResponse other = (NearbySearchResponse) obj;
        if (!Objects.equals(this.nextPageToken, other.nextPageToken))
        {
            return false;
        }
        if (!Objects.equals(this.status, other.status))
        {
            return false;
        }
        if (!Objects.equals(this.htmlAttributions, other.htmlAttributions))
        {
            return false;
        }
        if (!Objects.equals(this.results, other.results))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "NearbySearchResponse{" + "nextPageToken=" + nextPageToken + ", htmlAttributions=" + htmlAttributions + ", results=" + results + ", status=" + status + '}';
    }

}
