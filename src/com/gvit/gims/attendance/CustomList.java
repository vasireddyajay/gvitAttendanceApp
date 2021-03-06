/**
 * 
 */
package com.gvit.gims.attendance;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @author Ajaykumar Vasireddy
 * @version 0.1
 * @since 2014
 */
public class CustomList extends ArrayAdapter<Student> {
	private final Activity context;
	private final Student[] web;
	private final Integer imageId;

	public CustomList(Activity context, Student[] web, Integer imageId) {
		super(context, R.layout.student_list_row, web);
		this.context = context;
		this.web = web;
		this.imageId = imageId;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		LayoutInflater inflater = context.getLayoutInflater();
		View rowView = inflater
				.inflate(R.layout.student_list_row, null, true);
		ImageView imageView = (ImageView) rowView.findViewById(R.id.image);
		TextView studentId = (TextView) rowView.findViewById(R.id.studentsID);
		studentId.setText(web[position].regno);
		TextView firstName = (TextView) rowView.findViewById(R.id.firstName);
		firstName.setText(web[position].firstName);
		imageView.setImageResource(imageId);
		return rowView;
	}
}
