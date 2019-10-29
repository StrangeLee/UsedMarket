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

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class AddProductFragment extends Fragment {
    private static final int PICK_FROM_ALBUM = 1;


    Spinner categorySpinner;

    EditText addProductName, addPrice, addDescription, addHashTag;
    Button addButton, addPicture;
    ListView imageListView;
    ImageView addImageView;
    int imageIndex = 0;

    File tempFile;
    File file;
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

        List<String> addString = new ArrayList<>();

        addString.add(addProductName.getText().toString());
        addString.add(addPrice.getText().toString());
        addString.add(addDescription.getText().toString());

        addButton.setOnClickListener(e -> {

            //----------------------------------------------------------------------------------------------------------
            // data send to server
            manager = TokenManager.getInstance(getActivity().getApplicationContext());

            // 예외처리
//            for (int i = 0 ; i < addString.size(); i ++) {
//                if (addString.get(i).equals("") || imageList[0] == null) {
//                    Toast.makeText(getActivity(), "입력하지 않은 항목이 있거나 사진을 추가해주세요.", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//            }

            // 인터페이스 인자값 생성
            RequestBody reqImage = RequestBody.create(MediaType.parse("image/*"), file);
            MultipartBody.Part part = MultipartBody.Part.createFormData("image", file.getName(), reqImage);

            RequestBody productName = RequestBody.create(MediaType.parse("text/plain"), addProductName.getText().toString());
            RequestBody description = RequestBody.create(MediaType.parse("text/plain"), addDescription.getText().toString());
            RequestBody hashTag = RequestBody.create(MediaType.parse("text/plain"), addHashTag.getText().toString());
            RequestBody category = RequestBody.create(MediaType.parse("text/plain"), categorySpinner.getSelectedItem().toString());
            RequestBody price = RequestBody.create(MediaType.parse("text/plain"), addPrice.getText().toString());

            Call<AddProduct> addProduct = service.postProductApply(manager.getToken().getToken(), productName,
                    description, price, hashTag, category, part);

            addProduct.enqueue(new Callback<AddProduct>() {
                @Override
                public void onResponse(Call<AddProduct> call, Response<AddProduct> response) {
                    Log.d("TAG", "상품 추가 성공");
                    //Intent intent = new Intent(v.getContext(), Ma);
                }

                @Override
                public void onFailure(Call<AddProduct> call, Throwable t) {
                    Log.d("TAG", t.toString());
                    Log.d("TAG", "상품 추가 실패");
                }
            });
        });

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
        Log.d("LOG", tempFile.getAbsolutePath()); // -> 파일 경로
        if (imageIndex > 3) {
            Toast.makeText(getActivity(), "이미지는 최대 3개까지만 등록할 수 있습니다.", Toast.LENGTH_LONG);
            imageIndex = 0;
        } else {
            imageList[imageIndex] = bitmap;
            Log.d("TAG", imageIndex + "");
        }

        file = new File(tempFile.getAbsolutePath());
        // Create a request body with file and image media type

        addImageView.setImageBitmap(imageList[imageIndex]);
        imageIndex++;

    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }


}
