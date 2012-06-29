package se.coffeemachine.adapters;

import se.coffeemachine.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class StatisticsListAdapter extends ArrayAdapter<Integer> {

	private static final String TAG = StatisticsListAdapter.class
			.getSimpleName();

	private final LayoutInflater inflater;

	public StatisticsListAdapter(Context context, Integer[] counters) {
		super(context, R.layout.statistics_list_textview, counters);
		this.inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Holder holder;
		Integer i = getItem(position);
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.statistics_list_textview,
					parent, false);
			holder = new Holder();
			holder.count = (TextView) convertView
					.findViewById(R.id.statistics_list_textview_id);
			convertView.setTag(holder);
		} else {
			holder = (Holder) convertView.getTag();
		}

		int color = ((position % 2) == 0) ? 0xFFF0FFE1 : 0xFFFFFFFF;
		convertView.setBackgroundColor(color);
		holder.count.setText(Integer.toString(i));

		return convertView;
	}

	private class Holder {
		public TextView count;
	}

}
