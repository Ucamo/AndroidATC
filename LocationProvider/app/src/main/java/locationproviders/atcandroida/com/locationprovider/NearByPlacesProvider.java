package locationproviders.atcandroida.com.locationprovider;

import android.os.AsyncTask;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.HashMap;
import java.util.List;

public class NearByPlacesProvider extends AsyncTask<Object, String, String>
{
    String googlePlacesData;
    GoogleMap mMap;
    String url;

    @Override
    protected String doInBackground(Object... params){
        try{
            mMap=(GoogleMap)params[0];
            url=(String)params[1];
            HttpHandler downloadUrl = new HttpHandler();
            googlePlacesData = downloadUrl.getDataFromUrl(url);
        }catch (Exception e){
            e.printStackTrace();
        }
        return googlePlacesData;
    }

    @Override
    protected void onPostExecute(String result){
        List<HashMap<String,String>> nearbyPlacesList = null;
        DataParse dataParser = new DataParse();
        nearbyPlacesList=dataParser.parse(result);
        ShowNearbyPlaces(nearbyPlacesList);
    }

    private void ShowNearbyPlaces(List<HashMap<String,String>> nearbyPlacesList){
        for(int i=0;i<nearbyPlacesList.size();i++){
            MarkerOptions markerOptions = new MarkerOptions();
            HashMap<String,String> googlePlace = nearbyPlacesList.get(i);
            double lat = Double.parseDouble(googlePlace.get("lat"));
            double lng = Double.parseDouble(googlePlace.get("lng"));
            String placeName = googlePlace.get("place_name");
            String vicinity=googlePlace.get("vicinity");
            LatLng latLng = new LatLng(lat,lng);

            markerOptions.position(latLng);
            markerOptions.title(placeName+" : "+vicinity);
            mMap.addMarker(markerOptions);
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
            //move map camera
            mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
            mMap.animateCamera(CameraUpdateFactory.zoomTo(11));
        }
    }
}
