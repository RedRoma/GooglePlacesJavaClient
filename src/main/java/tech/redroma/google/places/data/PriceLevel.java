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

/**
 *
 * @author SirWellington
 */
public enum PriceLevel
{
    @SerializedName("0")
    FREE(0),
    @SerializedName("1")
    INEXPENSIVE(1),
    @SerializedName("2")
    MODERATE(2),
    @SerializedName("3")
    EXPENSIVE(3),
    @SerializedName("4")
    VERY_EXPENSIVE(4)
    ;
    
    public final int value;

    private PriceLevel(int value)
    {
        this.value = value;
    }
    
}
