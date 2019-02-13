package com.example.baseapp.base;

/**
 * @author: Five_伍
 * @create: 2019/2/12
 * @Describe:
 */
public interface Presenter<M extends BaseModel, V extends BaseView> {
    /**
     * 注册Model层
     *
     * @param model
     */
    void registerModel(M model);

    /**
     * 注册View层
     *
     * @param view
     */
    void registerView(V view);

    /**
     * 获取View
     *
     * @return
     */
    V getView();

    /**
     * 销毁动作（如Activity、Fragment的卸载）
     */
    void destroy();
}
