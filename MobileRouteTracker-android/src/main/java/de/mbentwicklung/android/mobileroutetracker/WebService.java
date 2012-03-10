/**
 * 
 */
package de.mbentwicklung.android.mobileroutetracker;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

/**
 * @author marc
 * 
 */
public class WebService {

	private DefaultHttpClient httpClient;
	private HttpContext httpContext;
	private HttpResponse httpResponse;
	private HttpPost httpPost;

	private String webServiceUrl;

	public WebService() {
		HttpParams httpParams = new BasicHttpParams();

		HttpConnectionParams.setConnectionTimeout(httpParams, 10000);
		HttpConnectionParams.setSoTimeout(httpParams, 10000);
		httpClient = new DefaultHttpClient(httpParams);
		httpContext = new BasicHttpContext();
		webServiceUrl = "http://192.168.2.103:3000/positions.json";
	}

	// Use this method to do a HttpPost\WebInvoke on a Web Service
	public String webInvoke(String methodName, Map<String, Object> params) {

		JSONObject jsonObject = new JSONObject();

		for (Map.Entry<String, Object> param : params.entrySet()) {
			try {
				jsonObject.put(param.getKey(), param.getValue());
			} catch (JSONException e) {
				Log.e("Groshie", "JSONException : " + e);
			}
		}
		return webInvoke(methodName, jsonObject.toString(), "application/json");
	}

	private String webInvoke(String methodName, String data, String contentType) {
		httpClient.getParams().setParameter(ClientPNames.COOKIE_POLICY, CookiePolicy.RFC_2109);

		httpPost = new HttpPost(webServiceUrl + methodName);
		httpResponse = null;

		StringEntity tmp = null;

		// httpPost.setHeader("User-Agent", "SET YOUR USER AGENT STRING HERE");
		httpPost.setHeader(
				"Accept",
				"text/html,application/xml,application/xhtml+xml,text/html;q=0.9,text/plain;q=0.8,image/png,*/*;q=0.5");

		if (contentType != null) {
			httpPost.setHeader("Content-Type", contentType);
		} else {
			httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
		}

		try {
			tmp = new StringEntity(data, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			Log.e("Groshie", "HttpUtils : UnsupportedEncodingException : " + e);
		}

		httpPost.setEntity(tmp);

		Log.d("Groshie", webServiceUrl + "?" + data);

		try {
			httpResponse = httpClient.execute(httpPost, httpContext);

			if (httpResponse != null) {
				return EntityUtils.toString(httpResponse.getEntity());
			}
		} catch (Exception e) {
			Log.e("Groshie", "HttpUtils: " + e);
		}

		return null;
	}
}
