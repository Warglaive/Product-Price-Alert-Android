package com.vogella.retrofitgerrit;

import com.vogella.retrofitgerrit.interfaces.ResponseWait;

import java.util.ArrayList;
import java.util.List;

public class ResponseWaitImpl implements ResponseWait {
    private List list;

    public ResponseWaitImpl(){
        this.list = new ArrayList();
    }

    @Override
    public void responseWaitArray(List response) {
        this.list = response;
    }

    public List getList(){
        return this.list;
    }
}
