package com.davidofffarchik.webclient;

import org.json.JSONObject;

public abstract class Parameter<T> {

    abstract public int getRequestMethod();

    abstract public T parseResponse (JSONObject jsonObject);

    abstract public JSONObject getBody();

    abstract public String getApiMethod();

    public String getUrl(){
        return "http://protected-wave-2984.herokuapp.com/api/";
    }

    /*
    public String getRequestURL() {
        if(getRequestMethod() == Request.Method.GET || getRequestMethod() == Request.Method.DELETE) {
            Uri uri = Uri.withAppendedPath(Uri.parse(BuildConstants.SERVER_URL), getApiMethod());
            Map<String, String> params = getParams();
            if(params == null) return uri.toString();

            Uri.Builder builder = uri.buildUpon();
            Set<String> keys = params.keySet();
            for(String key : keys) {
                String value = params.get(key);
                if(!StringUtils.isEmpty(value)) builder.appendQueryParameter(key, value);
            }
            return builder.build().toString();
        }
        try {
            URI absolute = new URI(BuildConstants.SERVER_URL);
            URI relative = new URI(getApiMethod());
            return absolute.resolve(relative).toString();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }
    */

}
