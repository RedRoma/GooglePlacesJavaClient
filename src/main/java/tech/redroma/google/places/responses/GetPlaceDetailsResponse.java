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
import tech.redroma.google.places.data.PlaceDetails;
import tech.sirwellington.alchemy.annotations.objects.Pojo;

import static com.google.common.base.Strings.isNullOrEmpty;

/**
 *
 * @author SirWellington
 */
@Pojo
public final class GetPlaceDetailsResponse
{

    private List<String> htmlAttributions;
    private PlaceDetails result;
    private String status;

    public boolean hasHtmlAttributions()
    {
        return Lists.notEmpty(htmlAttributions);
    }

    public boolean hasResult()
    {
        return Objects.nonNull(result);
    }

    public boolean hasStatus()
    {
        return !isNullOrEmpty(status);
    }

    public List<String> getHtmlAttributions()
    {
        return htmlAttributions;
    }

    public PlaceDetails getResult()
    {
        return result;
    }

    public String getStatus()
    {
        return status;
    }

    @Override
    public int hashCode()
    {
        int hash = 3;
        hash = 17 * hash + Objects.hashCode(this.htmlAttributions);
        hash = 17 * hash + Objects.hashCode(this.result);
        hash = 17 * hash + Objects.hashCode(this.status);
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
        final GetPlaceDetailsResponse other = (GetPlaceDetailsResponse) obj;
        if (!Objects.equals(this.status, other.status))
        {
            return false;
        }
        if (!Objects.equals(this.htmlAttributions, other.htmlAttributions))
        {
            return false;
        }
        if (!Objects.equals(this.result, other.result))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "GetPlaceDetailsResponse{" + "htmlAttributions=" + htmlAttributions + ", result=" + result + ", status=" + status + '}';
    }

}
