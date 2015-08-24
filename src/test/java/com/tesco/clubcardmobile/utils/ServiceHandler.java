package com.tesco.clubcardmobile.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ServiceHandler {

	HttpResponse response = null;
	HttpGet request = null;
	HttpPost post = null;
	HttpClient client = null;
	String url = null;
	String accessKey = null;
	String domain = null;

	public String getDomain(String env) {
		if (env.equalsIgnoreCase("ops")) {
			domain = "http://212.140.177.75";
		} else if (env.equalsIgnoreCase("live")) {
			domain = "http://mobile.tesco.com";
		}
		return domain;

	}

	public String getAccessToken(String env, String key, String username,
			String password) throws ParseException, IOException, JSONException {

		url = getDomain(env)
				+ "/groceryapi/restservice.aspx?COMMAND=TOKEN&partial=Y";

		client = HttpClientBuilder.create().build();
		post = new HttpPost(url);

		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		urlParameters.add(new BasicNameValuePair("client_secret",
				"7BC2DC5A472D4D7AB376"));
		urlParameters.add(new BasicNameValuePair("client_id",
				"C473B5BAA16047B6A6B5"));
		urlParameters.add(new BasicNameValuePair("grant_type", "password"));
		urlParameters.add(new BasicNameValuePair("username", username));
		urlParameters.add(new BasicNameValuePair("password", password));
		urlParameters.add(new BasicNameValuePair("version", "2.0"));
		urlParameters.add(new BasicNameValuePair("IdentityToken", "Y"));

		post.setEntity(new UrlEncodedFormEntity(urlParameters));
		response = client.execute(post);
		String json = EntityUtils.toString(response.getEntity());
		JSONObject obj = new JSONObject(json);
		return obj.getString(key);

	}

	public String getResponse(String url, String key, String accessKey)
			throws ClientProtocolException, IOException, JSONException {
		client = HttpClientBuilder.create().build();
		request = new HttpGet(url);

		request.setHeader("Accept", "application/json");
		request.setHeader("Authorization", accessKey);
		request.setHeader("Host", domain.split("//")[1]);

		response = client.execute(request);
		String json = EntityUtils.toString(response.getEntity());
		JSONObject obj = new JSONObject(json);
		return obj.getString(key);

	}

	public JSONArray getResponseArray(String url, String key, String accessKey)
			throws ClientProtocolException, IOException, JSONException {
		client = HttpClientBuilder.create().build();
		request = new HttpGet(url);

		request.setHeader("Accept", "application/json");
		request.setHeader("Authorization", accessKey);
		request.setHeader("Host", domain.split("//")[1]);

		response = client.execute(request);
		String json = EntityUtils.toString(response.getEntity());
		JSONObject obj = new JSONObject(json);
		return obj.getJSONArray(key);

	}

	public JSONObject getProfileResponse(String env, String username,
			String password) throws ParseException, IOException, JSONException {
		accessKey = getAccessToken(env, "access_token", username, password);
		//System.out.println("AccessKey :: "+ accessKey);
		url = getDomain(env)
				+ "/MobileEnterpriseAPI/api/CustomerProfile?fields=userId,title,forename,surname,clubcards,postalAddresses";
		client = HttpClientBuilder.create().build();
		request = new HttpGet(url);

		request.setHeader("Accept", "application/json");
		request.setHeader("Authorization", accessKey);
		request.setHeader("Host", domain.split("//")[1]);

		response = client.execute(request);
		String json = EntityUtils.toString(response.getEntity());
		JSONObject obj = new JSONObject(json);
		return obj;

	}

	public JSONObject getCouponResponse(String env, String username,
			String password) throws ParseException, IOException, JSONException {
		accessKey = getAccessToken(env, "access_token", username, password);
		url = getDomain(env)
				+ "/MobileEnterpriseAPI/api/Coupons?limit=99&offset=0";
		client = HttpClientBuilder.create().build();
		request = new HttpGet(url);

		request.setHeader("Accept", "application/json");
		request.setHeader("Authorization", accessKey);
		request.setHeader("Host", domain.split("//")[1]);

		response = client.execute(request);
		String json = EntityUtils.toString(response.getEntity());
		JSONObject obj = new JSONObject(json);
		return obj;
		//return obj.getJSONArray("CouponsList");

	}

	public String getPostcode(String env, String username, String password,
			int whichAddressPostcode) throws ParseException, IOException,
			JSONException {
		JSONObject profileResponse = getProfileResponse(env, username, password);
		JSONObject firstPostAddress = (JSONObject) profileResponse
				.getJSONArray("PostalAddresses").get(whichAddressPostcode);
		return firstPostAddress.get("postcode").toString()
				.replaceAll("\\s+", "");
	}

	public String getClubcardNumber(String env, String username,
			String password, int whichClubcard) throws ParseException,
			IOException, JSONException {
		JSONObject profileResponse = getProfileResponse(env, username, password);
		JSONArray clubcardNumber = profileResponse.getJSONArray("Clubcards");
		return clubcardNumber.getString(whichClubcard);
	}

	public JSONArray getClubcardPointsResponse(String env, String username,
			String password) throws ParseException, IOException, JSONException {
		accessKey = getAccessToken(env, "access_token", username, password);

		url = getDomain(env)
				+ "/oneapp/restservice.aspx?COMMAND=CLUBCARDPOINTS&clubcardnumber="
				+ getClubcardNumber(env, username, password, 0) + "&postcode="
				+ getPostcode(env, username, password, 0) + "&version=2.0";
		client = HttpClientBuilder.create().build();
		request = new HttpGet(url);

		request.setHeader("Accept", "application/json");
		request.setHeader("Authorization", accessKey);
		request.setHeader("Host", domain.split("//")[1]);

		response = client.execute(request);
		String json = EntityUtils.toString(response.getEntity());
		JSONObject obj = new JSONObject(json);
		return obj.getJSONArray("ClubcardPoints");

	}

	public JSONArray getOffersResponse(String env, String username,
			String password, String offerType) throws ParseException,
			IOException, JSONException {
		accessKey = getAccessToken(env, "access_token", username, password);
		String offerTypeValue = null;
		if (offerType.equalsIgnoreCase("halfprice")) {
			offerTypeValue = "4";
		} else if (offerType.equalsIgnoreCase("topoffers")) {
			offerTypeValue = "3";
		} else if (offerType.equalsIgnoreCase("buyonegetoneoffers")) {
			offerTypeValue = "7";
		}
		url = getDomain(env)
				+ "/groceryapi/restservice.aspx?COMMAND=LISTPRODUCTSBYSPECIALOFFER&MENUID="
				+ offerTypeValue + "&PAGE=1&sessionkey=" + accessKey
				+ "&version=2.0";
		client = HttpClientBuilder.create().build();
		request = new HttpGet(url);

		request.setHeader("Accept", "application/json");
		request.setHeader("Authorization", accessKey);
		request.setHeader("Host", domain.split("//")[1]);

		response = client.execute(request);
		String json = EntityUtils.toString(response.getEntity());
		JSONObject obj = new JSONObject(json);
		return obj.getJSONArray("Products");

	}

	public JSONArray getFuelsaveResponse(String env, String username,
			String password) throws ParseException, IOException, JSONException {
		accessKey = getAccessToken(env, "access_token", username, password);

		url = getDomain(env)
				+ "/MobileEnterpriseAPI/api/Coupons/Balance?balances=FuelPoints2";
		client = HttpClientBuilder.create().build();
		request = new HttpGet(url);

		request.setHeader("Accept", "application/json");
		request.setHeader("Authorization", accessKey);
		request.setHeader("Host", domain.split("//")[1]);

		response = client.execute(request);
		String json = EntityUtils.toString(response.getEntity());
		JSONObject obj = new JSONObject(json);
		JSONObject fuelBalances = obj.getJSONObject("Balance");
		fuelBalances.getJSONArray("BalanceByExpiry");
		return fuelBalances.getJSONArray("BalanceByExpiry");

	}
}
