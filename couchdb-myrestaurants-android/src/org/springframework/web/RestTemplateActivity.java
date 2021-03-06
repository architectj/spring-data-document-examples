package org.springframework.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class RestTemplateActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		TextView view = new TextView(this);
		view.append("Running tests...\n\n");
		setContentView(view);
		try {			
			List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
			converters.add(new MappingJacksonHttpMessageConverter());
			RestTemplate restTemplate = new RestTemplate();
			restTemplate.setMessageConverters(converters);

			
			Map response = restTemplate.getForObject("http://127.0.0.1:5984",
					Map.class);
								
			
			view.append(response.toString());
			
			
			/*
			URL url = new URL("http:/127.0.0.1:5984");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			List<String> lines= new ArrayList<String>();
			while((line = reader.readLine()) != null){
			    lines.add(line);
			}
			reader.close();
			view.append(lines.toString());*/
			

			view.append("all ok!");
		} catch (Exception e) {
			view.append(e.toString());
			Log.e("rest-template","this is bad", e);			
		}

	}
}