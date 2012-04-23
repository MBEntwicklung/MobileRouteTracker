/**
 * 
 */
package de.mbentwicklung.android.mobileroutetracker.webservice;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
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
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;
import de.mbentwicklung.android.mobileroutetracker.ConnectionSetting;
import de.mbentwicklung.android.mobileroutetracker.MRTConstants;

/**
 * @author Marc Bellmann <marc.bellmann@mb-entwicklung.de>
 */
public class WebService {

	private static final String ACCEPT = "text/html,application/xml,application/xhtml+xml,text/html;q=0.9,text/plain;q=0.8,image/png,*/*;q=0.5";

	private static final String CONTENT_TYPE_JSON = "application/json";

	private DefaultHttpClient httpClient;

	private HttpContext httpContext;

	private final ConnectionSetting connectionSetting;

	public WebService(final ConnectionSetting connectionSetting) {
		this.connectionSetting = connectionSetting;

		HttpParams httpParams = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(httpParams, 10000);
		HttpConnectionParams.setSoTimeout(httpParams, 10000);
		httpClient = new DefaultHttpClient(httpParams);
		httpContext = new BasicHttpContext();
	}

	public boolean send(Map<String, Object> params) {
		final JSONObject jsonObject = new JSONObject();
		final HttpPost httpPost = new HttpPost(connectionSetting.getWebserviceLink());
		httpPost.setHeader("Accept", ACCEPT);
		httpPost.setHeader("Content-Type", CONTENT_TYPE_JSON);
		httpClient.getParams().setParameter(ClientPNames.COOKIE_POLICY, CookiePolicy.RFC_2109);

		for (Map.Entry<String, Object> param : params.entrySet()) {
			try {
				jsonObject.put(param.getKey(), param.getValue());
			} catch (JSONException e) {
				Log.e(MRTConstants.LogTags.WS_TAG, "JSONException : " + e);
			}
		}

		try {
			final StringEntity tmp = new StringEntity(jsonObject.toString(), "UTF-8");
			httpPost.setEntity(tmp);

			Log.d(MRTConstants.LogTags.WS_TAG, connectionSetting.getWebserviceLink() + "?" + jsonObject.toString());
			final HttpResponse httpResponse = httpClient.execute(httpPost, httpContext);

			if (httpResponse != null && httpResponse.getStatusLine().getStatusCode() == 200) {
				Log.d("WS", "sending successful");
				return true;
			}
		} catch (UnsupportedEncodingException e) {
			Log.e(MRTConstants.LogTags.WS_TAG, "UnsupportedEncodingException : " + e);
		} catch (ClientProtocolException e) {
			Log.e(MRTConstants.LogTags.WS_TAG, "ClientProtocolException : " + e);
		} catch (IOException e) {
			Log.e(MRTConstants.LogTags.WS_TAG, "IOException : " + e);
		}

		Log.d("WS", "sending failure");
		return false;
	}
}
