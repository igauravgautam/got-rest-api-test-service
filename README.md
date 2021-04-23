# got-rest-api-call
GOT based API Call : https://anapioficeandfire.com/Documentation

Get specific house
`$ curl "https://www.anapioficeandfire.co/api/houses/10"`

# Pagination
An API of Ice And Fire provides a lot of data about the world of Westeros. To prevent our servers from getting cranky, the API will automatically paginate the responses. You will learn how to create requests with pagination parameters and consume the response.

# Things worth noting:
Information about the pagination is included in the Link header
Page numbering is 1-based
You can specify how many items you want to receive per page, the maximum is 50
Constructing a request with pagination
You specify which page you want to access with the ?page parameter, if you don't provide the ?page parameter the first page will be returned. You can also specify the size of the page with the ?pageSize parameter, if you don't provide the ?pageSize parameter the default size of 10 will be used.

Let's make a request for the first page of characters with a page size of 10. Since we're only interested in the pagination information we provide the -I parameter to say that we only care about the headers.

$ curl -I "https://www.anapioficeandfire.com/api/characters?page=1&pageSize=10"
Here's the link header in the response:

- Link:
`<https://www.anapioficeandfire.com/api/characters?page=2&pageSize=10>; rel="next", <https://www.anapioficeandfire.com/api/characters?page=1&pageSize=10>; rel="first", <https://www.anapioficeandfire.com/api/characters?page=214&pageSize=10>; rel="last"`
# Possible link types:
next - Next page of results
prev - Previous page of results
first - First page of results
last - Last page of results
These links can then be used to navigate to other pages of results.

# Rate Limiting
To prevent malicious usage of our API we've a limit on the number of requests a given IP address can make to the API. This limit is set to 20000 requests per day. There should be no reason for hitting the limit if you implement proper caching strategies in your client. If you happen to hit the limit you'll receive a 403 Forbidden on all your requests for the remainder of the 24 hour time period.

# Caching
Apart from the standard cache headers such as max-age we also provide a few different ways to ease the load on our servers and your client. Each API response includes the ETag-header and the Last-Modified header. These headers can be used to ask our server if the data has changed or not. If the data has not changed you will receive an empty response body with a 304 Not Modified. If the data has changed you will receive a 200 with the updated data.

To use the ETag, include the following header in your request:
`If-None-Match: "your_etag_here"`
To use Last-Modified, include the following header in your request:
`If-Modified-Since: "date_here"`

-Versioning
Custom media types are used in An API of Ice And Fire to let consumers choose which version of the data they wish to receive. This is done by adding the following type to the Accept header when you make a request. Note that media types are specific to resources, this allows them to change independently from each other.

Important: If a version is not specified in the request the default version will be used. The default version may change in the future and can thus break the consumer's application. Make sure to always request a specific version in the Accept header as shown in the example below.

You specify a version like so:

application/vnd.anapioficeandfire+json; version=1

# Root
The Root resource contains information about all available resources in the API.