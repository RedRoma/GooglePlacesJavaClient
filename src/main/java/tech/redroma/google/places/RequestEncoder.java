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
import tech.sirwellington.alchemy.http.AlchemyRequest;


/**
 * Responsible for taking information from a request object and embedding it into an {@link AlchemyRequest}.
 * 
 * @author SirWellington
 */
@Internal
interface RequestEncoder<Request>
{
    /**
     * Takes the {@linkplain AlchemyRequest.Step3 Alchemy Request} and embeds it with the information
     * from the {@code Request} object.
     * 
     * @param alchemyRequest
     * @param request
     * @return An {@linkplain AlchemyRequest.Step3 Alchemy Request} with information from {@code request} injected.
     */
    AlchemyRequest.Step3 encodeRequest(AlchemyRequest.Step3 alchemyRequest, Request request);
}
