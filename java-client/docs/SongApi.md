# SongApi

All URIs are relative to *http://localhost:8080*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**createSong**](SongApi.md#createSong) | **POST** /api/songs |  |
| [**deleteSong**](SongApi.md#deleteSong) | **DELETE** /api/songs/{songId} |  |
| [**getSong**](SongApi.md#getSong) | **GET** /api/songs/{songId} |  |
| [**getSongs**](SongApi.md#getSongs) | **GET** /api/songs |  |
| [**updateSong**](SongApi.md#updateSong) | **PUT** /api/songs/{songId} |  |


<a name="createSong"></a>
# **createSong**
> createSong(song)



create song

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.SongApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");
    
    // Configure OAuth2 access token for authorization: jhipster-uaa
    OAuth jhipster-uaa = (OAuth) defaultClient.getAuthentication("jhipster-uaa");
    jhipster-uaa.setAccessToken("YOUR ACCESS TOKEN");

    // Configure OAuth2 access token for authorization: jhipster-uaa
    OAuth jhipster-uaa = (OAuth) defaultClient.getAuthentication("jhipster-uaa");
    jhipster-uaa.setAccessToken("YOUR ACCESS TOKEN");

    SongApi apiInstance = new SongApi(defaultClient);
    Song song = new Song(); // Song | song model
    try {
      apiInstance.createSong(song);
    } catch (ApiException e) {
      System.err.println("Exception when calling SongApi#createSong");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **song** | [**Song**](Song.md)| song model | [optional] |

### Return type

null (empty response body)

### Authorization

[jhipster-uaa](../README.md#jhipster-uaa), [jhipster-uaa](../README.md#jhipster-uaa)

### HTTP request headers

 - **Content-Type**: application/vnd.vroong.private.v1+json
 - **Accept**: application/problem+json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **201** | Created |  -  |
| **400** | Bad Request |  -  |
| **401** | Unauthorized |  -  |
| **403** | Forbidden |  -  |
| **500** | Internal Server Error |  -  |

<a name="deleteSong"></a>
# **deleteSong**
> deleteSong(songId)



delete song

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.SongApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");
    
    // Configure OAuth2 access token for authorization: jhipster-uaa
    OAuth jhipster-uaa = (OAuth) defaultClient.getAuthentication("jhipster-uaa");
    jhipster-uaa.setAccessToken("YOUR ACCESS TOKEN");

    // Configure OAuth2 access token for authorization: jhipster-uaa
    OAuth jhipster-uaa = (OAuth) defaultClient.getAuthentication("jhipster-uaa");
    jhipster-uaa.setAccessToken("YOUR ACCESS TOKEN");

    SongApi apiInstance = new SongApi(defaultClient);
    Long songId = 56L; // Long | song id
    try {
      apiInstance.deleteSong(songId);
    } catch (ApiException e) {
      System.err.println("Exception when calling SongApi#deleteSong");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **songId** | **Long**| song id | |

### Return type

null (empty response body)

### Authorization

[jhipster-uaa](../README.md#jhipster-uaa), [jhipster-uaa](../README.md#jhipster-uaa)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/problem+json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **204** | No Content |  -  |
| **400** | Bad Request |  -  |
| **401** | Unauthorized |  -  |
| **403** | Forbidden |  -  |
| **500** | Internal Server Error |  -  |

<a name="getSong"></a>
# **getSong**
> getSong(songId)



get song

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.SongApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");
    
    // Configure OAuth2 access token for authorization: jhipster-uaa
    OAuth jhipster-uaa = (OAuth) defaultClient.getAuthentication("jhipster-uaa");
    jhipster-uaa.setAccessToken("YOUR ACCESS TOKEN");

    // Configure OAuth2 access token for authorization: jhipster-uaa
    OAuth jhipster-uaa = (OAuth) defaultClient.getAuthentication("jhipster-uaa");
    jhipster-uaa.setAccessToken("YOUR ACCESS TOKEN");

    SongApi apiInstance = new SongApi(defaultClient);
    Long songId = 56L; // Long | song id
    try {
      apiInstance.getSong(songId);
    } catch (ApiException e) {
      System.err.println("Exception when calling SongApi#getSong");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **songId** | **Long**| song id | |

### Return type

null (empty response body)

### Authorization

[jhipster-uaa](../README.md#jhipster-uaa), [jhipster-uaa](../README.md#jhipster-uaa)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/problem+json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Ok |  -  |
| **400** | Bad Request |  -  |
| **401** | Unauthorized |  -  |
| **403** | Forbidden |  -  |
| **500** | Internal Server Error |  -  |

<a name="getSongs"></a>
# **getSongs**
> getSongs(page, size)



get song list

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.SongApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");
    
    // Configure OAuth2 access token for authorization: jhipster-uaa
    OAuth jhipster-uaa = (OAuth) defaultClient.getAuthentication("jhipster-uaa");
    jhipster-uaa.setAccessToken("YOUR ACCESS TOKEN");

    // Configure OAuth2 access token for authorization: jhipster-uaa
    OAuth jhipster-uaa = (OAuth) defaultClient.getAuthentication("jhipster-uaa");
    jhipster-uaa.setAccessToken("YOUR ACCESS TOKEN");

    SongApi apiInstance = new SongApi(defaultClient);
    Integer page = 1; // Integer | 
    Integer size = 10; // Integer | 
    try {
      apiInstance.getSongs(page, size);
    } catch (ApiException e) {
      System.err.println("Exception when calling SongApi#getSongs");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **page** | **Integer**|  | [optional] [default to 1] |
| **size** | **Integer**|  | [optional] [default to 10] |

### Return type

null (empty response body)

### Authorization

[jhipster-uaa](../README.md#jhipster-uaa), [jhipster-uaa](../README.md#jhipster-uaa)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/problem+json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Ok |  -  |
| **400** | Bad Request |  -  |
| **401** | Unauthorized |  -  |
| **403** | Forbidden |  -  |
| **500** | Internal Server Error |  -  |

<a name="updateSong"></a>
# **updateSong**
> updateSong(songId, song)



update song

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.SongApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");
    
    // Configure OAuth2 access token for authorization: jhipster-uaa
    OAuth jhipster-uaa = (OAuth) defaultClient.getAuthentication("jhipster-uaa");
    jhipster-uaa.setAccessToken("YOUR ACCESS TOKEN");

    // Configure OAuth2 access token for authorization: jhipster-uaa
    OAuth jhipster-uaa = (OAuth) defaultClient.getAuthentication("jhipster-uaa");
    jhipster-uaa.setAccessToken("YOUR ACCESS TOKEN");

    SongApi apiInstance = new SongApi(defaultClient);
    Long songId = 56L; // Long | song id
    Song song = new Song(); // Song | song model
    try {
      apiInstance.updateSong(songId, song);
    } catch (ApiException e) {
      System.err.println("Exception when calling SongApi#updateSong");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **songId** | **Long**| song id | |
| **song** | [**Song**](Song.md)| song model | [optional] |

### Return type

null (empty response body)

### Authorization

[jhipster-uaa](../README.md#jhipster-uaa), [jhipster-uaa](../README.md#jhipster-uaa)

### HTTP request headers

 - **Content-Type**: application/vnd.vroong.private.v1+json
 - **Accept**: application/problem+json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **204** | No Content |  -  |
| **400** | Bad Request |  -  |
| **401** | Unauthorized |  -  |
| **403** | Forbidden |  -  |
| **500** | Internal Server Error |  -  |

