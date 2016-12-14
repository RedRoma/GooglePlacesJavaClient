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

    @Pojo
    @Mutable
    @ThreadUnsafe
    public static class AddressComponent
    {

        String longName;
        String shortName;
        List<Types.ReturnedPlaceType> types;
    }

}
