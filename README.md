Google Places Java Client
======================================

[<img src="https://avatars2.githubusercontent.com/u/16062337?s=400&u=9547a9cfcec08112036b279df94a6232889eec7a&v=4" width="300">](http://RedRoma.tech)

[![Travis](https://travis-ci.org/RedRoma/YelpJavaClient.svg?branch=develop)](https://travis-ci.org/RedRoma/GooglePlacesJavaClient)
![Maven Central Version](http://img.shields.io/maven-central/v/tech.redroma.google/google-places-api.svg)

---

RedRoma's Java Client to the [**Google Places API**](https://developers.google.com/places/web-service/).



## Download

### Maven

```xml
<dependency>
	<groupId>tech.redroma.google</groupId>
	<artifactId>google-places-api</artifactId>
    <version>1.0</version>
</dependency>
```

## Creating a Client

Create a client using the API Key that you obtained from the [Google Developer Console](https://support.google.com/googleapi/answer/6158862).

```java
String apiKey = "....";
GooglePlacesAPI api = GooglePlacesAPI.create(apiKey);
```

## Searching Places

#### [Google API Documentation](https://developers.google.com/places/web-service/search)


Searching places is as easy as making a request object and using the `searchNearbyPlaces()` method.

```java
//Create a request object
Location location = Location.of(40.814697, -73.908013);
NearbySearchRequest request = NearbySearchRequest.newBuilder()
     .withKeyword("grocery")
     .withRadiusInMeters(5_000)
     .withLocation(location)
     .build();

//Make the request
NearbySearchResponse response = api.searchNearbyPlaces(request);

LOG.info("Found {} results for request {}", response.getResults().size(), request);
```


## Place Details

#### [Google API Documentation](https://developers.google.com/places/web-service/details)


Sometimes you want more detailed information about a place, such as the business hours, additional photos, and price information.

Simply call the `getPlaceDetails()` method.

```java
//Using any place
Place place = Lists.oneOf(results);

//Make the request to get business details.
PlaceDetails details = instance.simpleGetPlaceDetails(place);

LOG.info("Received detailed info for business named {}: [{}]", business.name, businessDetails);

if (details.openingHours.openNow)
{
    LOG.info("{} is open now.", place.name)
}
```

## Photos

#### [Google API Documentation](https://developers.google.com/places/web-service/photos)


```java
Place place = Lists.oneOf(results);
Photo photo = Lists.oneOf(place.photos);

//Get the URL for the image
GetPhotoRequest request = GetPhotoRequest.newBuilder()
            .withPhotoReference(photo.photoReference)
            .withMaxHeight(GetPhotoRequest.Builder.MAX_HEIGHT)
            .build();

URL imageURL = api.getPhoto(request);

//Or download it
byte[] rawImage = api.downloadPhoto(photo);

```

## [Javadocs](http://www.javadoc.io/doc/tech.redroma.google/google-places-api/)

## Currently Unsupported

We do not yet support the following API calls:

+ [Query Autocomplete](https://developers.google.com/places/web-service/query)
+ [Add Places](https://developers.google.com/places/web-service/add-place)
+ [Text Search Requests](https://developers.google.com/places/web-service/search#TextSearchRequests)

## Guiding Philosophy

We used [**Alchemy Design Principles**](https://github.com/SirWellington/alchemy) when designing this library.

[<img src="https://raw.githubusercontent.com/SirWellington/alchemy/develop/Graphics/Logo/Alchemy-Logo-v7-name.png" width="200">](https://github.com/SirWellington/alchemy)

### Swift
We wanted our code to feel like it was barely there. This meant keeping things minimal and light.

### Intuitive
Yelp already designed a great intuitive API. We didn't want to add a pool of unnecessary soda.

### Solid
Nearly everything is unit tested, and it is already being used in production by [BlackNectar](http://docs.blacknectarapi.apiary.io/), and others.

### Invigorating
We wanted you to have fun, and to feel powerful.   
We ditched the no-fun java `get() set()` pojo style in favor of open `public` variables.
We trust you.

# License

This Software is licensed under the Apache 2.0 License

http://www.apache.org/licenses/LICENSE-2.0
