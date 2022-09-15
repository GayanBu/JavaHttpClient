String serveletPath = SystemResource.HttpPost request = new HttpPost(serveletPath);
List<NameValuePair> requestParams = new ArrayList<>();
requestParams.add(new BasicNameValuePair("sqlStatement", sq1));requestParams.add(new BasicNameValuePair("method", "executeUpdate"));
CloseableHttpResponse response = null;
try {
request.setEntity(new UrlEncodedFormEntity(requestParams,StandardCharsets.UTF_8));response =httpClient.execute(request, httpClientContext);//
int status = response.getCode();
if (status == HttpStatus.SC_OK) {
try {
responseEntity = objectMapper.readValue(response.getEntity().getContent(),
ExecuteUpdateResponseEntity.class);
System.out.println("update status :" + responseEntity.getIsSucceeded());if (responseEntity.getIsSucceeded() == "true" && Objects.isNu11(responseEntity.getException())) {
／／ サーバーから受けられたクッキーをSessionContext（Staticクラス）に設定する
CookieStore cookieStore=httpClientContext.getCookieStore();
customCookie = cookieStore.getCookies().stream(). filter(
cookie -> "sessionID".equals(cookie.getName()) && Objects.nonNu11(cookie.getValue()))
.findFirst().orElseThrow(
() -> new SQLException("session id couldn't found in the previous response!"));
HttpClientSessionContext.getSessionData().setSessionId(customCookie.getValue().toString());System.out,print1n("current sessionId : " + customCookie.getValue().toString());
rs=1;} else if (Objects.nonNu11(responseEntity.getException())) {
System.out.print1n("error message :" + responseEntity,getException().getMessage());
rs = 0;
customP12JavaConnection_.collback();
throw new SQLException(responseEntity.getException());
} else {rs=0;}
} catch(UnsupportedOperationException e) {
System.out.print1n("error ocsuced! please check the server log for details.");e.printStackTrace();
rs = -1;
customP12JavaConnection_·collback();
throw new SQLException(e);
}
}