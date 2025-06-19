package com.nekoyu;

import com.nekoyu.LawsLoader.Law;

public class testlaw extends Law {
    @Override
    public void prepare() {
        logger.info("onPrepare");
    }

    @Override
    public void run() {
        logger.info("run");
    }

    @Override
    public void stop() {
        logger.info("onStop");
    }
}
