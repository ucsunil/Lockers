package com.ebay.lockers.adapters;

import android.content.Context;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.ebay.lockers.R;
import com.ebay.lockers.models.HomePageItem;
import com.ebay.lockers.views.ActivityHome;
import com.ebay.lockers.views.fragments.dialogs.BuyItemDialog;
import com.ebay.lockers.views.fragments.dialogs.ExchangeDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sunil on 6/27/2016.
 */
public class HomePageItemsAdapter extends RecyclerView.Adapter<HomePageItemsAdapter.HomePageItemViewHolder> {

    private Context context;
    private List<HomePageItem> items;

    public HomePageItemsAdapter(Context context, List<HomePageItem> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public HomePageItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_page_item_view, parent, false);
        HomePageItemViewHolder viewHolder = new HomePageItemViewHolder(layout);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final HomePageItemViewHolder holder, int position) {
        holder.image.setImageResource(R.mipmap.ic_launcher);
        holder.imageDescription.setText("Item " + position + "\nDescription for item " + position);
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class HomePageItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView image;
        private TextView imageDescription;
        private ImageView favorite;
        private ImageView exchange;
        private ImageView buy;
        private ListView listView;
        private EditText comment;
        ArrayAdapter<String> adapter;

        public HomePageItemViewHolder(View view) {
            super(view);

            image = (ImageView) view.findViewById(R.id.itemCover);
            imageDescription = (TextView) view.findViewById(R.id.itemDetails);
            favorite = (ImageView) view.findViewById(R.id.favorite);
            exchange = (ImageView) view.findViewById(R.id.exchange);
            buy = (ImageView) view.findViewById(R.id.buy);
            listView = (ListView) view.findViewById(R.id.commentsList);
            comment = (EditText) view.findViewById(R.id.comment);

            buy.setOnClickListener(this);
            exchange.setOnClickListener(this);
            comment.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if(s.toString().trim().length() > 0) {
                        comment.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_send_black_24dp, 0);
                        comment.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {
                                final int DRAWABLE_LEFT = 0;
                                final int DRAWABLE_TOP = 1;
                                final int DRAWABLE_RIGHT = 2;
                                final int DRAWABLE_BOTTOM = 3;

                                if(event.getAction() == MotionEvent.ACTION_UP) {
                                    if(event.getRawX() >= comment.getRight() - comment.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width()) {
                                        // Means the send button was clicked
                                        // Send the comment and set the text to empty
                                        // TO DO - Send Comment
                                        adapter.add(comment.getText().toString().trim());
                                        comment.setText("");

                                        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                                        imm.hideSoftInputFromInputMethod(comment.getWindowToken(), 0);
                                        return true;
                                    }
                                }
                                return false;
                            }
                        });
                    } else {
                        comment.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                        comment.setOnTouchListener(null);
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }

            });
            loadSimpleComments();
        }

        @Override
        public void onClick(View v) {
            switch(v.getId()) {
                case R.id.exchange:
                    DialogFragment exchangeDialog = new ExchangeDialog();
                    exchangeDialog.show(((ActivityHome) context).getSupportFragmentManager(), "exchangeDialog");
                    break;
                case R.id.buy:
                    DialogFragment buyDialog = new BuyItemDialog();
                    buyDialog.show(((ActivityHome) context).getSupportFragmentManager(), "buyDialog");
                    break;
            }
        }

        private void loadSimpleComments() {
            List<String> comments = new ArrayList<String>();
            comments.add("This is comment 1");
            comments.add("This is comment 2");
            comments.add("This is comment 3");

            adapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, comments);
            listView.setAdapter(adapter);
        }
    }
}
