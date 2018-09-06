package com.example.framework.appframework;

import android.support.annotation.NonNull;

public interface BaseView<T> {

    /**
     * 设置presnenter
     * @param presenter
     */
    void setPresenter(@NonNull T presenter);
    void showErrorMsg(@NonNull String type, @NonNull String msg);
    void showSuccessMsg(@NonNull String msg);
}
