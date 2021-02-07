package com.koen.exam.presenter;

import com.koen.exam.model.GenericResponse;

public interface GlobalPresenter {
    void detachView();
    void destroy();
    void getMessageAnswer(String messageAnswer);
    void listenerFinishError(String message);
    void listenerFinish(GenericResponse<?> finishData);
}
