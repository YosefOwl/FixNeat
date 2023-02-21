package com.example.fixneat.views.ui.orders;

import com.example.fixneat.Model.Window;

public class OrderHelper {

    static String[] doorTypes = {"Sliding", "Axis", "Belgian Sliding", "Belgian Axis"};

    static String[] windowTypes = {"Sliding", "Keep", "Dry-Keep", "Casment", "Pocket"};

    static String[] profileTypes = {"100x100", "80x80", "40x80", "40x120", "40x100", "10x10", "15x15", "20x20", "5x15"};

    static String[] pergolaMaterialTypes = {"Aluminium", "Wood"};

    static String[] balconyMaterialTypes = {"Aluminium", "Sheet Metal"};

    static String[] glassTypes = {"Regular", "Milk", "Double", "Triplex", "Chinchella"};

    static String[] colorsTypes = {"BP Green Ivy",  "Green Light", "Gray Shell", "Gray Medium", "Bronze", "Bronze Brown",
                                    "Black Matte", "Black Gloss", "Silver Metallic", "Bone White", "Mill Finish", "Bright Gold",
                                    "Bright Brushed Gold", "Gold Satin", "Bright Clear", "Bright Brushed Clear", "Midnight Bronze PVDF",
                                    "Black PVDF",  "Dark Bronze Anodized","LA ExtraDark Anodized", "Black Anodized", "Clear Satin"};

    static String[] jobsTypes = {"Pergola", "Balcony", "Window", "Door"};


    public static String[] getDoorTypes() {
        return doorTypes;
    }

    public static String[] getWindowTypes() {
        return windowTypes;
    }

    public static String[] getProfileTypes() {
        return profileTypes;
    }

    public static String[] getPergolaMaterialTypes() {
        return pergolaMaterialTypes;
    }

    public static String[] getBalconyMaterialTypes() {
        return balconyMaterialTypes;
    }

    public static String[] getGlassTypes() {
        return glassTypes;
    }

    public static String[] getColorsTypes() {
        return colorsTypes;
    }

    public static String[] getJobsTypes() {
        return jobsTypes;
    }
}
