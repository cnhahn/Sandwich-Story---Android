package thesandwichguys.sandwichstory;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;


public class ingredientListAdapter extends ArrayAdapter<Ingredients>{
    private Context mContext;
    int mResource;
    ArrayList<Ingredients> ingredients;

    public ingredientListAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull ArrayList<Ingredients> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mResource = resource;
        this.ingredients = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LinearLayout view;

        if (convertView == null) {
            view = new LinearLayout(getContext());
            LayoutInflater vi = (LayoutInflater)
                    getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            vi.inflate(mResource,  view, true);
        } else {
            view = (LinearLayout) convertView;
        }

        String name = getItem(position).getIngredientName();
        String qty = getItem(position).getQty();
        String unit = getItem(position).getUnit();

        Button delete_button = (Button) view.findViewById(R.id.delete_button);
        delete_button.setTag(new Integer(position+1));

        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = v.getTag().toString();
                int i = Integer.parseInt(s);
                i = i-1;
                ingredients.remove(i);
                notifyDataSetChanged();
            }
        });

        TextView name_tv = (TextView) view.findViewById(R.id.name_tv);
        TextView qty_tv = (TextView) view.findViewById(R.id.qty_tv);
        TextView unit_tv = (TextView) view.findViewById(R.id.measurement_tv);

        name_tv.setText(name);
        qty_tv.setText(qty);
        unit_tv.setText(unit);

        return view;
    }
}