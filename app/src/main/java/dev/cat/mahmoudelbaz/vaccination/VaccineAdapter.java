package dev.cat.mahmoudelbaz.vaccination;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;

/**
 * Created by mahmoudelbaz on 1/18/18.
 */

public class VaccineAdapter extends BaseAdapter {

    private final Context context;
    private final Boolean fromHome;
    private final String fromTab;
    private ArrayList<Vaccine_item> feedItems;
    private ArrayList<Vaccine_item> tempItems;

    public VaccineAdapter(String fromTab, Boolean fromHome, Context cx, ArrayList<Vaccine_item> feedItems) {
        this.context = cx;
        this.feedItems = feedItems;
        this.fromTab = fromTab;
        this.fromHome = fromHome;
        this.tempItems = feedItems;
    }


    @Override
    public int getCount() {
        return feedItems.size();
    }

    @Override
    public Vaccine_item getItem(int i) {
        return feedItems.get(i);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        VaccineAdapter.ViewHolder viewHolder = null;
        if (convertView == null) {
            LayoutInflater li = LayoutInflater.from(context);
            convertView = li.inflate(R.layout.vaccine_item, parent, false);
            viewHolder = new VaccineAdapter.ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (VaccineAdapter.ViewHolder) convertView.getTag();
        }

        Vaccine_item feedItem = getItem(position);
        if (viewHolder != null) {
            viewHolder.setItem(feedItem);
        }

        return convertView;
    }


    class ViewHolder {

        private TextView nameView;
        private View mainView;
        private ImageView imageView;
        private ImageView images;

        public ViewHolder(View convertView) {
            nameView = (TextView) convertView.findViewById(R.id.vaccineTitle);
            mainView = (View) convertView.findViewById(R.id.mainView);
            imageView = (ImageView) convertView.findViewById(R.id.imageVaccine);
            images = (ImageView) convertView.findViewById(R.id.images);
            convertView.setTag(this);
        }

        void setItem(Vaccine_item vaccine) {
            if (vaccine.getName().contains("Inactivated")) {
                if (fromTab.equals("Heart Diseases") || fromTab.equals("Diabetes")) {
                    images.setBackgroundResource(R.drawable.inactivated);
                }
                if (fromTab.equals("Lung Diseases") || fromTab.contains("Patients")) {
                    images.setBackgroundResource(R.drawable.inactivated_blue);
                }
                if (fromTab.equals("Kidney Diseases") || fromTab.equals("HIV")) {
                    images.setBackgroundResource(R.drawable.inactivated_white);
                }
                if (fromTab.equals("Chronic Liver Diseases")) {
                    images.setBackgroundResource(R.drawable.inactivated_green);
                }
                if (fromTab.equals("Aged People Over 65 Years")) {
                    images.setBackgroundResource(R.drawable.inactivated_burble);
                }
            }
            if (vaccine.getName().contains("Hepatitis A")) {
                if (fromTab.equals("Heart Diseases") || fromTab.equals("Diabetes")) {
                    images.setBackgroundResource(R.drawable.hepatitis_a_red);
                }
                if (fromTab.equals("Lung Diseases") || fromTab.contains("Patients")) {
                    images.setBackgroundResource(R.drawable.hepatitis_a_blue);
                }
                if (fromTab.equals("Kidney Diseases") || fromTab.equals("HIV")) {
                    images.setBackgroundResource(R.drawable.hepatitis_a_white);
                }
                if (fromTab.equals("Chronic Liver Diseases")) {
                    images.setBackgroundResource(R.drawable.hepatitis_a_green);
                }
                if (fromTab.equals("Aged People Over 65 Years")) {
                    images.setBackgroundResource(R.drawable.hepatitis_a_burble);
                }
            }
            if (vaccine.getName().contains("Hepatitis B")) {
                if (fromTab.equals("Heart Diseases") || fromTab.equals("Diabetes")) {
                    images.setBackgroundResource(R.drawable.hepatitis_b_red);
                }
                if (fromTab.equals("Lung Diseases") || fromTab.contains("Patients")) {
                    images.setBackgroundResource(R.drawable.hepatitis_b_blue);
                }
                if (fromTab.equals("Kidney Diseases") || fromTab.equals("HIV")) {
                    images.setBackgroundResource(R.drawable.hepatitis_b_white);
                }
                if (fromTab.equals("Chronic Liver Diseases")) {
                    images.setBackgroundResource(R.drawable.hepatitis_b_green);
                }
                if (fromTab.equals("Aged People Over 65 Years")) {
                    images.setBackgroundResource(R.drawable.hepatitis_b_burble);
                }
            }
            if (vaccine.getName().contains("Tetanus")) {
                if (fromTab.equals("Heart Diseases") || fromTab.equals("Diabetes")) {
                    images.setBackgroundResource(R.drawable.tat_white);
                }
                if (fromTab.equals("Lung Diseases") || fromTab.contains("Patients")) {
                    images.setBackgroundResource(R.drawable.tat_blue);
                }
                if (fromTab.equals("Kidney Diseases") || fromTab.equals("HIV")) {
                    images.setBackgroundResource(R.drawable.tat_red);
                }
                if (fromTab.equals("Chronic Liver Diseases")) {
                    images.setBackgroundResource(R.drawable.tat_green);
                }
                if (fromTab.equals("Aged People Over 65 Years")) {
                    images.setBackgroundResource(R.drawable.tat_burble);
                }
            }
            if (vaccine.getName().contains("Pneumococcal conjugate")) {
                if (fromTab.equals("Heart Diseases") || fromTab.equals("Diabetes")) {
                    images.setBackgroundResource(R.drawable.pneumococcal_red);
                }
                if (fromTab.equals("Lung Diseases") || fromTab.contains("Patients")) {
                    images.setBackgroundResource(R.drawable.pneumococcal_blue);
                }
                if (fromTab.equals("Kidney Diseases") || fromTab.equals("HIV")) {
                    images.setBackgroundResource(R.drawable.pneumococcal_white);
                }
                if (fromTab.equals("Chronic Liver Diseases")) {
                    images.setBackgroundResource(R.drawable.pneumococcal_green);
                }
                if (fromTab.equals("Aged People Over 65 Years")) {
                    images.setBackgroundResource(R.drawable.pneumococcal_burble);
                }
            }
            if (vaccine.getName().contains("Pneumococcal polysaccharide")) {
                if (fromTab.equals("Heart Diseases") || fromTab.equals("Diabetes")) {
                    images.setBackgroundResource(R.drawable.pneumococcal2_red);
                }
                if (fromTab.equals("Lung Diseases") || fromTab.contains("Patients")) {
                    images.setBackgroundResource(R.drawable.pneumococcal2_blue);
                }
                if (fromTab.equals("Kidney Diseases") || fromTab.equals("HIV")) {
                    images.setBackgroundResource(R.drawable.pneumococcal2_white);
                }
                if (fromTab.equals("Chronic Liver Diseases")) {
                    images.setBackgroundResource(R.drawable.pneumococcal2_green);
                }
                if (fromTab.equals("Aged People Over 65 Years")) {
                    images.setBackgroundResource(R.drawable.pneumococcal2_burble);
                }
            }
            if (vaccine.getName().contains("MMR")) {
                if (fromTab.equals("Heart Diseases") || fromTab.equals("Diabetes")) {
                    images.setBackgroundResource(R.drawable.mmr_red);
                }
                if (fromTab.equals("Lung Diseases") || fromTab.contains("Patients")) {
                    images.setBackgroundResource(R.drawable.mmr_blue);
                }
                if (fromTab.equals("Kidney Diseases") || fromTab.equals("HIV")) {
                    images.setBackgroundResource(R.drawable.mmr_white);
                }
                if (fromTab.equals("Chronic Liver Diseases")) {
                    images.setBackgroundResource(R.drawable.mmr_green);
                }
                if (fromTab.equals("Aged People Over 65 Years")) {
                    images.setBackgroundResource(R.drawable.mmr_burble);
                }
            }
            if (vaccine.getName().contains("Zoster")) {
                if (fromTab.equals("Heart Diseases") || fromTab.equals("Diabetes")) {
                    images.setBackgroundResource(R.drawable.zoster);
                }
                if (fromTab.equals("Lung Diseases") || fromTab.contains("Patients")) {
                    images.setBackgroundResource(R.drawable.zoster_blue);
                }
                if (fromTab.equals("Kidney Diseases") || fromTab.equals("HIV")) {
                    images.setBackgroundResource(R.drawable.zoster_white);
                }
                if (fromTab.equals("Chronic Liver Diseases")) {
                    images.setBackgroundResource(R.drawable.zoster_green);
                }
                if (fromTab.equals("Aged People Over 65 Years")) {
                    images.setBackgroundResource(R.drawable.zoster_burble);
                }
            }
            if (vaccine.getName().contains("Varicella")) {
                if (fromTab.equals("Heart Diseases") || fromTab.equals("Diabetes")) {
                    images.setBackgroundResource(R.drawable.varicella);
                }
                if (fromTab.equals("Lung Diseases") || fromTab.contains("Patients")) {
                    images.setBackgroundResource(R.drawable.varicella_blue);
                }
                if (fromTab.equals("Kidney Diseases") || fromTab.equals("HIV")) {
                    images.setBackgroundResource(R.drawable.varicella_white);
                }
                if (fromTab.equals("Chronic Liver Diseases")) {
                    images.setBackgroundResource(R.drawable.varicella_green);
                }
                if (fromTab.equals("Aged People Over 65 Years")) {
                    images.setBackgroundResource(R.drawable.varicella_burble);
                }
            }
            if (vaccine.getName().contains("Humanpapilloma")) {
                if (fromTab.equals("Heart Diseases") || fromTab.equals("Diabetes")) {
                    images.setBackgroundResource(R.drawable.humanpapilloma_red);
                }
                if (fromTab.equals("Lung Diseases") || fromTab.contains("Patients")) {
                    images.setBackgroundResource(R.drawable.humanpapilloma_blue);
                }
                if (fromTab.equals("Kidney Diseases") || fromTab.equals("HIV")) {
                    images.setBackgroundResource(R.drawable.humanpapilloma_white);
                }
                if (fromTab.equals("Chronic Liver Diseases")) {
                    images.setBackgroundResource(R.drawable.humanpapilloma_green);
                }
                if (fromTab.equals("Aged People Over 65 Years")) {
                    images.setBackgroundResource(R.drawable.humanpapilloma_burble);
                }
            }


            if (fromHome) {
                images.setVisibility(View.GONE);
                imageView.setVisibility(View.VISIBLE);
                Picasso.with(context).load(vaccine.getImage()).fit().centerCrop().into(imageView);

            } else {
                images.setVisibility(View.VISIBLE);
                imageView.setVisibility(View.GONE);
                Picasso.with(context).load(vaccine.getImage()).into(new Target() {
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            mainView.setBackground(new BitmapDrawable(bitmap));
                        }
                    }

                    @Override
                    public void onBitmapFailed(Drawable errorDrawable) {

                    }

                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {

                    }
                });
            }

            nameView.setText(Html.fromHtml(vaccine.getName()));
            nameView.setTextColor(vaccine.getColorId());


        }

    }
}

