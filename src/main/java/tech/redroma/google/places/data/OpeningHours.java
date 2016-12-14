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

import com.google.gson.JsonDeserializer;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import java.util.List;
import java.util.Objects;
import sir.wellington.alchemy.collections.lists.Lists;
import tech.sirwellington.alchemy.annotations.arguments.NonEmpty;
import tech.sirwellington.alchemy.annotations.concurrency.Mutable;
import tech.sirwellington.alchemy.annotations.concurrency.ThreadUnsafe;
import tech.sirwellington.alchemy.annotations.objects.Pojo;

import static tech.sirwellington.alchemy.arguments.Arguments.checkThat;
import static tech.sirwellington.alchemy.arguments.assertions.Assertions.equalTo;
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

    public List<Period> periods;

    public OpeningHours()
    {
    }

    public OpeningHours(Boolean openNow, List<String> weekdayText, List<Period> periods)
    {
        checkThat(weekdayText)
            .is(nonEmptyList());

        this.openNow = openNow;
        this.weekdayText = weekdayText;
        this.periods = periods;
    }

    static OpeningHours create(boolean isOpenNow, @NonEmpty List<String> weekdayText, List<Period> periods)
    {
        return new OpeningHours(isOpenNow, weekdayText, periods);
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

    public boolean hasPeriods()
    {
        return Lists.notEmpty(periods);
    }

    @Override
    public int hashCode()
    {
        int hash = 5;
        hash = 83 * hash + Objects.hashCode(this.openNow);
        hash = 83 * hash + Objects.hashCode(this.weekdayText);
        hash = 83 * hash + Objects.hashCode(this.periods);
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
        if (!Objects.equals(this.periods, other.periods))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "OpeningHours{" + "openNow=" + openNow + ", weekdayText=" + weekdayText + ", periods=" + periods + '}';
    }

    @Pojo
    @Mutable
    @ThreadUnsafe
    public static class Period
    {

        private Boolean open;
        private Integer day;
        private String time;

        public Boolean getOpen()
        {
            return open;
        }

        public Integer getDay()
        {
            return day;
        }

        public String getTime()
        {
            return time;
        }

        @Override
        public int hashCode()
        {
            int hash = 3;
            hash = 89 * hash + Objects.hashCode(this.open);
            hash = 89 * hash + Objects.hashCode(this.day);
            hash = 89 * hash + Objects.hashCode(this.time);
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
            final Period other = (Period) obj;
            if (!Objects.equals(this.time, other.time))
            {
                return false;
            }
            if (!Objects.equals(this.open, other.open))
            {
                return false;
            }
            if (!Objects.equals(this.day, other.day))
            {
                return false;
            }
            return true;
        }

        @Override
        public String toString()
        {
            return "Period{" + "open=" + open + ", day=" + day + ", time=" + time + '}';
        }

        public static JsonDeserializer<Period> createDeserializer()
        {
            return (json, type, context) ->
                {
                    checkThat(type)
                        .is(equalTo(Period.class));

                    if (json == null)
                    {
                        return null;
                    }

                    Period period = new Period();

                    if (!json.isJsonObject())
                    {
                        return period;
                    }

                    JsonObject object = json.getAsJsonObject();

                    JsonObject periodObject = null;
                    if (object.has("open"))
                    {
                        period.open = true;
                        periodObject = object.getAsJsonObject("open");
                    }
                    else if (object.has("closed"))
                    {
                        period.open = false;
                        periodObject = object.getAsJsonObject("closed");
                    }

                    if (periodObject == null)
                    {
                        return period;
                    }

                    if (periodObject.has("day"))
                    {
                        period.day = periodObject.get("day").getAsInt();
                    }

                    if (periodObject.has("time"))
                    {
                        period.time = periodObject.get("time").getAsString();
                    }

                    return period;
                };
        }

    }

}
