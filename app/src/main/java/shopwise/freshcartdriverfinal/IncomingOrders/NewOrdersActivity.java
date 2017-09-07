package shopwise.freshcartdriverfinal.IncomingOrders;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import shopwise.freshcartdriverfinal.Orders.OrderList;
import shopwise.freshcartdriverfinal.Orders.Orders;
import shopwise.freshcartdriverfinal.R;

public class NewOrdersActivity extends AppCompatActivity {


    private TextView ord_CustName;

    ListView ord_ListView;
    DatabaseReference mOrdersDatabase;
    List<Orders> orders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_orders);

        ord_CustName = (TextView) findViewById(R.id.ord_CustName);
        orders = new ArrayList<>();
        ord_ListView = (ListView) findViewById(R.id.ord_ListView);

        mOrdersDatabase = FirebaseDatabase.getInstance().getReference("Orders");


    }

    @Override
    protected void onStart() {
        super.onStart();

        mOrdersDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                orders.clear();

                for(DataSnapshot orderSnapshot : dataSnapshot.getChildren()){
                    Orders order = orderSnapshot.getValue(Orders.class);
                    orders.add(order);
                }

                OrderList orderListAdapter = new OrderList(NewOrdersActivity.this, orders);
                ord_ListView.setAdapter(orderListAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
