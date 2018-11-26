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

import java.io.IOException;
import java.net.*;
import javax.inject.Inject;

import io.mikael.urlbuilder.UrlBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tech.redroma.google.places.exceptions.GooglePlacesBadArgumentException;
import tech.redroma.google.places.exceptions.GooglePlacesException;
import tech.redroma.google.places.requests.*;
import tech.redroma.google.places.responses.GetPlaceDetailsResponse;
import tech.redroma.google.places.responses.NearbySearchResponse;
import tech.sirwellington.alchemy.annotations.access.Internal;
import tech.sirwellington.alchemy.http.AlchemyHttp;
import tech.sirwellington.alchemy.http.AlchemyRequestSteps;

import static com.google.common.base.Strings.isNullOrEmpty;
import static tech.sirwellington.alchemy.arguments.Arguments.*;
import static tech.sirwellington.alchemy.arguments.assertions.Assertions.notNull;
import static tech.sirwellington.alchemy.arguments.assertions.StringAssertions.*;

/**
 *
 * @author SirWellington
 */
@Internal
final class GooglePlacesAPIImpl implements GooglePlacesAPI
{

    private final static Logger LOG = LoggerFactory.getLogger(GooglePlacesAPIImpl.class);

    private final String apiKey;
    private final AlchemyHttp http;
    private final ExceptionMapper exceptionMapper;
    private final RequestEncoder<NearbySearchRequest> nearbySearchRequestEncoder;
    private final RequestEncoder<GetPlaceDetailsRequest> placeDetailsRequestEncoder;
    private final RequestEncoder<AutocompletePlaceRequest> autocompleteRequestEncoder;
    private final URLProvider urls;

    @Inject
    GooglePlacesAPIImpl(String apiKey,
                        AlchemyHttp http,
                        ExceptionMapper exceptionMapper,
                        RequestEncoder<NearbySearchRequest> nearbySearchRequestEncoder,
                        RequestEncoder<GetPlaceDetailsRequest> placeDetailsRequestEncoder,
                        RequestEncoder<AutocompletePlaceRequest> autocompleteRequestEncoder,
                        URLProvider urls)
    {
        checkThat(apiKey).is(nonEmptyString());
        checkThat(http, exceptionMapper, nearbySearchRequestEncoder, placeDetailsRequestEncoder, autocompleteRequestEncoder)
            .are(notNull());

        this.apiKey = apiKey;
        this.http = http;
        this.exceptionMapper = exceptionMapper;
        this.nearbySearchRequestEncoder = nearbySearchRequestEncoder;
        this.placeDetailsRequestEncoder = placeDetailsRequestEncoder;
        this.autocompleteRequestEncoder = autocompleteRequestEncoder;
        this.urls = urls;
    }

    @Override
    public NearbySearchResponse searchNearbyPlaces(NearbySearchRequest request) throws GooglePlacesException
    {
        checkRequest(request);

        AlchemyRequestSteps.Step3 httpRequest = http.go()
                                                    .get()
                                                    .usingQueryParam(Keys.API_KEY, apiKey);

        String url = urls.getNearbySearch();
        httpRequest = nearbySearchRequestEncoder.encodeRequest(httpRequest, request);

        try
        {
            return httpRequest
                .expecting(NearbySearchResponse.class)
                .at(url);
        }
        catch (Exception ex)
        {
            LOG.error("Failed to make request to at: {}", url, ex);
            throw exceptionMapper.mapException(ex);
        }
    }

    @Override
    public GetPlaceDetailsResponse getPlaceDetails(GetPlaceDetailsRequest request) throws GooglePlacesException
    {
        checkRequest(request);

        AlchemyRequestSteps.Step3 httpRequest = http.go()
            .get()
            .usingQueryParam(Keys.API_KEY, apiKey);

        String url = urls.getPlaceDetails();
        httpRequest = placeDetailsRequestEncoder.encodeRequest(httpRequest, request);

        try
        {
            return httpRequest
                .expecting(GetPlaceDetailsResponse.class)
                .at(url);
        }
        catch (Exception ex)
        {
            LOG.error("Failed to make request to get PlaceDetails: [{}] at [{}]", request, url, ex);
            throw exceptionMapper.mapException(ex);
        }
    }

    @Override
    public URL getPhoto(GetPhotoRequest request) throws GooglePlacesException
    {
        checkRequest(request);

        String url = urls.getPhotoAPI();
        URL result;
        try
        {
            result = buildURLFor(request, url);
        }
        catch (URISyntaxException | MalformedURLException ex)
        {
            LOG.error("Failed to create URL to get photo: [{]] at [{}]", request, url, ex);
            throw exceptionMapper.mapException(ex);
        }
        
        try
        {
            result = determineRedirectAt(result);
        }
        catch (Exception ex)
        {
            LOG.error("Failed to follow a redirect to get image at: [{}]", url, ex);
        }
        
        return result;
    }

    private void checkRequest(Object request)
    {
        checkThat(request)
            .throwing(GooglePlacesBadArgumentException.class)
            .usingMessage("request missing")
            .is(notNull());

    }

    private URL buildURLFor(GetPhotoRequest request, String url) throws URISyntaxException, MalformedURLException
    {

        UrlBuilder builder = UrlBuilder.fromString(url);
        builder.addParameter(Keys.API_KEY, apiKey)
               .addParameter(Keys.PHOTO_REFERENCE, request.photoReference);

        if (request.hasMaxHeight())
        {
            builder = builder.addParameter(Keys.HEIGHT, String.valueOf(request.maxHeight));
        }
        else if (request.hasMaxWidth())
        {
            builder = builder.addParameter(Keys.WIDTH, String.valueOf(request.maxWidth));
        }

        URI uri = builder.toUri();
        return uri.toURL();
    }

    private URL determineRedirectAt(URL url) throws IOException
    {
        URLConnection connection = url.openConnection();
        
        if (!(connection instanceof HttpURLConnection))
        {
            return url;
        }
        
        HttpURLConnection httpConnection = (HttpURLConnection) connection;
        httpConnection.setInstanceFollowRedirects(false);
        httpConnection.setRequestMethod(Keys.HEAD);
        int responseCode = httpConnection.getResponseCode();
        
        try
        {
            String redirectURL = httpConnection.getHeaderField(Keys.REDIRECTED_HEADER);

            if (!isNullOrEmpty(redirectURL))
            {
                return new URL(redirectURL);
            }
            else
            {
                return url;
            }
        }
        finally
        {
            httpConnection.disconnect();
        }
    }

    static class Keys
    {

        static final String API_KEY = "key";
        static final String PHOTO_REFERENCE = "photoreference";
        static final String WIDTH = "maxwidth";
        static final String HEIGHT = "maxheight";
        static final String REDIRECTED_HEADER = "location";
        
        static final String HEAD = "HEAD";
        static final String GET = "GET";
    }

}
