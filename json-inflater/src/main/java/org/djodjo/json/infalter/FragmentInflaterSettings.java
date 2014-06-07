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

package org.djodjo.json.infalter;

import android.os.Bundle;

import org.djodjo.json.android.fragment.BasePropertyFragment;
import org.djodjo.json.schema.Schema;
import org.djodjo.json.util.LinkedTreeMap;
import org.hamcrest.Matcher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FragmentInflaterSettings {

    //setings bundle args

    private static final String ARG_BUTTON_SELECTORS = "customButtonSelectors";
    private static final String ARG_CUSTOM_TITLE_STYLE = "customTitleTextAppearances";
    private static final String ARG_CUSTOM_LAYOUTS = "customLayouts";
    private static final String ARG_CUSTOM_DESC_STYLE = "customDescTextAppearances";
    private static final String ARG_CUSTOM_VALUE_TEXT_STYLE = "customValueTextAppearances";
    private static final String ARG_NO_TITLE = "noTitle";
    private static final String ARG_NO_DESC = "noDescription";
    private static final String ARG_TRANSLATE_ENUMS = "translateEnums";
    private static final String ARG_TRANSLATE_TITLE = "translateTitle";
    private static final String ARG_TRANSLATE_ENUMS_PREFIX = "translateEnumsPrefix";
    private static final String ARG_TRANSLATE_TITLE_PREFIX = "translateTitlePrefix";

    private static final String ARG_GLOBAL_LAYOUTS = "globalLayouts";

    private static final String ARG_GLOBAL_THEME_COLOR = "globalThemeColor";


    //used for the hashmap containing all possible global selectors
    public static final String ARG_GLOBAL_BOTTON_SELECTORS = "globalButtonSelectors";
    private static final String ARG_GLOBAL_TITLE_STYLE = "globalTitleTextAppearance";
    private static final String ARG_GLOBAL_DESC_STYLE = "globalDescTextAppearance";
    private static final String ARG_GLOBAL_VALUE_TEXT_STYLE = "globalValuesTextAppearance";
    private static final String ARG_GLOBAL_NO_DESC = "globalNoDescription";
    private static final String ARG_GLOBAL_NO_TITLE = "globalNoTitle";
    private static final String ARG_GLOBAL_TRANSLATE_ENUMS = "globalTranslateEnums";
    private static final String ARG_GLOBAL_TRANSLATE_TITLE = "globalTranslateTitle";
    private static final String ARG_GLOBAL_TRANSLATE_ENUMS_PREFIX = "globalTranslateEnumsPrefix";
    private static final String ARG_GLOBAL_TRANSLATE_TITLE_PREFIX = "globalTranslateTitlePrefix";

    /**
     * custom matchers to be used to match custom Fragment to a specific property schema type.
     * if any they all need to be passed to the fragment builders
     */
    static LinkedTreeMap<Matcher<Schema>, FragmentBuilder.FragmentPack> customPropertyMatchers = new LinkedTreeMap<Matcher<Schema>, FragmentBuilder.FragmentPack>();

    //map for custom layouts for specific properties for this object
    private HashMap<String, Integer> customLayouts =  new HashMap<String, Integer>();
    private HashMap<String, Integer> customButtonSelectors =  new HashMap<String, Integer>();
    private HashMap<String, Integer> customTitleTextAppearances = new HashMap<String, Integer>();
    private HashMap<String, Integer> customDescTextAppearances =  new HashMap<String, Integer>();
    private HashMap<String, Integer> customValueTextAppearances = new HashMap<String, Integer>();
    private HashMap<String, Boolean> noTitle =  new HashMap<String, Boolean>();
    private HashMap<String, Boolean> noDescription = new HashMap<String, Boolean>();
    private HashMap<String, Boolean> translateEnums = new HashMap<String, Boolean>();
    private HashMap<String, Boolean> translateTitle = new HashMap<String, Boolean>();
    private HashMap<String, String> translateEnumsPrefix = new HashMap<String, String>();
    private HashMap<String, String> translateTitlePrefix = new HashMap<String, String>();

    /**
     * a mask of the possible display types for all elements
     */
    HashMap<String, Integer> globalLayouts = new HashMap<String, Integer>();


    int globalThemeColor = -1;

    HashMap<String, Integer> globalButtonSelectors = new HashMap<String, Integer>();
    //style ref
    private int globalTitleTextAppearance = R.style.textTitle;
    //style ref
    private int globalDescTextAppearance = R.style.textDesc;
    //style ref
    private int globalValuesTextAppearance = R.style.textValue;
    private boolean globalNoDescription = false;
    private boolean globalNoTitle = false;
    private boolean globalTranslateEnums = false;
    private boolean globalTranslateTitle = false;
    private String globalTranslateEnumsPrefix = "";
    private String globalTranslateTitlePrefix = "";

    //the following controllers are used instead of a general selector
    //these are actual Json Object properties with Enum values,
    //where the name of the property is added to the list
    //the generation of the other Views will depend on the value of these
    //the they are picked in the order defined and control the following ones
    //i.e the first controller will get all possible values displayed,
    //the next one just the ones under the specific schema and sub-schemas, etc.

    ArrayList<String> oneOfControllers = new ArrayList<String>();
    ArrayList<String> ignoredProperties = new ArrayList<String>();




    //TODO Use singleton containing settings instances instead of static here
    /**
     * used to specify exact fragment for a property.
     */
    static HashMap<String, FragmentBuilder.FragmentPack> customFragments = new HashMap<String, FragmentBuilder.FragmentPack>();



    public FragmentInflaterSettings() {
        this.globalButtonSelectors =  new HashMap<String, Integer>();
        globalButtonSelectors.put(BasePropertyFragment.ARG_GLOBAL_CHECKBOX_SELECTOR,0);
        globalButtonSelectors.put(BasePropertyFragment.ARG_GLOBAL_RADIOBUTTON_SELECTOR,0);
        globalButtonSelectors.put(BasePropertyFragment.ARG_GLOBAL_SLIDER_THUMB_SELECTOR,0);
        globalButtonSelectors.put(BasePropertyFragment.ARG_GLOBAL_SLIDER_PROGRESS_DRAWABLE,0);
        globalButtonSelectors.put(BasePropertyFragment.ARG_GLOBAL_TOGGLEBUTTON_SELECTOR,0);
        globalButtonSelectors.put(BasePropertyFragment.ARG_GLOBAL_SWITCHBUTTON_SELECTOR,0);

        this.globalLayouts = new HashMap<String, Integer>();
//        setGlobalStringLayout(FragmentLayouts.STRING_TEXT);
//        setGlobalNumberLayout(FragmentLayouts.NUMBER_TEXT);
//        setGlobalLimitedNumberLayout(FragmentLayouts.NUMBER_SLIDER);
//        setGlobalBooleanLayout(FragmentLayouts.BOOLEAN_CHECKED_TEXTVIEW);
//        setGlobalArrayLayout(FragmentLayouts.ENUM_SPINNER);
//        setGlobalArrayEnumLayout(FragmentLayouts.ENUM_SPINNER); //multi select
//        setGlobalEnumLayout(FragmentLayouts.ENUM_SPINNER); //single select
//        setGlobalRangeLayout(FragmentLayouts.RANGE_SLIDER);

        setGlobalStringLayout(0);
        setGlobalNumberLayout(0);
        setGlobalLimitedNumberLayout(0);
        setGlobalBooleanLayout(0);
        setGlobalArrayLayout(0);
        setGlobalArrayEnumLayout(0); //multi select
        setGlobalEnumLayout(0); //single select
        setGlobalRangeLayout(0);
    }

    public FragmentInflaterSettings setSettingsBundle(Bundle args) {
        //populate hashmaps and values
        if(args == null) return this;

        customButtonSelectors =  (HashMap<String, Integer>)args.getSerializable(ARG_BUTTON_SELECTORS);
        customTitleTextAppearances = (HashMap<String, Integer>)args.getSerializable(ARG_CUSTOM_TITLE_STYLE);
        customDescTextAppearances =  (HashMap<String, Integer>)args.getSerializable(ARG_CUSTOM_DESC_STYLE);
        customValueTextAppearances = (HashMap<String, Integer>)args.getSerializable(ARG_CUSTOM_VALUE_TEXT_STYLE);
        noTitle =   (HashMap<String, Boolean>)args.getSerializable(ARG_NO_TITLE);
        noDescription = ( HashMap<String, Boolean>)args.getSerializable(ARG_NO_DESC);
        customLayouts = (HashMap<String, Integer>) args.getSerializable(ARG_CUSTOM_LAYOUTS);
        translateEnums =   (HashMap<String, Boolean>)args.getSerializable(ARG_TRANSLATE_ENUMS);
        translateTitle =   (HashMap<String, Boolean>)args.getSerializable(ARG_TRANSLATE_TITLE);
        translateEnumsPrefix =   (HashMap<String, String>)args.getSerializable(ARG_TRANSLATE_ENUMS_PREFIX);
        translateTitlePrefix =   (HashMap<String, String>)args.getSerializable(ARG_TRANSLATE_TITLE_PREFIX);

        globalLayouts = (HashMap<String, Integer>) args.getSerializable(ARG_GLOBAL_LAYOUTS);
        globalThemeColor = args.getInt(ARG_GLOBAL_THEME_COLOR, -1);
        globalButtonSelectors = (HashMap<String, Integer>) args.getSerializable(ARG_GLOBAL_BOTTON_SELECTORS);
        globalTitleTextAppearance = args.getInt(ARG_GLOBAL_TITLE_STYLE, R.style.textTitle);
        globalDescTextAppearance = args.getInt(ARG_GLOBAL_DESC_STYLE, R.style.textDesc);
        globalValuesTextAppearance = args.getInt(ARG_GLOBAL_VALUE_TEXT_STYLE, R.style.textValue);
        globalNoDescription = args.getBoolean(ARG_GLOBAL_NO_DESC, false);
        globalNoTitle = args.getBoolean(ARG_GLOBAL_NO_TITLE, false);
        globalTranslateEnums = args.getBoolean(ARG_GLOBAL_TRANSLATE_ENUMS, false);
        globalTranslateTitle = args.getBoolean(ARG_GLOBAL_TRANSLATE_TITLE, false);
        globalTranslateEnumsPrefix = args.getString(ARG_GLOBAL_TRANSLATE_ENUMS_PREFIX, "");
        globalTranslateTitlePrefix = args.getString(ARG_GLOBAL_TRANSLATE_TITLE_PREFIX, "");

        return this;
    }

    public Bundle bundleSettings() {
        Bundle bundle = new Bundle();
        bundle.putSerializable(ARG_BUTTON_SELECTORS, customButtonSelectors);
        bundle.putSerializable(ARG_CUSTOM_TITLE_STYLE, customTitleTextAppearances);
        bundle.putSerializable(ARG_CUSTOM_DESC_STYLE, customDescTextAppearances);
        bundle.putSerializable(ARG_CUSTOM_VALUE_TEXT_STYLE, customValueTextAppearances);
        bundle.putSerializable(ARG_NO_TITLE, noTitle);
        bundle.putSerializable(ARG_NO_DESC, noDescription);
        bundle.putSerializable(ARG_TRANSLATE_ENUMS, translateEnums);
        bundle.putSerializable(ARG_TRANSLATE_TITLE, translateTitle);
        bundle.putSerializable(ARG_TRANSLATE_ENUMS_PREFIX, translateEnumsPrefix);
        bundle.putSerializable(ARG_TRANSLATE_TITLE_PREFIX, translateTitlePrefix);

        bundle.putSerializable(ARG_CUSTOM_LAYOUTS, customLayouts);
        bundle.putSerializable(ARG_GLOBAL_LAYOUTS, globalLayouts);
        bundle.putInt(ARG_GLOBAL_THEME_COLOR, globalThemeColor);
        bundle.putSerializable(ARG_GLOBAL_BOTTON_SELECTORS, globalButtonSelectors);
        bundle.putInt(ARG_GLOBAL_TITLE_STYLE, globalTitleTextAppearance);
        bundle.putInt(ARG_GLOBAL_DESC_STYLE, globalDescTextAppearance);
        bundle.putInt(ARG_GLOBAL_VALUE_TEXT_STYLE, globalValuesTextAppearance);
        bundle.putBoolean(ARG_GLOBAL_NO_DESC, globalNoDescription);
        bundle.putBoolean(ARG_GLOBAL_TRANSLATE_ENUMS, globalTranslateEnums);
        bundle.putBoolean(ARG_GLOBAL_TRANSLATE_TITLE, globalTranslateTitle);
        bundle.putString(ARG_GLOBAL_TRANSLATE_ENUMS_PREFIX, globalTranslateEnumsPrefix);
        bundle.putString(ARG_GLOBAL_TRANSLATE_TITLE_PREFIX, globalTranslateTitlePrefix);

        return bundle;
    }


    /**
     * Add cusotm selector for a specific property.
     * @param propertyName the property to have this selector
     * @param customButtonSelector the style id of the selector
     * @return
     */
    public FragmentInflaterSettings addCustomButtonSelector(String propertyName, Integer customButtonSelector) {
        this.customButtonSelectors.put(propertyName, customButtonSelector);
        return this;
    }

    public FragmentInflaterSettings addCustomButtonSelectors(HashMap<String, Integer> customButtonColors) {
        this.customButtonSelectors.putAll(customButtonColors);
        return this;
    }

    public FragmentInflaterSettings addCustomTitleTextAppearance(String propertyName, Integer customTitleTextAppearance) {
        this.customTitleTextAppearances.put(propertyName, customTitleTextAppearance);
        return this;
    }

    public FragmentInflaterSettings addCustomTitleTextAppearances(HashMap<String, Integer> customTitleTextAppearances) {
        this.customTitleTextAppearances.putAll(customTitleTextAppearances);
        return this;
    }

    public FragmentInflaterSettings addCustomDescTextAppearance(String propertyName, Integer customDescTextAppearance) {
        this.customDescTextAppearances.put(propertyName, customDescTextAppearance);
        return this;
    }

    public FragmentInflaterSettings addCustomDescTextAppearances(HashMap<String, Integer> customDescTextAppearances) {
        this.customDescTextAppearances.putAll(customDescTextAppearances);
        return this;
    }

    public FragmentInflaterSettings addCustomValueTextAppearance(String propertyName, Integer customValueTextAppearance) {
        this.customValueTextAppearances.put(propertyName, customValueTextAppearance);
        return this;
    }

    public FragmentInflaterSettings addCustomValueTextAppearances(HashMap<String, Integer> customValueTextAppearances) {
        this.customValueTextAppearances.putAll(customValueTextAppearances);
        return this;
    }

    public FragmentInflaterSettings addNoTitle(String propertyName) {
        this.noTitle.put(propertyName, true);
        return this;
    }

    public FragmentInflaterSettings addNoTitles(HashMap<String, Boolean> noTitles) {
        this.noTitle.putAll(noTitles);
        return this;
    }

    public FragmentInflaterSettings addTranslateEnums(String label, boolean translate) {
        this.translateEnums.put(label, translate);
        return this;
    }

    public FragmentInflaterSettings addTranslateEnums(HashMap<String, Boolean> translateEnums) {
        this.translateEnums.putAll(translateEnums);
        return this;
    }

    public FragmentInflaterSettings addTranslateTitle(String label, boolean translate) {
        this.translateTitle.put(label, translate);
        return this;
    }

    public FragmentInflaterSettings addTranslateTitle(HashMap<String, Boolean> translateTitle) {
        this.translateTitle.putAll(translateTitle);
        return this;
    }

    public FragmentInflaterSettings addTranslateEnumsPrefix(String label, String prefix) {
        this.translateEnumsPrefix.put(label, prefix);
        return this;
    }

    public FragmentInflaterSettings addTranslateEnumsPrefix(HashMap<String, String> translateEnums) {
        this.translateEnumsPrefix.putAll(translateEnums);
        return this;
    }

    public FragmentInflaterSettings addTranslateTitlePrefix(String label, String prefix) {
        this.translateTitlePrefix.put(label, prefix);
        return this;
    }

    public FragmentInflaterSettings addTranslateTitlePrefix(HashMap<String, String> translateTitlePrefix) {
        this.translateTitlePrefix.putAll(translateTitlePrefix);
        return this;
    }

    public FragmentInflaterSettings addNoDescription(String propertyName) {
        this.noDescription.put(propertyName, true);
        return this;
    }

    public FragmentInflaterSettings addNoDescriptions(HashMap<String, Boolean> noDescriptions) {
        this.noDescription.putAll(noDescriptions);
        return this;
    }

    public FragmentInflaterSettings setGlobalStringLayout(int globalDisplayType) {
        this.globalLayouts.put(BasePropertyFragment.ARG_GLOBAL_STRING_LAYOUT, globalDisplayType);
        return this;
    }

    public FragmentInflaterSettings setGlobalNumberLayout(int globalDisplayType) {
        this.globalLayouts.put(BasePropertyFragment.ARG_GLOBAL_NUMBER_LAYOUT, globalDisplayType);
        return this;
    }

    /**
     * Set global display type of a limited number property. Limited means the property has "minimum" and "maximum" values defined.
     * @param globalDisplayType
     * @return
     */
    public FragmentInflaterSettings setGlobalLimitedNumberLayout(int globalDisplayType) {
        this.globalLayouts.put(BasePropertyFragment.ARG_GLOBAL_LIMITED_NUMBER_LAYOUT, globalDisplayType);
        return this;
    }

    public FragmentInflaterSettings setGlobalBooleanLayout(int globalDisplayType) {
        this.globalLayouts.put(BasePropertyFragment.ARG_GLOBAL_BOOLEAN_LAYOUT, globalDisplayType);
        return this;
    }

    public FragmentInflaterSettings setGlobalArrayLayout(int globalDisplayType) {
        this.globalLayouts.put(BasePropertyFragment.ARG_GLOBAL_ARRAY_LAYOUT, globalDisplayType);
        return this;
    }

    public FragmentInflaterSettings setGlobalArrayEnumLayout(int globalDisplayType) {
        this.globalLayouts.put(BasePropertyFragment.ARG_GLOBAL_ARRAY_ENUM_LAYOUT, globalDisplayType);
        return this;
    }

    public FragmentInflaterSettings setGlobalEnumLayout(int globalDisplayType) {
        this.globalLayouts.put(BasePropertyFragment.ARG_GLOBAL_ENUM_LAYOUT, globalDisplayType);
        return this;
    }

    public FragmentInflaterSettings setGlobalRangeLayout(int globalDisplayType) {
        this.globalLayouts.put(BasePropertyFragment.ARG_GLOBAL_RANGE_LAYOUT, globalDisplayType);
        return this;
    }

    public FragmentInflaterSettings setGlobalThemeColor(int globalThemeColor) {
        this.globalThemeColor = globalThemeColor;
        return this;
    }

    public FragmentInflaterSettings setGlobalCheckBoxSelector(int globalCheckBoxSelector) {
        this.globalButtonSelectors.put(BasePropertyFragment.ARG_GLOBAL_CHECKBOX_SELECTOR, globalCheckBoxSelector);
        return this;
    }

    public FragmentInflaterSettings setGlobalRadioButtonSelector(int globalRadioButtonSelector) {
        this.globalButtonSelectors.put(BasePropertyFragment.ARG_GLOBAL_RADIOBUTTON_SELECTOR, globalRadioButtonSelector);
        return this;
    }

    public FragmentInflaterSettings setGlobalSliderThumbSelector(int globalSliderThumbSelector) {
        this.globalButtonSelectors.put(BasePropertyFragment.ARG_GLOBAL_SLIDER_THUMB_SELECTOR, globalSliderThumbSelector);
        return this;
    }

    public FragmentInflaterSettings setGlobalSliderProgressDrawable(int globalSliderProgressDrawable) {
        this.globalButtonSelectors.put(BasePropertyFragment.ARG_GLOBAL_SLIDER_PROGRESS_DRAWABLE, globalSliderProgressDrawable);
        return this;
    }

    public FragmentInflaterSettings setGlobalToggleButtonSelector(int globalToggleButtonSelector) {
        this.globalButtonSelectors.put(BasePropertyFragment.ARG_GLOBAL_TOGGLEBUTTON_SELECTOR, globalToggleButtonSelector);
        return this;
    }

    public FragmentInflaterSettings setGlobalSwitchButtonSelector(int globalSwitchButtonSelector) {
        this.globalButtonSelectors.put(BasePropertyFragment.ARG_GLOBAL_SWITCHBUTTON_SELECTOR, globalSwitchButtonSelector);
        return this;
    }

    public FragmentInflaterSettings setGlobalTitleTextAppearance(int globalTitleTextAppearance) {
        this.globalTitleTextAppearance = globalTitleTextAppearance;
        return this;
    }

    public FragmentInflaterSettings setGlobalDescTextAppearance(int globalDescTextAppearance) {
        this.globalDescTextAppearance = globalDescTextAppearance;
        return this;
    }

    public FragmentInflaterSettings setGlobalValuesTextAppearance(int globalValuesTextAppearance) {
        this.globalValuesTextAppearance = globalValuesTextAppearance;
        return this;
    }

    public FragmentInflaterSettings setGlobalNoDescription(boolean globalNoDescription) {
        this.globalNoDescription = globalNoDescription;
        return this;
    }

    public FragmentInflaterSettings setGlobalNoTitle(boolean globalNoTitle) {
        this.globalNoTitle = globalNoTitle;
        return this;
    }
    public FragmentInflaterSettings setGlobalTranslateEnums (boolean globalTranslateEnums) {
        this.globalTranslateEnums = globalTranslateEnums;
        return this;
    }

    public FragmentInflaterSettings setGlobalTranslateTitle (boolean globalTranslateTitle) {
        this.globalTranslateTitle = globalTranslateTitle;
        return this;
    }

    public FragmentInflaterSettings setGlobalTranslateEnumsPrefix (String prefix) {
        this.globalTranslateEnumsPrefix = prefix;
        return this;
    }

    public FragmentInflaterSettings setGlobalTranslateTitlePrefix (String prefix) {
        this.globalTranslateTitlePrefix = prefix;
        return this;
    }

    public FragmentInflaterSettings addCustomPropertyMatcher (Matcher<Schema> propertyMatcher,  FragmentBuilder.FragmentPack fragmentClass) {
        customPropertyMatchers.put(propertyMatcher, fragmentClass);
        return this;
    }

    public FragmentInflaterSettings addCustomPropertyMatchers (Map<Matcher<Schema>, FragmentBuilder.FragmentPack> propertyMatchers) {
        customPropertyMatchers.putAll(propertyMatchers);
        return this;
    }

    public FragmentInflaterSettings addOneOfController(String propertyName) {
        oneOfControllers.add(propertyName);
        return this;
    }

    public FragmentInflaterSettings addOneOfControllers(ArrayList<String> propertyNames) {
        oneOfControllers.addAll(propertyNames);
        return this;
    }

    public FragmentInflaterSettings ignoreProperty(String propertyName) {
        ignoredProperties.add(propertyName);
        return this;
    }

    public FragmentInflaterSettings ignoreProperties(ArrayList<String> propertyNames) {
        ignoredProperties.addAll(propertyNames);
        return this;
    }

    public FragmentInflaterSettings addCustomLayout (String propertyName, int layoutId) {
        customLayouts.put(propertyName, layoutId);
        return this;
    }

    public FragmentInflaterSettings addCustomLayouts (Map<String, Integer> propertyLayouts) {
        customLayouts.putAll(propertyLayouts);
        return this;
    }

    public FragmentInflaterSettings addCustomFragment(String propertyName, FragmentBuilder.FragmentPack fragmentClass) {
        customFragments.put(propertyName, fragmentClass);
        return this;
    }

    public FragmentInflaterSettings addCustomFragments(Map<String, FragmentBuilder.FragmentPack> customFragments) {
        this.customFragments.putAll(customFragments);
        return this;
    }

    int chooseButtonSelectors(String property) {
        int res = 0;
        if(customButtonSelectors.containsKey(property)) {
            res = customButtonSelectors.get(property);
        }
        return res;
    }


    int chooseTitleTextAppearance(String property) {
        int res = 0;

        if(customTitleTextAppearances.containsKey(property)) {
            res = customTitleTextAppearances.get(property);
        } else {
            res = globalTitleTextAppearance;
        }

        return res;
    }

    int chooseDescTextAppearance(String property) {
        int res = 0;

        if(customDescTextAppearances.containsKey(property)) {
            res = customDescTextAppearances.get(property);
        } else {
            res = globalDescTextAppearance;
        }

        return res;
    }

    int chooseValueTextAppearance(String property) {
        int res = 0;

        if(customValueTextAppearances.containsKey(property)) {
            res = customValueTextAppearances.get(property);
        } else {
            res = globalValuesTextAppearance;
        }

        return res;
    }

    String getEnumTranslationPrefix(String property) {
        String res = "";
        if(translateEnumsPrefix.containsKey(property)) {
            res = translateEnumsPrefix.get(property);
        } else {
            res = globalTranslateEnumsPrefix;
        }
        return res;
    }

    boolean hasEnumTranslation(String property) {
        boolean res = false;
        if(translateEnums.containsKey(property)) {
            res = translateEnums.get(property);
        } else {
            res = globalTranslateEnums;
        }
        return res;
    }

    String getTitleTranslationPrefix(String property) {
        String res = "";
        if(translateTitlePrefix.containsKey(property)) {
            res = translateTitlePrefix.get(property);
        } else {
            res = globalTranslateTitlePrefix;
        }
        return res;
    }

    boolean hasTitleTranslation(String property) {
        boolean res = false;
        if(translateTitle.containsKey(property)) {
            res = translateTitle.get(property);
        } else {
            res = globalTranslateTitle;
        }
        return res;
    }

    boolean isNoTile(String property) {
        boolean res = false;
        if(noTitle.containsKey(property)) {
            res = noTitle.get(property);
        } else {
            res = globalNoTitle;
        }
        return res;
    }

    boolean isNoDescription(String property) {
        boolean res = false;
        if(noDescription.containsKey(property)) {
            res = noDescription.get(property);
        } else {
            res = globalNoDescription;
        }
        return res;
    }

    int getCustomLayoutId(String propertyName) {
        int res = 0;
        if(customLayouts.containsKey(propertyName)) {
            res = customLayouts.get(propertyName);
        }
        return res;
    }

}