package com.example.roy1473.recyclerviewexample;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.roy1473.recyclerviewexample.CustomAdapter.CustomAdapter;
import com.example.roy1473.recyclerviewexample.Interface.OnItemClickListener;
import com.example.roy1473.recyclerviewexample.Models.Shop;
import com.example.roy1473.recyclerviewexample.Models.ShopData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainFragment extends Fragment{

    RecyclerView mRecyclerView;
    private InternetManager rManager;
    CustomAdapter adapter;
    private List<Shop> shopList;
    private List<Shop> shops;


    public MainFragment(){

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rManager = new InternetManager();
        getGourmetData();
        mRecyclerView = (RecyclerView)view.findViewById(R.id.recycleView);





    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {


        return inflater.inflate(R.layout.activity_main_fragment, container, false);
    }

    private void bindViews() {
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        adapter = new CustomAdapter(shops, R.layout.activity_custom_items, getActivity());
        mRecyclerView.setAdapter(adapter);
        adapter.setmClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                DetailActivity detailFragment = new DetailActivity();
                Shop shopInfo = shops.get(position);
                Bundle bundle = new Bundle();
                bundle.putString("title", shopInfo.getName());
                bundle.putString("address", shopInfo.getAddress());
                bundle.putString("catches", shopInfo.getCatch());
                bundle.putString("access", shopInfo.getAccess());
                bundle.putString("open", shopInfo.getOpen());
                bundle.putString("close", shopInfo.getClose());
                bundle.putString("wifi", shopInfo.getWifi());
                bundle.putString("private", shopInfo.getPrivateRoom());
                bundle.putString("card", shopInfo.getCard());
                bundle.putString("about", shopInfo.getOtherMemo());
                bundle.putString("budget", shopInfo.getBudget().getAverage());
                bundle.putString("photo", shopInfo.getPhoto().getMobile().getL());
                bundle.putString("url", shopInfo.getUrls().getMobile());

                detailFragment.setArguments(bundle);

                FragmentManager fragmentManager = getActivity().getFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.activity_main, detailFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

    }





    private void getGourmetData(){
        String shinsaibashiCode = "Y315";


        String json = "json";
        final Call<ShopData> requestData = rManager.getApiInterface().getShopData("{your_own_key}",
                shinsaibashiCode, json);

        requestData.enqueue(new Callback<ShopData>() {
            @Override
            public void onResponse(Call<ShopData> call, Response<ShopData> response) {
               shopList = response.body().results.getShop();
               shops = shopList;
                bindViews();
            }

            @Override
            public void onFailure(Call<ShopData> call, Throwable t) {
                Log.e("Restaurants","Error" + t.getMessage());
                Toast.makeText(getActivity().getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });



    }




}
