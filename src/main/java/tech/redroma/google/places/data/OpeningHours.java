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
import tech.sirwellington.alchemy.annotations.arguments.NonEmpty;
import tech.sirwellington.alchemy.annotations.concurrency.Mutable;
import tech.sirwellington.alchemy.annotations.concurrency.ThreadUnsafe;
import tech.sirwellington.alchemy.annotations.objects.Pojo;

import static tech.sirwellington.alchemy.arguments.Arguments.checkThat;
import static tech.sirwellington.alchemy.arguments.assertions.CollectionAssertions.nonEmptyList;

/**
 *
 * @author SirWellington
 */
@Mutable
@ThreadUnsafe
@Pojo
public class OpeningHours
{

    /**
     * Whether the business is open at the time of request.
     */
    @SerializedName(value = "open_now")
    public Boolean openNow;

    @SerializedName(value = "weekday_text")
    public List<String> weekdayText;

    public OpeningHours()
    {
    }

    public OpeningHours(boolean openNow, List<String> weekdayText)
    {
        checkThat(weekdayText)
            .is(nonEmptyList());

        this.openNow = openNow;
        this.weekdayText = weekdayText;
    }

    static OpeningHours create(boolean isOpenNow, @NonEmpty List<String> weekdayText)
    {
        return new OpeningHours(isOpenNow, weekdayText);
    }

    public boolean isOpenNow()
    {
        if (Objects.isNull(openNow))
        {
            return false;
        }

        return openNow;
    }

    public boolean hasWeekdayText()
    {
        return !Lists.isEmpty(weekdayText);
    }

    @Override
    public int hashCode()
    {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.openNow);
        hash = 97 * hash + Objects.hashCode(this.weekdayText);
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
        final OpeningHours other = (OpeningHours) obj;
        if (!Objects.equals(this.openNow, other.openNow))
        {
            return false;
        }
        if (!Objects.equals(this.weekdayText, other.weekdayText))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "OpeningHours{" + "openNow=" + openNow + ", weekdayText=" + weekdayText + '}';
    }

}
