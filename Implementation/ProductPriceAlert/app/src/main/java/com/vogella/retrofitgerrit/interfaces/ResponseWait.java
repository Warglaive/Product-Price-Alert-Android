package com.vogella.retrofitgerrit.interfaces;

import com.vogella.retrofitgerrit.ProductData;

import java.util.ArrayList;
import java.util.List;

public interface ResponseWait<T> {

    void responseWaitArray(List<T> response);
}
