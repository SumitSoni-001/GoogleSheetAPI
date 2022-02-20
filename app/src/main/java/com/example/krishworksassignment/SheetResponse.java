package com.example.krishworksassignment;

import java.util.ArrayList;
import java.util.List;

public class SheetResponse {
    String range;
    String majorDimension;
    List<List<String>> values;  // Nested List is taken to get different fields from the sheet(like Id, Name, Marks).

    public String getRange() {
        return range;
    }

    public String getMajorDimension() {
        return majorDimension;
    }

    public List<List<String>> getValues() {
        return values;
    }
}
