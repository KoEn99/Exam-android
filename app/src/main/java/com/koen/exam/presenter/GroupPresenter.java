package com.koen.exam.presenter;

import com.koen.exam.views.GlobalView;

public interface GroupPresenter extends GlobalPresenter {
    void findByName(String name);
}
