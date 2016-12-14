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


import java.time.Instant;
import java.util.List;
import tech.sirwellington.alchemy.annotations.concurrency.Mutable;
import tech.sirwellington.alchemy.annotations.concurrency.ThreadUnsafe;
import tech.sirwellington.alchemy.annotations.objects.Pojo;

/**
 *
 * @author SirWellington
 */
@Pojo
public final class Review 
{
    
    private String authorName;
    private String authorURL;
    private Language language;
    private String authorPhotoURL;
    private Integer rating;
    private String relativeTimeDescription;
    private Instant time;
    private List<AspectRating> aspects;
    
    @Pojo
    @Mutable
    @ThreadUnsafe
    public static class AspectRating
    {
        private Integer rating;
        private String aspect;
    }
}
