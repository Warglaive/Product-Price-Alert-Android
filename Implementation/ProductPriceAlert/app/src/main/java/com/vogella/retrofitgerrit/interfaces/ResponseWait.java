package com.vogella.retrofitgerrit.interfaces;

import com.vogella.retrofitgerrit.ProductData;
import com.vogella.retrofitgerrit.UserData;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

public interface ResponseWait<T> {

    void responseWaitArray(List<T> response) throws MalformedURLException, IOException;
    void responseWaitSingle(ProductData productData);

    void responseWaitSingle(UserData userData);
}
