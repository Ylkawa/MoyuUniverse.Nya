package com.nekoyu;

import com.nekoyu.LawsLoader.Law;

public class testlaw extends Law {
    @Override
    public void onPrepare() {
        logger.info("onPrepare");
    }

    @Override
    public void run() {
        logger.info("run");
    }

    @Override
    public void onStop() {
        logger.info("onStop");
    }
}
