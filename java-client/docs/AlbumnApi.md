# AlbumnApi

All URIs are relative to *http://localhost:8080*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**createAlbum**](AlbumnApi.md#createAlbum) | **POST** /api/singers/albums |  |


<a name="createAlbum"></a>
# **createAlbum**
> createAlbum(album)



create an album

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.AlbumnApi;

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

    AlbumnApi apiInstance = new AlbumnApi(defaultClient);
    Album album = new Album(); // Album | album model
    try {
      apiInstance.createAlbum(album);
    } catch (ApiException e) {
      System.err.println("Exception when calling AlbumnApi#createAlbum");
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
| **album** | [**Album**](Album.md)| album model | [optional] |

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

