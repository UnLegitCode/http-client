<div align="center">

# HTTP Client
</div>

------------------------------------------
### Обратная связь
* **[Telegram](https://t.me/UnLegit)**
* **[VK](https://vk.com/unlegit_code)**
* Discord: UnLegit#6190  
------------------------------------------

## Client

```java
// Create default client
HttpClient defaultClient = HttpClient.newClient();

// Create client builder, configure it and build client
HttpClient configuredClient = HttpClient.newBuilder().editConfig(config -> {
    config.setConnectTimeout(1000);
    config.setReadTimeout(500);
    config.setUseSecureProtocol(true);
}).build();
```

## Request

```java
// Create GET request for https://127.0.0.1:8080/profile?id=1
HttpRequest getRequest = GetRequest.newBuilder()
        .path("127.0.0.1:8080") // host & port/domain name
        .path("profile")
        .parameter("id", 1)
        .build();

// Execute request
HttpResponse response = client.executeRequest(getRequest);

// Get response status
HttpResponse.Status status = response.getStatus();
// Responsed data
JsonContent responsedData = response.getBody();
// Parse response body
Profile responsedProfile = responsedData.<Profile>asObject();

// Create POST request
HttpRequest postRequest = PostRequest.newBuilder()
        .path("127.0.0.1:8080")
        .path("profile")
        .body(responsedBody)
        .build();

// Similarly execute post request

// Also u can create DELETE request using DeleteRequest
```