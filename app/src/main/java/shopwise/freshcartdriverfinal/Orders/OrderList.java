package shopwise.freshcartdriverfinal.Orders;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import shopwise.freshcartdriverfinal.R;

/**
 * Created by macbook on 06/09/2017.
 */

public class OrderList extends ArrayAdapter<Orders> {

    private Activity context;
    private List<Orders> ordersList;

    public OrderList(Activity context, List<Orders> ordersList){
        super(context, R.layout.order_layout, ordersList);
        this.context = context;
        this.ordersList = ordersList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View OrdersViewItem = inflater.inflate(R.layout.order_layout, null, true);

        TextView prodName = (TextView) OrdersViewItem.findViewById(R.id.ord_CustName);

        Orders order = ordersList.get(position);
        prodName.setText(order.getProdName());

        return OrdersViewItem;
    }
}
