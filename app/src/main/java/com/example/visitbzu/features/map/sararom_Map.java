package com.example.visitbzu.features.map;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.example.visitbzu.HomePage;
import com.example.visitbzu.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;


public class sararom_Map extends AppCompatActivity implements OnMapReadyCallback  {
    private Toolbar toolbar;
    private GoogleMap sararomMap;
    LinearLayout map_get_loc;
    BottomSheetDialog dialog;
    ArrayList<sararom_Marker> sararom_parking = new ArrayList<sararom_Marker>();
    ArrayList<sararom_Marker> sararom_cafeterias = new ArrayList<sararom_Marker>();
    ArrayList<sararom_Marker> sararom_faculties =new ArrayList<sararom_Marker>();
    ArrayList<sararom_Marker> sararom_libraries = new ArrayList<sararom_Marker>();
    ArrayList<sararom_Marker> sararom_clinic = new ArrayList<sararom_Marker>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sararom_map);
        /*** Variables ***/
        ImageView sararom_zoom_in = findViewById(R.id.sararom_zoom_in);
        ImageView sararom_zoom_out = findViewById(R.id.sararom_zoom_out);
        ImageView sararom_layers = findViewById(R.id.sararom_layers);
        ImageView sararom_default_view = findViewById(R.id.sararom_map);

        fillLocations();

        map_get_loc = findViewById(R.id.sararom_destination_details);

        // For custom toolbar
        ImageView back = findViewById(R.id.sararom_arrow_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(sararom_Map.this, HomePage.class));
            }
        });

        // ******************** Map section *********************************
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.sararom_campus_map);
        mapFragment.getMapAsync(this);

        //----> zoom in
        sararom_zoom_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {sararomMap.animateCamera(CameraUpdateFactory.zoomIn());}
        });
        //----> zoom out
        sararom_zoom_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {sararomMap.animateCamera(CameraUpdateFactory.zoomOut());}
        });
        //----> layers
        sararom_layers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sararomMap.animateCamera(CameraUpdateFactory.zoomIn());
                if (sararomMap.getMapType() == GoogleMap.MAP_TYPE_NORMAL) {
                    sararomMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                } else {
                    sararomMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                }
            }
        });
        //----> default view
        sararom_default_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LatLng location = new LatLng(31.959239, 35.181365); // Replace latitude and longitude with your desired coordinates
                float zoomLevel = 18.0f; // Adjust the zoom level as needed
                sararomMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, zoomLevel));
                }
        });


        // ******************** Bottom sheet ********************************
        dialog = new BottomSheetDialog(this);
        createDialog();
        LinearLayout linearLayout = findViewById(R.id.sararom_lowerbar);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
            }
        });
        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);

    }

    private void createDialog() {
        View view = getLayoutInflater().inflate(R.layout.sararom_map_bottom_sheet,null,false);
        View close_dialog = view.findViewById(R.id.sararom_close_dialog);
        close_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        ImageView destination = view.findViewById(R.id.sararom_destination);
        destination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                map_get_loc.setVisibility(View.VISIBLE);

            }
        });

        dialog.setContentView(view);
    }



    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        sararomMap = googleMap;
        LatLng BZU = new LatLng(31.959215250040728, 35.182017827795256);
        sararomMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(this, R.raw.custom_map_style));
        sararomMap.moveCamera(CameraUpdateFactory.newLatLngZoom(BZU,17));
        sararomMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        for(int i = 0;i<sararom_cafeterias.size();i++) {
            MarkerOptions markerOptions = new MarkerOptions()
                    .position(sararom_cafeterias.get(i).getLoc())
                    .icon(bitmapDescriptorFromVector(this, R.drawable.sararom_caf))
                    .title(sararom_cafeterias.get(i).getName());
            sararomMap.addMarker(markerOptions);
        }
        for(int i = 0;i<sararom_faculties.size();i++) {
            MarkerOptions markerOptions = new MarkerOptions()
                    .position(sararom_faculties.get(i).getLoc())
                    .icon(bitmapDescriptorFromVector(this, R.drawable.sararom_fac))
                    .title(sararom_faculties.get(i).getName());
            sararomMap.addMarker(markerOptions);
        }
        for(int i = 0;i<sararom_libraries.size();i++) {
            MarkerOptions markerOptions = new MarkerOptions()
                    .position(sararom_libraries.get(i).getLoc())
                    .icon(bitmapDescriptorFromVector(this, R.drawable.sararom_l))
                    .title(sararom_libraries.get(i).getName());
            sararomMap.addMarker(markerOptions);
        }
        for(int i = 0;i<sararom_clinic.size();i++) {
            MarkerOptions markerOptions = new MarkerOptions()
                    .position(sararom_clinic.get(i).getLoc())
                    .icon(bitmapDescriptorFromVector(this, R.drawable.sararom_cl))
                    .title(sararom_clinic.get(i).getName());
            sararomMap.addMarker(markerOptions);
        }
        for(int i = 0;i<sararom_parking.size();i++) {
            MarkerOptions markerOptions = new MarkerOptions()
                    .position(sararom_parking.get(i).getLoc())
                    .icon(bitmapDescriptorFromVector(this, R.drawable.sararom_p))
                    .title(sararom_parking.get(i).getName());
            sararomMap.addMarker(markerOptions);
        }


    }
    public void fillLocations(){
        /**** Cafeterias ****/
        sararom_cafeterias.add(new sararom_Marker(new LatLng(31.960426, 35.182240),"كافيتيريا الدوم"));
        sararom_cafeterias.add(new sararom_Marker(new LatLng(31.959543, 35.181655),"المجمع"));
        sararom_cafeterias.add(new sararom_Marker(new LatLng(31.960608, 35.182406),"كافيتيريا الاداب"));
        sararom_cafeterias.add(new sararom_Marker(new LatLng(31.961967, 35.182638) ,"كافيتيريا التمريض"));
        sararom_cafeterias.add(new sararom_Marker(new LatLng(31.960208, 35.182417) ,"سوبر ماركت الشيني"));
        sararom_cafeterias.add(new sararom_Marker(new LatLng(31.958192, 35.183253),"كافيتيريا التحارة"));
        sararom_cafeterias.add(new sararom_Marker(new LatLng(31.958383, 35.181022),"كافيتيريا العلوم"));


        /*** Parking ***/
        sararom_parking.add(new sararom_Marker(new LatLng(31.962667, 35.184850),"موقف السيارات الشرقي "));
        sararom_parking.add(new sararom_Marker(new LatLng(31.958616, 35.178696),"موقف السيارات الغربي "));
        sararom_parking.add(new sararom_Marker(new LatLng(31.958765, 35.180719),"موقف سيارات العاملين في كلية الهندسة"));
        sararom_parking.add(new sararom_Marker(new LatLng(31.961377, 35.184187),"موقف سيارات العاملين في كلية هندسة المعلومات"));
        sararom_parking.add(new sararom_Marker(new LatLng(31.957983, 35.182822),"موقف سيارات العاملين في كلية الاعمال والاقتصاد"));
        sararom_parking.add(new sararom_Marker(new LatLng(31.958551, 35.180840),"موقف سيارات العاملين في كلية العلوم"));
        sararom_parking.add(new sararom_Marker(new LatLng(31.959909, 35.182829),"موقف سيارات كلية الحقوق"));
        sararom_parking.add(new sararom_Marker(new LatLng(31.962421, 35.184133),"موقف سيارات مسرح نسيب شاهين"));

        /*** Libraries ***/
        sararom_libraries.add(new sararom_Marker(new LatLng(31.958794, 35.182615),"المكتبة الرئيسية"));
        sararom_libraries.add(new sararom_Marker(new LatLng(31.959283, 35.182418),"مكتبة معهد الحقوق"));
        sararom_libraries.add(new sararom_Marker(new LatLng(31.961101, 35.183122),"مكتبة دراسات المراة"));
        sararom_libraries.add(new sararom_Marker(new LatLng(31.961070, 35.183508),"مكتبة مبنى سعيد خوري للتنمية"));

        /*** Clinic ***/
        sararom_clinic.add(new sararom_Marker(new LatLng(31.958389, 35.181986),"عيادة الجامعة"));

        /*** Faculties ***/
        sararom_faculties.add(new sararom_Marker(new LatLng(31.959262, 35.180574),"كلية الهندسة"));
        sararom_faculties.add(new sararom_Marker(new LatLng(31.959865, 35.180884),"مشاغل الهندسة"));
        sararom_faculties.add(new sararom_Marker(new LatLng(31.961170, 35.181763),"كلية الرياضة"));
        sararom_faculties.add(new sararom_Marker(new LatLng(31.958452, 35.180899),"كلية العلوم"));
        sararom_faculties.add(new sararom_Marker(new LatLng(31.958234, 35.181279),"ملحق العلوم"));
        sararom_faculties.add(new sararom_Marker(new LatLng(31.958190, 35.180918),"مبنى سمير عبد الهادي للرياضيات"));
        sararom_faculties.add(new sararom_Marker(new LatLng(31.958307, 35.183109),"كلية الاعمال والاقتصاد"));
        sararom_faculties.add(new sararom_Marker(new LatLng(31.958301, 35.183787),"مبنى عمر عبد الهادي "));
        sararom_faculties.add(new sararom_Marker(new LatLng(31.962105, 35.182089),"كلية الفنون"));
        sararom_faculties.add(new sararom_Marker(new LatLng(31.962147, 35.183019),"كلية الصيدلة و التغذية والتمريض"));
        sararom_faculties.add(new sararom_Marker(new LatLng(31.962136, 35.183979),"مسرح نسيب شاهين"));
        sararom_faculties.add(new sararom_Marker(new LatLng(31.961427, 35.184694),"كلية تكنولوجيا المعلومات"));
        sararom_faculties.add(new sararom_Marker(new LatLng(31.961027, 35.184348),"مبنى نجاد زعني"));
        sararom_faculties.add(new sararom_Marker(new LatLng(31.960619, 35.183635),"كلية التربية"));
        sararom_faculties.add(new sararom_Marker(new LatLng(31.960196, 35.183085),"كلية الحقوق"));
        sararom_faculties.add(new sararom_Marker(new LatLng(31.959277, 35.182463),"معهد الحقوق"));
        sararom_faculties.add(new sararom_Marker(new LatLng(31.959356, 35.183136),"مبنى رئاسة الجامعة"));
        sararom_faculties.add(new sararom_Marker(new LatLng(31.959088, 35.181570),"مبنى كمال ناصر"));
        sararom_faculties.add(new sararom_Marker(new LatLng(31.958858, 35.181934),"مركز ريتاج"));
        sararom_faculties.add(new sararom_Marker(new LatLng(31.959711, 35.181894),"مبنى ادارة شؤون الطلبة"));
        sararom_faculties.add(new sararom_Marker(new LatLng(31.959090, 35.181251),"مبنى جابي البرامكي"));
        sararom_faculties.add(new sararom_Marker(new LatLng(31.960796, 35.182782),"كلية الاداب"));
        sararom_faculties.add(new sararom_Marker(new LatLng(31.961157, 35.183018),"معهد دراسات المراة"));
        sararom_faculties.add(new sararom_Marker(new LatLng(31.961532, 35.183636),"كلية الصحافة والاعلام"));
        sararom_faculties.add(new sararom_Marker(new LatLng(31.961013, 35.183426),"مبنى سعيد خوري للتنمية"));
        sararom_faculties.add(new sararom_Marker(new LatLng(31.958335, 35.182582),"مبنى نسيب عزيز شاهين للدراسات العليا"));
        sararom_faculties.add(new sararom_Marker(new LatLng(31.961576, 35.182348),"مبنى شوقي شاهين"));
    }

    private BitmapDescriptor bitmapDescriptorFromVector(Context context, int vectorResId) {
        Drawable vectorDrawable = ContextCompat.getDrawable(context, vectorResId);
        vectorDrawable.setBounds(0, 0, vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight());
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        vectorDrawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }


}
