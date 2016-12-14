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

package tech.redroma.google.places;

import tech.sirwellington.alchemy.annotations.access.Internal;

/**
 *
 * @author SirWellington
 */
@Internal
final class URLProviderProduction implements URLProvider
{
    
    @Override
    public String getBase()
    {
        return URLs.BASE;
    }

    @Override
    public String getPhotoAPI()
    {
        return URLs.PHOTO;
    }

    @Override
    public String getPlaceDetails()
    {
        return URLs.PLACE_DETAILS;
    }

    @Override
    public String getNearbySearch()
    {
        return URLs.NEARBY_SEARCH;
    }

    @Override
    public String getAutocomplete()
    {
        return URLs.AUTOCOMPLETE;
    }

    static class URLs
    {

        static final String BASE = "https://maps.googleapis.com/maps/api/place";
        static final String PHOTO = "https://maps.googleapis.com/maps/api/place/photo";
        static final String PLACE_DETAILS = "https://maps.googleapis.com/maps/api/place/details/json";
        static final String NEARBY_SEARCH = "https://maps.googleapis.com/maps/api/place/nearbysearch/json";
        static final String AUTOCOMPLETE = "https://maps.googleapis.com/maps/api/place/autocomplete/json";
    }

}
