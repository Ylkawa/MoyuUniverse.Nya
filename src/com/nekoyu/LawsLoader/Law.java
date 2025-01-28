package com.nekoyu.LawsLoader;

public abstract class Law {
    public Law() {}

    public abstract void onPrepare();

    public abstract void run();

    public abstract void onStop();
}
