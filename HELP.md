# URL Shortner

### API Documentation

The following are the endpoints provided by the API:

* Creating Short Url :-
  * POST Method
  * http://localhost:8080/api/generate
  * Body:- x-www-form-urlencoded
    * Key:- longUrl, Value:- {URL to be shortened}
* Getting redirected through Short Url :-
  * GET Method
  * http://localhost:8080/api/redirect/{shortUrl}
