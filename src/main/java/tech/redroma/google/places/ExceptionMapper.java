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

import tech.redroma.google.places.exceptions.GooglePlacesAuthenticationException;
import tech.redroma.google.places.exceptions.GooglePlacesBadArgumentException;
import tech.redroma.google.places.exceptions.GooglePlacesException;
import tech.redroma.google.places.exceptions.GooglePlacesLimitExceededException;
import tech.redroma.google.places.exceptions.GooglePlacesOperationFailedException;
import tech.sirwellington.alchemy.http.HttpResponse;
import tech.sirwellington.alchemy.http.HttpStatusCode;
import tech.sirwellington.alchemy.http.exceptions.AlchemyHttpException;

import static tech.sirwellington.alchemy.http.HttpStatusCode.BAD_REQUEST;
import static tech.sirwellington.alchemy.http.HttpStatusCode.UNAUTHORIZED;


/**
 * Responsible for translating general exceptions to a {@link GooglePlacesException}.
 * 
 * @author SirWellington
 */
interface ExceptionMapper 
{

    GooglePlacesException mapException(Exception ex);
    
    /*
     * Singleton instance.
     */
    ExceptionMapper INSTANCE = (Exception ex) ->
    {
        if (ex instanceof GooglePlacesException)
        {
            return (GooglePlacesException) ex;
        }
        
        if (ex instanceof IllegalArgumentException)
        {
            return new GooglePlacesBadArgumentException(ex);
        }
        
        if (ex instanceof AlchemyHttpException)
        {
            AlchemyHttpException aex = (AlchemyHttpException) ex;
            
            if (!aex.hasResponse())
            {
                return new GooglePlacesOperationFailedException(aex);
            }
            
            HttpResponse response = aex.getResponse();
            
            if (response.statusCode() == BAD_REQUEST.getCode())
            {
                return new GooglePlacesBadArgumentException(aex);
            }
            
            if (response.statusCode() == UNAUTHORIZED.getCode())
            {
                return new GooglePlacesAuthenticationException(aex);
            }
            
            if (response.statusCode() == HttpStatusCode.NOT_FOUND.getCode())
            {
                return new GooglePlacesBadArgumentException(aex);
            }
            
            if (response.bodyAsString().contains("OVER_QUERY_LIMIT"))
            {
                return new GooglePlacesLimitExceededException(aex);
            }
        }
        
        return new GooglePlacesOperationFailedException(ex);
    };
}
