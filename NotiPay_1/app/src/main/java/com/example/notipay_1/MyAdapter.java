package com.example.notipay_1;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter{

    public String str="1";
    public int account;


    /* 아이템을 세트로 담기 위한 어레이 */
    private ArrayList<MyItem> mItems = new ArrayList<>();

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public MyItem getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final Context context = parent.getContext();

        /* 'listview_custom' Layout을 inflate하여 convertView 참조 획득 */
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_custom, parent, false);
        }

        /* 'listview_custom'에 정의된 위젯에 대한 참조 획득 */
        ImageView iv_img = (ImageView) convertView.findViewById(R.id.img) ;
        TextView tv_name = (TextView) convertView.findViewById(R.id.name) ;
        final TextView tv_account = (TextView) convertView.findViewById(R.id.account) ;
        final TextView tv_price = (TextView)convertView.findViewById(R.id.price);
        Button bt_plus = (Button)convertView.findViewById(R.id.plus);
        Button bt_minus = (Button)convertView.findViewById(R.id.minus);

        /* 각 리스트에 뿌려줄 아이템을 받아오는데 mMyItem 재활용 */
        final MyItem myItem = getItem(position);


        /* 각 위젯에 세팅된 아이템을 뿌려준다 */
        iv_img.setImageDrawable(myItem.getIcon());
        tv_name.setText(myItem.getName());
        tv_price.setText(myItem.getPrice());
        tv_account.setText(String.valueOf(myItem.getAccount()));

        /* (위젯에 대한 이벤트리스너를 지정하고 싶다면 여기에 작성하면된다..)  */
        bt_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int account_i = mItems.get(position).getAccount();
                account_i++;
                tv_account.setText(String.valueOf(account_i));
                mItems.get(position).getAccount();
                mItems.get(position).setAccount(account_i);

            }
        });

        bt_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int account_i = mItems.get(position).getAccount();
                if(account_i>=1){
                    account_i--;
                    tv_account.setText(String.valueOf(account_i));
                    mItems.get(position).setAccount(account_i);
                }
            }
        });

        return convertView;


    }

    /* 아이템 데이터 추가를 위한 함수. 자신이 원하는대로 작성 */
    public void addItem(Drawable img, String name, String price) {

        MyItem mItem = new MyItem();

        account=1;

        /* MyItem에 아이템을 setting한다. */
        mItem.setIcon(img);
        mItem.setName(name);
        mItem.setPrice(price);
        mItem.setAccount(account);


        /* mItems에 MyItem을 추가한다. */
        mItems.add(mItem);

    }


}