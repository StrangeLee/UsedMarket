package com.wedontanything.usedmarket.Fragment;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.wedontanything.usedmarket.Activity.Basic;
import com.wedontanything.usedmarket.Adapter.AddImageAdapter;
import com.wedontanything.usedmarket.DataBase.TokenManager;
import com.wedontanything.usedmarket.Interface.ProductService;
import com.wedontanything.usedmarket.Product.AddProduct;
import com.wedontanything.usedmarket.Product.TestResponse;
import com.wedontanything.usedmarket.R;
import com.wedontanything.usedmarket.Utils;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Currency;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AddProductFragment extends Fragment {
    private static final int PICK_FROM_ALBUM = 1;


    Spinner categorySpinner;

    EditText addProductName, addPrice, addDescription, addHashTag;
    Button addButton, addPicture;
    ListView imageListView;
    ImageView addImageView;
    int imageIndex = 0;

    File tempFile;
    //List<Bitmap> imageList = new ArrayList<>();
    Bitmap [] imageList = new Bitmap[3];

    ProductService service = Utils.RETROFIT.create(ProductService.class);
    TokenManager manager;

    private OnFragmentInteractionListener mListener;

    public AddProductFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_add_product, container, false);

        //----------------------------------------------------------------------------------------------------------
        // spinner
        categorySpinner = v.findViewById(R.id.addSpinnerCategory);
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this.getActivity(),
                android.R.layout.simple_spinner_item,
                getResources().getStringArray(R.array.spinnerArray));
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        categorySpinner.setAdapter(spinnerAdapter);
        //----------------------------------------------------------------------------------------------------------
        // 항목 null 체크
        addButton = v.findViewById(R.id.addButtonCommit);
        addProductName = v.findViewById(R.id.addEditProductName);
        addPrice = v.findViewById(R.id.addEditProductPrice);
        addDescription = v.findViewById(R.id.addEditProductContents);
        addHashTag = v.findViewById(R.id.addEditHashTag);
        addPicture = v.findViewById(R.id.addButtonImage);
        addImageView = v.findViewById(R.id.addImageProductImage);
        //imageListView = v.findViewById(R.id.addImageList);

        // image 클릭 처리
        //addImageView.setOnClickListener(e -> setImage());

        Basic basic = new Basic();
        List<String> addString = new ArrayList<>();

//        addButton.setOnClickListener(e -> {
//            for (int i = 0 ; i < 5; i ++) {
//                Log.d("add", "" + addString.size());
////                if (addString.get(i).isEmpty()) {
////                    basic.showDialog(getActivity(), "Message", addString.get(i));
////                    return;
////                }
//            }
        addButton.setOnClickListener(e -> {
            int price = 0;

            price = Integer.parseInt(addPrice.getText().toString());
            //----------------------------------------------------------------------------------------------------------
            // data send to server
            manager = TokenManager.getInstance(getActivity().getApplicationContext());

            Call<AddProduct> addProduct = service.postProductApply(manager.getToken().getToken(), addProductName.getText().toString(),
                    addDescription.getText().toString(), price, addHashTag.getText().toString(), categorySpinner.getSelectedItem().toString(), "");


            addProduct.enqueue(new Callback<AddProduct>() {
                @Override
                public void onResponse(Call<AddProduct> call, Response<AddProduct> response) {
                    Log.d("TAG", "상품 추가 성공");
                }

                @Override
                public void onFailure(Call<AddProduct> call, Throwable t) {
                    Log.d("TAG", t.toString());
                    Log.d("TAG", "상품 추가 실패");
                }
            });
        });


        //});

        addPicture.setOnClickListener(e -> {
            getPermission();
        });

        return v;
    }

    private void getPermission() {
        PermissionListener permissionListener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                Log.d("Log", "권한 요청 성공");
                goToAlbume();
            }

            @Override
            public void onPermissionDenied(ArrayList<String> deniedPermissions) {

            }
        };

        TedPermission.with(getActivity())
                .setPermissionListener(permissionListener)
                .setRationaleMessage(getResources().getString(R.string.permission2))
                .setDeniedMessage(getResources().getString(R.string.permission1))
                .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
                .check();
    }

    private void goToAlbume() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
        startActivityForResult(intent, PICK_FROM_ALBUM);
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_FROM_ALBUM) {
            Uri photoUri = data.getData();

            Cursor cursor = null;
            try {
                String [] proj = { MediaStore.Images.Media.DATA};
                assert photoUri != null;
                cursor = getContext().getContentResolver().query(photoUri, proj, null, null, null);

                assert cursor != null;
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                cursor.moveToFirst();

                tempFile = new File(cursor.getString(column_index));

            } catch (Exception e) {
                Log.d("TAG", e.getMessage() + "");
            } finally {
                if (cursor != null) {
                    cursor.close();
                }
            }

            setImage();

        }
    }

    private void setImage() {
        BitmapFactory.Options options = new BitmapFactory.Options();
        Bitmap bitmap = BitmapFactory.decodeFile(tempFile.getAbsolutePath(), options);
        if (imageIndex > 3) {
            Toast.makeText(getActivity(), "이미지는 최대 3개까지만 등록할 수 있습니다.", Toast.LENGTH_LONG);
            imageIndex = 0;
        } else {
            imageList[imageIndex] = bitmap;
            Log.d("TAG", imageIndex + "");
        }
//        AddImageAdapter addImageAdapter = new AddImageAdapter(imageList);
//        imageListView.setAdapter(addImageAdapter);
//        addImageAdapter.notifyDataSetChanged();
        addImageView.setImageBitmap(imageList[imageIndex]);
        imageIndex++;

    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
