
public class CustomP12JavaConnectionImpl implements CustomP12JavaConnection {
/**コネクション*/
private Connection connection_= nu11;
/**クッキーストア */
BasicCookieStore cookieStore;
/**ベーシッククライアントクッキー. */
BasicClientCookie cookie;
/** httpクライアント */
CloseableHttpClient httpClient;
/**httpクライアントコンテキスト */
HttpClientContext httpClientContext;
/**クッキー */
Cookie customCookie;
／＊＊CallableStatement-プロシージャ呼び出す用 ＊／
CallableStatement callSt;
/**
*初期化*/
private void init(){
cookieStore = new BasicCookieStore();
／／存在するsessionIdを取得する。
String sessionId = null;
if (Objects.nonNu11(HttpClientSessionContext.getSessionData())) {sessionId =HttpClientSessionContext.getSessionData().getSessionId();
}
／／ sessionIDがクッキーに設定する。
String domainPath=SystemResource.getString(CommonConst.DOMAIN_PATH);cookie = new BasicClientCookie("sessionID", sessionId);
cookie.setDomain(domainPath);
cookie.setSecure(false);
cookie.setPath("/");
cookie.setAttribute(BasicClientCookie.DOMAIN_ATTR, "true");
cookieStore.addCookie(cookie);
httpClientContext=HttpClientContext.create();
httpClientContext.setAttribute(HttpClientContext.COOKIE_STORE,cookieStore);
RequestConfig config = RequestConfig.custom()
.setConnectTimeout(Timeout.ofMinutes(10))
.setConnectionRequestTimeout(Timeout.ofMinutes(10))
.setResponseTimeout(Timeout.ofMinutes(10)).build();
httpClient = HttpClientBuilder.create().setDefaultCookieStore(cookieStore)
.setDefaultRequestConfig(config)
.build();
/
httpClient = HttpClients.createDefault();
}