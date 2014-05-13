/*
 * Copyright (C) 2014 Kalin Maldzhanski
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.djodjo.json.android.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.djodjo.json.JsonElement;
import org.djodjo.json.JsonObject;
import org.djodjo.json.android.R;
import org.djodjo.json.exception.JsonException;
import org.djodjo.widget.MultiSlider;


/**
 * this fragment renders range objects with properties of the form:
 "properties" : {
     "min": {
         "type" : "integer",
         "minimum" : 0,
         "maximum" : 10000
     },
     "max": {
         "type" : "integer",
         "minimum" : 0,
         "maximum" : 10000
     }
 }

 where min and max are not required.
 the slider has extra positions at the end and beginning and when selected no min and max values are returned, as assuming infinite/undefined range
 *
 */

public class RangeFragment extends BasePropertyFragment{

    public final static int LAYOUT_RANGE_SLIDER = R.layout.fragment_range_slider;
    public static final String ARG_MIN_BUNDLE = "min";
    public static final String ARG_MAX_BUNDLE = "max";

    protected int minVal1;
    protected int maxVal1;

    protected int minVal2;
    protected int maxVal2;

    MultiSlider seekBar;

    public RangeFragment() {
        // Required empty public constructor
    }

    @Override
    protected int getLayoutId() {
        int currLayoutId =  globalLayouts.get(ARG_GLOBAL_RANGE_LAYOUT);
        if(currLayoutId==0) {
            currLayoutId = LAYOUT_RANGE_SLIDER;
        }
        return currLayoutId;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            minVal1 = getArguments().getBundle(ARG_MIN_BUNDLE).getInt(NumberFragment.ARG_MINIMUM);
            maxVal1 = getArguments().getBundle(ARG_MIN_BUNDLE).getInt(NumberFragment.ARG_MAXIMUM);
            minVal2 = getArguments().getBundle(ARG_MAX_BUNDLE).getInt(NumberFragment.ARG_MINIMUM);
            maxVal2 = getArguments().getBundle(ARG_MAX_BUNDLE).getInt(NumberFragment.ARG_MAXIMUM);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = super.onCreateView(inflater, container, savedInstanceState);
        final TextView min = (TextView) v.findViewById(R.id.minValue);
        final TextView max = (TextView) v.findViewById(R.id.maxValue);

        if(min!=null) {
            min.setTextAppearance(getActivity(), styleValue);
        }
        if(max!=null) {
            max.setTextAppearance(getActivity(), styleValue);
        }



        seekBar = (MultiSlider)v.findViewById(R.id.range_slider);

        if(seekBar!=null) {
            seekBar.setMax(maxVal2, true, true);
            seekBar.setMin(minVal1, true, true);


            if(min!=null) {
                min.setText(String.valueOf(seekBar.getThumb(0).getValue()));
            }
            if(max!=null) {
                max.setText(String.valueOf(seekBar.getThumb(1).getValue()));
            }

            seekBar.setOnThumbValueChangeListener(new MultiSlider.OnThumbValueChangeListener() {
                @Override
                public void onValueChanged(MultiSlider multiSlider, MultiSlider.Thumb thumb, int thumbIndex, int value) {
                    if (thumbIndex == 0) {
                        if(min!=null) {
                            min.setText(String.valueOf(value));
                        }
                    } else {
                        if(max!=null) {
                            max.setText(String.valueOf(value));
                        }
                    }
                }
            });
        }
        return v;
    }

    @Override
    public JsonElement getJsonElement() {
        if(seekBar==null) return null;
        JsonObject res = new JsonObject();
        try {
            res.put("min", seekBar.getThumb(0).getValue());
            res.put("max", seekBar.getThumb(1).getValue());
        } catch (JsonException e) {
            e.printStackTrace();
        }
        return res;
    }
//TODO
//    @Override
//    public void onSaveInstanceState(Bundle outState) {
//        outState.putInt("minVal", seekBar.getThumb(0).getValue());
//        outState.putInt("maxVal", seekBar.getThumb(1).getValue());
//        super.onSaveInstanceState(outState);
//    }
//
//    @Override
//    public void onViewStateRestored(Bundle savedInstanceState) {
//        super.onViewStateRestored(savedInstanceState);
//        if(savedInstanceState!=null) {
//            seekBar.getThumb(0).setValue(savedInstanceState.getInt("minVal", minVal1));
//            seekBar.getThumb(1).setValue(savedInstanceState.getInt("maxVal", maxVal2));
//        }
//    }
}
