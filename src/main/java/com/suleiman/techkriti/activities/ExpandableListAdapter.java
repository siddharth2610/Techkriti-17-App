package com.suleiman.techkriti.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.suleiman.techkriti.R;

import java.util.ArrayList;
import java.util.List;

public class ExpandableListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int HEADER = 0;
    public static final int CHILD = 1;
    Intent intent;

    private List<Item> data;

    private LinearLayoutManager linearLayoutManager;

    public ExpandableListAdapter(List<Item> data, LinearLayoutManager linearLayoutManager) {
        this.data = data;this.linearLayoutManager=linearLayoutManager;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int type) {
        View view = null;

        Context context = parent.getContext();
        float dp = context.getResources().getDisplayMetrics().density;


        int subItemPaddingLeft = (int) (18 * dp);
        int subItemPaddingTopAndBottom = (int) (5 * dp);
        switch (type) {
            case HEADER:
                LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.dummy_fragment1, parent, false);
                ListHeaderViewHolder header = new ListHeaderViewHolder(view);
                return header;
            case CHILD:
                LayoutInflater inflater1 = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater1.inflate(R.layout.dummy_fragment2, parent, false);
                ListHeaderViewHolder1 header1 = new ListHeaderViewHolder1(view);
                return header1;
        }
        return null;
    }


    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final Item item = data.get(position);

        switch (item.type) {
            case HEADER:
                final ListHeaderViewHolder itemController = (ListHeaderViewHolder) holder;
                itemController.refferalItem = item;

                itemController.header_title.setText(item.text);

                if (item.invisibleChildren == null) {
                    itemController.btn_expand_toggle.setImageResource(R.drawable.ic_keyboard_arrow_up_black_18dp);
                } else {
                    itemController.btn_expand_toggle.setImageResource(R.drawable.ic_keyboard_arrow_down_black_18dp);
                }
                itemController.btn_expand_toggle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (item.invisibleChildren == null) {

                            item.invisibleChildren = new ArrayList<Item>();
                            int count = 0;
                            int pos = data.indexOf(itemController.refferalItem);

                            while (data.size() > pos + 1 && data.get(pos + 1).type == CHILD) {
                                item.invisibleChildren.add(data.remove(pos + 1));
                                count++;
                            }
                            notifyItemRangeRemoved(pos + 1, count);
                            itemController.btn_expand_toggle.setImageResource(R.drawable.ic_keyboard_arrow_down_black_18dp);
                        } else {
                            int pos = data.indexOf(itemController.refferalItem);
                            linearLayoutManager.scrollToPositionWithOffset(pos, 0);
                            int index = pos + 1;
                            for (Item i : item.invisibleChildren) {
                                data.add(index, i);
                                index++;
                            }
                            notifyItemRangeInserted(pos + 1, index - pos - 1);
                            itemController.btn_expand_toggle.setImageResource(R.drawable.ic_keyboard_arrow_up_black_18dp);
                            item.invisibleChildren = null;
                        }
                    }
                });
                itemController.header_title.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (item.invisibleChildren == null) {
                            item.invisibleChildren = new ArrayList<Item>();
                            int count = 0;
                            int pos = data.indexOf(itemController.refferalItem);

                            while (data.size() > pos + 1 && data.get(pos + 1).type == CHILD) {
                                item.invisibleChildren.add(data.remove(pos + 1));
                                count++;
                            }
                            notifyItemRangeRemoved(pos + 1, count);
                            itemController.btn_expand_toggle.setImageResource(R.drawable.ic_keyboard_arrow_down_black_18dp);
                        } else {
                            int pos = data.indexOf(itemController.refferalItem);
                            linearLayoutManager.scrollToPositionWithOffset(pos, 0);
                            int index = pos + 1;
                            for (Item i : item.invisibleChildren) {
                                data.add(index, i);
                                index++;
                            }

                            notifyItemRangeInserted(pos + 1, index - pos - 1);
                            itemController.btn_expand_toggle.setImageResource(R.drawable.ic_keyboard_arrow_up_black_18dp);
                            item.invisibleChildren = null;
                        }
                    }
                });
                itemController.cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (item.invisibleChildren == null) {
                            item.invisibleChildren = new ArrayList<Item>();
                            int count = 0;
                            int pos = data.indexOf(itemController.refferalItem);


                            while (data.size() > pos + 1 && data.get(pos + 1).type == CHILD) {
                                item.invisibleChildren.add(data.remove(pos + 1));
                                count++;
                            }
                            notifyItemRangeRemoved(pos + 1, count);
                            itemController.btn_expand_toggle.setImageResource(R.drawable.ic_keyboard_arrow_down_black_18dp);
                        } else {
                            int pos = data.indexOf(itemController.refferalItem);
                            linearLayoutManager.scrollToPositionWithOffset(pos, 0);

                            int index = pos + 1;
                            for (Item i : item.invisibleChildren) {
                                data.add(index, i);
                                index++;
                            }

                            notifyItemRangeInserted(pos + 1, index - pos - 1);
                            itemController.btn_expand_toggle.setImageResource(R.drawable.ic_keyboard_arrow_up_black_18dp);
                            item.invisibleChildren = null;
                        }
                    }
                });



                break;
            case CHILD:
                final ListHeaderViewHolder1 itemController1 = (ListHeaderViewHolder1) holder;
                itemController1.refferalItem = data.get(position);
                itemController1.header_title.setText(data.get(position).text);

                /*TextView itemTextView = (TextView) holder.itemView;
                itemTextView.setText(data.get(position).text);*/
                itemController1.header_title.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos = data.indexOf(itemController1.refferalItem);
                      //  int pos = data.indexOf(itemController1.refferalItem);
                        if(data.get(pos).text=="Bridge Design Challenge")
                        {



                        intent=new Intent(v.getContext(),TabsHeaderActivity1.class);
                            intent.putExtra("Title",data.get(pos).text);
                            intent.putExtra("id",pos);
                            intent.putExtra("tabs", 7);
                            intent.putExtra("tabstr1","Home").putExtra("tabstr2", "Event Structure").putExtra("tabstr3","Arena").putExtra("tabstr4","Judging Criteria").putExtra("tabstr5","Scoring").putExtra("tabstr6", "Contacts").putExtra("tabstr7", "Abstract");
                            v.getContext().startActivity(intent);}
                        else if(data.get(pos).text=="Junkyard Wars")
                        {
                            intent=new Intent(v.getContext(),TabsHeaderActivity1.class);
                            intent.putExtra("Title",data.get(pos).text);
                            intent.putExtra("id",pos);
                            intent.putExtra("tabs", 7);
                            intent.putExtra("tabstr1","Home").putExtra("tabstr2", "Event Structure").putExtra("tabstr3","Arena").putExtra("tabstr4","Problem Statement").putExtra("tabstr5","Rules").putExtra("tabstr6", "Abstract").putExtra("tabstr7","Contacts");
                            v.getContext().startActivity(intent);

                        }
                        else if(data.get(pos).text=="Scientoon")
                        {
                            intent=new Intent(v.getContext(),TabsHeaderActivity2.class);
                            intent.putExtra("Title",data.get(pos).text);
                            intent.putExtra("id",pos);
                            intent.putExtra("tabs", 4);
                            intent.putExtra("tabstr1", "Home").putExtra("tabstr2","Event Structure").putExtra("tabstr3","Judging Criteria").putExtra("tabstr4","Contacts");
                            v.getContext().startActivity(intent);

                        }
                        else if(data.get(pos).text=="Techkriti Grand Prix")
                        {
                            intent=new Intent(v.getContext(),TabsHeaderActivity1.class);
                            intent.putExtra("Title",data.get(pos).text);
                            intent.putExtra("id",pos);
                            intent.putExtra("tabs", 8);
                            intent.putExtra("tabstr1","Home").putExtra("tabstr2","Event Structure").putExtra("tabstr3","Arena").putExtra("tabstr4","Problem Statement").putExtra("tabstr5", "Machine Specification").putExtra("tabstr6", "Rules").putExtra("tabstr7","Abstract").putExtra("tabstr8","Contacts");
                            v.getContext().startActivity(intent);

                        }

                        else if(data.get(pos).text=="Concatenate")
                        {
                            intent=new Intent(v.getContext(),TabsHeaderActivity1.class);
                            intent.putExtra("Title",data.get(pos).text);
                            intent.putExtra("id",pos);
                            intent.putExtra("tabs", 7);
                            intent.putExtra("tabstr1", "Home").putExtra("tabstr2","Rules").putExtra("tabstr3","Arena").putExtra("tabstr4", "Problem Statement").putExtra("tabstr5", "Judging").putExtra("tabstr6","Contacts").putExtra("tabstr7","Abstract");
                            v.getContext().startActivity(intent);

                        }
                        else if(data.get(pos).text=="Wild Soccer")
                        {
                            intent=new Intent(v.getContext(),TabsHeaderActivity1.class);
                            intent.putExtra("Title",data.get(pos).text);
                            intent.putExtra("id",pos);
                            intent.putExtra("tabs", 8);
                            intent.putExtra("tabstr1","Home").putExtra("tabstr2","Bot Specifications").putExtra("tabstr3", "Arena").putExtra("tabstr4","Gameplay").putExtra("tabstr5", "Abstract").putExtra("tabstr6", "Rules").putExtra("tabstr7","Point").putExtra("tabstr8","Contacts");
                            v.getContext().startActivity(intent);

                        }
                        else if(data.get(pos).text=="IARC")
                        {
                            intent=new Intent(v.getContext(),TabsHeaderActivity1.class);
                            intent.putExtra("Title",data.get(pos).text);
                            intent.putExtra("id",pos);
                            intent.putExtra("tabs", 10);
                            intent.putExtra("tabstr1","Home").putExtra("tabstr2", "Rules").putExtra("tabstr3", "Arena").putExtra("tabstr4", "Event Structure").putExtra("tabstr5", "Abstract").putExtra("tabstr6","Robot Controls").putExtra("tabstr7","Bot Specifications").putExtra("tabstr8", "Point System").putExtra("tabstr9","Contacts").putExtra("tabstr10","Mission Objective");
                            v.getContext().startActivity(intent);

                        }
                        else if(data.get(pos).text=="Manoeuvre")
                        {
                            intent=new Intent(v.getContext(),TabsHeaderActivity1.class);
                            intent.putExtra("Title",data.get(pos).text);
                            intent.putExtra("id",pos);
                            intent.putExtra("tabs", 10);
                            intent.putExtra("tabstr1","Home").putExtra("tabstr2", "Rules").putExtra("tabstr3", "Arena").putExtra("tabstr4", "Gameplay").putExtra("tabstr5", "Abstract").putExtra("tabstr6", "Robot Controls").putExtra("tabstr7","Bot Specifications").putExtra("tabstr8", "Point System").putExtra("tabstr9","Contacts").putExtra("tabstr10","Event Structure");
                            v.getContext().startActivity(intent);

                        }
                        else if(data.get(pos).text=="IRGT")
                        {
                            intent=new Intent(v.getContext(),TabsHeaderActivity1.class);
                            intent.putExtra("Title",data.get(pos).text);
                            intent.putExtra("id", pos);
                            intent.putExtra("tabs", 10);
                            intent.putExtra("tabstr1","Home").putExtra("tabstr2", "Objective").putExtra("tabstr3", "Arena").putExtra("tabstr4", "Rules").putExtra("tabstr5", "Bot Specifications").putExtra("tabstr6", "Gameplay").putExtra("tabstr7", "Point System").putExtra("tabstr8", "Event Structure").putExtra("tabstr9", "Abstract").putExtra("tabstr10","Contacts");
                            v.getContext().startActivity(intent);

                        }

                        else if(data.get(pos).text=="Electromania")
                        {
                            intent=new Intent(v.getContext(),TabsHeaderActivity2.class);
                            intent.putExtra("Title",data.get(pos).text);
                            intent.putExtra("id",pos);
                            intent.putExtra("tabs", 6);
                            intent.putExtra("tabstr1","Home").putExtra("tabstr2", "Problem Statement").putExtra("tabstr3", "Judgin Criteria").putExtra("tabstr4","Abstract").putExtra("tabstr5", "Rules").putExtra("tabstr6","Contacts");
                            v.getContext().startActivity(intent);

                        }
                        else if(data.get(pos).text=="Embedded")
                        {
                            intent=new Intent(v.getContext(),TabsHeaderActivity2.class);
                            intent.putExtra("Title",data.get(pos).text);
                            intent.putExtra("id",pos);
                            intent.putExtra("tabs", 6);
                            intent.putExtra("tabstr1", "Home").putExtra("tabstr2","Problem Statement").putExtra("tabstr3", "Abstract").putExtra("tabstr4","Judging Criteria").putExtra("tabstr5", "Specifications").putExtra("tabstr6","Contacts");
                            v.getContext().startActivity(intent);

                        }
                        else if(data.get(pos).text=="FPGA")
                        {
                            intent=new Intent(v.getContext(),TabsHeaderActivity2.class);
                            intent.putExtra("Title",data.get(pos).text);
                            intent.putExtra("id",pos);
                            intent.putExtra("tabs", 6);
                            intent.putExtra("tabstr1", "Home").putExtra("tabstr2","Problem Statement").putExtra("tabstr3", "Judging  Criteria").putExtra("tabstr4", "Rules").putExtra("tabstr5", "Contacts").putExtra("tabstr6","Abstract").putExtra("tabstr6","FAQs");
                            v.getContext().startActivity(intent);

                        }

                        else if(data.get(pos).text=="Electrade")
                        {
                            intent=new Intent(v.getContext(),TabsHeaderActivity2.class);
                            intent.putExtra("Title",data.get(pos).text);
                            intent.putExtra("id",pos);
                            intent.putExtra("tabs", 4);
                            intent.putExtra("tabstr1", "Home").putExtra("tabstr2", "Event STructure").putExtra("tabstr3","Rules").putExtra("tabstr4", "FAQs");
                            v.getContext().startActivity(intent);

                        }
                        else if(data.get(pos).text=="Battle City")
                        {
                            intent=new Intent(v.getContext(),TabsHeaderActivity2.class);
                            intent.putExtra("Title",data.get(pos).text);
                            intent.putExtra("id",pos);
                            intent.putExtra("tabs", 3);
                            intent.putExtra("tabstr1", "Home").putExtra("tabstr2","Rules").putExtra("tabstr3","Contacts");
                            v.getContext().startActivity(intent);

                        }
                        else if(data.get(pos).text=="Chaos")
                        {
                            intent=new Intent(v.getContext(),TabsHeaderActivity2.class);
                            intent.putExtra("Title",data.get(pos).text);
                            intent.putExtra("id",pos);
                            intent.putExtra("tabs", 4);
                            intent.putExtra("tabstr1", "Home").putExtra("tabstr2", "Rules").putExtra("tabstr3", "FAQs").putExtra("tabstr4","Contacts");
                            v.getContext().startActivity(intent);

                        }
                        else if(data.get(pos).text=="IOPC")
                        {
                            intent=new Intent(v.getContext(),TabsHeaderActivity2.class);
                            intent.putExtra("Title",data.get(pos).text);
                            intent.putExtra("id",pos);
                            intent.putExtra("tabs", 3);
                            intent.putExtra("tabstr1", "Home").putExtra("tabstr2","Rules").putExtra("tabstr3","Contacts");
                            v.getContext().startActivity(intent);

                        }
                        else if(data.get(pos).text=="Appathon")
                        {
                            intent=new Intent(v.getContext(),TabsHeaderActivity2.class);
                            intent.putExtra("Title",data.get(pos).text);
                            intent.putExtra("id",pos);
                            intent.putExtra("tabs", 5);
                            intent.putExtra("tabstr1","Home").putExtra("tabstr2", "Judging Criteria").putExtra("tabstr3", "Objective").putExtra("tabstr4", "Abstract").putExtra("tabstr5","Contacts");
                            v.getContext().startActivity(intent);

                        }
                        else if(data.get(pos).text=="Crypto")
                        {
                            intent=new Intent(v.getContext(),TabsHeaderActivity2.class);
                            intent.putExtra("Title",data.get(pos).text);
                            intent.putExtra("id",pos);
                            intent.putExtra("tabs", 4);
                            intent.putExtra("tabstr1", "Home").putExtra("tabstr2","Judging Criteria").putExtra("tabstr3", "Rules").putExtra("tabstr4", "Contacts");
                            v.getContext().startActivity(intent);

                        }
                        else if(data.get(pos).text=="IHPC")
                        {
                            intent=new Intent(v.getContext(),TabsHeaderActivity2.class);
                            intent.putExtra("Title",data.get(pos).text);
                            intent.putExtra("id",pos);
                            intent.putExtra("tabs", 4);
                            intent.putExtra("tabstr1", "Home").putExtra("tabstr2"," Rules").putExtra("tabstr3", "Tutorials").putExtra("tabstr4", "FAQs");
                            v.getContext().startActivity(intent);

                        }
                        else if(data.get(pos).text=="Hoverush")
                        {
                            intent=new Intent(v.getContext(),TabsHeaderActivity1.class);
                            intent.putExtra("Title",data.get(pos).text);
                            intent.putExtra("id",pos);
                            intent.putExtra("tabs", 10);
                            intent.putExtra("tabstr1", "Home").putExtra("tabstr2", "Event Structure").putExtra("tabstr3", "Arena").putExtra("tabstr4", "Model Specifications").putExtra("tabstr5", "Rules and Regulations").putExtra("tabstr6", "Abstract").putExtra("tabstr7", "Useful Links").putExtra("tabstr8", "FAQs").putExtra("tabstr9","Contacts");
                            v.getContext().startActivity(intent);

                        }
                        else if(data.get(pos).text=="Impulse")
                        {
                            intent=new Intent(v.getContext(),TabsHeaderActivity1.class);
                            intent.putExtra("Title",data.get(pos).text);
                            intent.putExtra("id",pos);
                            intent.putExtra("tabs", 10);
                            intent.putExtra("tabstr1", "Home").putExtra("tabstr2", "Event Structure").putExtra("tabstr3", "Arena").putExtra("tabstr4", "Model Specifications").putExtra("tabstr5", "Rules and Regulations").putExtra("tabstr6", "Judging Criteria").putExtra("tabstr7", "Abstract").putExtra("tabstr8", "Useful Links").putExtra("tabstr9", "FAQs").putExtra("tabstr10","Contacts");
                            v.getContext().startActivity(intent);

                        }

                        else if(data.get(pos).text=="Multirotor")
                        {
                            intent=new Intent(v.getContext(),TabsHeaderActivity1.class);
                            intent.putExtra("Title",data.get(pos).text);
                            intent.putExtra("id",pos);
                            intent.putExtra("tabs", 9);
                            intent.putExtra("tabstr1", "Home").putExtra("tabstr2", "Abstract").putExtra("tabstr3", "Arena").putExtra("tabstr4", "Model Specifications").putExtra("tabstr5", "Rules and Regulations").putExtra("tabstr6", "Judging Criteria").putExtra("tabstr7", "Useful Links").putExtra("tabstr8", "FAQs").putExtra("tabstr9", "Contacts");

                            v.getContext().startActivity(intent);

                        }

                        else if(data.get(pos).text=="Rule The Sky")
                        {
                            intent=new Intent(v.getContext(),TabsHeaderActivity1.class);
                            intent.putExtra("Title",data.get(pos).text);
                            intent.putExtra("id",pos);
                            intent.putExtra("tabs", 9);
                            intent.putExtra("tabstr1", "Home").putExtra("tabstr2", "Event Structure").putExtra("tabstr3", "Arena").putExtra("tabstr4", "Model Specifications").putExtra("tabstr5", "Rules and Regulations").putExtra("tabstr6", "Abstract").putExtra("tabstr7", "Useful Links").putExtra("tabstr8", "FAQs").putExtra("tabstr9","Contacts");
                            v.getContext().startActivity(intent);

                        }

                        else if(data.get(pos).text=="Sky Sparks")
                        {
                            intent=new Intent(v.getContext(),TabsHeaderActivity1.class);
                            intent.putExtra("Title",data.get(pos).text);
                            intent.putExtra("id",pos);
                            intent.putExtra("tabs", 9);
                            intent.putExtra("tabstr1", "Home").putExtra("tabstr2", "Event Structure").putExtra("tabstr3", "Arena").putExtra("tabstr4", "Model Specifications").putExtra("tabstr5", "Rules and Regulations").putExtra("tabstr6", "Abstract").putExtra("tabstr7", "Useful Links").putExtra("tabstr8", "FAQs").putExtra("tabstr9", "Contacts");
                            v.getContext().startActivity(intent);

                        }

                        else if(data.get(pos).text=="WhatsUp")
                        {
                            intent=new Intent(v.getContext(),TabsHeaderActivity2.class);
                            intent.putExtra("Title",data.get(pos).text);
                            intent.putExtra("id",pos);
                            intent.putExtra("tabs", 3);
                            intent.putExtra("tabstr1", "Home").putExtra("tabstr2", "Event Structure").putExtra("tabstr3", "Judging Criteria");
                            v.getContext().startActivity(intent);

                        }

                        else if(data.get(pos).text=="IORC")
                        {
                            intent=new Intent(v.getContext(),TabsHeaderActivity2.class);
                            intent.putExtra("Title",data.get(pos).text);
                            intent.putExtra("id",pos);
                            intent.putExtra("tabs", 7);
                            intent.putExtra("tabstr1", "Home").putExtra("tabstr2", "Event Structure").putExtra("tabstr3", "Points System").putExtra("tabstr4", "Awards and Recognition").putExtra("tabstr5", "Rules and Regulations").putExtra("tabstr6", "FAQs").putExtra("tabstr7","Contacts");
                            v.getContext().startActivity(intent);

                        }
                        else if(data.get(pos).text=="AstroQuiz")
                        {
                            intent=new Intent(v.getContext(),TabsHeaderActivity2.class);
                            intent.putExtra("Title",data.get(pos).text);
                            intent.putExtra("id", pos);
                            intent.putExtra("tabs", 4);
                            intent.putExtra("tabstr1", "Home").putExtra("tabstr2", "Event Structure").putExtra("tabstr3", "Rules and Regulations").putExtra("tabstr4","Judging Criteria");
                            v.getContext().startActivity(intent);

                        }

                        else if(data.get(pos).text=="Astro Treasure")
                        {
                            intent=new Intent(v.getContext(),TabsHeaderActivity2.class);
                            intent.putExtra("Title",data.get(pos).text);
                            intent.putExtra("id",pos);
                            intent.putExtra("tabs", 4);
                            intent.putExtra("tabstr1", "Home").putExtra("tabstr2", "Event Structure").putExtra("tabstr3", "Rules and Regulations").putExtra("tabstr4", "Judging Criteria");
                            v.getContext().startActivity(intent);

                        }
                        else if(data.get(pos).text=="Nutcracker")
                        {
                            intent=new Intent(v.getContext(),TabsHeaderActivity2.class);
                            intent.putExtra("Title",data.get(pos).text);
                            intent.putExtra("id",pos);
                            intent.putExtra("tabs", 3);
                            intent.putExtra("tabstr1", "Home").putExtra("tabstr2", "Rules").putExtra("tabstr3", "Judging Criteria");
                            v.getContext().startActivity(intent);

                        }
                        else if(data.get(pos).text=="IMP")
                        {
                            intent=new Intent(v.getContext(),TabsHeaderActivity2.class);
                            intent.putExtra("Title",data.get(pos).text);
                            intent.putExtra("id",pos);
                            intent.putExtra("tabs", 10);
                            intent.putExtra("tabstr1", "Home").putExtra("tabstr2", "Event Structure").putExtra("tabstr3", "Problem Statement").putExtra("tabstr4", "Incentive").putExtra("tabstr5", "Rules and Regulations").putExtra("tabstr6", "Eligibility").putExtra("tabstr7", "Contacts");
                            v.getContext().startActivity(intent);

                        }
                        else if(data.get(pos).text=="CrimeRun")
                        {
                            intent=new Intent(v.getContext(),TabsHeaderActivity2.class);
                            intent.putExtra("Title",data.get(pos).text);
                            intent.putExtra("id",pos);
                            intent.putExtra("tabs", 5);
                            intent.putExtra("tabstr1", "Home").putExtra("tabstr2", "Objective").putExtra("tabstr3", "Points System").putExtra("tabstr4", "Rules and Regulations").putExtra("tabstr5", "Contacts");
                            v.getContext().startActivity(intent);

                        }
                        else if(data.get(pos).text=="Battlefield")
                        {
                            intent=new Intent(v.getContext(),TabsHeaderActivity2.class);
                            intent.putExtra("Title",data.get(pos).text);
                            intent.putExtra("id",pos);
                            intent.putExtra("tabs", 5);
                            intent.putExtra("tabstr1", "Home").putExtra("tabstr2","Event Format").putExtra("tabstr3", "Rules").putExtra("tabstr4","Important Dates").putExtra("tabstr5", "Contact");
                            v.getContext().startActivity(intent);

                        }
                        else if(data.get(pos).text=="Marketing Villa")
                        {
                            intent=new Intent(v.getContext(),TabsHeaderActivity2.class);
                            intent.putExtra("Title",data.get(pos).text);
                            intent.putExtra("id",pos);
                            intent.putExtra("tabs", 6);
                            intent.putExtra("tabstr1","Home").putExtra("tabstr2", "Event Format").putExtra("tabstr3", "Rules").putExtra("tabstr4", "Judging Criteria").putExtra("tabstr5","Important Dates").putExtra("tabstr6", "Contact");
                            v.getContext().startActivity(intent);


                        }
                        else if(data.get(pos).text=="Be the Tycoon")
                        {
                            intent=new Intent(v.getContext(),TabsHeaderActivity2.class);
                            intent.putExtra("Title",data.get(pos).text);
                            intent.putExtra("id",pos);
                            intent.putExtra("tabs", 5);
                            intent.putExtra("tabstr1","Home").putExtra("tabstr2", "Event Format").putExtra("tabstr3", "Rules").putExtra("tabstr4", "Important Dates").putExtra("tabstr5", "Contact");
                            v.getContext().startActivity(intent);


                        }
                        else if(data.get(pos).text=="Be the Tycoon")
                        {
                            intent=new Intent(v.getContext(),TabsHeaderActivity2.class);
                            intent.putExtra("Title",data.get(pos).text);
                            intent.putExtra("id",pos);
                            intent.putExtra("tabs", 5);
                            intent.putExtra("tabstr1","Home").putExtra("tabstr2", "Event Format").putExtra("tabstr3", "Rules").putExtra("tabstr4","Important Dates").putExtra("tabstr5", "Contact");
                            v.getContext().startActivity(intent);


                        }
                        else if(data.get(pos).text=="Countdown")
                        {
                            intent=new Intent(v.getContext(),TabsHeaderActivity2.class);
                            intent.putExtra("Title",data.get(pos).text);
                            intent.putExtra("id",pos);
                            intent.putExtra("tabs", 5);
                            intent.putExtra("tabstr1","Home").putExtra("tabstr2", "Event Format").putExtra("tabstr3", "Rules").putExtra("tabstr4","Important Dates").putExtra("tabstr5", "Contact");
                            v.getContext().startActivity(intent);


                        }
                        else if(data.get(pos).text=="Stocksim")
                        {
                            intent=new Intent(v.getContext(),TabsHeaderActivity2.class);
                            intent.putExtra("Title",data.get(pos).text);
                            intent.putExtra("id",pos);
                            intent.putExtra("tabs", 4);
                            intent.putExtra("tabstr1","Home").putExtra("tabstr2", "Event Format").putExtra("tabstr3", "Contacts").putExtra("tabstr4", "Important Dates");
                            v.getContext().startActivity(intent);


                        }
                        else if(data.get(pos).text=="Best Manager")
                        {
                            intent=new Intent(v.getContext(),TabsHeaderActivity2.class);
                            intent.putExtra("Title",data.get(pos).text);
                            intent.putExtra("id",pos);
                            intent.putExtra("tabs", 5);
                            intent.putExtra("tabstr1","Home").putExtra("tabstr2", "Event Structure").putExtra("tabstr3", "Rules").putExtra("tabstr4","Timeline").putExtra("tabstr5", "Contact");
                            v.getContext().startActivity(intent);


                        }
                        else if(data.get(pos).text=="Buisness Venture")
                        {
                            intent=new Intent(v.getContext(),TabsHeaderActivity2.class);
                            intent.putExtra("Title",data.get(pos).text);
                            intent.putExtra("id",pos);
                            intent.putExtra("tabs", 7);
                            intent.putExtra("tabstr1","Home").putExtra("tabstr2", "Event Structure").putExtra("tabstr3", "Abstract").putExtra("tabstr4","Eligibility Criteria").putExtra("tabstr5", "Terms and conditions").putExtra("tabstr6","Judging Criteria").putExtra("tabstr7","Contacts");
                            v.getContext().startActivity(intent);


                        }
                        else if(data.get(pos).text=="Social Track")
                        {
                            intent=new Intent(v.getContext(),TabsHeaderActivity2.class);
                            intent.putExtra("Title",data.get(pos).text);
                            intent.putExtra("id",pos);
                            intent.putExtra("tabs", 8);
                            intent.putExtra("tabstr1", "Social Track").putExtra("tabstr2", "Home").putExtra("tabstr3", "Event Structure").putExtra("tabstr4", "Abstract").putExtra("tabstr5","Eligibility Criteria").putExtra("tabstr6", "Terms and conditions").putExtra("tabstr7", "Judging Criteria").putExtra("tabstr8", "Contacts");
                            v.getContext().startActivity(intent);

                        }
                        else if(data.get(pos).text=="Elevator Pitch")
                        {
                            intent=new Intent(v.getContext(),TabsHeaderActivity2.class);
                            intent.putExtra("Title",data.get(pos).text);
                            intent.putExtra("id",pos);
                            intent.putExtra("tabs", 4);
                            intent.putExtra("tabstr1", "Home").putExtra("tabstr2", "Objective").putExtra("tabstr3","Eligibility Criteria").putExtra("tabstr4", "Contacts");
                            v.getContext().startActivity(intent);


                        }
                        else if(data.get(pos).text=="Manmohan Gill Bio Business")
                        {
                            intent=new Intent(v.getContext(),TabsHeaderActivity2.class);
                            intent.putExtra("Title",data.get(pos).text);
                            intent.putExtra("id",pos);
                            intent.putExtra("tabs", 5);
                            intent.putExtra("tabstr1", "Home").putExtra("tabstr2", "Event Structure").putExtra("tabstr3", "Abstract").putExtra("tabstr4","Eligibility Criteria").putExtra("tabstr5","Contacts");

                            v.getContext().startActivity(intent);


                        }
                        else if(data.get(pos).text=="IOT")
                        {
                            intent=new Intent(v.getContext(),TabsHeaderActivity2.class);
                            intent.putExtra("Title",data.get(pos).text);
                            intent.putExtra("id",pos);
                            intent.putExtra("tabs", 8);
                            intent.putExtra("tabstr1", "IOT").putExtra("tabstr2", "Home").putExtra("tabstr3", "Event Structure").putExtra("tabstr4", "Abstract").putExtra("tabstr5","Eligibility Criteria").putExtra("tabstr6", "Terms and conditions").putExtra("tabstr7", "Judging Criteria").putExtra("tabstr8", "Contacts");
                            v.getContext().startActivity(intent);

                        }





















                    }
                });

                break;
        }
    }


    @Override
    public int getItemViewType(int position) {
        return data.get(position).type;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    private static class ListHeaderViewHolder extends RecyclerView.ViewHolder {
        public TextView header_title;
        public ImageView btn_expand_toggle;
        public CardView cardView;
        public Item refferalItem;

        public ListHeaderViewHolder(View itemView) {
            super(itemView);
            header_title = (TextView) itemView.findViewById(R.id.header_title);
            btn_expand_toggle = (ImageView) itemView.findViewById(R.id.btn_expand_toggle);
cardView=(CardView)itemView.findViewById(R.id.cardlist_item);
        }
    }

    private static class ListHeaderViewHolder1 extends RecyclerView.ViewHolder {
        public TextView header_title;
        public Item refferalItem;

        public ListHeaderViewHolder1(View itemView) {
            super(itemView);
            header_title = (TextView) itemView.findViewById(R.id.header_title);

        }
    }

    public static class Item {
        public int type;
        public String text;
        public List<Item> invisibleChildren;

        public Item() {
        }

        public Item(int type, String text) {
            this.type = type;
            this.text = text;
        }
    }
    public static class Item1 {
        public int type;
        public String text;

        public Item1() {
        }

        public Item1(int type, String text) {
            this.type = type;
            this.text = text;
        }
    }

}