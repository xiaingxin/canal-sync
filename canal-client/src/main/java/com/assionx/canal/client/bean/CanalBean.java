package com.assionx.canal.client.bean;

/**
 * @author xiaingxin
 * @date 2023/7/11-17:44
 **/
public interface CanalBean<T> {

    /**
     * 插入会调用该方法
     * @param t
     * @return 返回是否消费成功  true成功false失败
     */
     boolean insert(T t);


    /**
     *   更新时候调用
     * @param before
     * @param after
     * @return 返回是否消费成功  true成功false失败
     */
    boolean update(T before, T after);

    /**
     * 删除时会调用
     * @param t
     * @return 返回是否消费成功  true成功false失败
     */
    boolean delete(T t);

}
