package com.nekoyu;

import com.nekoyu.LawsLoader.Law;

public class testlaw extends Law {
    @Override
    public void onPrepare() {
        System.out.println("onPrepare");
    }

    @Override
    public void run() {
        System.out.println("run");
    }

    @Override
    public void onStop() {
        System.out.println("onStop");
    }
}
